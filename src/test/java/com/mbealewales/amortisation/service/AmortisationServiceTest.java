package com.mbealewales.amortisation.service;

import static com.mbealewales.amortisation.TestUtils.UNBALLOONED_INSTALLMENTS;
import static com.mbealewales.amortisation.TestUtils.totalInterest;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbealewales.amortisation.AmortisationCalculatorService;
import com.mbealewales.amortisation.entity.LoanDetails;
import com.mbealewales.amortisation.entity.LoanSchedule;
import com.mbealewales.amortisation.repository.AmortisationInstallmentRepository;
import com.mbealewales.amortisation.repository.LoanDetailsRepository;
import com.mbealewales.amortisation.repository.LoanScheduleRepository;
import com.mbealewales.amortisation.service.exception.BadLoanDetailsException;
import com.mbealewales.amortisation.service.exception.NoSuchLoanScheduleException;

//@Ignore
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
    private AmortisationServiceImpl amortisationService;

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
        loanDetails.setId(1234L);

        final LoanSchedule loanSchedule = new LoanSchedule();
        loanSchedule.setId(4567L);
        loanSchedule.setMonthlyRepaymentAmount(1735.15);
        loanSchedule.setTotalInterestDue(totalInterest(UNBALLOONED_INSTALLMENTS));
        loanSchedule.setLoanDetails(loanDetails);
        loanSchedule.setTotalPaymentsDue(20821.80);

        when(loanDetailsRepository.save(loanDetails)).thenReturn(loanDetails);
        
        when(amortisationCalculatorService.calculateMonthlyRepayment(loanDetails)).thenReturn(1735.15);
        when(amortisationCalculatorService.calculateInstallments(loanDetails, 1735.15)).thenReturn(UNBALLOONED_INSTALLMENTS);
        when(amortisationCalculatorService.calculateTotalInterest(UNBALLOONED_INSTALLMENTS))
        .thenReturn(totalInterest(UNBALLOONED_INSTALLMENTS));
        when(amortisationCalculatorService.totalAmountPaid(1735.15, 12)).thenReturn(20821.80);

        when(loanScheduleRepository.save(loanSchedule)).thenReturn(loanSchedule);

        when(amortisationInstallmentRepository.saveAll(UNBALLOONED_INSTALLMENTS)).thenReturn(UNBALLOONED_INSTALLMENTS);

        amortisationService.createAmortisationSchedule(loanDetails);
    }

    @Test(expected = NoSuchLoanScheduleException.class)
    public void missingLoanSchedule() {
        when(loanScheduleRepository.findById(1234L)).thenReturn(Optional.empty());

        amortisationService.getAmortisationAndLoanSchedulesByLoanScheduleId(1234L);
    }
}
