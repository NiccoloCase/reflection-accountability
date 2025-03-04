from . import Accountability


class Action:
    def __init__(self, description: str, accountability: Accountability):
        self.description = description
        self.accountability = accountability

    def get_description(self) -> str:
        return self.description

    def get_accountability(self) -> Accountability:
        return self.accountability
