class PartyType:
    def __init__(self, name: str, supertype: "PartyType" = None):
        self.name = name
        self.supertype = supertype
    
    def get_all_types(self):
        types = {self}
        current = self.supertype
        while current:
            types.add(current)
            current = current.supertype
        return types

    def is_compatible_with(self, other: "PartyType"):
        return bool(self.get_all_types() & other.get_all_types())  # Intersezione non vuota

    def __repr__(self):
        return f"PartyType({self.name})"
