package com.alok.loans.controller;


import com.alok.loans.adaptor.LoansAdaptor;
import com.alok.loans.model.LoansDto;
import com.alok.loans.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansService loansService;

    @Autowired
    private LoansAdaptor loansAdaptor;

    @GetMapping("/myLoans/{customerId}")
    public ResponseEntity<List<LoansDto>> getLoansDetails(@PathVariable int customerId) {
        return new ResponseEntity<>(loansAdaptor.adapt(loansService.findByCustomerId(customerId)), HttpStatus.OK);

    }
}
