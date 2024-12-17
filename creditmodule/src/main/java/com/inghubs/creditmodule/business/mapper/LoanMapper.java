package com.inghubs.creditmodule.business.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.inghubs.creditmodule.core.entity.CustomerEntity;
import com.inghubs.creditmodule.core.entity.LoanEntity;
import com.inghubs.creditmodule.core.entity.LoanInstallmentEntity;
import com.inghubs.creditmodule.web.request.CreateLoan;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
public interface LoanMapper {

    @Mapping(target = "numberOfInstallment", source = "createLoan.installment")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "loanAmount", source = "createLoan.amount")
    @Mapping(target = "isPaid", constant = "false")
    @Mapping(target = "id", ignore = true)
    LoanEntity toLoanEntity(CreateLoan createLoan, CustomerEntity customer, List<LoanInstallmentEntity> instalments);

}
