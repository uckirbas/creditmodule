package com.inghubs.creditmodule.business.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.inghubs.creditmodule.core.entity.LoanInstallmentEntity;
import com.inghubs.creditmodule.web.request.CreateLoan;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = SPRING)
public abstract class LoanInstallmentMapper {

    @Mapping(target = "isPaid", constant = "false")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "amount", source = "createLoan", qualifiedByName = "toAmount")
    public abstract LoanInstallmentEntity toLoanInstallment(CreateLoan createLoan, LocalDate dueDate);

    @Named("toAmount")
    protected double toAmount(CreateLoan createLoan) {
        return (createLoan.getAmount() * (1 + createLoan.getInterestRate()))
                / createLoan.getInstallment().getLoanInstallmentValue();
    }

}
