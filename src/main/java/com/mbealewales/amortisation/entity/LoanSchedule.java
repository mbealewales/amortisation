package com.mbealewales.amortisation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Defines the table used for storing an Amortisation Schedule
 * @author Mike Beale
 */

@Entity
@Table(name="loan_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long  id;

    @OneToOne
    @JoinColumn(name="loan_schedule", referencedColumnName = "id")
    private LoanDetails loanDetails;

    @Column(name="monthly_repayment_amount", nullable = false)
    private double monthlyRepaymentAmount;

    @Column(name="total_interest_due", nullable = false)
    private double totalInterestDue;
    @Column(name="total_payments_due", nullable = false)
    private double totalPaymentsDue;
}
