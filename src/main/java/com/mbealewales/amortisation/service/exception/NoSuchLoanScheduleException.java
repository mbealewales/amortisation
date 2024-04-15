package com.mbealewales.amortisation.service.exception;

/**
 * Exception encapsulating when an attempt was made to retrieve 
 * an amortisation schedule and/or installments with an unknown identifier.
 * @author Mike Beale
 */
public class NoSuchLoanScheduleException extends RuntimeException {
    public NoSuchLoanScheduleException(Long id) {
        super(String.format("No such Loan Schedule with id %d", id));
    }
}
