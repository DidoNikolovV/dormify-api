package com.dormify.applications;

import com.dormify.users.User;
import org.springframework.stereotype.Component;

@Component
public class FirstCourseHandler extends AbstractApplicationHandler {
    private final ApplicationMapper mapper;

    public FirstCourseHandler(ApplicationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supports(CreateApplicationRequest request) {
        return request instanceof ApplicationFirstCourseDto;
    }

    @Override
    public HandlerResult handle(CreateApplicationRequest request, User user) {
        var firstCourseApplication = (ApplicationFirstCourseDto) request;
        var application = mapper.toEntityFromFirstCourse(firstCourseApplication);

        return new HandlerResult(application);
    }
}
