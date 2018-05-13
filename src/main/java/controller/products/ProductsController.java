package controller.products;

import controller.App;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.stage.StageStyle;
import model.product.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class ProductsController implements Initializable {
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, String> statusColumn;
    @FXML private TableColumn<Product, String> quantityColumn;
    @FXML private TableColumn<Product, String> sellingPriceColumn;
    @FXML private TableColumn<Product, String> buyingPriceColumn;
    @FXML private TableColumn<Product, String> discountColumn;
    @FXML private TableColumn<Product, String> nationColumn;

    @FXML private JFXButton resetButton;
    @FXML private JFXButton addButton;
    @FXML private JFXButton editButton;
    @FXML private JFXButton deleteButton;

    @FXML private JFXComboBox<String> categoryComboBox;

    @FXML private JFXTextField searchTextField;

    FilteredList<Product> filteredData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addButton.setOnAction(e -> displayAddBox());
        editButton.setOnAction(e -> editButtonOnClick());
        deleteButton.setOnAction(e -> deleteButtonOnClick());

        editButton.setDisable(true);
        deleteButton.setDisable(true);

        handleRowSelection();
        bindTableData();

        setCategoryComboBox();
        handleResetButton();
    }

    private void handleRowSelection()
    {

        productsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

//        productsTable.getSelectionModel().getSelectedItems().addListener((obs, oldSelection, newSelection) -> {
//            System.out.println("selected : " + newSelection.getProductID());
//        });

        productsTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Product> c) -> {
            int numberSelections = c.getList().toArray().length;

            System.out.println("select : "+numberSelections);
            if(numberSelections == 0)
            {
                editButton.setDisable(true);
                deleteButton.setDisable(true);
            }
            else if(numberSelections == 1)
            {
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            }
            else
            {
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            }
        });

    }

    private void bindTableData()
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
            String category = p.getCategory().toString().replace('_', ' ').toLowerCase();
            category = WordUtils.capitalizeFully(category);
            return new SimpleStringProperty(category);
        });

        statusColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String status = p.getStatus().toString();
            return new SimpleStringProperty(status);
        });

        quantityColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            int quantity = p.getQuantity();

            return new SimpleStringProperty(String.valueOf(quantity));
        });

        sellingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            double sellingPrice = p.getSellingPrice();

            return new SimpleStringProperty(String.format("%.2f",sellingPrice));
        });

        buyingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            double buyingPrice = p.getBuyingPrice();

            return new SimpleStringProperty(String.format("%.2f",buyingPrice));
        });

        discountColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            int discount = p.getDiscount();

            return new SimpleStringProperty(String.valueOf(discount + " %"));
        });

        nationColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String nation = p.getNation().toString().replace('_', ' ').toLowerCase();
            nation = WordUtils.capitalizeFully(nation);

            return new SimpleStringProperty(nation);
        });


        ObservableList<Product> listProducts = App.dataManager.getProductsManager().getProducts();
        filteredData = new FilteredList<>(listProducts, p -> true);

