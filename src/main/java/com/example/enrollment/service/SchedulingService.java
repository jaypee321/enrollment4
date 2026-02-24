package com.example.enrollment.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.enrollment.entity.ClassSchedule;
import com.example.enrollment.repository.ClassScheduleRepository;
import jakarta.transaction.Transactional;

@Service
public class SchedulingService {

    @Autowired
    private ClassScheduleRepository scheduleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Checks if the section has reached its maximum student capacity.
     */
    public boolean isSectionFull(Integer sectionId) {
        if (sectionId == null) return false;

        String sql = "SELECT cs.max_capacity, " +
                     "(SELECT COUNT(*) FROM student_enlistments se WHERE se.section_id = cs.section_id) as current_count " +
                     "FROM class_sections cs WHERE cs.section_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, sectionId);
        
        if (rows.isEmpty()) {
            return false; 
        }

        Map<String, Object> data = rows.get(0);
        
        int max = (data.get("max_capacity") != null) ? ((Number) data.get("max_capacity")).intValue() : 0;
        long current = (data.get("current_count") != null) ? ((Number) data.get("current_count")).longValue() : 0;
        
        return current >= max;
    }

    /**
     * Adds a student to the waitlist table.
     */
    @Transactional
    public void addToWaitlist(Long studentId, Integer sectionId) {
        Integer courseId = jdbcTemplate.queryForObject(
            "SELECT course_id FROM class_sections WHERE section_id = ?", Integer.class, sectionId);

        jdbcTemplate.update(
            "INSERT INTO student_waitlist (student_id, course_id, status, priority_date) VALUES (?, ?, 'WAITING', NOW())", 
            studentId, courseId);
    }

    /**
     * Detects schedule overlaps between a new section and a student's current load.
     * 100% SQL Based for perfect accuracy.
     */
    public void validateStudentScheduleConflict(Long studentId, Integer sectionIdToCheck) {
        // 1. Get the schedules of the NEW section directly from the database
        List<Map<String, Object>> newSchedules = jdbcTemplate.queryForList(
            "SELECT day_of_week, start_time, end_time FROM class_schedules WHERE section_id = ?", sectionIdToCheck);

        if (newSchedules.isEmpty()) return; // No schedule = no conflict

        // 2. Get the student's CURRENTLY enrolled schedules
        List<Map<String, Object>> currentSchedules = jdbcTemplate.queryForList(
            "SELECT sch.start_time, sch.end_time, sch.day_of_week, c.course_title " +
            "FROM student_enlistments se " +
            "JOIN courses c ON se.course_id = c.course_id " +
            "JOIN class_sections cs ON se.section_id = cs.section_id " +
            "JOIN class_schedules sch ON cs.section_id = sch.section_id " +
            "WHERE se.student_id = ?", studentId);

        // 3. Compare times
        for (Map<String, Object> newSched : newSchedules) {
            Object newDayObj = newSched.get("day_of_week");
            if (newDayObj == null || newSched.get("start_time") == null) continue;
            Integer newDay = ((Number) newDayObj).intValue();
            
            LocalTime newStart = ((java.sql.Time) newSched.get("start_time")).toLocalTime();
            LocalTime newEnd = ((java.sql.Time) newSched.get("end_time")).toLocalTime();

            for (Map<String, Object> exSched : currentSchedules) {
                Object exDayObj = exSched.get("day_of_week");
                if (exDayObj == null) continue;
                Integer exDay = ((Number) exDayObj).intValue();

                if (newDay.equals(exDay)) {
                    LocalTime exStart = ((java.sql.Time) exSched.get("start_time")).toLocalTime();
                    LocalTime exEnd = ((java.sql.Time) exSched.get("end_time")).toLocalTime();

                    // Collision check
                    if (newStart.isBefore(exEnd) && newEnd.isAfter(exStart)) {
                        String dayName = getDayName(newDay);
                        throw new IllegalStateException("Schedule Conflict: Cannot add this subject because it overlaps with " + 
                            exSched.get("course_title") + " on " + dayName + ".");
                    }
                }
            }
        }
    }

    private String getDayName(int dayOfWeek) {
        return switch (dayOfWeek) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Unknown Day";
        };
    }

    /**
     * Automatically promotes the next student from the waitlist.
     */
    @Transactional
    public void promoteFromWaitlist(Integer courseId) {
        if (courseId == null) return;

        List<Integer> sectionIds = jdbcTemplate.queryForList(
            "SELECT section_id FROM class_sections WHERE course_id = ? LIMIT 1", Integer.class, courseId);
        
        if (sectionIds.isEmpty()) return;
        Integer sectionId = sectionIds.get(0);

        if (!isSectionFull(sectionId)) {
            List<Map<String, Object>> waitlist = jdbcTemplate.queryForList(
                "SELECT waitlist_id, student_id FROM student_waitlist " +
                "WHERE course_id = ? AND status = 'WAITING' " +
                "ORDER BY priority_date ASC LIMIT 1", courseId);

            if (!waitlist.isEmpty()) {
                Map<String, Object> nextInLine = waitlist.get(0);
                Long nextStudentId = ((Number) nextInLine.get("student_id")).longValue();
                Long waitlistId = ((Number) nextInLine.get("waitlist_id")).longValue();

                jdbcTemplate.update("INSERT INTO student_enlistments (student_id, course_id, section_id) VALUES (?, ?, ?)", 
                    nextStudentId, courseId, sectionId);
                
                jdbcTemplate.update("UPDATE student_waitlist SET status = 'PROMOTED' WHERE waitlist_id = ?", waitlistId);
                
                System.out.println("Waitlist Promotion: Student " + nextStudentId + " promoted to Course " + courseId);
            }
        }
    }
}