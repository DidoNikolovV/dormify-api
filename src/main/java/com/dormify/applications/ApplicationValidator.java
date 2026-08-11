package com.dormify.applications;

import java.util.Set;

public interface ApplicationValidator<T extends CreateApplicationRequest> {
    Set<ApplicationType> getType();

    void validate(T dto, DocumentsWrapper documents);
}
