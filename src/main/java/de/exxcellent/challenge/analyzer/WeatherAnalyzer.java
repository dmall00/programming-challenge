package de.exxcellent.challenge.analyzer;

import java.util.Map;

public class WeatherAnalyzer extends DataAnalyzer<Integer> {

    @Override
    protected Integer extractElement(Map<String, String> row) {
        return Integer.parseInt(row.get("Day"));
    }

    @Override
    protected int calculateDifference(int value1, int value2) {
        return value1 - value2;
    }
}
