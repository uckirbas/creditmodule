package com.inghubs.creditmodule.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inghubs.creditmodule.business.constant.LoanInstallmentType;
import com.inghubs.creditmodule.connetor.model.CustomerModel;
import com.inghubs.creditmodule.connetor.model.LoanInstallmentModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse {

    private CustomerModel customer;

    private double loanAmount;

    private LoanInstallmentType numberOfInstallment;

    private List<LoanInstallmentModel> loanInstallments;

    private Boolean isPaid;
}
