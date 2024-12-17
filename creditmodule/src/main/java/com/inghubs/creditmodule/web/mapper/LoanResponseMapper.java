package com.inghubs.creditmodule.web.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.inghubs.creditmodule.connetor.model.LoanInstallmentModel;
import com.inghubs.creditmodule.core.entity.LoanEntity;
import com.inghubs.creditmodule.core.entity.LoanInstallmentEntity;
import com.inghubs.creditmodule.web.response.LoanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = SPRING)
public abstract class LoanResponseMapper {
    @Mapping(target = "numberOfInstallment", source = "loan.numberOfInstallment")
    @Mapping(target = "loanInstallments", source = "loan.instalments")
    @Mapping(target = "isPaid", source = "loan", qualifiedByName = "toIsPaid")
    public abstract LoanResponse toLoanResponse(LoanEntity loan);

    @Mapping(target = "isPaid", source = "loanInstallment", qualifiedByName = "toIsPaid")
    public abstract LoanInstallmentModel toLoanInstallmentModel(LoanInstallmentEntity loanInstallment);

    @Named("toIsPaid")
    protected Boolean toIsPaid(LoanEntity loan) {
        return loan.isPaid();
    }

    @Named("toIsPaid")
    protected Boolean toIsPaid(LoanInstallmentEntity loanInstallment) {
        return loanInstallment.isPaid();
    }
}
