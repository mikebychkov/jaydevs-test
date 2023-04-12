package com.company.marketer.stock;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface StockRepository extends ReactiveCassandraRepository<Stock, Integer> {

    Mono<Stock> findByIdAndName(Integer id, String name);
}
