package com.company.marketer.client;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface ClientRepository extends ReactiveCassandraRepository<Client, Integer> {
    @AllowFiltering
    Flux<Client> findByEmail(String email);
}
