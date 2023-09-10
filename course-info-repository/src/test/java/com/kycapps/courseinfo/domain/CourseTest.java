package com.kycapps.courseinfo.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;


/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 13:41
*/

class CourseTest {


    @Test()
    void testExceptionThrown_whenIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(null, "Name", 0, "URL", Optional.empty());
        });
    }

    @Test()
    void testExceptionThrown_whenIdEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("", "Name", 0, "URL", Optional.empty());
        });
    }

    @Test()
    void testExceptionThrown_whenNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("id", null, 0, "URL", Optional.empty());
        });
    }

    @Test()
    void testExceptionThrown_whenNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("id", "", 0, "URL", Optional.empty());
        });
    }

    @Test
    void testNoExceptionThrown() {
        Course course = new Course("id", "name", 0, "URL", Optional.empty());
        assertEquals("name", course.name());
        assertEquals("id", course.id());
        assertEquals("URL", course.url());
        assertEquals(0, course.length());
    }


}