package com.kycapps.courseinfo.repository;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 13:55
*/

import com.kycapps.courseinfo.domain.Course;
import com.kycapps.courseinfo.repository.exceptions.RepositoryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

class AbstractCourseRepository implements CourseRepository {

    private static final String INSERT_COURSE = """
              MERGE INTO Courses (id,name,length,url)
               VALUES (?,?,?,?)
          """;

    private static final String ADD_NOTES = """
          UPDATE Courses SET notes = ?
           WHERE id = ?
          """;

    protected final DataSource dataSource;

    protected AbstractCourseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveCourse(final Course course) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insertScript());
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setString(4, course.url());
            statement.setLong(3, course.length());
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to save " + course, e);
        }
    }

    protected String insertScript() {
        return INSERT_COURSE;
    }

    @Override
    public List<Course> getAllCourses() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSES");

            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {

                Course course = new Course(resultSet.getString(1),
                      resultSet.getString(2),
                      resultSet.getLong(3),
                      resultSet.getString(4),
                      Optional.ofNullable(resultSet.getString(5)));

                courses.add(course);
            }

            return courses;
        } catch (SQLException e) {
            throw new RepositoryException("Failed while fetching all courses ", e);
        }
    }

    @Override
    public void addNotes(final String id, final String notes) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NOTES);
            statement.setString(1, notes);
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to add notes to course id  " + id, e);
        }
    }
}
