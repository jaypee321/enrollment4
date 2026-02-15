package com.example.enrollment.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_schedules")
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private ClassSection section;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private Integer dayOfWeek; // 1-7 (Mon-Sun)
    private LocalTime startTime;
    private LocalTime endTime;

    @Column(length = 20)
    private String scheduleType = "Lecture";

	public LocalTime getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalTime getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSection(ClassSection section2) {
		// TODO Auto-generated method stub
		
	}

	public void setRoom(Room room2) {
		// TODO Auto-generated method stub
		
	}

	public void setStartTime(LocalTime start) {
		// TODO Auto-generated method stub
		
	}

	public void setScheduleType(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setEndTime(LocalTime end) {
		// TODO Auto-generated method stub
		
	}

	public void setDayOfWeek(Integer dayOfWeek2) {
		// TODO Auto-generated method stub
		
	}

	public void setFaculty(Faculty faculty2) {
		// TODO Auto-generated method stub
		
	}

	public Object getDayOfWeek() {
		// TODO Auto-generated method stub
		return null;
	}
}
