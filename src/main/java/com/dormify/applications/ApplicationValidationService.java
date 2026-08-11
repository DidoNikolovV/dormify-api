package com.dormify.applications;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationValidationService{
    private final List<ApplicationValidator<? extends AcademicApplicationDto>> validators;

    /**
     * Calls specific validator based on the type.
     */
    @SuppressWarnings("unchecked")
    public <T extends CreateApplicationRequest> void validateApplication(T request, DocumentsWrapper documents) {
        for (ApplicationValidator validator : validators) {
            if (validator.getType().contains(request.getType())) {
                validator.validate(request, documents);
            }
        }
    }
}
