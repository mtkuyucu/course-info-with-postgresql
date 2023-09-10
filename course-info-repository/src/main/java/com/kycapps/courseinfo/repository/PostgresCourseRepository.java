package com.kycapps.courseinfo.repository;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 13:55
*/

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

class PostgresCourseRepository extends AbstractCourseRepository {

    private static final String POSTGRES_DATABASE_URL = "jdbc:postgresql://localhost:5435/courses";

    private static final String INSERT_SCRIPT = """
          INSERT INTO Courses (id, name, length, url)
          VALUES (?,?,?,?)
          ON CONFLICT (id)
          DO UPDATE SET\s
              name = EXCLUDED.name,\s
              length = EXCLUDED.length,
              url = EXCLUDED.url,
              notes = EXCLUDED.notes;
          """;

    protected PostgresCourseRepository() {
        super(createDataSource());
    }

    private static DataSource createDataSource() {
        PGSimpleDataSource jdbcDataSource = new PGSimpleDataSource();
        jdbcDataSource.setURL(POSTGRES_DATABASE_URL);
        jdbcDataSource.setUser("course");
        jdbcDataSource.setPassword("c31a1i");
        return jdbcDataSource;
    }

    @Override
    protected String insertScript() {
        return INSERT_SCRIPT;
    }
}
