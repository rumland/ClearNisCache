package ca.test;

import ca.java.GetRobotsParser;
import ca.java.Robot;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetRobotsParserTest {
    @Test
    public void parseRobotsTest() {
        List<String> allLines = new ArrayList<>();
        String file = getClass().getResource("resources/getRobotsExampleOutput.txt").getFile();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader instream = new InputStreamReader(fileInputStream);
             BufferedReader reader = new BufferedReader(instream)) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        GetRobotsParser getRobotsParser = new GetRobotsParser(allLines);
        List<Robot> robots = getRobotsParser.getAllRobots();
        List<Robot> expectedRobots = new ArrayList<>(Arrays.asList(
                new Robot("ruw2k8r2uim", "/ruw2k8r2uim_domain/ruw2k8r2uim_hub/ruw2k8r2uim", "ruw2k8r2uim_hub", "10.238.42.236")
        ));

        Assert.assertTrue(String.format("Expected robots:\n %s\n Actual robots:\n %s\n", expectedRobots, robots),
                expectedRobots.equals(robots));
    }
}
