package model;

import java.util.Map;

public record JsonStandardApiResponse(String base_code, Map<String, Double> conversion_rates) {}

