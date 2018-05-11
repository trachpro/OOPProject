package controller.cashflow;

import controller.App;
import model.cashflow.Entry;
import model.cashflow.Expense;
import model.cashflow.Revenue;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class CashflowController implements Initializable {

    @FXML private TableView<Entry> cashflowTable;
    @FXML private TableColumn<Entry, String> dateColumn;
    @FXML private TableColumn<Entry, String> descriptionColumn;
    @FXML private TableColumn<Entry, String> revenueColumn;
    @FXML private TableColumn<Entry, String> expenseColumn;

    @FXML private Button searchButton;

    @FXML private Label totalRevenue;
    @FXML private Label totalExpense;
    @FXML private Label totalProfit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bindTableData();
    }

    private void bindTableData()
    {
        dateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            return new SimpleStringProperty( e.getDate().toString() );
        });

        descriptionColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            return new SimpleStringProperty(e.getDescription());
        });

        revenueColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            if(e instanceof Revenue) {
                return new SimpleStringProperty( String.format("%.2f",e.getAmount() ) );
            } else {
                return new SimpleStringProperty("");
            }
        });

        expenseColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            if( e instanceof Expense) {
                return new SimpleStringProperty(String.format("%.2f",e.getAmount() ));
            } else {
                return new SimpleStringProperty("");
            }

        });

        CashflowManager cashflowManager = App.dataManager.getCashflowManager();

        cashflowTable.setItems(cashflowManager.getEntries());

        totalExpense.setText(String.format("%.2f",cashflowManager.getTotalExpense()));
        totalRevenue.setText(String.format("%.2f",cashflowManager.getTotalRevenue()));
        totalProfit.setText(String.format("%.2f",cashflowManager.getTotalProfit()));
    }
}
