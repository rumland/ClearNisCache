package ca.java;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        PuRunner runner = new PuRunner("administrator", "t3sti9",
                "/ruw2k8r2uim_domain/ruw2k8r2uim_hub/ruw2k8r2uim", true);
        System.out.printf("Hub count: %s\n", runner.getAllHubs().size());
        for (Hub hub : runner.getAllHubs()) {
            System.out.printf("%s", hub);
        }

        for (Hub hub : runner.getAllHubs()) {
            System.out.printf("Getting all robots for hub %s\n", hub.getName());
            List<Robot> allRobots = runner.getAllRobots(hub);
            for (Robot robot : allRobots) {
                runner.clearNisCache(hub, robot);
            }
        }
    }
}
