package com.alok.loans.controller;


import com.alok.loans.adaptor.LoansAdaptor;
import com.alok.loans.config.LoansServiceConfig;
import com.alok.loans.model.LoansDto;
import com.alok.loans.model.PropertiesDto;
import com.alok.loans.service.LoansService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class LoansController {

    @Autowired
    private LoansService loansService;

    @Autowired
    private LoansAdaptor loansAdaptor;

    @Autowired
    private LoansServiceConfig loansServiceConfig;

    @GetMapping("/myLoans/{customerId}")
    public ResponseEntity<List<LoansDto>> getLoansDetails(@PathVariable int customerId,
                                                          @RequestHeader("eazybank-correlation-id")  String correlationId) {
        log.info("correlationId {}", correlationId);
        return new ResponseEntity<>(loansAdaptor.adapt(loansService.findByCustomerId(customerId)), HttpStatus.OK);

    }

    @GetMapping("/myLoans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow= new ObjectMapper().writer().withDefaultPrettyPrinter();
        PropertiesDto properties= PropertiesDto.builder().msg(loansServiceConfig.getMsg())
                .buildVersion(loansServiceConfig.getBuildVersion()).mailDetails(loansServiceConfig.getMailDetails())
                .activeBranches(loansServiceConfig.getActiveBranches()).build();
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
