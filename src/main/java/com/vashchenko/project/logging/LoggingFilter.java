package com.vashchenko.project.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;


@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        long startTime = System.currentTimeMillis();
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logResponse(responseWrapper, requestWrapper, request, duration);
    }

    private void logResponse(ContentCachingResponseWrapper responseWrapper,
                             ContentCachingRequestWrapper requestWrapper,
                             HttpServletRequest request,
                             Long duration) throws IOException {
        int status = responseWrapper.getStatus();

        log.info("Request: method={}, path={}; Response: status={}, completed in {} ms",
                request.getMethod(), request.getRequestURI(), status, duration);
        responseWrapper.copyBodyToResponse();
    }
}