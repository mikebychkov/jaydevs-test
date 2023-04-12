package com.company.marketer.stock;

import reactor.core.publisher.Mono;

import java.util.List;

public interface StockService {

    void save(List<Stock> stocks);
    Mono<Stock> findByIdAndName(Integer id, String name);
}
