package com.inghubs.creditmodule.business.constant;


public enum LoanInstallmentType {

    LOAN_INSTALLMENT_6(6),
    LOAN_INSTALLMENT_9(9),
    LOAN_INSTALLMENT_12(12),
    LOAN_INSTALLMENT_24(24),
    UNRECOGNIZED(0);


    private final int value;

    public final int getLoanInstallmentValue() {
        return this.value;
    }

    LoanInstallmentType(int value) {
        this.value = value;
    }

}
