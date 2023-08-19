package com.placementsystem.PlacementManagementSystem.Exception;

import com.placementsystem.PlacementManagementSystem.Entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserDetailsServiceControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidUserNameAndPassword.class)
    public ErrorResponse invalidCreditentialsExecption(InvalidUserNameAndPassword ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
