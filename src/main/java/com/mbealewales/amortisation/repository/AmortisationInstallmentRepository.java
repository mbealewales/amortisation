package com.mbealewales.amortisation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mbealewales.amortisation.entity.AmortisationInstallment;
import com.mbealewales.amortisation.entity.LoanSchedule;

public interface AmortisationInstallmentRepository extends
CrudRepository<AmortisationInstallment, Long> {
    /**
     * 
     * @param loanSchedule matching loanSchedule.
     * @return the installements associated with the loanSchedule./
     */
    List<AmortisationInstallment> findByLoanSchedule(LoanSchedule loanSchedule);
}
