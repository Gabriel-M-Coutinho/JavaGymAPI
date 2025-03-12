package com.desert.gym.services;

import com.desert.gym.dtos.plan.PlanCreateDto;
import com.desert.gym.models.Installment.Installment;
import com.desert.gym.models.Installment.InstallmentStatus;
import com.desert.gym.models.Plan;
import com.desert.gym.models.client.Client;
import com.desert.gym.models.payment.Payment;
import com.desert.gym.models.payment.PaymentMethod;
import com.desert.gym.models.payment.PaymentStatus;
import com.desert.gym.repositories.InstallmentRepository;
import com.desert.gym.repositories.PaymentRepository;
import com.desert.gym.repositories.PlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PlanService planService;

    @Transactional
    public Payment createPayment(int clientId, int planId, int installments, PaymentMethod paymentMethod) throws Exception {

        Client client = clientService.getClientById(clientId);
        Plan plan = planService.getPlan(planId);

        Payment payment = new Payment();
        payment.setClient(client);
        payment.setPlan(plan);
        payment.setInstallments(installments);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(PaymentStatus.PENDING);


        createInstallments(payment);

        return paymentRepository.save(payment);
    }

    private void createInstallments(Payment payment) {
        BigDecimal installmentValue = payment.getInstallmentValue();
        LocalDate dueDate = LocalDate.now();
        List<Installment> installments = new ArrayList<>();

        for (int i = 0; i < payment.getInstallments(); i++) {
            Installment installment = new Installment();
            installment.setPayment(payment);
            installment.setAmount(installmentValue);
            installment.setDueDate(dueDate.plusMonths(i));
            installment.setStatus(InstallmentStatus.PENDING);
            installments.add(installment);
        }

        installmentRepository.saveAll(installments);
    }

    @Transactional
    public Installment confirmInstallmentPayment(int installmentId) {

        Installment installment = installmentRepository.findById(installmentId)
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));


        installment.setStatus(InstallmentStatus.PAID);
        installmentRepository.save(installment);


        Payment payment = installment.getPayment();


        boolean allInstallmentsPaid = payment.getInstallmentsList().stream()
                .allMatch(i -> i.getStatus() == InstallmentStatus.PAID);


        if (allInstallmentsPaid) {
            payment.setStatus(PaymentStatus.PAID);
            paymentRepository.save(payment);
        }

        return installment;
    }

    public Payment getPaymentById(Integer id){
      Optional<Payment> payment =  paymentRepository.findById(id);
      return  payment.get();
    }

    public List<Payment> getPaymentsByClientId(int clientId) {
        return paymentRepository.findByClientId(clientId);
    }
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }


}
