package org.nc.operational;

public class Action {
    private final String description;
    private final Accountability accountability;

    public Action(String description, Accountability accountability) {
        this.description = description;
        this.accountability = accountability;
    }

    public String getDescription() {
        return description;
    }

    public Accountability getAccountability() {
        return accountability;
    }
}