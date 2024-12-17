package com.inghubs.creditmodule.config.initializer;

import com.inghubs.creditmodule.core.entity.CustomerEntity;
import com.inghubs.creditmodule.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("case")
public class DataLoader implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    public void run(ApplicationArguments args) {
        customerRepository.save(
                new CustomerEntity(1L,"firstName","firstSurname",500.000,34.000)
        );
    }
}
