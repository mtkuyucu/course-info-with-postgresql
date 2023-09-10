package com.kycapps.courseinfo.server;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 18:49
*/

import com.kycapps.courseinfo.repository.CourseRepository;
import com.kycapps.courseinfo.repository.CourseRepository.DatabaseType;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class CourseServer {

    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static final String BASE_URI = "http://localhost:8080/";
    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);

    public static void main(String[] args) {
        String databaseFileName = getDatabaseName();
        LOG.info("Course Server starting with database : {}", databaseFileName);
//        CourseRepository courseRepository = CourseRepository.openCourseRepository(DatabaseType.H2, "./courses.db");
        CourseRepository courseRepository = CourseRepository.openCourseRepository(DatabaseType.POSTGRES, null);
        ResourceConfig resourceConfig = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);

    }

    private static String getDatabaseName() {
        try (InputStream resourceAsStream = CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties.getProperty("course-info.database");
        } catch (IOException e) {
            throw new IllegalStateException("Could not load database filename");
        }
    }
}
