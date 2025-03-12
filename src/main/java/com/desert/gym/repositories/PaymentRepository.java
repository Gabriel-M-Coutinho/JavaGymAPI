package com.desert.gym.repositories;

import com.desert.gym.models.client.Client;
import com.desert.gym.models.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    List<Payment> findByClientId(int clientId);

}
