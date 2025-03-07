package com.desert.gym.repositories;

import com.desert.gym.models.client.Client;
import com.desert.gym.models.client.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    // Método personalizado para buscar clientes por nome
    List<Client> findByName(String name);

    List<Client> findByNameContainingIgnoreCase(String name);

    // Método personalizado para buscar clientes por status
    List<Client> findByStatus(ClientStatus status);

    // Método personalizado para buscar um cliente por email
    Optional<Client> findByEmail(String email);
}
