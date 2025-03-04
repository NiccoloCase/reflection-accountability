class PartyType:
    def __init__(self, name: str, supertype: "PartyType" = None):
        self.name = name
        self.supertype = supertype
    
    
    # (Derivazione) Restituisce l'insieme di tutti i tipi di party a cui appartiene il tipo corrente
    def get_all_types(self):
        # Essenzialmente risale la gerarchia dei tipi di partito fino a raggiungere il tipo radice
        types = [self]
        current = self.supertype
        while current:
            types.append(current)
            current = current.supertype
        return types


    def __repr__(self):
        return f"PartyType({self.name})"

