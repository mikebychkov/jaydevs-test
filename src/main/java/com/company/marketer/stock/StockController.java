package com.company.marketer.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public Mono<Stock> findStock(@RequestParam("timestamp") Integer timestamp,
                                 @RequestParam("name") String name) {

        return stockService.findByIdAndName(timestamp, name);
    }
}
