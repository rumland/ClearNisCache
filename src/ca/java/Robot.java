package ca.java;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Robot {
    private String name;
    private String addr;
    private String origin;

    public String getIp() {
        return ip;
    }

    public String getOrigin() {
        return origin;
    }

    public String getAddr() {
        return addr;
    }

    public String getName() {
        return name;
    }

    private String ip;

    public Robot(String name, String addr, String origin, String ip) {
        this.name = name;
        this.addr = addr;
        this.origin = origin;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return String.format("Name: %s; Address: %s; Origin: %s; IP: %s\n", name, addr, origin, ip);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Robot rhs = (Robot) obj;
        return new EqualsBuilder()
                .append(getName(), rhs.getName())
                .append(getAddr(), rhs.getAddr())
                .append(getOrigin(), rhs.getOrigin())
                .append(getIp(), rhs.getIp())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(getName()).
                append(getAddr()).
                append(getOrigin()).
                append(getIp()).
                toHashCode();
    }
}
