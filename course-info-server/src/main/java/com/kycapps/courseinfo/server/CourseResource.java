package com.kycapps.courseinfo.server;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 18:43
*/

import com.kycapps.courseinfo.domain.Course;
import com.kycapps.courseinfo.repository.CourseRepository;
import com.kycapps.courseinfo.repository.exceptions.RepositoryException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/courses")
public class CourseResource {

    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses() {
        try {
            return courseRepository.getAllCourses()
                  .stream()
                  .sorted(Comparator.comparing(Course::id));
        } catch (RepositoryException e) {
            throw new NotFoundException("No course find", e);
        }
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes) {
        courseRepository.addNotes(id, notes);
    }
}
