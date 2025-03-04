from datetime import date

class TimePeriod:
    def __init__(self, start_date: date, end_date: date):
        if end_date < start_date:
            raise ValueError("end_date cannot be before start_date")
        self.start_date = start_date
        self.end_date = end_date

    def get_duration_days(self):
        return (self.end_date - self.start_date).days