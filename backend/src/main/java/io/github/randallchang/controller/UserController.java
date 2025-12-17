package io.github.randallchang.controller;

import io.github.randallchang.model.response.User;
import io.github.randallchang.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("list")
    public List<User> findAllUser() {
        return userService.findAllUser();
    }
}
