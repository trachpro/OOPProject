package controller.inventory;

import controller.App;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.receipts.ItemOrder;
import model.receipts.BuyReceipt;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class InventoryController implements Initializable {

    @FXML private TableView<BuyReceipt> buyReceiptsTable;
    @FXML private TableColumn<BuyReceipt, String> receiptIDColumn;
    @FXML private TableColumn<BuyReceipt, String> dateColumn;
    @FXML private TableColumn<BuyReceipt, String> supplierColumn;
    @FXML private TableColumn<BuyReceipt, String> purchaserColumn;
    @FXML private TableColumn<BuyReceipt, Double> totalColumn;

    @FXML private TableView<ItemOrder> detailReceiptTable;
    @FXML private TableColumn<ItemOrder, String> itemIDColumn;
    @FXML private TableColumn<ItemOrder, String> itemNameColumn;
    @FXML private TableColumn<ItemOrder, Integer> amountColumn;
    @FXML private TableColumn<ItemOrder, Double> buyingPriceColumn;
    @FXML private TableColumn<ItemOrder, Double> totalPriceColumn;

    @FXML private JFXButton resetButton;
    @FXML private JFXButton addButton;
    @FXML private JFXButton saveButton;

    @FXML private JFXTextField searchText;
    @FXML private JFXDatePicker fromDate;
    @FXML private JFXDatePicker toDate;

    @FXML private Label totalCostLabel;
    @FXML private TextArea remarkTextArea;

    FilteredList<BuyReceipt> filteredData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureReceiptsTable();
        bindReceiptsTable();

        configureDetailReceiptTable();

        addButton.setOnAction(e -> {
            displayAddReceiptBox();
        });

        handleTextArea();
        handleSaveButton();
        handleResetButton();
    }

    public void bindReceiptsTable()
    {
        receiptIDColumn.setCellValueFactory((TableColumn.CellDataFeatures<BuyReceipt, String> cdf) -> {
            BuyReceipt r = cdf.getValue();
            return new SimpleStringProperty(r.getReceiptID());
        });

        dateColumn.setCellValueFactory((TableColumn.CellDataFeatures<BuyReceipt, String> cdf) -> {
            BuyReceipt r = cdf.getValue();
            return new SimpleStringProperty(r.getDate().toString());
        });

        supplierColumn.setCellValueFactory((TableColumn.CellDataFeatures<BuyReceipt, String> cdf) -> {
            BuyReceipt r = cdf.getValue();
            return new SimpleStringProperty(r.getSupplierName());
        });

        purchaserColumn.setCellValueFactory((TableColumn.CellDataFeatures<BuyReceipt, String> cdf) -> {
            BuyReceipt r = cdf.getValue();
            return new SimpleStringProperty(r.getPurchaserName());
        });

        totalColumn.setCellValueFactory((TableColumn.CellDataFeatures<BuyReceipt, Double> cdf) -> {
            BuyReceipt r = cdf.getValue();
            double totalCost = r.getTotalCost();

            return new SimpleDoubleProperty(totalCost).asObject();
        });

        ObservableList<BuyReceipt> listBuyReceipts = App.dataManager.getInventoryManager().getListBuyReceipts();
        filteredData = new FilteredList<>(listBuyReceipts, p -> true);

        ObjectProperty<Predicate<BuyReceipt>> nameFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<BuyReceipt>> fromDateFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<BuyReceipt>> toDateFilter = new SimpleObjectProperty<>();

        nameFilter.bind(Bindings.createObjectBinding(() ->
                buyReceipt -> {
                    String text = searchText.getText().toLowerCase();
                    if(text.equals("")) return true;
                    return (buyReceipt.getReceiptID().toLowerCase().contains(text) || buyReceipt.getPurchaserName().toLowerCase().contains(text) || buyReceipt.getSupplierName().toLowerCase().contains(text));
                }, searchText.textProperty()));


        fromDateFilter.bind(Bindings.createObjectBinding(() ->
                buyReceipt -> {
                    LocalDate date = fromDate.getValue();
                    if(date == null) return true;

                    return (buyReceipt.getDate().isAfter(date) || buyReceipt.getDate().isEqual(date));

                }, fromDate.valueProperty()));

        toDateFilter.bind(Bindings.createObjectBinding(() ->
                buyReceipt -> {
                    LocalDate date = toDate.getValue();

                    if(date == null) return true;

                    return (buyReceipt.getDate().isBefore(date) || buyReceipt.getDate().isEqual(date));

                }, toDate.valueProperty()));

        filteredData.predicateProperty().bind(Bindings.createObjectBinding(
                () -> nameFilter.get().and(fromDateFilter.get()).and(toDateFilter.get()),
                nameFilter, fromDateFilter, toDateFilter));


        SortedList<BuyReceipt> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(buyReceiptsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        buyReceiptsTable.setItems(sortedData);

    }

    private void configureReceiptsTable()
    {
        buyReceiptsTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        buyReceiptsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bindDetailReceiptTable(newSelection);
                setTotalCostLabel(newSelection.getTotalCost());
                setRemarkTextArea(newSelection.getRemark());

                remarkTextArea.setDisable(false);

                System.out.println(newSelection.toString());
            }
            else
            {
                detailReceiptTable.setItems(null);
                setTotalCostLabel(0.0);
                setRemarkTextArea("");
                saveButton.setDisable(true);
                remarkTextArea.setDisable(true);
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

        buyingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleDoubleProperty(i.getProduct().getSellingPrice()).asObject();
        });

        totalPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();
            double cost = i.getProduct().getSellingPrice() * i.getAmount() * (1 - (i.getProduct().getDiscount() / 100));
            return new SimpleDoubleProperty(cost).asObject();
        });

    }

    private void bindDetailReceiptTable(BuyReceipt _sellReceipt)
    {
        ObservableList<ItemOrder> obsListItems = FXCollections.observableArrayList(_sellReceipt.getListItems());
        detailReceiptTable.setItems(obsListItems);
    }


    private void displayAddReceiptBox()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New BuyReceipt");
        window.setMinWidth(1210);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventory/AddBuyReceipt.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        AddBuyReceiptController addBuyReceiptController = (AddBuyReceiptController) fxmlLoader.getController();
        addBuyReceiptController.setParentStage(window);

        window.setScene(scene);
        window.showAndWait();
    }

    private void setTotalCostLabel(Double _cost)
    {
        totalCostLabel.setText(String.valueOf(_cost));
    }

    private void setRemarkTextArea(String _text)
    {
        remarkTextArea.setText(_text);
    }

    private void handleTextArea()
    {
        remarkTextArea.textProperty().addListener((observable, oldValue, newValue) -> {

            BuyReceipt currentSelected = buyReceiptsTable.getSelectionModel().getSelectedItem();

            if(!newValue.equals(oldValue) && !newValue.equals(currentSelected.getRemark()))
            {
                saveButton.setDisable(false);
            }
            else saveButton.setDisable(true);
        });
    }

    private void handleSaveButton()
    {
        saveButton.setOnAction(e -> {
            BuyReceipt currentSelected = buyReceiptsTable.getSelectionModel().getSelectedItem();
            currentSelected.setRemark(remarkTextArea.getText());
            saveButton.setDisable(true);
        });
    }

    private void handleResetButton()
    {
        resetButton.setOnAction(e -> {
            searchText.setText("");
            fromDate.setValue(null);
            toDate.setValue(null);
        });
    }
}
