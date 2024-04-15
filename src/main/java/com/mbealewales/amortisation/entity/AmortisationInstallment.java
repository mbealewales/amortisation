package com.mbealewales.amortisation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the table used for storing an Amortisation Payment.
 * @author Mike Beale
 */
@Entity
@Table(name="amortisation_installment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmortisationInstallment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="period")
    private int period;
    @Column(name="payment")
    private double payment;
    @Column(name="principal")
    private double principal;
    @Column(name="interest")
    private double interest;
    @Column(name="balance")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="loan_schedule", referencedColumnName = "id")
    private LoanSchedule loanSchedule;
}
