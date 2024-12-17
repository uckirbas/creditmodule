package com.inghubs.creditmodule.factory;

import static com.inghubs.creditmodule.business.constant.LoanInstallmentType.LOAN_INSTALLMENT_6;

import com.inghubs.creditmodule.core.entity.LoanEntity;
import com.inghubs.creditmodule.core.entity.LoanInstallmentEntity;
import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {


    public static LoanEntity loanEntity(Long loanId) {
        return LoanEntity.builder()
                .id(loanId)
                .loanAmount(120)
                .numberOfInstallment(LOAN_INSTALLMENT_6)
                .isPaid(false)
                .instalments(loanInstallments())
                .build();
    }

    public static List<LoanInstallmentEntity> loanInstallments() {
        var loanInstallments = new ArrayList<LoanInstallmentEntity>();
        loanInstallments.add(
                LoanInstallmentEntity.builder()
                        .id(1L)
                        .amount(90)
                        .isPaid(false)
                        .build()
        );
        loanInstallments.add(
                LoanInstallmentEntity.builder()
                        .id(2L)
                        .amount(90)
                        .isPaid(false)
                        .build()
        );
        loanInstallments.add(
                LoanInstallmentEntity.builder()
                        .id(3L)
                        .amount(90)
                        .isPaid(false)
                        .build()
        );
        loanInstallments.add(
                LoanInstallmentEntity.builder()
                        .id(4L)
                        .amount(90)
                        .isPaid(false)
                        .build()
        );
        loanInstallments.add(
                LoanInstallmentEntity.builder()
                        .id(5L)
                        .amount(90)
                        .isPaid(false)
                        .build()
        );

        loanInstallments.add(
                LoanInstallmentEntity.builder()
                        .id(6L)
                        .amount(90)
                        .isPaid(false)
                        .build()
        );

        return loanInstallments;


    }
}
