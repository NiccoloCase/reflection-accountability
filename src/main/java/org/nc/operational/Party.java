package org.nc.operational;

import org.nc.knowledge.PartyType;

public class Party {
    private final String name;
    private PartyType type;

    public Party(String name, PartyType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PartyType getType() {
        return type;
    }

    public void setType(PartyType type) {
        this.type = type;
    }
}