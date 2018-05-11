package model.expense;

import java.time.LocalDate;

public class Expense {
    private String expenseID;
    private Double cost;
    private LocalDate purchaseDate;
    private String purchaser;
    private String remark;

    public Expense()
    {
        setExpenseID("");
        setCost(0.0);
        setPurchaseDate(null);
        setPurchaser("");
        setRemark("");
    }

    public Expense(String _expenseID, Double _cost, LocalDate _date, String _purchaser, String _remark)
    {
        this.setExpenseID(_expenseID);
        this.setCost(_cost);
        this.setPurchaseDate(_date);
        this.setPurchaser(_purchaser);
        this.setRemark(_remark);
    }

    public String getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }
}
