package com.desert.gym.repositories;

import com.desert.gym.models.Installment.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment,Integer> {
}
