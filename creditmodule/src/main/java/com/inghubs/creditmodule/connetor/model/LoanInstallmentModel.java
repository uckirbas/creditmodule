package com.inghubs.creditmodule.connetor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanInstallmentModel {


    private double amount;

    private double paidAmount;

    private LocalDate dueDate;

    private LocalDate paymentDate;

    private boolean isPaid;
}
