package com.dormify.applications;

import com.dormify.users.User;
import org.springframework.stereotype.Component;

@Component
public class UpperCourseHandler extends AbstractApplicationHandler {
    private final ApplicationMapper mapper;

    public UpperCourseHandler(ApplicationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supports(CreateApplicationRequest request) {
        return request instanceof ApplicationUpperCourseDto;
    }

    @Override
    public HandlerResult handle(CreateApplicationRequest request, User user) {
        var applicationUpperCourseDto = (ApplicationUpperCourseDto) request;
        Application application = mapper.toEntity(applicationUpperCourseDto);
        application.setCurrentRoom(applicationUpperCourseDto.getRoomNumber());
        application.setCurrentDormitory(applicationUpperCourseDto.getDormitoryName());

        return new HandlerResult(application);
    }
}
