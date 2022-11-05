package com.alok.loans.service;

import com.alok.loans.entity.Loans;

import java.util.List;

public interface LoansService {
    List<Loans> findByCustomerId(int customerId);
}
