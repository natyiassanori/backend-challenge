package com.userapi.support;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ UserNotFoundException.class })
    public final ResponseEntity<?> handleUserNotFoundException(Exception ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        UserNotFoundException userNotFoundException = (UserNotFoundException) ex;
        return handleExceptionInternal(userNotFoundException, status);
    }

    @ExceptionHandler({ EmailDuplicatedException.class })
    public final ResponseEntity<?> handleEmailDuplicatedException(Exception ex) {

        HttpStatus status = HttpStatus.CONFLICT;
        EmailDuplicatedException emailDuplicatedException = (EmailDuplicatedException) ex;
        return handleExceptionInternal(emailDuplicatedException, status);
    }

    @ExceptionHandler({ InvalidEmailException.class })
    public final ResponseEntity<?> handleInvalidEmailException(Exception ex) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        InvalidEmailException invalidEmailException = (InvalidEmailException) ex;
        return handleExceptionInternal(invalidEmailException, status);
    }

    @ExceptionHandler({ UserAlreadyHasTheEmailException.class })
    public final ResponseEntity<?> handleUserAlreadyHasTheEmailException(Exception ex) {

        HttpStatus status = HttpStatus.CONFLICT;
        UserAlreadyHasTheEmailException userAlreadyHasTheEmailException = (UserAlreadyHasTheEmailException) ex;
        return handleExceptionInternal(userAlreadyHasTheEmailException, status);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public final ResponseEntity<?> handleBadRequestException() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>("There are wrong format input on the request", status);
    }

    @ExceptionHandler({ EmailNotFoundException.class })
    public final ResponseEntity<?> handleEmailNotFoundException(Exception ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        EmailNotFoundException emailNotFoundException = (EmailNotFoundException) ex;
        return handleExceptionInternal(emailNotFoundException, status);
    }

    protected ResponseEntity<?> handleExceptionInternal(Exception ex, HttpStatus status) {
        return new ResponseEntity<>(ex.getMessage(), status);
    }
}