package com.inghubs.creditmodule.web.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayLoan {

    @NotNull
    Long loanId;
    
    @NotNull
    double amount;

}
