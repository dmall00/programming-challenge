package de.exxcellent.challenge.fileio;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import de.exxcellent.challenge.data.DataContainer;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CsvReader extends DataReader {

    private final String filePath;
    private final String delimeter;

    public CsvReader(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimeter = delimiter;
    }

    @Override
    public DataContainer getDataContainer() {
        List<Map<String, String>> rows = new ArrayList<>();
        List<String> headers = new ArrayList<>();


        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values = csvReader.readNext(); // Read the headers from the CSV
            if (values != null) {
                headers = Arrays.asList(values);
            }

            while ((values = csvReader.readNext()) != null) {   // Read Rows and put them in Map
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    row.put(headers.get(i), values[i]);
                }
                rows.add(row);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return new DataContainer(rows, headers);
    }
}
