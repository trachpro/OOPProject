package Controller.Sales;

import Model.Sales.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalesManager {
    private String currentReceiptID;
    private ObservableList<Receipt> listReceipts;

    public SalesManager()
    {
        currentReceiptID = "RE0000";
        setListReceipts(FXCollections.observableArrayList());

        addReceipt(new Receipt());
        addReceipt(new Receipt());
    }

    public void addReceipt(Receipt _receipt)
    {
        currentReceiptID = getNextReceiptID();
        _receipt.setReceiptID(currentReceiptID);
        getListReceipts().add(_receipt);
    }


    public String getNextReceiptID()
    {
        int currentNumber = Integer.valueOf(currentReceiptID.substring(2));

        String nextNumber = String.valueOf(currentNumber + 1);

        if(nextNumber.length() < 4)
        {
            int currentLength = nextNumber.length();
            for(int i = 0; i < 4 - currentLength; i++)
            {
                nextNumber = "0".concat(nextNumber);
            }
        }

        String nextReceiptID = "RE".concat(nextNumber);
        return nextReceiptID;
    }

    public ObservableList<Receipt> getListReceipts() {
        return listReceipts;
    }

    public void setListReceipts(ObservableList<Receipt> listReceipts) {
        this.listReceipts = listReceipts;
    }
}
