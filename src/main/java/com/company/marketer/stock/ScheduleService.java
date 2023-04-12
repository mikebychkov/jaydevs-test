package com.company.marketer.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduleService {

    private final StockJsonService stockJsonService;
    private final StockService stockService;

    @Scheduled(cron = "${cron.stock}")
    public void initStockLoading() {

        log.info("CRON STOCK LOADING INIT...");

        stockService.save(stockJsonService.getStocks());
    }
}
