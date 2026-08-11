package com.dormify.applications;

import com.dormify.users.User;
import org.springframework.stereotype.Component;

@Component
public class MarriedHandler extends AbstractApplicationHandler {

    private final ApplicationMapper mapper;

    public MarriedHandler(ApplicationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supports(CreateApplicationRequest request) {
        return request instanceof MarriedApplicationDto;
    }

    @Override
    public HandlerResult handle(CreateApplicationRequest request, User user) {
        var marriedApplicationDto = (MarriedApplicationDto) request;

        Application application = mapper.toEntityFromMarriedApplication(marriedApplicationDto);

        return new com.dormify.applications.HandlerResult(application);
    }

}
