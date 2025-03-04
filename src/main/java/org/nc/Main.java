package org.nc;

import org.nc.knowledge.AccountabilityType;
import org.nc.knowledge.PartyType;
import org.nc.operational.Party;
import org.nc.operational.Accountability;
import org.nc.operational.TimePeriod;
import org.nc.operational.Action;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //////////////////////////////////////////////////////
        // KNOWLEDGE LEVEL
        //////////////////////////////////////////////////////

        PartyType studentType = new PartyType("Student");
        PartyType universityType = new PartyType("University");

        // Iscrizione Universitaria
        AccountabilityType enrollmentType = new AccountabilityType("Enrollment");
        enrollmentType.addValidCommissionerType(universityType);
        enrollmentType.addValidResponsibleType(studentType);


        //////////////////////////////////////////////////////
        // OPERATIONAL LEVEL
        //////////////////////////////////////////////////////

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party niccolo = new Party("Niccolò Caselli", studentType);

        TimePeriod academicYear2025 = new TimePeriod(
                LocalDate.of(2022, 9, 1),
                LocalDate.of(2025, 7, 31)
        );

        Accountability enrollment = new Accountability(enrollmentType, unifi, niccolo, academicYear2025);


        Action examAction = new Action("Compila il piano di studi", enrollment);

        System.out.println("Azione: " + examAction.getDescription()
                + " sotto il tipo di accountability: "
                + examAction.getAccountability().getType().getName());
    }
}
