package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.analyzer.FootballAnalyzer;
import de.exxcellent.challenge.analyzer.WeatherAnalyzer;
import de.exxcellent.challenge.data.DataContainer;
import de.exxcellent.challenge.fileio.DataReader;

import java.io.IOException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static final String WEATHER_PATH = "src/main/resources/de/exxcellent/challenge/weather.csv";
    public static final String FOOTBALL_PATH = "src/main/resources/de/exxcellent/challenge/football.csv";
    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {


        try {
            DataContainer weatherContainer = DataReader.readIntoDataContainer(WEATHER_PATH);
            DataAnalyzer<Integer> weatherAnalyzer = new WeatherAnalyzer();
            int dayWithSmallestTempSpread = weatherAnalyzer.findElementWithSmallestDifference(weatherContainer,
                    "MxT", "MnT");
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);


            DataContainer footballContainer = DataReader.readIntoDataContainer(FOOTBALL_PATH);
            DataAnalyzer<String> footballAnalyzer = new FootballAnalyzer();
            String teamWithSmallestGoalSpread = footballAnalyzer.findElementWithSmallestDifference(footballContainer,
                    "Goals", "Goals Allowed");

            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
