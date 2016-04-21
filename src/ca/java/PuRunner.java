package ca.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PuRunner {
    private String userName;
    private String password;
    private String uimRobotAddr;
    private boolean destructive;

    public String getDomain() {
        return domain;
    }

    public String getUimRobotAddr() {
        return uimRobotAddr;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    private String domain;

    public PuRunner(String userName, String password, String uimRobotAddr, boolean destructive) {
        this.userName = userName;
        this.password = password;
        this.uimRobotAddr = uimRobotAddr;
        this.domain = uimRobotAddr.split("/")[1];
        this.destructive = destructive;
    }

    public List<Hub> getAllHubs() {
        final String command = String.format("pu.exe -u %s -p %s %s/hub gethubs", userName, password, uimRobotAddr);

        List<String> allLines = runPuCommand(command);
        if (allLines == null) return null;

        GetHubsParser getHubsParser = new GetHubsParser(allLines);
        return getHubsParser.getAllHubs(domain);
    }

    public List<Robot> getAllRobots(Hub hub) {
        final String command = String.format("pu.exe -u %s -p %s %s getrobots \"\" 0", userName, password, hub.getAddr());

        List<String> allLines = runPuCommand(command);

        GetRobotsParser getRobotsParser = new GetRobotsParser(allLines);
        return getRobotsParser.getAllRobots();
    }

    public void clearNisCache(Hub hub, Robot robot) {
        final String command = String.format("pu.exe -u %s -p %s /%s/%s/%s/controller _nis_cache_clean %s 0",
                userName, password, hub.getDomain(), hub.getName(), hub.getRobotName(), robot.getName());

        if (destructive) {
            System.out.printf("Clearing NIS cache for robot %s at %s\n", robot.getName(), robot.getAddr());
            System.out.printf("command: %s\n", command);
            List<String> doNotCare = runPuCommand(command, true);
        }
    }

    private List<String> runPuCommand(String command) {
        return runPuCommand(command, false);
    }

    private List<String> runPuCommand(String command, boolean verbose) {
        List<String> allLines = new ArrayList<>();
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (InputStreamReader instream = new InputStreamReader(process.getInputStream());
             BufferedReader reader = new BufferedReader(instream)) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
                if (verbose) {
                    System.out.printf("%s\n", line);
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLines;
    }
}
