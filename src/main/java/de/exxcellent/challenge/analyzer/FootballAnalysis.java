package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.data.DataContainer;

import java.util.Map;

public class FootballAnalysis {

    public static String getSmallesDifferenceGoals(DataContainer container) {

        String team = "";
        int smallesGoalSpread = Integer.MAX_VALUE;

        for (Map<String, String> row : container.getRows()) {
            int goals = Integer.parseInt(row.get("Goals"));
            int goalsAllowed = Integer.parseInt(row.get("Goals Allowed"));
            int goalSpread = Math.abs(goals - goalsAllowed);

            if (goalSpread < smallesGoalSpread) {
                smallesGoalSpread = goalSpread;
                team = row.get("Team");
            }

        }
        return team;
    }
}
