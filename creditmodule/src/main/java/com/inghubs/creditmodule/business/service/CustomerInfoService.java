package com.inghubs.creditmodule.business.service;

import static java.lang.String.format;

import com.inghubs.creditmodule.core.entity.CustomerEntity;
import com.inghubs.creditmodule.core.exception.ElementNotFoundException;
import com.inghubs.creditmodule.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerInfoService {

    private final CustomerRepository customerRepository;

    public CustomerEntity getCustomer(Long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new ElementNotFoundException(format("Customer was Not Found for Id: %s",
                                customerId))
                );
    }
}
