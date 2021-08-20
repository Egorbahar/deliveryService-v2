package com.exposit.core;

import com.exposit.core.exception.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zalando.problem.Problem;

import java.net.URI;

import static org.zalando.problem.Status.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<?> resourceNotFoundException(ProductNotFoundException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, "application/problem+json")
                .body(Problem.builder()
                             .withType(URI.create("https://example.org/out-of-stock"))
                             .withTitle("Out of Stock")
                             .withStatus(BAD_REQUEST)
                             .withDetail("Item X is no longer available")
                             .with("product", "X")
                             .build());
    }

}

