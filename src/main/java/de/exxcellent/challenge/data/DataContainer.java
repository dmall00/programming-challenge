package de.exxcellent.challenge.data;

import java.util.List;

/**
 * Container Class to hold tabular like data
 */
public class DataContainer {

    private final List<Row> rows;

    public DataContainer(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public static class DataContainerException extends RuntimeException {
        public DataContainerException(String message) {
            super(message);
        }
    }

    public static class KeyNotFoundException extends DataContainerException {
        public KeyNotFoundException() {
            super("Specified Key not found. No value can be returend.");
        }
    }
}
