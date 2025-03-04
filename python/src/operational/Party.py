from ..knowledge.PartyType import PartyType 

class Party:
    def __init__(self, name: str, party_type: PartyType):
        self.name = name
        self.party_type = party_type
    
    def get_all_types(self):
        return self.party_type.get_all_types()

    def get_type(self):
        return self.party_type

    def __repr__(self):
        return f"Party({self.name}, {self.party_type.name})"
