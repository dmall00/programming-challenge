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


public class CsvReader extends DataReader {

    private final String filePath;
    private final char delimeter;

    public CsvReader(String filePath, char delimiter) {
        this.filePath = filePath;
        this.delimeter = delimiter;
    }

    @Override
    public DataContainer getDataContainer() throws IOException, CsvValidationException {
        List<Row> rows = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(this.delimeter)
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(csvParser)
                .build();

        String[] values = csvReader.readNext(); // Read the headers from the CSV
        if (values != null) {
            headers = Arrays.asList(values);
        }

        while ((values = csvReader.readNext()) != null) {   // Read Rows and put them in Map
            Row row = new Row();
            for (int i = 0; i < headers.size(); i++) {
                row.putValue(headers.get(i), values[i]);
            }
            rows.add(row);
        }


        return new DataContainer(rows, headers);
    }
}
