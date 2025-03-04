package org.nc.operational;

import org.nc.knowledge.AccountabilityType;
import org.nc.knowledge.PartyType;

import java.util.Set;

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

    // 1) Ogni commissioner deve appartenere a un tipo compatibile con il tipo del Party.
    //        x.commissioner.allTypes ∩ x.type.commissioners ≠ ∅

    // 2) Ogni responsible deve appartenere a un tipo compatibile con il tipo del Party.
    //        x. responsible.allTypes ∩ x.type.responsibles ≠ ∅

    // allTypes è una derivazione di party: self type and all self type's supertypes

    public void validateConstraints() {
            Set<PartyType> commissionerTypes = commissioner.getAllTypes();
            Set<PartyType> responsibleTypes = responsible.getAllTypes();

            boolean validCommissioner = false;
            for (PartyType commType : commissionerTypes) {
                if (type.getCommissioners().contains(commType)) {
                    validCommissioner = true;
                    break;
                }
            }

            boolean validResponsible = false;
            for (PartyType respType : responsibleTypes) {
                if (type.getResponsibles().contains(respType)) {
                    validResponsible = true;
                    break;
                }
            }

            if (!validCommissioner) {
                throw new IllegalStateException(
                        "Commissioner type '" + commissioner.getType().getName() +
                                "' is not valid for AccountabilityType '" + type.getName() + "'.");
            }
            if (!validResponsible) {
                throw new IllegalStateException(
                        "Responsible type '" + responsible.getType().getName() +
                                "' is not valid for AccountabilityType '" + type.getName() + "'.");
            }
        }
}