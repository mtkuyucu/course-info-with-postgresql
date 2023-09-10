package com.kycapps.courseinfo.cli.service;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 12:10
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kycapps.courseinfo.cli.PluralsightCourse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

public class CourseRetrievalService {

    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";
    private static final HttpClient CLIENT = HttpClient
          .newBuilder()
          .followRedirects(Redirect.ALWAYS)
          .build();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public List<PluralsightCourse> getCoursesFor(String authorId) {

        final HttpRequest request = HttpRequest.newBuilder(URI.create(PS_URI.formatted(authorId)))
              .GET()
              .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, BodyHandlers.ofString());
            return switch (response.statusCode()) {
                case 200 -> toPluralsightCourses(response);
                case 404 -> List.of();
                default -> throw new RuntimeException("Failed while calling the Pluralsight status code : " + response.statusCode());
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not call the Pluralsight", e);
        }
    }

    private static List<PluralsightCourse> toPluralsightCourses(final HttpResponse<String> response) throws JsonProcessingException {
        final JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, PluralsightCourse.class);
        return OBJECT_MAPPER.readValue(response.body(), javaType);
    }
}
