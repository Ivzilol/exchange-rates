package com.ivzilol.kafka.model;

import java.math.BigDecimal;
import java.util.Map;

public record ExRatesDTO(String currency, Map<String, BigDecimal> rates) {


}
