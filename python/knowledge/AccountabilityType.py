from knowledge.PartyType import PartyType

class AccountabilityType:
    def __init__(self, name: str):
        self.name = name
        self.valid_commissioners = set()
        self.valid_responsibles = set()

    def add_valid_commissioner_type(self, party_type: PartyType):
        self.valid_commissioners.add(party_type)

    def add_valid_responsible_type(self, party_type: PartyType):
        self.valid_responsibles.add(party_type)

    def __repr__(self):
        return f"AccountabilityType({self.name})"
