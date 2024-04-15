package com.mbealewales.amortisation.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbealewales.amortisation.AmortisationCalculatorService;
import com.mbealewales.amortisation.entity.LoanDetails;
import com.mbealewales.amortisation.repository.AmortisationInstallmentRepository;
import com.mbealewales.amortisation.repository.LoanDetailsRepository;
import com.mbealewales.amortisation.repository.LoanScheduleRepository;
import com.mbealewales.amortisation.service.exception.BadLoanDetailsException;
import com.mbealewales.amortisation.service.exception.NoSuchLoanScheduleException;

@RunWith(MockitoJUnitRunner.class)
public class AmortisationServiceTest {

    @Mock
    private AmortisationInstallmentRepository amortisationInstallmentRepository;

    @Mock
    private LoanDetailsRepository loanDetailsRepository;

    @Mock
    private LoanScheduleRepository loanScheduleRepository;

    @Mock
    private AmortisationCalculatorService amortisationCalculatorService;

    @InjectMocks
    private AmortisationService amortisationService;

    /**
     * @TODO This test is just one of several ways in which invalid data is passed
     * into LoanDetails.
     */
    @Test(expected = BadLoanDetailsException.class)
    public void badLoanDetailsPassedForCreation() {
        final LoanDetails loanDetails = new LoanDetails();
        loanDetails.setAssetCost(25000.0);
        loanDetails.setDeposit(5000.0);
        loanDetails.setYearlyInterestRate(7.5);
        loanDetails.setMonthlyRepayments(-1);

        amortisationService.createAmortisationSchedule(loanDetails);
    }

    @Test
    public void successfulCreation() {
        final LoanDetails loanDetails = new LoanDetails();
        loanDetails.setAssetCost(25000.0);
        loanDetails.setDeposit(5000.0);
        loanDetails.setYearlyInterestRate(7.5);
        loanDetails.setMonthlyRepayments(12);

        when(loanDetailsRepository.save(loanDetails)).thenReturn(loanDetails);
        
        when(amortisationCalculatorService.calculateMonthlyRepayment(loanDetails)).thenReturn(25.0);
        when(amortisationCalculatorService.calculateInstallments(loanDetails, 25.0)).thenReturn(null);
        when(amortisationCalculatorService.calculateTotalInterest(null)).thenReturn(0.0);
        when(amortisationCalculatorService.totalAmountPaid(1305.85, 24)).thenReturn(31340.40);

        when(loanScheduleRepository.save(null)).thenReturn(null);

        when(amortisationInstallmentRepository.saveAll(null)).thenReturn(null);

        amortisationService.createAmortisationSchedule(loanDetails);
    }

    @Test(expected = NoSuchLoanScheduleException.class)
    public void missingLoanSchedule() {
        when(loanScheduleRepository.findById(1234L)).thenReturn(Optional.empty());

        amortisationService.getAmortisationAndLoanSchedulesByLoanScheduleId(1234L);
    }
}
