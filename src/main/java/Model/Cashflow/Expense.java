package Model.Cashflow;

import java.time.LocalDate;
import java.util.Date;

public class Expense extends Entry{
    public Expense(LocalDate date, String description, double amount) {
        super(date, description, amount);
    }
}
