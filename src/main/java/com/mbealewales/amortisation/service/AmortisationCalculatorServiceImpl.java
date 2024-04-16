package com.mbealewales.amortisation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.mbealewales.amortisation.AmortisationCalculatorService;
import com.mbealewales.amortisation.entity.AmortisationInstallment;
import com.mbealewales.amortisation.entity.LoanDetails;

@Service
public class AmortisationCalculatorServiceImpl implements AmortisationCalculatorService {

    private static final double MONTHLY_AS_PERCENTAGE = 1200.0;

    @Override
    public double toPoundsPence(double val) {
        return Double.parseDouble(String.format("%.2f", val));
    }

    @Override
    public double totalAmountPaid(double monthlyPayment, int numberOfRepayments) {
        return this.toPoundsPence(monthlyPayment * numberOfRepayments);
    }

    @Override
    public double calculateTotalInterest(List<AmortisationInstallment> installments) {
        return this.toPoundsPence(installments.stream().mapToDouble(v -> v.getInterest()).sum());
    }
    
    @Override
    public double calculateMonthlyRepayment(LoanDetails loanDetails) {
        double r = loanDetails.getYearlyInterestRate() / MONTHLY_AS_PERCENTAGE;
        double p = loanDetails.getAssetCost() - loanDetails.getDeposit();
        double n = loanDetails.getMonthlyRepayments();
        double b = loanDetails.getBalloonPayment();

        double monthlyRepayment;

        if (b == 0.0) {
            monthlyRepayment = p * ((r * Math.pow(1 + r, n) / (Math.pow(1+r, n) - 1)));
        } else {
            monthlyRepayment = (p - (b / Math.pow(1 + r,  n))) * (r / (1 - Math.pow(1 + r, -n)));
        }
        return this.toPoundsPence(monthlyRepayment);
    }

    @Override
    public List<AmortisationInstallment> calculateInstallments(LoanDetails loanDetails, double monthlyRepayment) {
        final List<AmortisationInstallment> installments = new ArrayList<>();
        final double [] r = { loanDetails.getYearlyInterestRate() / MONTHLY_AS_PERCENTAGE  };
        final double [] p = { loanDetails.getAssetCost() - loanDetails.getDeposit() };

        IntStream.rangeClosed(1, loanDetails.getMonthlyRepayments()).forEach(period -> {
            double i = this.toPoundsPence(r[0] * p[0]);
            double principal = this.toPoundsPence(monthlyRepayment - i);
            p[0] = p[0] - principal;
            // Loan Schedule will be calculated and set later...
            final AmortisationInstallment installment = new AmortisationInstallment(0L,
             period, monthlyRepayment, principal, i, p[0], null);
            
             installments.add(installment);
        });
        return installments;
    }
}
