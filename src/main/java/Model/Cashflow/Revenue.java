package Model.Cashflow;

import java.time.LocalDate;
import java.util.Date;

public class Revenue extends Entry {

    public Revenue(LocalDate date, String description, double amount) {
        super(date, description, amount);
    }
}