//        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(p -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (p.getProductID().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                } else if (p.getName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches last name.
//                }
//                return false; // Does not match.
//            });
//        });

        ObjectProperty<Predicate<Product>> nameFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Product>> categoryFilter = new SimpleObjectProperty<>();

        nameFilter.bind(Bindings.createObjectBinding(() ->
                        product -> {
                            String name = searchTextField.getText().toLowerCase();
                            if(product.getProductID().toLowerCase().contains(name) || product.getName().toLowerCase().contains(name))
                                return true;
                            else return false;
                        }, searchTextField.textProperty()));



        categoryFilter.bind(Bindings.createObjectBinding(() ->
                        product -> {
                            if(categoryComboBox.getValue() == null || Enum.valueOf(Category.class, categoryComboBox.getValue()) == product.getCategory())
                                return true;
                            else return false;
                        }, categoryComboBox.valueProperty()));

        filteredData.predicateProperty().bind(Bindings.createObjectBinding(
                () -> nameFilter.get().and(categoryFilter.get()),
                nameFilter, categoryFilter));


        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(productsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        productsTable.setItems(sortedData);
    }

    private void setCategoryComboBox()
    {
        String[] listCategory = App.getEnumConstants(Category.class);
        for(String s: listCategory)
        {
            categoryComboBox.getItems().add(s);
        }

//        categoryComboBox.getItems().add("");

//        categoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(p -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                Enum<Status> statusFilter = Enum.valueOf(Status.class, newValue);
//
//                if (p.getStatus() == statusFilter)
//                    return true; // Filter matches first name.
//
//                return false; // Does not match.
//            });
//        });
    }

    private void searchButtonOnClick()
    {
//        String _idOrName = nameTextField.getText();
//        String _category = categoryComboBox.getSelectionModel().getSelectedItem();
//        String _nation = nationComboBox.getSelectionModel().getSelectedItem();
//        boolean _isDeleted = isDeletedCheckBox.selectedProperty().get();
//        System.out.println(_idOrName + " " + _category + " " + _nation + " " + _isDeleted);

        App.dataManager.writeProductsFile();
    }

    private void displayAddBox()
    {
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New product");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/products/Update.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        UpdateProductController updateProductController = (UpdateProductController) fxmlLoader.getController();
        updateProductController.init(Mode.ADD, new Book());
        updateProductController.setParentStage(window);
        updateProductController.setParentScene(scene);

        window.setScene(scene);
        window.showAndWait();
    }

    private void displayEditBox()
    {
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit product");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/products/Update.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        UpdateProductController updateProductController = (UpdateProductController) fxmlLoader.getController();
        updateProductController.init(Mode.EDIT, productsTable.getSelectionModel().getSelectedItem());
        updateProductController.setParentStage(window);
        updateProductController.setParentScene(scene);

        window.setScene(scene);
        window.showAndWait();
    }

    private void editButtonOnClick()
    {
        System.out.println("Edit...");
        displayEditBox();
    }

    private void deleteButtonOnClick()
    {
        System.out.println("Delete...");

        ObservableList<Product> selectedList = productsTable.getSelectionModel().getSelectedItems();

        int selectedListLength = selectedList.toArray().length;

        if(selectedListLength != 0)
        {
            boolean selection = displayDeleteConfirmBox(selectedListLength);

            if(selection)
            {
//            App.dataManager.getProductsManager().getProducts().add(new product());

                selectedList.forEach(App.dataManager.getProductsManager().getProducts()::remove);
            }
        }
    }

    private boolean displayDeleteConfirmBox(int noOfSelectedRows)
    {
        AtomicBoolean selection = new AtomicBoolean(false);
        JFXButton yesButton;
        JFXButton noButton;
        Label warningLabel;

        Stage window = new Stage(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete");
        window.setMinWidth(250);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/products/Delete.fxml"));
        VBox deleteLayout = null;
        try {
            deleteLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(deleteLayout);
        yesButton = (JFXButton) scene.lookup("#yesButton");
        noButton = (JFXButton) scene.lookup("#noButton");
        warningLabel = (Label) scene.lookup("#warningLabel");

        if(noOfSelectedRows == 1)
        {
            warningLabel.setText("Are you sure to delete this product?");
        }
        else
        {
            warningLabel.setText("Are you sure to delete" + " these " + noOfSelectedRows + " product?");
        }

        yesButton.setOnAction(e -> {
            selection.set(true);
            window.close();
        });

        noButton.setOnAction(e -> {
            selection.set(false);
            window.close();
        });

        window.setScene(scene);

        window.showAndWait();
        return selection.get();
    }

    public void refreshTable()
    {
        productsTable.refresh();
    }

    private void handleResetButton()
    {
        resetButton.setOnAction(e -> {
            categoryComboBox.setValue(null);
            searchTextField.setText("");
        });

        refreshTable();
    }

}
