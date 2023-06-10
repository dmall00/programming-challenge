package de.exxcellent.challenge.analyzer;

import java.util.Map;

public class FootballAnalyzer extends DataAnalyzer<String> {

    @Override
    protected String extractElement(Map<String, String> row) {
        return row.get("Team");
    }

    @Override
    protected int calculateDifference(int value1, int value2) {
        return Math.abs(value1 - value2);
    }
}
