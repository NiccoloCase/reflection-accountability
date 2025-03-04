package org.nc.knowledge;

public class PartyType {
    private final String name;
    private final PartyType supertype;

    public PartyType(String name) {
        this.name = name;
        this.supertype = null;
    }

    public PartyType(String name, PartyType supertype) {
        this.name = name;
        this.supertype = supertype;
    }

    public String getName() {
        return name;
    }

    public PartyType getSupertype() {
        return supertype;
    }

}