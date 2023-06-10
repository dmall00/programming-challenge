package de.exxcellent.challenge;

import com.opencsv.exceptions.CsvValidationException;
import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.analyzer.FootballAnalyzer;
import de.exxcellent.challenge.analyzer.WeatherAnalyzer;
import de.exxcellent.challenge.data.DataContainer;
import de.exxcellent.challenge.fileio.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Example JUnit 5 test case.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    public static final String WEATHER_PATH = "src/main/resources/de/exxcellent/challenge/weather.csv";
    public static final String FOOTBALL_PATH = "src/main/resources/de/exxcellent/challenge/football.csv";
    public static final String JSON_PATH = "src/test/java/resources/de/exxcellent/challenge/no_reader_test.json";
    private static final int DAY_WITH_SMALLEST_SPREAD = 14;
    private static final String TEAM_WITH_SMALLEST_SPREAD = "Aston_Villa";

    /**
     * test overall weather challenge
     *
     * @throws IOException
     */
    @Test
    void weatherTest() throws IOException, CsvValidationException {
        DataContainer weatherContainer = DataReader.readIntoDataContainer(WEATHER_PATH);
        DataAnalyzer<Integer> weatherAnalyzer = new WeatherAnalyzer();
        int dayWithSmallestTempSpread = weatherAnalyzer.findElementWithSmallestDifference(weatherContainer,
                "MxT", "MnT");
        Assertions.assertEquals(DAY_WITH_SMALLEST_SPREAD, dayWithSmallestTempSpread, "Wrong day with smalles spread");
    }

    /**
     * test overall football challenge
     *
     * @throws IOException
     */
    @Test
    void footballTest() throws IOException, CsvValidationException {
        DataContainer footballContainer = DataReader.readIntoDataContainer(FOOTBALL_PATH);
        DataAnalyzer<String> footballAnalyzer = new FootballAnalyzer();
        String teamWithSmallestGoalSpread = footballAnalyzer.findElementWithSmallestDifference(footballContainer,
                "Goals", "Goals Allowed");
        Assertions.assertEquals(TEAM_WITH_SMALLEST_SPREAD, teamWithSmallestGoalSpread, "Wrong Team with smallest spread");
    }

    /**
     * test exception handling when file isnt found or there is no Reader for file format
     */
    @Test
    void fileExceptionTests() {
        // Test IOException if file isnt found
        assertThrows(FileNotFoundException.class, () -> {
            DataContainer weatherContainer = DataReader.readIntoDataContainer("wrong path");
        }, "FileNotFoundException not triggered");

        // Test DataReaderNotImplementedException if specific reader isnt implemented
        assertThrows(DataReader.DataReaderNotImplementedException.class, () -> {
            DataContainer weatherContainer = DataReader.readIntoDataContainer(JSON_PATH);
        }, "DataReaderNotImplementedException not triggered.");
    }


    /**
     * test exception handling when a key is passed that doesnt exist or when the parsing goes wrong
     */
    @Test
    void keyExceptionTests() {
        // test if a wrong key is passed where no value exists
        assertThrows(DataContainer.KeyNotFoundException.class, () -> {
            DataContainer footballContainer = DataReader.readIntoDataContainer(FOOTBALL_PATH);
            DataAnalyzer<String> footballAnalyzer = new FootballAnalyzer();
            String teamWithSmallestGoalSpread = footballAnalyzer.findElementWithSmallestDifference(footballContainer,
                    "That key doesnt exist", "Goals Allowed");
        }, "KeyNotFoundException not triggered.");

        // test if key cant be parsed into integer
        assertThrows(RuntimeException.class, () -> {
            DataContainer footballContainer = DataReader.readIntoDataContainer(FOOTBALL_PATH);
            DataAnalyzer<String> footballAnalyzer = new FootballAnalyzer();
            String teamWithSmallestGoalSpread = footballAnalyzer.findElementWithSmallestDifference(footballContainer,
                    "Tean", "Goals Allowed");
        }, "RuntimeException not triggered.");

    }

}