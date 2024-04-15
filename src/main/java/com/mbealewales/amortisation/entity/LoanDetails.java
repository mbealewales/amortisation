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

/**
 * Defines the table used for storing the initial loan detail parameters.
 * @author Mike Beale
 */
@Entity
@Table(name="loan_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long  id;
    @Column(name="asset_cost", nullable = false)
    private double assetCost;
    @Column(name="deposit", nullable = false)
    private double deposit ;
    @Column(name="interest_rate", nullable = false)
    private double yearlyInterestRate;
    @Column(name="monthly_repayments", nullable = false)
    private int monthlyRepayments;
    @Column(name="balloon_payment", nullable = true)
    private double balloonPayment = 0.0f;
}
