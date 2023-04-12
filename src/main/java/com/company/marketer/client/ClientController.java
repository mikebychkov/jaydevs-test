package com.company.marketer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

//    @PostConstruct
//    public void saveClients() {
//        List<Client> clients = new ArrayList<>();
//        clients.add(new Client(123, "John Doe", "Delaware", "jdoe@xyz.com", 31));
//        clients.add(new Client(324, "Adam Smith", "North Carolina", "asmith@xyz.com", 43));
//        clients.add(new Client(355, "Kevin Dunner", "Virginia", "kdunner@xyz.com", 24));
//        clients.add(new Client(643, "Mike Lauren", "New York", "mlauren@xyz.com", 41));
//        clientService.initializeClients(clients);
//    }

    @GetMapping("/list")
    public Flux<Client> getAllClients() {
        Flux<Client> clients = clientService.getAllClients();
        return clients;
    }

    @GetMapping("/{id}")
    public Mono<Client> getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/getByEmail/{email}")
    public Flux<Client> getClientsByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }
}
