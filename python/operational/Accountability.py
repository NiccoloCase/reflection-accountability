from operational.Party import Party
from knowledge.AccountabilityType import AccountabilityType

class Accountability:
    def __init__(self, acc_type: AccountabilityType, commissioner: Party, responsible: Party):
        self.acc_type = acc_type
        self.commissioner = commissioner
        self.responsible = responsible
        self.validate_constraints()

    def validate_constraints(self):
        if not any(t in self.acc_type.valid_commissioners for t in self.commissioner.get_all_types()):
            raise ValueError(f"Commissioner type '{self.commissioner.party_type.name}' is not valid for {self.acc_type.name}")

        if not any(t in self.acc_type.valid_responsibles for t in self.responsible.get_all_types()):
            raise ValueError(f"Responsible type '{self.responsible.party_type.name}' is not valid for {self.acc_type.name}")

    def __repr__(self):
        return f"Accountability({self.acc_type.name}, {self.commissioner.name}, {self.responsible.name})"
