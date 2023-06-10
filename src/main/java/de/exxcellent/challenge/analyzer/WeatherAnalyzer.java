package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.Row;

/**
 * specific implementation for the weather problem
 */
public class WeatherAnalyzer extends DataAnalyzer<Integer> {

    @Override
    protected Integer extractElement(Row row) {
        return Integer.parseInt(row.getValue("Day"));
    }

    @Override
    protected int calculateDifference(int value1, int value2) {
        return value1 - value2;
    }
}
