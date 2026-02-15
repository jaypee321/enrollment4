package com.example.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollment.entity.ClassSchedule;

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Integer> {
    // Find schedules for a specific room to check for conflicts
    List<ClassSchedule> findByRoom_RoomIdAndDayOfWeek(Integer roomId, Integer dayOfWeek);
    
    // Find schedules for a specific faculty member
    List<ClassSchedule> findByFaculty_FacultyIdAndDayOfWeek(Integer facultyId, Integer dayOfWeek);

	List<ClassSchedule> findBySection_SectionId(Integer sectionIdToCheck);
}
