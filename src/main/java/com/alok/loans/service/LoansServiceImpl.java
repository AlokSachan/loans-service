package com.alok.loans.service;

import com.alok.loans.entity.Loans;
import com.alok.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoansServiceImpl implements LoansService {

    @Autowired
    private LoansRepository loansRepository;

    @Override
    public List<Loans> findByCustomerId(int customerId) {
       return loansRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }
}
