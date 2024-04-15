package com.mbealewales.amortisation.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mbealewales.amortisation.service.exception.BadLoanDetailsException;
import com.mbealewales.amortisation.service.exception.NoSuchLoanScheduleException;

/**
 * Maps RunTimeExceptions to response codes.
 * 
 * @author Mike Beale
 */
@ControllerAdvice
public class AmortisationExceptionHandler {
    @ExceptionHandler(BadLoanDetailsException.class)
    public ResponseEntity<Object> handleBadLoanDetails() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchLoanScheduleException.class)
    public ResponseEntity<Object> handleNoLoanSchedule() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
