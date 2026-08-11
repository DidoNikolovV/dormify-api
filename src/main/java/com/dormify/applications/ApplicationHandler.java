package com.dormify.applications;

import com.dormify.users.User;
import com.sun.nio.sctp.HandlerResult;

public interface ApplicationHandler {
    boolean supports(CreateApplicationRequest request);

    HandlerResult handle(CreateApplicationRequest request, User user);
}
