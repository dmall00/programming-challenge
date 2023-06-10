package de.exxcellent.challenge.fileio;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import de.exxcellent.challenge.data.DataContainer;
import de.exxcellent.challenge.data.Row;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * specific implementation of a csv reader with the opencsv dependency
 */
public class CsvReader extends DataReader {

    private final String filePath;
    private final char delimiter;

    public CsvReader(String filePath, char delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    @Override
    public DataContainer getDataContainer() throws IOException, CsvValidationException {
        List<Row> rows = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(this.delimiter)
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(csvParser)
                .build();

        // Read the headers from the CSV
        String[] values = csvReader.readNext();
        if (values != null) {
            headers = Arrays.asList(values);
        }

        // Read Rows and put them in Map together with a header
        while ((values = csvReader.readNext()) != null) {
            Row row = new Row();
            for (int i = 0; i < headers.size(); i++) {
                row.putValue(headers.get(i), values[i]);
            }
            rows.add(row);
        }
        return new DataContainer(rows);
    }
}
