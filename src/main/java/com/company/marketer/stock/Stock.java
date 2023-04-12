package com.company.marketer.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
public class Stock {

    @PrimaryKey
    private Integer timestamp;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double volume;
    private String name;
}
