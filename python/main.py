from knowledge.PartyType import PartyType
from knowledge.AccountabilityType import AccountabilityType
from operational.Accountability import Accountability
from operational.Party import Party

if __name__ == "__main__":


    ######################################################
    # KNOWLEDGE LAYER
    ######################################################
    
    person_type = PartyType("Person") # Persona
    professor_type = PartyType("Professor", person_type) # Professore
    full_professor_type = PartyType("FullProfessor", professor_type)  # Professore Ordinario
    student_type = PartyType("Student", person_type) # Studente
    university_type = PartyType("University")  # Università

    # Iscrizione universitaria
    enrollment_type = AccountabilityType("Enrollment") 
    enrollment_type.add_valid_commissioner_type(university_type)
    enrollment_type.add_valid_responsible_type(student_type)

    # Insegnamento corso
    teaching_type = AccountabilityType("Teaching")
    teaching_type.add_valid_commissioner_type(university_type)
    teaching_type.add_valid_responsible_type(professor_type)  

    

    ######################################################
    # OPERATIONAL LAYER
    ######################################################

    unifi = Party("Università degli Studi di Firenze", university_type)
    niccolo = Party("Niccolò Caselli", student_type)
    prof_vicario = Party("Prof. Enrico Vicario", full_professor_type)



    # Instanzio le accountability
 
    try:
        enrollment = Accountability(enrollment_type, unifi, niccolo)
    except ValueError as e:
        print(f"Errore: {e}")


    try:
        teaching = Accountability(teaching_type, unifi, prof_vicario)
    except ValueError as e:
        print(f"Errore: {e}")




