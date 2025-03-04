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

        PartyType personType = new PartyType("Person"); // Persona
        PartyType professorType = new PartyType("Professor", personType); // Professore
        PartyType fullProfessorType = new PartyType("FullProfessor", professorType); // Professore Ordinario
        PartyType studentType = new PartyType("Student", personType);
        PartyType universityType = new PartyType("University");

        // Iscrizione Universitaria
        AccountabilityType enrollmentType = new AccountabilityType("Enrollment");
        enrollmentType.addValidCommissionerType(universityType);
        enrollmentType.addValidResponsibleType(studentType);

        // Insegnamento
        AccountabilityType teachingType = new AccountabilityType("Teaching");
        teachingType.addValidCommissionerType(universityType);
        teachingType.addValidResponsibleType(professorType); // Accetta solo Professore o FullProfessor


        //////////////////////////////////////////////////////
        // OPERATIONAL LEVEL
        //////////////////////////////////////////////////////

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party niccolo = new Party("Niccolò Caselli", studentType);
        Party profVicario = new Party("Prof. Enrico Vicario", fullProfessorType); // Professore Ordinario


        TimePeriod academicYear2025 = new TimePeriod(
                LocalDate.of(2022, 9, 1),
                LocalDate.of(2025, 7, 31)
        );

        try {
            Accountability enrollment = new Accountability(enrollmentType, unifi, niccolo, academicYear2025);
            Action examAction = new Action("Compila il piano di studi", enrollment);

            System.out.println("Azione: " + examAction.getDescription()
                    + " sotto il tipo di accountability: "
                    + examAction.getAccountability().getType().getName());

        } catch (IllegalStateException e) {
            System.err.println("Errore nella creazione dell'accountability di iscrizione: " + e.getMessage());
        }


        TimePeriod teachingPeriod = new TimePeriod(
                LocalDate.of(2002, 1, 1),
                LocalDate.now()
        );

        try {
            Accountability teaching = new Accountability(teachingType, unifi, profVicario, teachingPeriod);
            Action lectureAction = new Action("Tiene la lezione di Ingegneria del Software", teaching);

            System.out.println("Azione: " + lectureAction.getDescription()
                    + " sotto il tipo di accountability: "
                    + lectureAction.getAccountability().getType().getName());

        } catch (IllegalStateException e) {
            System.err.println("Errore nella creazione dell'accountability di insegnamento: " + e.getMessage());
        }
    }
}
