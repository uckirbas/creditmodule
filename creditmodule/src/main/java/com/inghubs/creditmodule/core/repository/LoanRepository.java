package com.inghubs.creditmodule.core.repository;

import com.inghubs.creditmodule.core.entity.LoanEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    Optional<LoanEntity> findByCustomerId(Long customerId);
}
