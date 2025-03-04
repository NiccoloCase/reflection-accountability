package org.nc.operational;

import org.nc.knowledge.AccountabilityType;
import org.nc.knowledge.PartyType;

public class Accountability {
    private final AccountabilityType type;
    private final Party commissioner;
    private final Party responsible;
    private final TimePeriod validFor;

    public Accountability(AccountabilityType type, Party commissioner, Party responsible, TimePeriod validFor) {
        this.type = type;
        this.commissioner = commissioner;
        this.responsible = responsible;
        this.validFor = validFor;
        validateConstraints();
    }


    public AccountabilityType getType() {
        return type;
    }

    public Party getCommissioner() {
        return commissioner;
    }

    public Party getResponsible() {
        return responsible;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    // Controlla che i tipi di party siano validi per il tipo di accountability
    public void validateConstraints() {
        PartyType commType = commissioner.getType();
        PartyType respType = responsible.getType();

        if (!type.getCommissioners().contains(commType)) {
            throw new IllegalStateException(
                    "Commissioner type '" + commType.getName() +
                            "' is not valid for AccountabilityType '" + type.getName() + "'.");
        }
        if (!type.getResponsibles().contains(respType)) {
            throw new IllegalStateException(
                    "Responsible type '" + respType.getName() +
                            "' is not valid for AccountabilityType '" + type.getName() + "'.");
        }
    }
}