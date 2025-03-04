from typing import Set
from knowledge.PartyType import PartyType

class AccountabilityType:
    def __init__(self, name: str):
        self.name = name
        self.valid_commissioner_types: Set[PartyType] = set()
        self.valid_responsible_types: Set[PartyType] = set()

    def get_name(self) -> str:
        return self.name

    def get_commissioners(self) -> Set[PartyType]:
        return self.valid_commissioner_types

    def get_responsibles(self) -> Set[PartyType]:
        return self.valid_responsible_types

    def add_valid_commissioner_type(self, pt: PartyType):
        self.valid_commissioner_types.add(pt)

    def add_valid_responsible_type(self, pt: PartyType):
        self.valid_responsible_types.add(pt)