package io.github.randallchang.service;

import io.github.randallchang.model.entity.UserEntity;
import io.github.randallchang.model.response.User;
import io.github.randallchang.repository.UserRepository;
import io.github.randallchang.util.JwtUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public String login(String username, String rawPassword) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            user = new UserEntity(null, username, encoder.encode(rawPassword));
            userRepository.save(user);
        }

        if (!encoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("wrong password.");
        }

        return jwtUtil.encrypt(Map.of(JwtUtil.LOGIN_USER_ID_KEY, user.getId()));
    }

    public List<User> findAllUser() {
        return userRepository.findAll()
            .stream()
            .map(u -> new User(u.getId(), u.getUsername()))
            .collect(Collectors.toList());
    }
}
