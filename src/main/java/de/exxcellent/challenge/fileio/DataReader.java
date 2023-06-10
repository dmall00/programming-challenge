package de.exxcellent.challenge.fileio;

import com.opencsv.exceptions.CsvValidationException;
import de.exxcellent.challenge.data.DataContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * abstract class for a DataReader for different formats like CSV
 * can be extended by implementing other reader like for json, etc
 */
public abstract class DataReader {

    public static DataContainer readIntoDataContainer(String filePath) throws IOException, CsvValidationException {

        String fileExenstion = getFileExtension(filePath);

        switch (fileExenstion) {
            case "csv":
                return new CsvReader(filePath, ',').getDataContainer();
            // case "json": return new DataContainer(new JsonReader(fileExtension));
            default:
                throw new DataReaderNotImplementedException();
        }
    }

    /**
     * Checks if file exists and returns file extension
     */
    private static String getFileExtension(String filePath) throws FileNotFoundException {

        if (!new File(filePath).exists()) {
            throw new FileNotFoundException("File not found.");
        }

        String extension = "";
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex > 0) {
            extension = filePath.substring(lastDotIndex + 1);
        }
        return extension.toLowerCase();
    }

    public abstract DataContainer getDataContainer() throws IOException, CsvValidationException;

    private static class DataReaderException extends IOException {
        public DataReaderException(String message) {
            super(message);
        }
    }

    public static class DataReaderNotImplementedException extends DataReaderException {
        public DataReaderNotImplementedException() {
            super("No datareader for this type of dataformat implemented.");
        }
    }


}
