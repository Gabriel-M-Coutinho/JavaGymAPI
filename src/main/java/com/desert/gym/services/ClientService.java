    package com.desert.gym.services;

    import com.desert.gym.dtos.client.ClientCreateDto;
    import com.desert.gym.dtos.client.ClientResponseDto;
    import com.desert.gym.dtos.client.ClientUpdateDto;
    import com.desert.gym.models.Plan;
    import com.desert.gym.models.client.Client;
    import com.desert.gym.models.client.ClientStatus;
    import com.desert.gym.repositories.ClientRepository;
    import com.desert.gym.repositories.PlanRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class ClientService {

        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        PlanRepository planRepository;

        public Client createClient(ClientCreateDto clientCreateDTO) {

            Client client = new Client();
            client.setName(clientCreateDTO.getName());
            client.setBirthDate(clientCreateDTO.getBirthDate());
            client.setPhone(clientCreateDTO.getPhone());
            client.setEmail(clientCreateDTO.getEmail());
            client.setAddress(clientCreateDTO.getAddress());
            client.setRegistrationDate(LocalDate.now());
            client.setStatus(ClientStatus.ACTIVE);

            return clientRepository.save(client);
        }


        // READ: Buscar um cliente pelo ID
        public Client getClientById(int clientId) throws Exception {
            Optional<Client> clientOptional = clientRepository.findById(clientId);

            if (clientOptional.isEmpty()) {
                throw new Exception("Cliente não encontrado com o ID: " + clientId);
            }

            return clientOptional.get();
        }

        // READ: Buscar todos os clientes
        public List<Client> getAllClients() {
            return clientRepository.findAll();
        }

        public Client updateClient(int clientId, ClientUpdateDto clientDetails) throws Exception {
            // Busca o cliente pelo ID
            Client client = getClientById(clientId);
            if (client == null) {
                throw new Exception("Cliente não encontrado com o ID: " + clientId);
            }

            // Atualiza apenas os campos fornecidos
            if (clientDetails.getName() != null) {
                client.setName(clientDetails.getName());
            }
            if (clientDetails.getBirthDate() != null) {
                client.setBirthDate(clientDetails.getBirthDate());
            }
            if (clientDetails.getPhone() != null) {
                client.setPhone(clientDetails.getPhone());
            }
            if (clientDetails.getEmail() != null) {
                client.setEmail(clientDetails.getEmail());
            }
            if (clientDetails.getAddress() != null) {
                client.setAddress(clientDetails.getAddress());
            }

            // Salva as alterações no banco de dados
            return clientRepository.save(client);
        }
        // DELETE: Deletar um cliente
        public void deleteClient(int clientId) throws Exception {
            Client client = getClientById(clientId);
            clientRepository.delete(client);
        }

        public List<Client> getClientByName(String name) {
            return clientRepository.findByNameContainingIgnoreCase(name);
        }


        public Client subscribeToPlan(int clientId, int planId) {

            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


            Plan plan = planRepository.findById(planId)
                    .orElseThrow(() -> new RuntimeException("Plano não encontrado"));


            client.subscribeToPlan(plan);


            return clientRepository.save(client);
        }


        public boolean isPlanActive(int clientId) {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            return client.isPlanActive();
        }


        public ClientResponseDto toClientResponseDto(Client client) {
            ClientResponseDto dto = new ClientResponseDto();
            dto.setId(client.getId());
            dto.setName(client.getName());
            dto.setBirthDate(client.getBirthDate());
            dto.setPhone(client.getPhone());
            dto.setEmail(client.getEmail());
            dto.setAddress(client.getAddress());
            dto.setRegistrationDate(client.getRegistrationDate());
            dto.setStatus(client.getStatus());
            dto.setPlan(client.getPlan());
            return dto;
        }
    }
