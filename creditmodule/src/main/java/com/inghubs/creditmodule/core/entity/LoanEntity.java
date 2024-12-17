package com.inghubs.creditmodule.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.inghubs.creditmodule.business.constant.LoanInstallmentType;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private CustomerEntity customer;

    @NotNull
    @Positive
    private double loanAmount;

    @Enumerated(EnumType.STRING)
    private LoanInstallmentType numberOfInstallment;

    @CreationTimestamp
    private LocalDateTime createDate;

    @NotNull
    private boolean isPaid = false;

    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "loan_id"
    )
    private List<LoanInstallmentEntity> instalments;

}
