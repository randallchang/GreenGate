package io.github.randallchang.controller;

import io.github.randallchang.model.request.LoginRequest;
import io.github.randallchang.model.response.LoginResponse;
import io.github.randallchang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse postMethodName(@RequestBody LoginRequest loginRequest) {
        return new LoginResponse(
            userService.login(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
    }
}
