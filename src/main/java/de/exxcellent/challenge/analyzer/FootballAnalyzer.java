package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.Row;

/**
 * specific implementation for the football problem
 */
public class FootballAnalyzer extends DataAnalyzer<String> {

    @Override
    protected String extractElement(Row row) {
        return row.getValue("Team");
    }

    @Override
    protected int calculateDifference(int value1, int value2) {
        return Math.abs(value1 - value2);
    }
}
