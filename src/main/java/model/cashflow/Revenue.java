package model.cashflow;

import java.time.LocalDate;

public class Revenue extends Entry {

    public Revenue(LocalDate date, String description, double amount) {
        super(date, description, amount);
    }
}
