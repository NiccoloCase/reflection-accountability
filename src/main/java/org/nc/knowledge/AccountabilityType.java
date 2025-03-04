package org.nc.knowledge;

import java.util.HashSet;
import java.util.Set;

public class AccountabilityType {
    private final String name;
    // Tipi di party validi per questa accountability
    private final Set<PartyType> validCommissionerTypes = new HashSet<>();
    private final Set<PartyType> validResponsibleTypes = new HashSet<>();

    public AccountabilityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<PartyType> getCommissioners() {
        return validCommissionerTypes;
    }

    public Set<PartyType> getResponsibles() {
        return validResponsibleTypes;
    }


    public void addValidCommissionerType(PartyType pt) {
        validCommissionerTypes.add(pt);
    }

    public void addValidResponsibleType(PartyType pt) {
        validResponsibleTypes.add(pt);
    }
}