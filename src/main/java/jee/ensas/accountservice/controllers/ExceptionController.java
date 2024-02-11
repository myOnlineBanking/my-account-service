package jee.ensas.accountservice.controllers;

import jee.ensas.accountservice.dtos.ErrorClass;
import jee.ensas.accountservice.exceptions.AccountException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorClass> handleChequeException(AccountException ex, HttpServletRequest request) {
        logger.error("Account Service Error" , ex);
        return ResponseEntity.status(ex.getStatus()).body(new ErrorClass(ex.getStatus(), ex.getMessage(), request.getRequestURI()));
    }

}
