package com.dormify.applications;

import com.dormify.users.User;

public interface ApplicationHandler {
    boolean supports(CreateApplicationRequest request);

    HandlerResult handle(CreateApplicationRequest request, User user);
}
