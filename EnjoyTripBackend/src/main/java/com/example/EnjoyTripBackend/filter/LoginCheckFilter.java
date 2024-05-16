package com.example.EnjoyTripBackend.filter;

import com.example.EnjoyTripBackend.exception.EnjoyTripException;
import com.example.EnjoyTripBackend.exception.ErrorCode;
import com.example.EnjoyTripBackend.util.SessionConst;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

import static org.springframework.http.MediaType.*;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {

    private final ObjectMapper objectMapper;

    private static final String[] whitelist = {"/api/signUp", "/api/login", "/api/logout","/api/place/**"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);

                HttpSession session = httpRequest.getSession(false);
                if ((session == null) || (session.getAttribute(SessionConst.LOGIN_MEMBER) == null)) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    AnonymousUserHandler(httpResponse, ErrorCode.ANONYMOUS_USER.getMessage());
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw new EnjoyTripException(ErrorCode.ANONYMOUS_USER, e.getMessage());
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    private void AnonymousUserHandler(HttpServletResponse response, String message) throws IOException{
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), new ResponseEntity(message, null, HttpStatus.FORBIDDEN.value()));
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}