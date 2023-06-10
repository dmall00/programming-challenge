package de.exxcellent.challenge.data;

import java.util.HashMap;
import java.util.Map;

/**
 * part of the DataContainer, important for exception handling
 */
public class Row {

    private final Map<String, String> data;

    public Row() {
        data = new HashMap<>();
    }

    public void putValue(String key, String value) {
        data.put(key, value);
    }


    public String getValue(String key) throws DataContainer.KeyNotFoundException {
        String value = data.get(key);
        if (value == null) {
            throw new DataContainer.KeyNotFoundException();
        } else {
            return value;
        }
    }

}
