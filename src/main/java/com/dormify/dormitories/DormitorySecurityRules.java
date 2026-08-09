package com.dormify.dormitories;

import com.dormify.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class DormitorySecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers(HttpMethod.GET, "/dormitories").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/dormitories/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/dormitories/{id}").hasAnyRole("ADMIN", "DORMITORY_MANAGER");
    }
}
