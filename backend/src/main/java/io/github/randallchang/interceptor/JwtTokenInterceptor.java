package io.github.randallchang.interceptor;

import io.github.randallchang.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {
    private static final String TOKEN_HEADER = "Authorization";
    private static final String USER_ID_KEY = "user_id";

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull Object handler) {
        if (request.getMethod() == "OPTIONS") {
            return true;
        }

        String token =
            Optional.ofNullable(request.getHeader(TOKEN_HEADER))
                .orElseThrow(() -> new RuntimeException("UNAUTHORIZED"));

        Map<String, Object> claims = jwtUtil.decrypt(token);

        MDC.put(USER_ID_KEY, claims.get(JwtUtil.LOGIN_USER_ID_KEY).toString());

        return true;
    }
}
