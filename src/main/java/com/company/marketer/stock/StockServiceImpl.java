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

    @Override
    public void save(List<Stock> stocks) {

        stocks.forEach(stock -> {

            boolean exists = stockRepository.existsById(stock.getTimestamp())
                    .blockOptional()
                    .orElse(false);
            if (!exists) {
                stockRepository.save(stock);
            }
        });
    }

    @Override
    public Mono<Stock> findByIdAndName(Integer id, String name) {

        return stockRepository.findByIdAndName(id, name);
    }
}
