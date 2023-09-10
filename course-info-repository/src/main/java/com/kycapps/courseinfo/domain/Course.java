package com.kycapps.courseinfo.domain;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 13:38
*/

import java.util.Optional;

public record Course(String id, String name, long length, String url, Optional<String> notes) {

    public Course {
        filled(id);
        filled(name);
        filled(url);
        notes.ifPresent(Course::filled);
    }

    private static void filled(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("No Value Present");
        }
    }

}
