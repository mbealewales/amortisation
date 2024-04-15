package com.mbealewales.amortisation.service;

import java.util.List;

import org.springframework.data.util.Pair;

import com.mbealewales.amortisation.entity.AmortisationInstallment;
import com.mbealewales.amortisation.entity.LoanDetails;
import com.mbealewales.amortisation.entity.LoanSchedule;

/**
 * Business Logic for Amortisation
 * @author Mike Beale
 */
public interface AmortisationService {
    /**
     * 
     * @param loanDetails
     * @return
     */
    LoanDetails createAmortisationSchedule(LoanDetails loanDetails);
    
    /**
     * @return all the Loan Schedules created.
     */
    List<LoanSchedule> getLoanSchedules();

    /**
     * For specified loan schedule, return the list of installments for the
     * schedule AND the loan schedule.
     * @param loanScheduleId the id of the schedule.
     * @return the pair of installment list and loan schedule.
     */
    Pair<List<AmortisationInstallment>, LoanSchedule> getAmortisationAndLoanSchedulesByLoanScheduleId(Long loanScheduleId);
}
