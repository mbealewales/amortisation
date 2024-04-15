package com.mbealewales.amortisation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mbealewales.amortisation.entity.LoanSchedule;
import com.mbealewales.amortisation.service.AmortisationService;
import com.mbealewales.amortisation.entity.AmortisationInstallment;
import com.mbealewales.amortisation.entity.LoanDetails;


/**
 * Defines the REST API for Amortisation functionality.
 * @author Mike Beale
 */
@Controller
public class AmortisationController {

    @Autowired
    private AmortisationService amortisationService;

    @PostMapping("/amortisation/schedules")
    public ResponseEntity<LoanDetails> createAmortisationSchedule(@RequestBody LoanDetails loanDetails) {
        return new ResponseEntity<>(amortisationService.createAmortisationSchedule(loanDetails), HttpStatus.CREATED);
    }
    
    @GetMapping("/amortisation/schedules")
    public ResponseEntity<List<LoanSchedule>> getAmortisationSchedules() {
        return new ResponseEntity<>(amortisationService.getLoanSchedules(), HttpStatus.OK);
    }

    @GetMapping("/amortisation/schedules/{id}")
    public ResponseEntity<Pair<List<AmortisationInstallment>, LoanSchedule>> getAmortisationSchedules(@PathVariable Long id) {
        return new ResponseEntity<>(amortisationService.getAmortisationAndLoanSchedulesByLoanScheduleId(id), HttpStatus.OK);
    }
    
}