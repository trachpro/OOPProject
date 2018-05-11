package controller.cashflow;

import model.cashflow.Entry;
import model.cashflow.Expense;
import model.cashflow.Revenue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CashflowManager {

    private ObservableList<Entry> entries;
    private double totalRevenue;
    private double totalExpense;
    private double totalProfit;

    public CashflowManager() {

        Entry b = new Expense(LocalDate.now(),"Salary in May",100000);
        Entry c = new Expense(LocalDate.now(),"Salary in May",100000);
        Entry d = new Revenue(LocalDate.now(),"sales in the day", 66561233);

        setEntries(FXCollections.observableArrayList());
        this.entries.add(b);
        this.entries.add(c);
        this.entries.add(d);
        for (int i = 0; i < 20; i++) {
            this.entries.add(new Revenue(LocalDate.now(),"sales in the day", 12131233));
        }
        this.calculateTotalAmounts();

    }

    public void setEntries(ObservableList<Entry> entries) {
        this.entries = entries;
    }

    public ObservableList<Entry> getEntries() {
        return entries;
    }

    private void calculateTotalAmounts() {
        for(Entry iterator: this.entries) {
            if (iterator instanceof Expense) this.totalExpense += iterator.getAmount();
            if (iterator instanceof Revenue) this.totalRevenue += iterator.getAmount();
        }

        this.totalProfit = this.totalRevenue - this.totalExpense;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getTotalProfit() {
        return totalProfit;
    }
}
