package com.mbealewales.amortisation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.mbealewales.amortisation.AmortisationCalculatorService;
import com.mbealewales.amortisation.entity.AmortisationInstallment;
import com.mbealewales.amortisation.entity.LoanDetails;
import com.mbealewales.amortisation.entity.LoanSchedule;
import com.mbealewales.amortisation.repository.AmortisationInstallmentRepository;
import com.mbealewales.amortisation.repository.LoanDetailsRepository;
import com.mbealewales.amortisation.repository.LoanScheduleRepository;
import com.mbealewales.amortisation.service.exception.BadLoanDetailsException;
import com.mbealewales.amortisation.service.exception.NoSuchLoanScheduleException;

/**
 * Business Logic Implementation for Amortisation
 * @author Mike Beale
 */
@Service
public class AmortisationServiceImpl implements AmortisationService {

    @Autowired
    AmortisationCalculatorService amortisationCalculatorService;

    @Autowired
    AmortisationInstallmentRepository amortisationInstallmentsRepository;
    @Autowired
    LoanDetailsRepository loanDetailsRepository;
    @Autowired
    LoanScheduleRepository loanScheduleRepository;
   
    private void validateLoanDetails(LoanDetails loanDetails) throws BadLoanDetailsException {
        if (loanDetails.getMonthlyRepayments() <= 0) {
            throw new BadLoanDetailsException("Monthly loan repayments must be at least 1.");
        }
        if (loanDetails.getMonthlyRepayments() <= 0) {
            throw new BadLoanDetailsException("Monthly loan repayments must be at least 1.");
        }
        if (loanDetails.getBalloonPayment() < 0) {
            throw new BadLoanDetailsException("Balloon payment must be >= 0.");
        }
        if (loanDetails.getDeposit() < 0) {
            throw new BadLoanDetailsException("Deposit must be >= 0.");
        }
        if (loanDetails.getYearlyInterestRate() < 0.0) {
            throw new BadLoanDetailsException("Cannot have negative interest rate.");
        }
        if (loanDetails.getDeposit() > loanDetails.getAssetCost()) {
            throw new BadLoanDetailsException("Deposit cannot be greater than asset cost."); 
        }

        if (loanDetails.getBalloonPayment() > loanDetails.getAssetCost()) {
            throw new BadLoanDetailsException("Balloon payment cannot be greater than asset cost."); 
        }
        if ((loanDetails.getBalloonPayment() + loanDetails.getDeposit()) > loanDetails.getAssetCost()) {
            throw new BadLoanDetailsException("Balloon payment + deposit cannot be greater than asset cost."); 
        }
    }
    
    @Override
    public LoanDetails createAmortisationSchedule(LoanDetails loanDetails) {
        this.validateLoanDetails(loanDetails);

        final LoanDetails savedLoanDetails = loanDetailsRepository.save(loanDetails);
        
        double monthlyRepayment = this.amortisationCalculatorService.calculateMonthlyRepayment(loanDetails);

        final List<AmortisationInstallment> installments = this.amortisationCalculatorService.calculateInstallments(loanDetails, monthlyRepayment);

        double totalInterest = this.amortisationCalculatorService.calculateTotalInterest(installments);

        final LoanSchedule loanSchedule = new LoanSchedule(0L, savedLoanDetails, monthlyRepayment, totalInterest,
        this.amortisationCalculatorService.totalAmountPaid(monthlyRepayment, loanDetails.getMonthlyRepayments()));
        final LoanSchedule savedLoanSchedule = loanScheduleRepository.save(loanSchedule);

        installments.stream().forEach(i -> i.setLoanSchedule(savedLoanSchedule));
        amortisationInstallmentsRepository.saveAll(installments);
        
        return savedLoanDetails;
    }
    @Override
    public List<LoanSchedule> getLoanSchedules() {
        final List<LoanSchedule> loanSchedules = new ArrayList<>();
        this.loanScheduleRepository.findAll().forEach(loanSchedules::add);
        return loanSchedules;
    }
    @Override
    public Pair<List<AmortisationInstallment>, LoanSchedule> getAmortisationAndLoanSchedulesByLoanScheduleId(
            Long loanScheduleId) {
       final Optional<LoanSchedule> optLoanSchedule = loanScheduleRepository.findById(loanScheduleId);
       if (optLoanSchedule.isPresent()) {
            final LoanSchedule loanSchedule = optLoanSchedule.get();
            return Pair.of(this.amortisationInstallmentsRepository.findByLoanSchedule(loanSchedule), loanSchedule);
       } else {
        throw new NoSuchLoanScheduleException(loanScheduleId);
       }
    }
    
}
