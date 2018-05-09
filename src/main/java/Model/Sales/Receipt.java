package Model.Sales;

import Controller.App;
import Model.Product.Book;
import Model.Product.MusicDisc;
import Model.Product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Receipt {
    private String receiptID;
    private String customerName;
    private String cashierName;
    private ObservableList<ItemOrder> listItems;
    private double totalCost;
    private String remark;

    private LocalDate date;

    public Receipt()
    {
        setReceiptID("1");
        setCustomerName("1");
        setListItems(FXCollections.observableArrayList());

//        Product p = App.dataManager.getProductsManager().getProductByID("PR0001");
        listItems.add(new ItemOrder(new Book(), 2));
        listItems.add(new ItemOrder(new MusicDisc(), 32));
       // listItems.add(new ItemOrder(App.dataManager.getProductsManager().getProductByID("PR0002"), 3));

        setTotalCost(0);
        setRemark("1");
        setDate(LocalDateTime.now().toLocalDate());
        setCashierName("1");
    }

    public void calculateTotalCost()
    {
        int noOfItems = getListItems().size();
        for(int i = 0; i < noOfItems; i++)
        {
            double cost = getListItems().get(i).calculateCost();
            setTotalCost(getTotalCost() + cost);
        }
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ObservableList<ItemOrder> getListItems() {
        return listItems;
    }

    public void setListItems(ObservableList<ItemOrder> listItems) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }
}
