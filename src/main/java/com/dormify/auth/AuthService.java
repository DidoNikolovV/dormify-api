package com.dormify.auth;

import com.dormify.common.ResourceAlreadyExistsException;
import com.dormify.users.User;
import com.dormify.users.UserDto;
import com.dormify.users.UserMapper;
import com.dormify.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dormify.common.ErrorMessage.*;

@AllArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();
        return userRepository.findById(userId).orElse(null);
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponse(accessToken, refreshToken);
    }

    @Transactional
    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByNationalId(request.getNationalId())) {
            throw new ResourceAlreadyExistsException(USER_WITH_NATIONAL_ID_ALREADY_EXISTS.getMessage(request.getNationalId()));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(USER_WITH_EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException(USER_WITH_PHONE_NUMBER_ALREADY_EXISTS.getMessage(request.getPhoneNumber()));
        }

        if (request.getFacultyNumber() != null && userRepository.existsByFacultyNumber(request.getFacultyNumber())) {
            throw new ResourceAlreadyExistsException(USER_WITH_FACULTY_NUMBER_ALREADY_EXISTS.getMessage(request.getFacultyNumber()));
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
