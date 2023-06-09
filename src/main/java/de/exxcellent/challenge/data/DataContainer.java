package de.exxcellent.challenge.data;

import java.util.List;
import java.util.Map;

/**
 * Container Class to hold tabular like data
 */
public class DataContainer {

    private final List<Map<String, String>> rows;

    private final List<String> headers;

    public DataContainer(List<Map<String, String>> rows, List<String> headers) {
        this.rows = rows;
        this.headers = headers;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

}
