package model.cashflow;

import java.time.LocalDate;

public class Expense extends Entry{
    public Expense(LocalDate date, String description, double amount) {
        super(date, description, amount);
    }
}
