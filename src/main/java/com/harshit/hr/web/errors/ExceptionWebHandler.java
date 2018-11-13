package com.harshit.hr.web.errors;

import com.harshit.hr.errors.ApplicationNotFoundException;
import com.harshit.hr.errors.OfferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionWebHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleOfferNotFoundException(OfferNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApplicationNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleApplicationNotFoundException(ApplicationNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    public static class ErrorResponse {
        private String errorMessage;

        public ErrorResponse(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
