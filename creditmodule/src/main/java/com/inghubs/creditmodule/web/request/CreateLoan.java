package com.inghubs.creditmodule.web.request;

import com.inghubs.creditmodule.business.constant.LoanInstallmentType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLoan {

    @NotNull
    Long customerId;

    @NotNull
    double amount;

    @NotNull
    @DecimalMin(value = "0.1", message = "interest rate must be equal or greater than 0.1")
    @DecimalMax(value = "0.5", message = "interest rate must be equal or lower than 0.5")
    double interestRate;

    @NotNull
    LoanInstallmentType installment;


}
