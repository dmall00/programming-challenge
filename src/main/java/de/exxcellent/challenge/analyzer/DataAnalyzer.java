package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.DataContainer;

import java.util.Map;

public abstract class DataAnalyzer<T> {
    protected abstract T extractElement(Map<String, String> row);

    protected abstract int calculateDifference(int value1, int value2);

    public T findElementWithSmallestDifference(DataContainer container, String value1Key, String value2Key) {
        T elementWithSmallestDifference = null;
        int smallestDifference = Integer.MAX_VALUE;

        for (Map<String, String> row : container.getRows()) {
            T element = extractElement(row);
            int value1 = Integer.parseInt(row.get(value1Key));
            int value2 = Integer.parseInt(row.get(value2Key));
            int difference = calculateDifference(value1, value2);

            if (difference < smallestDifference) {
                smallestDifference = difference;
                elementWithSmallestDifference = element;
            }
        }

        return elementWithSmallestDifference;
    }
}
