package com.dormify.applications;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public enum ApplicationType {
    APPLICATION_FIRST_COURSE_NOT_FAMILY("application-first-course-not-family"),
    APPLICATION_FIRST_COURSE_FAMILY("application-first-course-family"),
    APPLICATION_UPPER_COURSE_RELOCATION("application-upper-course-relocation"),
    APPLICATION_UPPER_COURSE_NOT_FAMILY("application-upper-course-not-family"),
    APPLICATION_UPPER_COURSE_FAMILY("application-upper-course-family"),
    NIGHTLY("nightly");

    @Getter
    private final String value;

    ApplicationType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ApplicationType fromString(String value) {
        if (value == null) return null;

        try {
            return ApplicationType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid application type value: %s", value));
        }
    }
}
