package com.kycapps.courseinfo.cli;

/*
    @author Mehmet T Kuyucu
    @on 9.09.2023 - 23:44
*/

import com.kycapps.courseinfo.cli.service.CourseRetrievalService;
import com.kycapps.courseinfo.cli.service.CourseStorageService;
import com.kycapps.courseinfo.repository.CourseRepository;
import com.kycapps.courseinfo.repository.CourseRepository.DatabaseType;
import java.util.List;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOG.info("CourseRetriever starting");
        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        } catch (Exception ex) {
            LOG.error("Unexpected error", ex);
        }
    }

    private static void retrieveCourses(final String authorId) {
        LOG.info("Courses are listing for the user '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
//        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        CourseRepository courseRepository = CourseRepository.openCourseRepository(DatabaseType.POSTGRES, null);
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
              .stream()
              .filter(Predicate.not(PluralsightCourse::isRetired))
              .toList();
        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);

        courseStorageService.storePluralsightCourses(coursesToStore);

        LOG.info("Courses has been saved success fully");
    }

}
