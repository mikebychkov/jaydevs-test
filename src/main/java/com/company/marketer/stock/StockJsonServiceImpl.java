package com.company.marketer.stock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockJsonServiceImpl implements StockJsonService {

    private final ObjectMapper objectMapper;
    @Override
    public List<Stock> getStocks() {

        JsonNode json;
        try {
            json = objectMapper.readValue(new File("AAPL.json"), JsonNode.class);
        } catch (Exception e) {

            log.error("STOCK LOADING ERROR", e);

            return List.of();
        }

        JsonNode parentObj = json.get("chart").get("result").get(0);

        JsonNode timestamp = parentObj.get("timestamp");
        JsonNode name = parentObj.get("meta").get("exchangeName");

        JsonNode qouteObj = parentObj.get("indicators").get("quote").get(0);
        JsonNode open = qouteObj.get("open");
        JsonNode close = qouteObj.get("close");
        JsonNode high = qouteObj.get("high");
        JsonNode low = qouteObj.get("low");
        JsonNode volume = qouteObj.get("volume");

        List<Integer> timestampList = objectMapper.convertValue(timestamp, new TypeReference<List<Integer>>() {});
        List<Double> openList = objectMapper.convertValue(open, new TypeReference<List<Double>>() {});
        List<Double> closeList = objectMapper.convertValue(close, new TypeReference<List<Double>>() {});
        List<Double> highList = objectMapper.convertValue(high, new TypeReference<List<Double>>() {});
        List<Double> lowList = objectMapper.convertValue(low, new TypeReference<List<Double>>() {});
        List<Double> volumeList = objectMapper.convertValue(volume, new TypeReference<List<Double>>() {});

        List<Stock> rsl = new LinkedList<>();

        for (int i = 0; i < timestampList.size(); i++) {
            rsl.add(
                    new Stock(timestampList.get(i),
                            openList.get(i),
                            closeList.get(i),
                            highList.get(i),
                            lowList.get(i),
                            volumeList.get(i),
                            name.toString())
            );
        }

        return rsl;
    }
}
