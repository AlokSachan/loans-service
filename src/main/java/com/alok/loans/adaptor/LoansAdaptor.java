package com.alok.loans.adaptor;

import com.alok.loans.entity.Loans;
import com.alok.loans.model.LoansDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LoansAdaptor {

    @Autowired
    private Environment environment;

    public List<LoansDto> adapt(List<Loans> loans) {
        return Optional.ofNullable(loans)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(input-> convertResponseIntoDto(input))
                .collect(Collectors.toList());
    }

    private LoansDto convertResponseIntoDto(Loans loans) {
        return LoansDto.builder()
                .customerId(loans.getCustomerId())
                .loanNumber(loans.getLoanNumber())
                .startDt(loans.getStartDt())
                .loanType(loans.getLoanType())
                .totalLoan(loans.getTotalLoan())
                .amountPaid(loans.getAmountPaid())
                .outstandingAmount(loans.getOutstandingAmount())
                .createDt(loans.getCreateDt())
                .port(Integer.parseInt(environment.getProperty("local.server.port"))).build();
    }
}
