package de.exxcellent.challenge;

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

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {


        try {
            DataContainer weatherContainer = DataReader.readIntoDataContainer(WEATHER_PATH);
            // @TODO..
        } catch (IOException e) {
            e.printStackTrace();
        }


        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
