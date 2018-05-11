package controller.inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.receipts.BuyReceipt;

public class InventoryManager {
    
    private String currentBuyReceiptID;
    private ObservableList<BuyReceipt> listBuyReceipts;

    public InventoryManager()
    {
        currentBuyReceiptID = "RB0000";
        setListBuyReceipts(FXCollections.observableArrayList());
        getListBuyReceipts().add(new BuyReceipt());
        getListBuyReceipts().add(new BuyReceipt());
    }

    public void addBuyReceipt(BuyReceipt _buyReceipt)
    {
        getListBuyReceipts().add(_buyReceipt);
        updateCurrentReceiptID(_buyReceipt.getReceiptID());
    }
    private void updateCurrentReceiptID(String _receiptID)
    {
        int currentNumber = Integer.valueOf(currentBuyReceiptID.substring(2));
        int next = Integer.valueOf(_receiptID.substring(2));

        if(currentNumber < next)
        {
            currentBuyReceiptID = _receiptID;
        }
    }

    public String getNextBuyReceiptID()
    {
        int currentNumber = Integer.valueOf(currentBuyReceiptID.substring(2));

        String nextNumber = String.valueOf(currentNumber + 1);

        if(nextNumber.length() < 4)
        {
            int currentLength = nextNumber.length();
            for(int i = 0; i < 4 - currentLength; i++)
            {
                nextNumber = "0".concat(nextNumber);
            }
        }

        String nextReceiptID = "RB".concat(nextNumber);
        return nextReceiptID;
    }

    public ObservableList<BuyReceipt> getListBuyReceipts() {
        return listBuyReceipts;
    }

    public void setListBuyReceipts(ObservableList<BuyReceipt> listBuyReceipts) {
        this.listBuyReceipts = listBuyReceipts;
    }
}
