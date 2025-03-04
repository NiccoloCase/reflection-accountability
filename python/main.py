from datetime import date
from src.operational.Accountability import Accountability
from src.operational.TimePeriod import TimePeriod  
from src.operational.Party import Party
from src.knowledge.AccountabilityType import AccountabilityType
from src.knowledge.PartyType import PartyType
from src.operational.Action import Action

def main():
    
    ######################################################
    # KNOWLEDGE LAYER
    ######################################################

    person_type = PartyType("Person")  # Persona
    professor_type = PartyType("Professor", person_type)  # Professore
    full_professor_type = PartyType("FullProfessor", professor_type)  # Professore Ordinario
    student_type = PartyType("Student", person_type) # Studente
    university_type = PartyType("University") # Università

    # Iscrizione Universitaria
    enrollment_type = AccountabilityType("Enrollment")
    enrollment_type.add_valid_commissioner_type(university_type)
    enrollment_type.add_valid_responsible_type(student_type)

    # Insegnamento universitario
    teaching_type = AccountabilityType("Teaching")
    teaching_type.add_valid_commissioner_type(university_type)
    teaching_type.add_valid_responsible_type(professor_type)  # Accetta solo Professore o FullProfessor


    ######################################################
    # OPERATIONAL LAYER
    ######################################################
    
    unifi = Party("Università degli Studi di Firenze", university_type)
    niccolo = Party("Niccolò Caselli", student_type)
    prof_vicario = Party("Prof. Enrico Vicario", full_professor_type)  # Professore Ordinario

    try:
        enrollment = Accountability(enrollment_type, unifi, niccolo, TimePeriod(date(2022, 9, 1), date(2025, 7, 31))) 
        exam_action = Action("Compila il piano di studi", enrollment)

        print(f"Azione: {exam_action.get_description()} sotto il tipo di accountability: {exam_action.get_accountability().type.get_name()}")

    except ValueError as e:
        print(f"Errore nella creazione dell'accountability di iscrizione: {e}")


    try:
        teaching = Accountability(teaching_type, unifi, prof_vicario, TimePeriod(date(2002, 1, 1), date.today()))
        lecture_action = Action("Tiene la lezione di Ingegneria del Software", teaching)

        print(f"Azione: {lecture_action.get_description()} sotto il tipo di accountability: {lecture_action.get_accountability().type.get_name()}")

    except ValueError as e:
        print(f"Errore nella creazione dell'accountability di insegnamento: {e}")

if __name__ == "__main__":
    main()