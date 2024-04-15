package com.blackout.university.dto.enrollment;

import com.blackout.university.models.Course;

public class CourseDetailmentDTO {

    private Long courseId;
    private String title;
    private String description;

    public CourseDetailmentDTO(Course course){
        this.courseId = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
    }

    public CourseDetailmentDTO() {
    }

    public CourseDetailmentDTO(Long courseId, String title, String description) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
