package com.mbealewales.amortisation.repository;

import org.springframework.data.repository.CrudRepository;

import com.mbealewales.amortisation.entity.LoanSchedule;

public interface LoanScheduleRepository extends CrudRepository<LoanSchedule, Long> {
    
}
