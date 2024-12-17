package com.inghubs.creditmodule.service;

import static com.inghubs.creditmodule.business.constant.LoanLogic.MAXIMUM_INSTALLMENT_PAYABLE;
import static com.inghubs.creditmodule.factory.TestDataFactory.loanEntity;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inghubs.creditmodule.business.mapper.LoanInstallmentMapper;
import com.inghubs.creditmodule.business.mapper.LoanMapper;
import com.inghubs.creditmodule.business.service.CustomerInfoService;
import com.inghubs.creditmodule.business.service.CustomerLoanService;
import com.inghubs.creditmodule.business.service.LoanInfoService;
import com.inghubs.creditmodule.business.service.LoanService;
import com.inghubs.creditmodule.core.repository.LoanRepository;
import com.inghubs.creditmodule.web.request.PayLoan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {


    @Mock
    private CustomerLoanService customerLoanService;
    @Mock
    private CustomerInfoService customerInfoService;
    @Mock
    private LoanInfoService loanInfoService;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private LoanMapper loanMapper;
    @Mock
    private LoanInstallmentMapper loanInstallmentMapper;

    @InjectMocks
    private LoanService loanService;


    @Test
    void loan_payment_exceed_maximum_payable_month() {

        var loanId = 1L;
        var loanEntity = loanEntity(loanId);

        var payLoan = PayLoan.builder().loanId(loanId).amount(9999999d).build();

        when(loanInfoService.getLoan(loanId)).thenReturn(loanEntity);

        loanService.payLoan(payLoan);

        verify(loanRepository, times(MAXIMUM_INSTALLMENT_PAYABLE)).save(loanEntity);
    }

    @Test
    void loan_payment_two_payment() {

        var loanId = 1L;
        var loanEntity = loanEntity(loanId);

        var payLoan = PayLoan.builder().loanId(loanId).amount(180).build();

        when(loanInfoService.getLoan(loanId)).thenReturn(loanEntity);

        loanService.payLoan(payLoan);

        verify(loanRepository, times(2)).save(loanEntity);
    }

    @Test
    void loan_payment_insufficient_payment() {
        var loanId = 1L;
        var loanEntity = loanEntity(loanId);

        var payLoan = PayLoan.builder().loanId(loanId).amount(10).build();

        when(loanInfoService.getLoan(loanId)).thenReturn(loanEntity);

        loanService.payLoan(payLoan);

        verify(loanRepository, never()).save(loanEntity);
    }


}
