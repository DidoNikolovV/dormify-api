package com.dormify.applications;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.dormify.applications.ApplicationType.APPLICATION_FIRST_COURSE_FAMILY;
import static com.dormify.applications.ApplicationType.APPLICATION_UPPER_COURSE_FAMILY;

@Component
public class MarriedApplicationValidator implements ApplicationValidator<MarriedApplicationDto> {
    @Override
    public Set<ApplicationType> getType() {
        return Set.of(
                APPLICATION_UPPER_COURSE_FAMILY,
                APPLICATION_FIRST_COURSE_FAMILY
        );
    }

    @Override
    public void validate(MarriedApplicationDto dto, DocumentsWrapper documents) {
        if (dto.getSpouseFirstName() == null || dto.getSpouseFirstName().isBlank()) {
            throw new ValidationException("Spouse first name is required for MARRIED application.");
        }

        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new ValidationException("Spouse last name is required for MARRIED application.");
        }

        if (documents.getMarriageCertificate() == null || documents.getMarriageCertificate().isEmpty()) {
            throw new ValidationException("Marriage certificate file is required for MARRIED application.");
        }
    }
}
