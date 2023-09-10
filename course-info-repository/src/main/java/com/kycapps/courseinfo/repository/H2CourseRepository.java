package com.kycapps.courseinfo.repository;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 13:55
*/

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

class H2CourseRepository extends AbstractCourseRepository {

    private static final String H2_DATABASE_URL = "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";

    public H2CourseRepository(String databaseName) {
        super(createDataSource(databaseName));
    }

    private static DataSource createDataSource(final String databaseName) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseName));
        return jdbcDataSource;
    }
}
