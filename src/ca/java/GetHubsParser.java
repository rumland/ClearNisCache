package ca.java;

import java.util.ArrayList;
import java.util.List;

public class GetHubsParser {
    private List<String> allHubsOutput;

    public GetHubsParser(List<String> allHubsOutput) {
        this.allHubsOutput = allHubsOutput;
    }

    public List<Hub> getAllHubs(String desiredDomain) {
        List<Hub> hubs = new ArrayList<>();
        for (int idx = 0; idx < allHubsOutput.size(); ++idx) {
            String line = allHubsOutput.get(idx);
            if (line.matches("^\\s[0-9]+.*")) {
                //Next 22 lines define hub
                String name = allHubsOutput.get(idx + 1).split("\\s+")[4];
                String domain = allHubsOutput.get(idx + 2).split("\\s+")[4];
                String robotName = allHubsOutput.get(idx + 3).split("\\s+")[4];
                String addr = allHubsOutput.get(idx + 4).split("\\s+")[4];
                String ip = allHubsOutput.get(idx + 5).split("\\s+")[4];
                if (domain.equals(desiredDomain)) {
                    Hub hub = new Hub(name, domain, robotName, addr, ip);
                    hubs.add(hub);
                }
            }
        }
        return hubs;
    }
}
