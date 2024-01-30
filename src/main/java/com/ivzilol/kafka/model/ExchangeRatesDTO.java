package com.ivzilol.kafka.model;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRatesDTO(String base,
                               long timestamp,
                               Map<String, BigDecimal> rates) {
}
