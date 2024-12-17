package com.inghubs.creditmodule.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.inghubs.creditmodule.core.entity")
@EnableJpaRepositories("com.inghubs.creditmodule.core.repository")
public class CreditModuleCaseConfig {
}
