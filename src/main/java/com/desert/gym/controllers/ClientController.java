package com.desert.gym.controllers;

import com.desert.gym.dtos.ApiResponse;
import com.desert.gym.dtos.client.ClientCreateDto;
import com.desert.gym.dtos.client.ClientResponseDto;
import com.desert.gym.dtos.client.ClientUpdateDto;
import com.desert.gym.models.client.Client;

import com.desert.gym.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDto>> createClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        Client client = clientService.createClient(clientCreateDto);
        ClientResponseDto clientResponseDto = clientService.toClientResponseDto(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<ClientResponseDto>(
                        HttpStatus.CREATED.value(),
                        "user created.",
                        clientResponseDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> getClientById(@PathVariable Integer id) throws Exception {
        Client client = clientService.getClientById(id);
        ClientResponseDto clientResponseDto = clientService.toClientResponseDto(client);
        return ResponseEntity.ok(new ApiResponse<ClientResponseDto>(201,"user created." ,clientResponseDto));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<List<Client>>> getClientsByName(@RequestBody String name){
        List<Client> client = clientService.getClientByName(name);
        return  ResponseEntity.ok().body(new ApiResponse<List<Client>>(HttpStatus.OK.value(), "Clientes encontrados com sucesso.",client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> updateClient(
            @PathVariable int id,
            @Valid @RequestBody ClientUpdateDto clientDetails
    ) {
        try {
            Client updatedClient = clientService.updateClient(id, clientDetails);
            return ResponseEntity.ok(
                    new ApiResponse<>(200, "Cliente atualizado com sucesso.", updatedClient)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(400, e.getMessage(), null)
            );
        }
    }

}