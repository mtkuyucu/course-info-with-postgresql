package com.kycapps.courseinfo.repository;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 13:50
*/

import com.kycapps.courseinfo.domain.Course;
import com.kycapps.courseinfo.repository.exceptions.RepositoryException;
import java.util.List;

public interface CourseRepository {

    enum DatabaseType {
        POSTGRES,
        H2
    }

    void saveCourse(Course course) throws RepositoryException;

    List<Course> getAllCourses();

    void addNotes(String id, String notes);

    static CourseRepository openCourseRepository(DatabaseType databaseType, String databaseFile) {
        return switch (databaseType) {
            case POSTGRES -> new PostgresCourseRepository();
            case H2 -> new H2CourseRepository(databaseFile);
            default -> throw new IllegalArgumentException("Unsupported database type");
        };
    }

}
