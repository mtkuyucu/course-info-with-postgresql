package com.kycapps.courseinfo.cli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 12:56
*/

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
          01:08:54.9613330, 68
          00:05:37, 5
          00:00:00.0, 0
          """)
    void durationInMinutes(String input, long expected) {
        PluralsightCourse course = new PluralsightCourse("id", "Test", input, "https://url", false);
        assertEquals(expected, course.durationInMinutes());
    }
}