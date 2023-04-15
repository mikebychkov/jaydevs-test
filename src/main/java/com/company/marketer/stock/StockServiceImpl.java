package com.company.marketer.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    private boolean stockNotExists(Stock stock) {

        return !stockRepository.existsById(stock.getTimestamp())
                .blockOptional()
                .orElse(false);
    }

    @Override
    public void save(List<Stock> stocks) {

        List<Stock> filteredStocks = stocks.stream().filter(this::stockNotExists).toList();
        Flux<Stock> fluxStocks = Flux.fromIterable(filteredStocks);
        stockRepository.saveAll(fluxStocks);
    }

    @Override
    public Mono<Stock> findByIdAndName(Integer id, String name) {

        return stockRepository.findByIdAndName(id, name);
    }
}
