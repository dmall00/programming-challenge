package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.DataContainer;
import de.exxcellent.challenge.data.Row;


/**
 * Base anstract class for smallest difference
 *
 * @param <T> return type of method
 */
public abstract class DataAnalyzer<T> {
    protected abstract T extractElement(Row row);

    protected abstract int calculateDifference(int value1, int value2);

    public T findElementWithSmallestDifference(DataContainer container, String value1Key, String value2Key) {
        T elementWithSmallestDifference = null;
        int smallestDifference = Integer.MAX_VALUE;

        for (Row row : container.getRows()) {
            T element = extractElement(row);
            int value1;
            int value2;
            try {
                // could fail
                value1 = Integer.parseInt(row.getValue(value1Key));
                value2 = Integer.parseInt(row.getValue(value2Key));
            } catch (DataContainer.KeyNotFoundException e) {
                throw new DataContainer.KeyNotFoundException();
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Can't parse values into Integer");
            }
            // calculate difference and compare
            int difference = calculateDifference(value1, value2);
            if (difference < smallestDifference) {
                smallestDifference = difference;
                elementWithSmallestDifference = element;
            }
        }

        return elementWithSmallestDifference;
    }
}
