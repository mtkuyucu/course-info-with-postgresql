package com.kycapps.courseinfo.cli;

/*
    @author Mehmet T Kuyucu
    @on 9.09.2023 - 23:44
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOG.info("CourseRetriver starting");
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
    }

}
