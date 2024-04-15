package com.mbealewales.amortisation;

import java.util.List;

import com.mbealewales.amortisation.entity.AmortisationInstallment;
import com.mbealewales.amortisation.entity.LoanDetails;

/**
 * Business Logic for Amortisation Calculation.
 * @author Mike Beale
 */
public interface AmortisationCalculatorService {
    /**
     * 
     * @param loanDetails the loan details supplied.
     * @param monthlyRepayment the calculated monthly repayments.
     * @return the collection of installments to be made.
     */
    List<AmortisationInstallment> calculateInstallments(LoanDetails loanDetails, double monthlyRepayment);
    
    /**
     * @param loanDetails the loan details supplied.
     * @return the calculated monthly repayments.
     */
    double calculateMonthlyRepayment(LoanDetails loanDetails);

    /**
     * @param installments the installments that are scheduled.
     * @return total interest paid over all installments
     */
    double calculateTotalInterest(List<AmortisationInstallment> installments);

    /**
     * @param monthlyPayment How much is repaid per month.
     * @param numberOfRepayments number of months.
     * @return Total amount repaid.
     */
    double totalAmountPaid(double monthlyPayment, int numberOfRepayments);

    /**
     * 
     * @param val Pound value as double.
     * @return to 2 decimal places.
     */
    double toPoundsPence(double val);
}
