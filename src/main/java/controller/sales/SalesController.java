package controller.sales;

import controller.App;
import model.sales.ItemOrder;
import model.sales.Receipt;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesController implements Initializable {

    @FXML private TableView<Receipt> receiptsTable;
    @FXML private TableColumn<Receipt, String> receiptIDColumn;
    @FXML private TableColumn<Receipt, String> saleDateColumn;
    @FXML private TableColumn<Receipt, String> customerColumn;
    @FXML private TableColumn<Receipt, String> cashierColumn;
    @FXML private TableColumn<Receipt, Double> totalColumn;

    @FXML private TableView<ItemOrder> detailReceiptTable;
    @FXML private TableColumn<ItemOrder, String> itemIDColumn;
    @FXML private TableColumn<ItemOrder, String> itemNameColumn;
    @FXML private TableColumn<ItemOrder, Integer> amountColumn;
    @FXML private TableColumn<ItemOrder, Double> sellingPriceColumn;
    @FXML private TableColumn<ItemOrder, Double> totalPriceColumn;

    @FXML private JFXButton searchButton;
    @FXML private JFXButton addButton;
    @FXML private JFXButton detailButton;
    @FXML private JFXButton deleteButton;

    @FXML private JFXTextField searchText;
    @FXML private JFXComboBox<String> searchType;
    @FXML private JFXDatePicker fromDate;
    @FXML private JFXDatePicker toDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureReceiptsTable();
        bindReceiptsTable();

        configureDetailReceiptTable();
    }

    public void configureReceiptsTable()
    {
        receiptIDColumn.setCellValueFactory((TableColumn.CellDataFeatures<Receipt, String> cdf) -> {
            Receipt r = cdf.getValue();
            return new SimpleStringProperty(r.getReceiptID());
        });

        saleDateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Receipt, String> cdf) -> {
            Receipt r = cdf.getValue();
            return new SimpleStringProperty(r.getDate().toString());
        });

        customerColumn.setCellValueFactory((TableColumn.CellDataFeatures<Receipt, String> cdf) -> {
            Receipt r = cdf.getValue();
            return new SimpleStringProperty(r.getCustomerName());
        });

        cashierColumn.setCellValueFactory((TableColumn.CellDataFeatures<Receipt, String> cdf) -> {
            Receipt r = cdf.getValue();
            return new SimpleStringProperty(r.getCashierName());
        });

        totalColumn.setCellValueFactory((TableColumn.CellDataFeatures<Receipt, Double> cdf) -> {
            Receipt r = cdf.getValue();
            double totalCost = r.getTotalCost();

            return new SimpleDoubleProperty(totalCost).asObject();
        });
    }

    private void bindReceiptsTable()
    {
        receiptsTable.setItems(App.dataManager.getSalesManager().getListReceipts());

        receiptsTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        receiptsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bindDetailReceiptTable(newSelection);
            }
            else
            {
                detailReceiptTable.setItems(null);
            }

        });
    }

    public void configureDetailReceiptTable()
    {
        itemIDColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, String> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleStringProperty(i.getProduct().getProductID());
        });

        itemNameColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, String> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleStringProperty(i.getProduct().getName());
        });

        amountColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Integer> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleIntegerProperty(i.getAmount()).asObject();
        });

        sellingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleDoubleProperty(i.getProduct().getSellingPrice()).asObject();
        });

        totalPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleDoubleProperty(i.calculateCost()).asObject();
        });

    }

    private void bindDetailReceiptTable(Receipt _receipt)
    {
        detailReceiptTable.setItems(_receipt.getListItems());
    }
}
