package com.inghubs.creditmodule.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.inghubs.creditmodule.business.service.LoanInfoService;
import com.inghubs.creditmodule.business.service.LoanService;
import com.inghubs.creditmodule.web.mapper.LoanResponseMapper;
import com.inghubs.creditmodule.web.request.CreateLoan;
import com.inghubs.creditmodule.web.request.PayLoan;
import com.inghubs.creditmodule.web.response.LoanResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "v1/loan",
        produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final LoanInfoService loanInfoService;
    private final LoanResponseMapper loanResponseMapper;


    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody CreateLoan createLoan) {
        return ResponseEntity.ok(loanResponseMapper
                .toLoanResponse(loanService.createLoan(createLoan)));
    }

    @PostMapping(value = "/pay", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanResponse> payLoan(@Valid @RequestBody PayLoan payLoan) {
        return ResponseEntity.ok(loanResponseMapper
                .toLoanResponse(loanService.payLoan(payLoan)));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable Long customerId) {
        return ResponseEntity.ok(loanResponseMapper
                .toLoanResponse(loanInfoService.getCustomerLoan(customerId)));
    }

    @GetMapping("/installment/{loanId}")
    public ResponseEntity<LoanResponse> getInstallments(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanResponseMapper
                .toLoanResponse(loanInfoService.getLoan(loanId)));
    }

}
