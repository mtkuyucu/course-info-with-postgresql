package com.kycapps.courseinfo.cli;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 12:27
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Duration;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourse(String id, String title, String duration, String contentUrl, boolean isRetired) {

    public long durationInMinutes() {
        return Duration.between(
              LocalTime.MIN,
              LocalTime.parse(duration())
        ).toMinutes();
    }

}
