package com.inghubs.creditmodule.business.service;

import com.inghubs.creditmodule.core.entity.CustomerEntity;
import com.inghubs.creditmodule.core.exception.BusinessViolationException;
import com.inghubs.creditmodule.core.repository.CustomerRepository;
import com.inghubs.creditmodule.web.request.CreateLoan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerLoanService {

    private final CustomerRepository customerRepository;

    public void validateCustomerLoan(CustomerEntity customer, CreateLoan createLoan) {
        if (!checkLoanLimit(customer, createLoan)) {
            throw new BusinessViolationException("Insufficient Customer Loan Limit");
        }
    }

    private boolean checkLoanLimit(CustomerEntity customer, CreateLoan createLoan) {
        return (customer.getCreditLimit() - customer.getUsedCreditLimit() >= createLoan.getAmount());
    }


    public CustomerEntity withDrawCreditLimit(CustomerEntity customer, CreateLoan createLoan) {
        double value = customer.getUsedCreditLimit() + createLoan.getAmount();
        customer.setUsedCreditLimit(value);
        return customerRepository.save(customer);
    }
}
