package com.inghubs.creditmodule.business.service;

import static java.lang.String.format;

import com.inghubs.creditmodule.core.entity.LoanEntity;
import com.inghubs.creditmodule.core.exception.ElementNotFoundException;
import com.inghubs.creditmodule.core.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanInfoService {

    private final LoanRepository loanRepository;

    public LoanEntity getLoan(Long loanId) {
        return loanRepository
                .findById(loanId)
                .orElseThrow(() ->
                        new ElementNotFoundException(format("Loan was not found for Loan Id: %s",
                                loanId))
                );
    }

    public LoanEntity getCustomerLoan(Long customerId) {
        return loanRepository
                .findByCustomerId(customerId)
                .orElseThrow(() ->
                        new ElementNotFoundException(format("Loan was not found for Customer Id: %s",
                                customerId))
                );
    }

}
