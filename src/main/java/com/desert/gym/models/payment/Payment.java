package com.desert.gym.models.payment;

import com.desert.gym.models.Plan;
import com.desert.gym.models.client.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull(message = "O cliente não pode ser nulo")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @NotNull(message = "O plano não pode ser nulo")
    private Plan plan;

    @Column(name = "processed_at", nullable = false)
    @NotNull(message = "A data do pagamento não pode ser nula")
    private LocalDateTime processedAt;

    @Column(name = "installments", nullable = false)
    @NotNull(message = "O número de parcelas não pode ser nulo")
    @Positive(message = "O número de parcelas deve ser positivo")
    private int installments;

    @Column(name = "installment_value", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O valor da parcela não pode ser nulo")
    @Positive(message = "O valor da parcela deve ser positivo")
    private BigDecimal installmentValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 50)
    @NotNull(message = "O método de pagamento não pode ser nulo")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "O status do pagamento não pode ser nulo")
    private PaymentStatus status;

    @Column(length = 255)
    private String description;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
        calculateInstallmentValue(); // Recalcula o valor da parcela ao definir o plano
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
        calculateInstallmentValue(); // Recalcula o valor da parcela ao definir o número de parcelas
    }

    public BigDecimal getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(BigDecimal installmentValue) {
        this.installmentValue = installmentValue;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método para calcular o valor da parcela
    private void calculateInstallmentValue() {
        if (plan != null && installments > 0) {
            this.installmentValue = plan.getPrice().divide(BigDecimal.valueOf(installments), 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    @PrePersist
    protected void onCreate() {
        if (processedAt == null) {
            processedAt = LocalDateTime.now();
        }
        calculateInstallmentValue(); // Garante que o valor da parcela seja calculado antes de persistir
    }
}