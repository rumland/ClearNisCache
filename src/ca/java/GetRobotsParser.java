package ca.java;

import java.util.ArrayList;
import java.util.List;

public class GetRobotsParser {
    private List<String> allRobotsOutput;

    public GetRobotsParser(List<String> allRobotsOutput) {
        this.allRobotsOutput = allRobotsOutput;
    }

    public List<Robot> getAllRobots() {
        List<Robot> robots = new ArrayList<>();
        for (int idx = 0; idx < allRobotsOutput.size(); ++idx) {
            String line = allRobotsOutput.get(idx);
            if (line.matches("^\\s[0-9]+.*")) {
                //Next 24 lines define robot
                String name = allRobotsOutput.get(idx + 1).split("\\s+")[4];
                String addr = allRobotsOutput.get(idx + 2).split("\\s+")[4];
                String origin = allRobotsOutput.get(idx + 3).split("\\s+")[4];
                String ip = allRobotsOutput.get(idx + 5).split("\\s+")[4];
                Robot robot = new Robot(name, addr, origin, ip);
                robots.add(robot);
            }
        }
        return robots;
    }
}
