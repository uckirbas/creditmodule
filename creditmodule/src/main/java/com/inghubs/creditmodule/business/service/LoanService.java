package com.inghubs.creditmodule.business.service;

import static com.inghubs.creditmodule.business.constant.LoanLogic.MAXIMUM_INSTALLMENT_PAYABLE;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import com.inghubs.creditmodule.business.mapper.LoanInstallmentMapper;
import com.inghubs.creditmodule.business.mapper.LoanMapper;
import com.inghubs.creditmodule.core.entity.LoanEntity;
import com.inghubs.creditmodule.core.entity.LoanInstallmentEntity;
import com.inghubs.creditmodule.core.exception.ElementNotFoundException;
import com.inghubs.creditmodule.core.repository.LoanRepository;
import com.inghubs.creditmodule.web.request.CreateLoan;
import com.inghubs.creditmodule.web.request.PayLoan;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final CustomerLoanService customerLoanService;
    private final CustomerInfoService customerInfoService;
    private final LoanInfoService loanInfoService;
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final LoanInstallmentMapper loanInstallmentMapper;

    public LoanEntity createLoan(CreateLoan createLoan) {

        var customer =
                customerInfoService
                        .getCustomer(createLoan.getCustomerId());

        customerLoanService
                .validateCustomerLoan(customer, createLoan);


        customerLoanService
                .withDrawCreditLimit(customer, createLoan);

        return loanRepository.save(
                loanMapper.toLoanEntity(
                        createLoan,
                        customer,
                        toLoanInstallments(createLoan))
        );

    }

    public LoanEntity payLoan(PayLoan payLoan) {

        var loan = loanInfoService.getLoan(payLoan.getLoanId());
        var loanInstallments =
                loan.getInstalments().stream()
                        .filter(not(LoanInstallmentEntity::isPaid))
                        .collect(toList());

        var affordableInstallment =
                affordableInstallment(payLoan.getAmount(),
                        loanInstallments
                                .stream()
                                .findFirst()
                                .map(LoanInstallmentEntity::getAmount)
                                .orElseThrow(
                                        () -> new ElementNotFoundException("Loan Installment Not Found to Pay")));

        range(0, affordableInstallment)
                .mapToObj(loanInstallments::get)
                .forEach(loanInstallment -> {

                    loanInstallment.setPaid(true);
                    loanInstallment.setPaymentDate(LocalDate.now());
                    loanInstallment.setPaidAmount(payLoan.getAmount());

                    if (checkLoanPaid(affordableInstallment, loanInstallments.size())) {
                        loan.setPaid(true);
                    }

                    loanRepository.save(loan);
                });

        return loan;
    }

    private boolean checkLoanPaid(int affordableInstallment, int unpaidLoanInstallments) {
        return affordableInstallment >= unpaidLoanInstallments;
    }

    private int affordableInstallment(double payAmount, double installmentAmount) {
        var affordableInstallment = MAXIMUM_INSTALLMENT_PAYABLE;
        var payable = (int) (payAmount / installmentAmount);
        if (affordableInstallment > payable) {
            affordableInstallment = payable;
        }
        return affordableInstallment;
    }

    private List<LoanInstallmentEntity> toLoanInstallments(CreateLoan createLoan) {
        var firstDayOfNextMonth = LocalDate.now().with(firstDayOfNextMonth());
        return range(0, createLoan.getInstallment().getLoanInstallmentValue())
                .mapToObj(firstDayOfNextMonth::plusMonths)
                .map(dueDate ->
                        loanInstallmentMapper
                                .toLoanInstallment(createLoan, dueDate))
                .collect(toList());
    }

}
