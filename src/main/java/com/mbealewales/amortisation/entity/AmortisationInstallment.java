package com.mbealewales.amortisation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@EqualsAndHashCode
@ToString
public class AmortisationInstallment implements Comparable<AmortisationInstallment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @EqualsAndHashCode.Exclude
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

    @ManyToOne
    @JoinColumn(name="loan_schedule", referencedColumnName = "id")
    private LoanSchedule loanSchedule;

    @Override
    public int compareTo(AmortisationInstallment other) {
        return this.period - other.period;
    }
}
