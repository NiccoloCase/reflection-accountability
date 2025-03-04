package org.nc.operational;

import org.nc.knowledge.PartyType;

import java.util.HashSet;
import java.util.Set;

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

    // Derivazione
    // Restituisce l'insieme di tutti i tipi di partito a cui appartiene il tipo corrente
    public Set<PartyType> getAllTypes() {
        // Essenzialmente risale la gerarchia dei tipi di partito fino a raggiungere il tipo radice
        Set<PartyType> types = new HashSet<>();
        PartyType current = this.type;
        while (current != null) {
            types.add(current);
            current = current.getSupertype();
        }
        return types;
    }
}