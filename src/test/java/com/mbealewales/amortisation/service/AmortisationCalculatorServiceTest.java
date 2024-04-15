package com.mbealewales.amortisation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.mbealewales.amortisation.AmortisationCalculatorService;
import com.mbealewales.amortisation.entity.LoanDetails;

@RunWith(MockitoJUnitRunner.class)
public class AmortisationCalculatorServiceTest {
    
    @InjectMocks
    private AmortisationCalculatorService amortisationCalculatorService;

    private LoanDetails createTestLoanDetails(boolean includeBalloon) {
        final LoanDetails loanDetails = new LoanDetails();
        loanDetails.setAssetCost(25000.0);
        loanDetails.setDeposit(5000.0);
        loanDetails.setYearlyInterestRate(7.5);
        loanDetails.setMonthlyRepayments(12);
        if (includeBalloon) {
            loanDetails.setBalloonPayment(10000.0);
        }
        return loanDetails;
    }
    @Test
    public void calculateMonthlyRepaymentWithoutBalloon() {
        assertEquals(40.0, amortisationCalculatorService.calculateMonthlyRepayment(this.createTestLoanDetails(false)));
    }

    @Test
    public void calculateMonthlyRepaymentWithBalloon() {
        assertEquals(25.0, amortisationCalculatorService.calculateMonthlyRepayment(this.createTestLoanDetails(true)));
    }

    @Test
    public void calculateTotalAmountPaid() {
        assertEquals(31340.40, amortisationCalculatorService.totalAmountPaid(1305.85, 24));
    }

    @Test
    public void calculateInstallmentsWhenNoBalloon() {
        assertEquals(null, amortisationCalculatorService.calculateInstallments(
            this.createTestLoanDetails(false), 40.0));
    }
    @Test
    public void calculateInstallmentsWhenBalloon() {
        assertEquals(null, amortisationCalculatorService.calculateInstallments(
            this.createTestLoanDetails(true), 25.0));
    }

    @Test
    public void calculateTotalInterest() {
        assertEquals(1200.0, amortisationCalculatorService.calculateTotalInterest(null));
    }
}
