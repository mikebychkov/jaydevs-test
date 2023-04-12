package com.company.marketer.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void initializeClients(List<Client> clients) {
        Flux<Client> savedClients = clientRepository.saveAll(clients);
        savedClients.subscribe();
    }

    public Flux<Client> getAllClients() {
        Flux<Client> clients = clientRepository.findAll();
        return clients;
    }

    public Flux<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Mono<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }
}
