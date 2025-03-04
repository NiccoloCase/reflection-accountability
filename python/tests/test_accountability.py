import unittest
from datetime import date
from src.knowledge.PartyType import PartyType
from src.knowledge.AccountabilityType import AccountabilityType
from src.operational.Accountability import Accountability
from src.operational.Party import Party
from src.operational.TimePeriod import TimePeriod

class TestAccountability(unittest.TestCase):

    def test_create_valid_accountability(self):
        student_type = PartyType("Student")
        university_type = PartyType("University")

        enrollment_type = AccountabilityType("Enrollment")
        enrollment_type.add_valid_commissioner_type(university_type)
        enrollment_type.add_valid_responsible_type(student_type)

        unifi = Party("Università degli Studi di Firenze", university_type)
        niccolo = Party("Niccolò Caselli", student_type)

        academic_year = TimePeriod(
            start_date=date(2024, 9, 1),
            end_date=date(2025, 6, 30)
        )

        try:
            Accountability(enrollment_type, unifi, niccolo, academic_year)
        except Exception as e:
            self.fail(f"Unexpected exception raised: {e}")


    def test_fail_if_commissioner_is_invalid(self):
        student_type = PartyType("Student")
        university_type = PartyType("University")
        company_type = PartyType("Company")

        enrollment_type = AccountabilityType("Enrollment")
        enrollment_type.add_valid_commissioner_type(university_type)
        enrollment_type.add_valid_responsible_type(student_type)

        google = Party("Google", company_type)
        niccolo = Party("Niccolò Caselli", student_type)

        academic_year = TimePeriod(
            start_date=date(2024, 9, 1),
            end_date=date(2025, 6, 30)
        )

        with self.assertRaises(ValueError) as context:
            Accountability(enrollment_type, google, niccolo, academic_year)

        self.assertEqual(str(context.exception), "Commissioner type 'Company' is not valid for AccountabilityType 'Enrollment'.")


    def test_allow_full_professor_to_teach(self):
        person_type = PartyType("Person")
        professor_type = PartyType("Professor", person_type)
        full_professor_type = PartyType("FullProfessor", professor_type)
        university_type = PartyType("University")

        teaching_type = AccountabilityType("Teaching")
        teaching_type.add_valid_commissioner_type(university_type)
        teaching_type.add_valid_responsible_type(professor_type)

        unifi = Party("Università degli Studi di Firenze", university_type)
        enrico_vicario = Party("Prof. Enrico Vicario", full_professor_type)

        teaching_period = TimePeriod(
            start_date=date(2002, 1, 1),
            end_date=date.today()
        )

        try:
            Accountability(teaching_type, unifi, enrico_vicario, teaching_period)
        except Exception as e:
            self.fail(f"Unexpected exception raised: {e}")


    def test_allow_department_director_role(self):
        person_type = PartyType("Person")
        director_type = PartyType("DepartmentDirector", person_type)
        university_type = PartyType("University")

        director_role = AccountabilityType("Department Leadership")
        director_role.add_valid_commissioner_type(university_type)
        director_role.add_valid_responsible_type(director_type)

        unifi = Party("Università degli Studi di Firenze", university_type)
        enrico_vicario = Party("Prof. Enrico Vicario", director_type)

        director_period = TimePeriod(
            start_date=date(2016, 11, 1),
            end_date=date(2020, 11, 1)
        )

 
        try:
            Accountability(director_role, unifi, enrico_vicario, director_period)
        except Exception as e:
            self.fail(f"Unexpected exception raised: {e}")


    def test_fail_if_full_professor_is_not_department_director(self):
        person_type = PartyType("Person")
        professor_type = PartyType("Professor", person_type)
        full_professor_type = PartyType("FullProfessor", professor_type)
        director_type = PartyType("DepartmentDirector", person_type)
        university_type = PartyType("University")

        director_role = AccountabilityType("Department Leadership")
        director_role.add_valid_commissioner_type(university_type)
        director_role.add_valid_responsible_type(director_type)

        unifi = Party("Università degli Studi di Firenze", university_type)
        enrico_vicario = Party("Prof. Enrico Vicario", full_professor_type)

        director_period = TimePeriod(
            start_date=date(2016, 11, 1),
            end_date=date(2020, 11, 1)
        )

        with self.assertRaises(ValueError) as context:
            Accountability(director_role, unifi, enrico_vicario, director_period)

        self.assertEqual(str(context.exception), "Responsible type 'FullProfessor' is not valid for AccountabilityType 'Department Leadership'.")


    def test_fail_if_department_director_cannot_teach(self):
        person_type = PartyType("Person")
        professor_type = PartyType("Professor", person_type)
        department_director_type = PartyType("DepartmentDirector", person_type)
        university_type = PartyType("University")

        teaching_type = AccountabilityType("Teaching")
        teaching_type.add_valid_commissioner_type(university_type)
        teaching_type.add_valid_responsible_type(professor_type)

        unifi = Party("Università degli Studi di Firenze", university_type)
        enrico_vicario = Party("Prof. Enrico Vicario", department_director_type)

        teaching_period = TimePeriod(
            start_date=date(2002, 1, 1),
            end_date=date.today()
        )

        with self.assertRaises(ValueError) as context:
            Accountability(teaching_type, unifi, enrico_vicario, teaching_period)

        self.assertEqual(str(context.exception), "Responsible type 'DepartmentDirector' is not valid for AccountabilityType 'Teaching'.")
