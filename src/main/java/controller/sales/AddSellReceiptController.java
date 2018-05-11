package controller.sales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.App;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.product.Product;
import model.product.Status;
import model.receipts.Category;
import model.receipts.ItemOrder;
import model.receipts.SellReceipt;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class AddSellReceiptController implements Initializable {

    private Stage parentStage;

    @FXML private JFXTextField searchText;
    @FXML private JFXComboBox<String> categorySearchComboBox;

    @FXML private JFXButton addButton;
    @FXML private JFXButton deleteButton;
    @FXML private JFXButton saveButton;
    @FXML private JFXButton cancelButton;

    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, String> statusColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;
    @FXML private TableColumn<Product, Double> sellingPriceColumn;
    @FXML private TableColumn<Product, Integer> discountColumn;

    @FXML private TableView<ItemOrder> itemsTable;
    @FXML private TableColumn<ItemOrder, String> idItemColumn;
    @FXML private TableColumn<ItemOrder, String> nameItemColumn;
    @FXML private TableColumn<ItemOrder, String> categoryItemColumn;
    @FXML private TableColumn<ItemOrder, Integer> amountItemColumn;
    @FXML private TableColumn<ItemOrder, Double> sellingPriceItemColumn;
    @FXML private TableColumn<ItemOrder, Double> costColumn;
    @FXML private TableColumn<ItemOrder, String> discountItemColumn;
    @FXML private TableColumn<ItemOrder, Double> finalCostColumn;

    @FXML private Label receiptIDLabel;
    @FXML private TextField customerTextField;
    @FXML private JFXDatePicker datePicker;
    @FXML private Label cashierLabel;
    @FXML private TextArea remarkTextArea;
    @FXML private Label costLabel;
    @FXML private Label discountLabel;
    @FXML private Label totalCostLabel;

    ObservableList<ItemOrder> listItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listItems = FXCollections.observableArrayList();

        bindItemsTable();
        bindProductsTable();
        handleProductsTableSelection();
        handleItemsTableSelection();
        handleAddButton();
        handleSaveButton();
        handleCancelButton();
        handleDeleteButton();

        setSellDate(LocalDate.now());

        setReceiptIDLabel();
        setCashierLabel();
    }

    private void bindItemsTable()
    {
        idItemColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, String> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleStringProperty(i.getProduct().getProductID());
        });

        nameItemColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, String> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleStringProperty(i.getProduct().getName());
        });

        categoryItemColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, String> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleStringProperty(i.getProduct().getCategory().toString());
        });

        amountItemColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Integer> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleIntegerProperty(i.getAmount()).asObject();
        });

        sellingPriceItemColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();
            return new SimpleDoubleProperty(i.getProduct().getSellingPrice()).asObject();
        });

        costColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();
            double cost = i.getAmount() * i.getProduct().getSellingPrice();
            return new SimpleDoubleProperty(cost).asObject();
        });

        discountItemColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, String> cdf) -> {
            ItemOrder i = cdf.getValue();
            int discount = i.getProduct().getDiscount();
            return new SimpleStringProperty(String.valueOf(discount+" %"));
        });

        finalCostColumn.setCellValueFactory((TableColumn.CellDataFeatures<ItemOrder, Double> cdf) -> {
            ItemOrder i = cdf.getValue();

            double cost = i.getAmount() * i.getProduct().getSellingPrice();
            int discount = i.getProduct().getDiscount();

            double finalCost = cost * (1.0 - 1.0*discount / 100);

            return new SimpleDoubleProperty(finalCost).asObject();
        });

        itemsTable.setItems(listItems);
    }

    private void bindProductsTable()
    {
        idColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            return new SimpleStringProperty(p.getProductID());
        });

        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            return new SimpleStringProperty(p.getName());
        });

        categoryColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            return new SimpleStringProperty(p.getCategory().toString());
        });

        statusColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String status = p.getStatus().toString();
            return new SimpleStringProperty(status);
        });

        quantityColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Integer> cdf) -> {
            Product p = cdf.getValue();
            int quantity = p.getQuantity();

            return new SimpleIntegerProperty(quantity).asObject();
        });

        sellingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Double> cdf) -> {
            Product p = cdf.getValue();
            double sellingPrice = p.getSellingPrice();

            return new SimpleDoubleProperty(sellingPrice).asObject();
        });

        discountColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Integer> cdf) -> {
            Product p = cdf.getValue();
            int discount = p.getDiscount();

            return new SimpleIntegerProperty(discount).asObject();
        });

        ObservableList<Product> listProducts = App.dataManager.getProductsManager().getProducts();
        FilteredList<Product> filteredData = new FilteredList<>(listProducts, p -> true);

        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (p.getProductID().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (p.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(productsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        productsTable.setItems(sortedData);

    }

    private void handleProductsTableSelection()
    {
        productsTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        productsTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Product> c) -> {
            int numberSelections = c.getList().toArray().length;

            if(numberSelections == 0)
                addButton.setDisable(true);
            else
                addButton.setDisable(false);
        });

        productsTable.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                   addItem(row.getItem());
                }
            });
            return row ;
        });
    }

    private void handleItemsTableSelection()
    {
        itemsTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        itemsTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends ItemOrder> c) -> {
            int numberSelections = c.getList().toArray().length;

            if(numberSelections == 0)
                deleteButton.setDisable(true);
            else
                deleteButton.setDisable(false);
        });
    }

    private int displayAmountBox(int maxNumber)
    {
        AtomicInteger selectedNumber = new AtomicInteger(0);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Amount");
        window.setMinWidth(300);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/sales/AmountBox.fxml"));
        AnchorPane amountLayout = null;
        try {
            amountLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(amountLayout);

        JFXButton okButton =(JFXButton) scene.lookup("#okButton");
        JFXButton cancelButton =(JFXButton) scene.lookup("#cancelButton");
        JFXComboBox<Integer> amountComboBox = (JFXComboBox<Integer>) scene.lookup("#amountComboBox");

        okButton.setOnAction(e -> {
            selectedNumber.set(amountComboBox.getValue());
            window.close();
        });

        for(int i = 1; i <= maxNumber; i++)
        {
            amountComboBox.getItems().add(i);
        }
        amountComboBox.setValue(1);

        cancelButton.setOnAction(e -> {
            selectedNumber.set(0);
            window.close();
        });

        window.setScene(scene);
        window.showAndWait();

        return selectedNumber.get();
    }

    private void handleAddButton()
    {
        addButton.setOnAction(e -> {
            Product p = productsTable.getSelectionModel().getSelectedItem();
            addItem(p);
        });
    }

    private void addItem(Product p)
    {
        int amountSelected = 0;

        if(p.getQuantity() < 1 || (p.getStatus() != Status.ACTIVE))
        {
            App.displayAlertingBox("This product is unavailable !");
        }
        else
        {
            amountSelected = displayAmountBox(p.getQuantity());

            if(amountSelected > 0)
            {
                System.out.println(p.toString() + "\nselected amount "+amountSelected);

                boolean isExisted = false;

                for(ItemOrder i: listItems)
                {
                    if(i.getProduct() == p)
                    {
                        i.setAmount(i.getAmount() + amountSelected);
                        p.setQuantity(p.getQuantity() - amountSelected);
                        isExisted = true;
                        break;
                    }
                }

                if(!isExisted)
                {
                    listItems.add(new ItemOrder(p, amountSelected));
                    p.setQuantity(p.getQuantity() - amountSelected);
                }

                refresh();
            }
        }
    }

    private void handleClosing()
    {
        int nSelected = listItems.size();
        if(nSelected == 0) parentStage.close();
        else {
            boolean select = App.displayConfirmBox("Are you sure to cancel ?");
            if (select) {
                listItems.forEach(i -> {
                    i.getProduct().setQuantity(i.getProduct().getQuantity() + i.getAmount());
                });

                parentStage.close();
            }
        }
    }
    private void handleCancelButton()
    {
        cancelButton.setOnAction(e -> {
            handleClosing();
        });
    }

    public void setParentStage(Stage _stage)
    {
        parentStage = _stage;
        _stage.setOnCloseRequest(e -> {
            e.consume();
            handleClosing();
        });
    }

    private void handleSaveButton()
    {
        saveButton.setOnAction(e -> {

            int nSelected = listItems.size();
            if(nSelected == 0) App.displayAlertingBox("Nothing to save !");
            else {
                Boolean saving = App.displayConfirmBox("Are you sure to save ?");
                if (saving) {
                    handleSaving();
                    parentStage.close();
                }
            }
        });
    }

    private void handleSaving()
    {
        SellReceipt r;
        String _receiptID = receiptIDLabel.getText();
        String _customer = customerTextField.getText();
        LocalDate _date = datePicker.getValue();
        String _cashier = cashierLabel.getText();
        String _remark = remarkTextArea.getText();
        Double _totalCost = Double.valueOf(totalCostLabel.getText());

        ArrayList<ItemOrder> _listItems = new ArrayList<>();

        for(ItemOrder i: listItems)
        {
            ItemOrder _i = new ItemOrder(new Product(i.getProduct()), i.getAmount());
            _listItems.add(_i);
        }
        r = new SellReceipt(_receiptID, Category.SELL, _customer, _cashier, _totalCost, _date, _remark, _listItems);

        App.dataManager.getSalesManager().addSellReceipt(r);
    }

    private void setReceiptIDLabel()
    {
        receiptIDLabel.setText(App.dataManager.getSalesManager().getNextSellReceiptID());
    }

    private void setCashierLabel()
    {
        cashierLabel.setText("Doan Sy Hung");
    }

    private void setCostLabel(double cost)
    {
        costLabel.setText(String.valueOf(cost));
    }

    private void setDiscountLabel(double discount)
    {
        discountLabel.setText(String.valueOf(discount));
    }

    private void setTotalCostLabel(double totalCost)
    {
        totalCostLabel.setText(String.valueOf(totalCost));
    }

    private void setSellDate(LocalDate _date)
    {
        datePicker.setValue(_date);
    }

    private void refresh()
    {
        productsTable.refresh();
        itemsTable.refresh();

        double initialCost = 0.0;
        double discount = 0.0;
        double totalCost = 0.0;

        for(ItemOrder i : listItems)
        {
            double originalCost = i.getProduct().getSellingPrice() * i.getAmount();
            initialCost += originalCost;
            discount += (originalCost * (1.0 * i.getProduct().getDiscount() / 100));
        }

        totalCost = initialCost - discount;

        setCostLabel(initialCost);
        setDiscountLabel(discount);
        setTotalCostLabel(totalCost);
    }


    private void handleDeleteButton()
    {
        deleteButton.setOnAction(e -> {
            ItemOrder i = itemsTable.getSelectionModel().getSelectedItem();
            i.getProduct().setQuantity(i.getProduct().getQuantity() + i.getAmount());
            listItems.remove(i);
            refresh();
        });
    }
}

