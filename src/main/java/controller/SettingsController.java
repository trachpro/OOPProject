package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.employee.Employee;
import model.employee.Level;
import model.employee.Status;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML private TextField nameTextField;
    @FXML private JFXComboBox<String> statusComboBox;
    @FXML private JFXComboBox<String> levelComboBox;
    @FXML private JFXDatePicker datePicker;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField salaryTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    @FXML private JFXButton saveButton;
    @FXML private JFXButton logoutButton;

    private Employee user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = App.getUser();
        setDetails();
        handleSaveButton();
        handleLogoutButton();
    }

    private void setDetails()
    {
        idLabel.setText(user.getEmployeeID());
        nameTextField.setText(user.getName());

        statusComboBox.getItems().add(user.getStatus().toString());
        statusComboBox.setValue(user.getStatus().toString());

        levelComboBox.getItems().add(user.getLevel().toString());
        levelComboBox.setValue(user.getLevel().toString());

        datePicker.setValue(user.getDob());
        phoneNumberTextField.setText(user.getPhoneNumber());
        salaryTextField.setText(String.format("%.0f", user.getSalary()));
        usernameTextField.setText(user.getUsername());
        passwordTextField.setText(user.getPassword());

        nameTextField.textProperty().addListener((observation, oldValue, newValue) -> {
            if(newValue != oldValue || !newValue.equals(user.getName()))
            {
                saveButton.setDisable(false);
            }
            else saveButton.setDisable(true);
        });

        phoneNumberTextField.textProperty().addListener((observation, oldValue, newValue) -> {
            if(newValue != oldValue || !newValue.equals(user.getPhoneNumber()))
            {
                saveButton.setDisable(false);
            }
            else saveButton.setDisable(true);
        });

        passwordTextField.textProperty().addListener((observation, oldValue, newValue) -> {
            if(newValue != oldValue || !newValue.equals(user.getPassword()))
            {
                saveButton.setDisable(false);
            }
            else saveButton.setDisable(true);
        });

        datePicker.valueProperty().addListener((observation, oldValue, newValue) -> {
            if(newValue != oldValue || !newValue.isEqual(user.getDob()))
            {
                saveButton.setDisable(false);
            }
            else saveButton.setDisable(true);
        });
    }

    private void handleSaveButton()
    {
        saveButton.setOnAction(e -> {
            Employee changedUser = new Employee();
            changedUser.setEmployeeID(idLabel.getText());
            changedUser.setName(nameTextField.getText());
            changedUser.setStatus(Status.valueOf(statusComboBox.getValue()));
            changedUser.setLevel(Level.valueOf(levelComboBox.getValue()));
            changedUser.setDob(datePicker.getValue());
            changedUser.setPhoneNumber(phoneNumberTextField.getText());
            changedUser.setSalary(Double.valueOf(salaryTextField.getText()));
            changedUser.setUsername(usernameTextField.getText());
            changedUser.setPassword(passwordTextField.getText());

            App.dataManager.getEmployeesManager().addUpdateEmployee(changedUser);
            user = changedUser;
            App.setUser(changedUser);

            saveButton.setDisable(true);
        });
    }

    private void handleLogoutButton()
    {
        logoutButton.setOnAction(e -> {
            App.logout();
        });
    }

}
