package controller.sales;

import model.receipts.SellReceipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalesManager {
    private String currentSellReceiptID;
    private ObservableList<SellReceipt> listSellReceipts;

    public SalesManager()
    {
        currentSellReceiptID = "RE0000";
        setListSellReceipts(FXCollections.observableArrayList());
    }

    public void addSellReceipt(SellReceipt _sellReceipt)
    {
        getListSellReceipts().add(_sellReceipt);
        updateCurrentReceiptID(_sellReceipt.getReceiptID());
    }
    private void updateCurrentReceiptID(String _receiptID)
    {
        int currentNumber = Integer.valueOf(currentSellReceiptID.substring(2));
        int next = Integer.valueOf(_receiptID.substring(2));

        if(currentNumber < next)
        {
            currentSellReceiptID = _receiptID;
        }
    }

    public String getNextSellReceiptID()
    {
        int currentNumber = Integer.valueOf(currentSellReceiptID.substring(2));

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

    public ObservableList<SellReceipt> getListSellReceipts() {
        return listSellReceipts;
    }

    public void setListSellReceipts(ObservableList<SellReceipt> listSellReceipts) {
        this.listSellReceipts = listSellReceipts;
    }
}
