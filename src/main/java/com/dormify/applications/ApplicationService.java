package com.dormify.applications;

import com.dormify.common.ResourceAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.dormify.applications.ApplicationStatus.DECLINED;
import static com.dormify.applications.ApplicationStatus.REJECTED;
import static com.dormify.common.ErrorMessage.APPLICATION_ALREADY_SUBMITTED;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final List<ApplicationHandler> handlers;

    public ApplicationDto submitApplication(Long windowId, CreateApplicationRequest request, DocumentsWrapper documents, String email) {
        if (applicationRepository.existsByApplicationWindowIdAndUserEmailAndStatusNotIn((windowId, email, Set.of(REJECTED, DECLINED))) {
            throw new ResourceAlreadyExistsException(APPLICATION_ALREADY_SUBMITTED.getMessage(email, windowId));
        }

        return null;
    }
}
