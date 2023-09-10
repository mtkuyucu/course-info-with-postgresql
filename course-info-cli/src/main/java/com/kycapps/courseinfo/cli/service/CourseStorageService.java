package com.kycapps.courseinfo.cli.service;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 15:04
*/


import com.kycapps.courseinfo.cli.PluralsightCourse;
import com.kycapps.courseinfo.domain.Course;
import com.kycapps.courseinfo.repository.CourseRepository;
import java.util.List;
import java.util.Optional;

public class CourseStorageService {

    private static final String PS_BASE_URL = "https://app.pluralsight.com";

    private final CourseRepository courseRepository;

    public CourseStorageService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public void storePluralsightCourses(List<PluralsightCourse> psCourses) {
        for (final PluralsightCourse psCourse : psCourses) {
            Course course = new Course(psCourse.id()
                  , psCourse.title()
                  , psCourse.durationInMinutes()
                  , PS_BASE_URL + psCourse.contentUrl()
                  , Optional.empty());
            courseRepository.saveCourse(course);
        }
    }
}
