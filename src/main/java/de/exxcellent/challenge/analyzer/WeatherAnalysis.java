package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.DataContainer;

import java.util.Map;

public final class WeatherAnalysis {

    public static int getSmallestTemperatureSpread(DataContainer container) {

        int dayNumber = -1;
        int smallestTemperatureSpread = Integer.MAX_VALUE;

        for (Map<String, String> row : container.getRows()) {
            int maxTemperature = Integer.parseInt(row.get("MxT"));
            int minTemperature = Integer.parseInt(row.get("MnT"));
            int temperatureSpread = maxTemperature - minTemperature;

            if (temperatureSpread < smallestTemperatureSpread) {
                smallestTemperatureSpread = temperatureSpread;
                dayNumber = Integer.parseInt(row.get("Day"));
            }

        }
        return dayNumber;
    }
}
