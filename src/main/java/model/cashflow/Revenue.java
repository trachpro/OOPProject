package model.cashflow;

import java.time.LocalDate;

public class Revenue extends Entry {


    public Revenue(LocalDate _date, double _revenue, int _no0fProductsSold, double _inventory, int _no0fProductsBought, double _expense) {
        super(_date, _revenue, _no0fProductsSold, _inventory, _no0fProductsBought, _expense);
    }
}
