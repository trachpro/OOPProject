package controller.expenses;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.App;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
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
import model.expense.Expense;
import model.receipts.ItemOrder;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ExpensesController implements Initializable {

    @FXML
    private TableView<Expense> expensesTable;
    @FXML private TableColumn<Expense, String> expenseIDColumn;
    @FXML private TableColumn<Expense, String> purchaseDateColumn;
    @FXML private TableColumn<Expense, String> purchaserColumn;
    @FXML private TableColumn<Expense, String> costColumn;


    @FXML private JFXButton resetButton;
    @FXML private JFXButton addButton;

    @FXML private JFXTextField searchText;
    @FXML private JFXDatePicker fromDate;
    @FXML private JFXDatePicker toDate;

    @FXML private Label expenseIDLabel;
    @FXML private Label costLabel;
    @FXML private JFXDatePicker purchaseDate;
    @FXML private Label purchaserLabel;
    @FXML private TextArea remarkTextArea;

    FilteredList<Expense> filteredData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        configureExpensesTable();
        bindExpensesTable();

        handleResetButton();
        addButton.setOnAction(e -> {
            displayAddExpenseBox();
        });
    }

    public void bindExpensesTable()
    {
        expenseIDColumn.setCellValueFactory((TableColumn.CellDataFeatures<Expense, String> cdf) -> {
            Expense e = cdf.getValue();
            return new SimpleStringProperty(e.getExpenseID());
        });

        purchaseDateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Expense, String> cdf) -> {
            Expense e = cdf.getValue();
            return new SimpleStringProperty(e.getPurchaseDate().toString());
        });

        purchaserColumn.setCellValueFactory((TableColumn.CellDataFeatures<Expense, String> cdf) -> {
            Expense e = cdf.getValue();
            return new SimpleStringProperty(e.getPurchaser());
        });

        costColumn.setCellValueFactory((TableColumn.CellDataFeatures<Expense, String> cdf) -> {
            Expense e = cdf.getValue();
            return new SimpleStringProperty(String.format("%.0f",e.getCost()));
        });


        ObservableList<Expense> listExpenses = App.dataManager.getExpensesManager().getListExpenses();
        filteredData = new FilteredList<>(listExpenses, p -> true);

        ObjectProperty<Predicate<Expense>> nameFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Expense>> fromDateFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Expense>> toDateFilter = new SimpleObjectProperty<>();

        nameFilter.bind(Bindings.createObjectBinding(() ->
                expense -> {
                    String text = searchText.getText().toLowerCase();
                    if(text.equals("")) return true;
                    return (expense.getExpenseID().toLowerCase().contains(text) || expense.getPurchaser().toLowerCase().contains(text) || expense.getRemark().toLowerCase().contains(text));
                }, searchText.textProperty()));


        fromDateFilter.bind(Bindings.createObjectBinding(() ->
                expense -> {
                    LocalDate date = fromDate.getValue();
                    if(date == null) return true;

                    return (expense.getPurchaseDate().isAfter(date) || expense.getPurchaseDate().isEqual(date));
                }, fromDate.valueProperty()));

        toDateFilter.bind(Bindings.createObjectBinding(() ->
                expense -> {
                    LocalDate date = toDate.getValue();
                    if(date == null) return true;

                    return (expense.getPurchaseDate().isBefore(date) || expense.getPurchaseDate().isEqual(date));
                }, toDate.valueProperty()));

        filteredData.predicateProperty().bind(Bindings.createObjectBinding(
                () -> nameFilter.get().and(fromDateFilter.get()).and(toDateFilter.get()),
                nameFilter, fromDateFilter, toDateFilter));


        SortedList<Expense> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(expensesTable.comparatorProperty());

        expensesTable.setItems(sortedData);
    }

    private void configureExpensesTable()
    {
        expensesTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        expensesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bindDetailExpense(newSelection);
            }
            else
            {
                bindDetailExpense(new Expense());
            }
        });
    }

    private void bindDetailExpense(Expense _expense)
    {
        expenseIDLabel.setText(_expense.getExpenseID());
        costLabel.setText(String.format("%.0f",_expense.getCost()));
        purchaseDate.setValue(_expense.getPurchaseDate());
        purchaserLabel.setText(_expense.getPurchaser());
        remarkTextArea.setText(_expense.getRemark());
    }


    private void displayAddExpenseBox()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Expense");
        window.setMinWidth(600);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/expenses/AddExpense.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        AddExpenseController addExpenseController = (AddExpenseController) fxmlLoader.getController();
        addExpenseController.setStage(window);

        window.setScene(scene);
        window.showAndWait();
    }

    private void handleResetButton()
    {
        resetButton.setOnAction(e -> {
            searchText.setText("");
            fromDate.setValue(null);
            toDate.setValue(null);
        });
    }

    public void setDates(LocalDate from, LocalDate to) {
        fromDate.setValue(from);
        toDate.setValue(to);
    }
}
