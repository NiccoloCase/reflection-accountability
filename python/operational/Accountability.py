from operational import TimePeriod
from operational.Party import Party
from knowledge.AccountabilityType import AccountabilityType

class Accountability:
    def __init__(self, acc_type: AccountabilityType, commissioner: Party, responsible: Party, valid_for: TimePeriod):
        self.type = acc_type
        self.commissioner = commissioner
        self.responsible = responsible
        self.valid_for = valid_for
        self.validate_constraints()


    def validate_constraints(self):
        """
        Controlla che i tipi di party siano validi per il tipo di accountability

        1) Ogni commissioner deve appartenere a un tipo compatibile con il tipo del Party.
            x.commissioner.allTypes ∩ x.type.commissioners ≠ ∅

        2) Ogni responsible deve appartenere a un tipo compatibile con il tipo del Party.
            x. responsible.allTypes ∩ x.type.responsibles ≠ ∅

        allTypes è una derivazione di party: self type and all self type's supertypes

        """
        commissioner_types = self.commissioner.get_all_types()
        responsible_types = self.responsible.get_all_types()

        valid_commissioner = any(t in self.type.get_commissioners() for t in commissioner_types)
        valid_responsible = any(t in self.type.get_responsibles() for t in responsible_types)

        if not valid_commissioner:
            raise ValueError(
                f"Commissioner type '{self.commissioner.get_type().name}' "
                f"is not valid for AccountabilityType '{self.type.get_name()}'."
            )
        if not valid_responsible:
            raise ValueError(
                f"Responsible type '{self.responsible.get_type().name}' "
                f"is not valid for AccountabilityType '{self.type.get_name()}'."
            )
