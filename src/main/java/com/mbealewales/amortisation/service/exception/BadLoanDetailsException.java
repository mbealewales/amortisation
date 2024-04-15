package com.mbealewales.amortisation.service.exception;

/**
 * Exception encapsulating when an attempt was made to create 
 * an amortisation schedule with missing or incorrect data.
 * @author Mike Beale
 */
public class BadLoanDetailsException extends RuntimeException {
    public BadLoanDetailsException(String reason) {
        super(reason);
    }
}
