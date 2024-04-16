package com.mbealewales.amortisation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbealewales.amortisation.entity.LoanDetails;

import static com.mbealewales.amortisation.TestUtils.*;

@RunWith(MockitoJUnitRunner.class)
public class AmortisationCalculatorServiceTest {

    @InjectMocks
    private AmortisationCalculatorServiceImpl amortisationCalculatorService;

    private LoanDetails createTestLoanDetailsWithBalloonAndOverYears(boolean includeBalloon, int years) {
        final LoanDetails loanDetails = new LoanDetails();
        loanDetails.setAssetCost(25000.0);
        loanDetails.setDeposit(5000.0);
        loanDetails.setYearlyInterestRate(7.5);
        loanDetails.setMonthlyRepayments(12 * years);
        if (includeBalloon) {
            loanDetails.setBalloonPayment(10000.0);
        }
        return loanDetails;
    }
    @Test
    public void calculateMonthlyRepaymentWithoutBalloon() {
        assertEquals(400.76, amortisationCalculatorService.calculateMonthlyRepayment(
            this.createTestLoanDetailsWithBalloonAndOverYears(false, 5)));
    }

    @Test
    public void calculateMonthlyRepaymentWithBalloon() {
        assertEquals(262.88, amortisationCalculatorService.calculateMonthlyRepayment(
            this.createTestLoanDetailsWithBalloonAndOverYears(true, 5)));
    }

    @Test
    public void calculateTotalAmountPaid() {
        assertEquals(24045.60, amortisationCalculatorService.totalAmountPaid(400.76, 60));
    }

    @Ignore
    @Test
    public void calculateInstallmentsWhenNoBalloonPayment() {
        assertEquals(UNBALLOONED_INSTALLMENTS, amortisationCalculatorService.calculateInstallments(
            this.createTestLoanDetailsWithBalloonAndOverYears(false, 1), 1735.15));
    }
    @Ignore
    @Test
    public void calculateInstallmentsWithBalloonPayment() {
        assertEquals(BALLOONED_INSTALLMENTS, amortisationCalculatorService.calculateInstallments(
            this.createTestLoanDetailsWithBalloonAndOverYears(true, 1), 930.07));
    }

    @Test
    public void calculateTotalInterest() {
        assertEquals(totalInterest(UNBALLOONED_INSTALLMENTS), amortisationCalculatorService.calculateTotalInterest(UNBALLOONED_INSTALLMENTS));
    }
}
