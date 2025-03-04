from datetime import date
from typing import Optional


class TimePeriod:
    def __init__(self, start: Optional[date], end: Optional[date]):
        self.start = start
        self.end = end

    def get_start_date(self) -> Optional[date]:
        return self.start

    def get_end_date(self) -> Optional[date]:
        return self.end

    def includes(self, check_date: date) -> bool:
        if check_date is None: return False

        after_start = self.start is None or check_date >= self.start
        before_end = self.end is None or check_date <= self.end
        
        return after_start and before_end