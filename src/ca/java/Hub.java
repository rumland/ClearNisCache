package ca.java;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Hub {
    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getRobotName() {
        return robotName;
    }

    public String getAddr() {
        return addr;
    }

    public String getIp() {
        return ip;
    }

    private String name;
    private String domain;
    private String robotName;
    private String addr;
    private String ip;

    public Hub(String name, String domain, String robotName, String addr, String ip) {
        this.name = name;
        this.domain = domain;
        this.robotName = robotName;
        this.addr = addr;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return String.format("Name: %s; Domain: %s; RobotName: %s; Address: %s; IP: %s\n",
                name, domain, robotName, addr, ip);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Hub rhs = (Hub) obj;
        return new EqualsBuilder()
                .append(getName(), rhs.getName())
                .append(getDomain(), rhs.getDomain())
                .append(getRobotName(), rhs.getRobotName())
                .append(getAddr(), rhs.getAddr())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(getName()).
                append(getDomain()).
                append(getRobotName()).
                append(getAddr()).
                toHashCode();
    }
}
