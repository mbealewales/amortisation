package com.mbealewales.amortisation.repository;

import org.springframework.data.repository.CrudRepository;

import com.mbealewales.amortisation.entity.LoanDetails;

public interface LoanDetailsRepository extends CrudRepository<LoanDetails, Long> {
    
}
