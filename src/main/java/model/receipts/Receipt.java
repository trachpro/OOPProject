package model.receipts;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Receipt {
    protected String receiptID;
    protected Enum<Category> category;

    protected double totalCost;
    protected String remark;

    protected ArrayList<ItemOrder> listItems;
    protected LocalDate date;

    public abstract void calculateTotalCost();

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public ArrayList<ItemOrder> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<ItemOrder> listItems) {
        this.listItems = listItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Enum<Category> getCategory() {
        return category;
    }

    public void setCategory(Enum<Category> category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

