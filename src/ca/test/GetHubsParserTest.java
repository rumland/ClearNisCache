package ca.test;

import ca.java.Hub;
import org.junit.Assert;
import org.junit.Test;

import ca.java.GetHubsParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GetHubsParserTest {
    @Test
    public void parseHubsTest() {
        List<String> allLines = new ArrayList<>();
        String file = getClass().getResource("resources/getHubsExampleOutput.txt").getFile();
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

        GetHubsParser getHubsParser = new GetHubsParser(allLines);
        getHubsParser.getAllHubs("ruw2k8r2uim_domain");

        List<Hub> expectedHubs = new ArrayList<>(Arrays.asList(
                new Hub("ruw2k8r2snmp", "ruw2k8r2uim_domain", "ruw2k8r2snmp", "/ruw2k8r2uim_domain/ruw2k8r2snmp/ruw2k8r2snmp/hub", "10.238.43.94"),
                new Hub("ruw2k8r2uim_hub", "ruw2k8r2uim_domain", "ruw2k8r2uim", "/ruw2k8r2uim_domain/ruw2k8r2uim_hub/ruw2k8r2uim/hub", "10.238.42.236")
        ));
        List<Hub> hubs = getHubsParser.getAllHubs("ruw2k8r2uim_domain");

        Assert.assertTrue(String.format("Expected hubs:\n %s\n Actual hubs:\n %s\n", expectedHubs, hubs),
                expectedHubs.equals(hubs));
    }
}
