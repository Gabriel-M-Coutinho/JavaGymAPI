package com.desert.gym.controllers;

import com.desert.gym.models.Installment.Installment;
import com.desert.gym.models.Installment.InstallmentStatus;
import com.desert.gym.models.payment.Payment;
import com.desert.gym.models.payment.PaymentMethod;
import com.desert.gym.models.payment.PaymentStatus;
import com.desert.gym.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

/*
    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(
            @RequestParam int clientId,
            @RequestParam int planId,
            @RequestParam int installments,
            @RequestParam PaymentMethod paymentMethod) throws Exception {
        Payment payment = paymentService.createPayment(clientId, planId, installments, paymentMethod);
        return ResponseEntity.ok(payment);
    }


    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }


    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }


    @PutMapping("/{paymentId}/status")
    public ResponseEntity<Payment> updatePaymentStatus(
            @PathVariable int paymentId,
            @RequestParam PaymentStatus status) {
        Payment payment = paymentService.updatePaymentStatus(paymentId, status);
        return ResponseEntity.ok(payment);
    }


    @GetMapping("/{paymentId}/installments")
    public ResponseEntity<List<Installment>> getInstallmentsByPaymentId(@PathVariable int paymentId) {
        List<Installment> installments = paymentService.getInstallmentsByPaymentId(paymentId);
        return ResponseEntity.ok(installments);
    }


    @PutMapping("/installments/{installmentId}/status")
    public ResponseEntity<Installment> updateInstallmentStatus(
            @PathVariable int installmentId,
            @RequestParam InstallmentStatus status) {
        Installment installment = paymentService.updateInstallmentStatus(installmentId, status);
        return ResponseEntity.ok(installment);
    }

    @PostMapping("/installments/{installmentId}/confirm-payment")
    public ResponseEntity<Installment> confirmInstallmentPayment(@PathVariable int installmentId) {
        Installment installment = paymentService.confirmInstallmentPayment(installmentId);
        return ResponseEntity.ok(installment);
    }*/
}
