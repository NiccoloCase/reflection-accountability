package org.nc.operational;

import org.junit.jupiter.api.Test;
import org.nc.knowledge.AccountabilityType;
import org.nc.knowledge.PartyType;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AccountabilityTest {

    @Test
    void shouldCreateValidAccountability() {
        PartyType studentType = new PartyType("Student");
        PartyType universityType = new PartyType("University");

        AccountabilityType enrollmentType = new AccountabilityType("Enrollment");
        enrollmentType.addValidCommissionerType(universityType);
        enrollmentType.addValidResponsibleType(studentType);

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party niccolo = new Party("Niccolò Caselli", studentType);

        TimePeriod academicYear = new TimePeriod(
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2025, 6, 30));

        assertDoesNotThrow(() -> new Accountability(enrollmentType, unifi, niccolo, academicYear));
    }

    @Test
    void shouldFailIfCommissionerIsInvalid() {
        PartyType studentType = new PartyType("Student");
        PartyType universityType = new PartyType("University");
        PartyType companyType = new PartyType("Company");

        AccountabilityType enrollmentType = new AccountabilityType("Enrollment");
        enrollmentType.addValidCommissionerType(universityType);
        enrollmentType.addValidResponsibleType(studentType);

        Party google = new Party("Google", companyType);
        Party niccolo = new Party("Niccolò Caselli", studentType);

        TimePeriod academicYear = new TimePeriod(
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2025, 6, 30));

        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Accountability(enrollmentType, google, niccolo, academicYear));

        assertEquals("Commissioner type 'Company' is not valid for AccountabilityType 'Enrollment'.", exception.getMessage());
    }

    @Test
    void shouldAllowFullProfessorToTeach() {
        PartyType personType = new PartyType("Person");
        PartyType professorType = new PartyType("Professor", personType);
        PartyType fullProfessorType = new PartyType("FullProfessor", professorType);
        PartyType universityType = new PartyType("University");

        AccountabilityType teachingType = new AccountabilityType("Teaching");
        teachingType.addValidCommissionerType(universityType);
        teachingType.addValidResponsibleType(professorType);

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party enricoVicario = new Party("Prof. Enrico Vicario", fullProfessorType);

        TimePeriod teachingPeriod = new TimePeriod(
                LocalDate.of(2002, 1, 1),
                LocalDate.now());

        assertDoesNotThrow(() -> new Accountability(teachingType, unifi, enricoVicario, teachingPeriod));
    }

    @Test
    void shouldAllowDepartmentDirectorRole() {
        PartyType personType = new PartyType("Person");
        PartyType directorType = new PartyType("DepartmentDirector", personType);
        PartyType universityType = new PartyType("University");

        AccountabilityType directorRole = new AccountabilityType("Department Leadership");
        directorRole.addValidCommissionerType(universityType);
        directorRole.addValidResponsibleType(directorType);

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party enricoVicario = new Party("Prof. Enrico Vicario", directorType);

        TimePeriod directorPeriod = new TimePeriod(
                LocalDate.of(2016, 11, 1),
                LocalDate.of(2020, 11, 1));

        assertDoesNotThrow(() -> new Accountability(directorRole, unifi, enricoVicario, directorPeriod));
    }

    @Test
    void shouldFailIfFullProfessorIsNotDepartmentDirector() {
        PartyType personType = new PartyType("Person");
        PartyType professorType = new PartyType("Professor", personType);
        PartyType fullProfessorType = new PartyType("FullProfessor", professorType);
        PartyType directorType = new PartyType("DepartmentDirector", personType);
        PartyType universityType = new PartyType("University");

        AccountabilityType directorRole = new AccountabilityType("Department Leadership");
        directorRole.addValidCommissionerType(universityType);
        directorRole.addValidResponsibleType(directorType);

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party enricoVicario = new Party("Prof. Enrico Vicario", fullProfessorType);

        TimePeriod directorPeriod = new TimePeriod(
                LocalDate.of(2016, 11, 1),
                LocalDate.of(2020, 11, 1));

        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Accountability(directorRole, unifi, enricoVicario, directorPeriod));

        assertEquals("Responsible type 'FullProfessor' is not valid for AccountabilityType 'Department Leadership'.", exception.getMessage());
    }

    @Test
    void shouldFailIfDepartmentDirectorCannotTeach() {
        PartyType personType = new PartyType("Person");
        PartyType professorType = new PartyType("Professor", personType);
        PartyType departmentDirectorType = new PartyType("DepartmentDirector", personType);
        PartyType universityType = new PartyType("University");

        AccountabilityType teachingType = new AccountabilityType("Teaching");
        teachingType.addValidCommissionerType(universityType);
        teachingType.addValidResponsibleType(professorType);

        Party unifi = new Party("Università degli Studi di Firenze", universityType);
        Party enricoVicario = new Party("Prof. Enrico Vicario", departmentDirectorType);

        TimePeriod teachingPeriod = new TimePeriod(
                LocalDate.of(2002, 1, 1),
                LocalDate.now());

        Exception exception = assertThrows(IllegalStateException.class, () ->
                new Accountability(teachingType, unifi, enricoVicario, teachingPeriod));

        assertNotNull(exception, "L'eccezione doveva essere sollevata ma non lo è stata.");
        assertEquals("Responsible type 'DepartmentDirector' is not valid for AccountabilityType 'Teaching'.", exception.getMessage());
    }

}
