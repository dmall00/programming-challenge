package de.exxcellent.challenge.fileio;

import de.exxcellent.challenge.data.DataContainer;

import java.io.File;
import java.io.IOException;

/**
 * Provides a DataReader for different formats like CSV
 */
public abstract class DataReader {

    public abstract DataContainer getDataContainer() throws IOException;

    public static DataContainer readIntoDataContainer(String filePath) throws IOException {

        String fileExenstion = getFileExtension(filePath);

        switch(fileExenstion){
            case "csv":
                return new CsvReader(filePath).getDataContainer();
            // case "json": return new DataContainer(new JsonReader(fileExtension));
            default:
                throw new IOException("No DataReader for " + fileExenstion + " found.");
        }
    }

    private static String getFileExtension(String filePath) throws IOException {
        /**
         * Checks if file exists and returns file extension
         * @param filePath
         */

        if (new File(filePath).exists()) {
            throw new IOException("File not found.");
        }

        String extension = "";
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex > 0) {
            extension = filePath.substring(lastDotIndex + 1);
        }
        return extension.toLowerCase();
    }

}
