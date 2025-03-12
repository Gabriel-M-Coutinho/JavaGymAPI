package com.desert.gym.models.Installment;

import com.desert.gym.models.payment.Payment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    @NotNull(message = "O pagamento não pode ser nulo")
    private Payment payment;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O valor da parcela não pode ser nulo")
    @Positive(message = "O valor da parcela deve ser positivo")
    private BigDecimal amount;

    @Column(name = "due_date", nullable = false)
    @NotNull(message = "A data de vencimento não pode ser nula")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "O status da parcela não pode ser nulo")
    private InstallmentStatus status;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public InstallmentStatus getStatus() {
        return status;
    }

    public void setStatus(InstallmentStatus status) {
        this.status = status;
    }
}
