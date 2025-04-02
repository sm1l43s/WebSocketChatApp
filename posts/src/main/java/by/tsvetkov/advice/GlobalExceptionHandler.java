package by.tsvetkov.advice;

import by.tsvetkov.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import by.tsvetkov.dto.error.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import by.tsvetkov.exception.NotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponse handleThrowable(Exception e) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .reason("Unexpected reason")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handleNotFoundException(NotFoundException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .reason("The required object was not found.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ErrorResponse handleInvalidToken(InvalidTokenException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.toString())
                .reason("Invalid token.")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
