package controller.login;

import com.jfoenix.controls.JFXButton;
import controller.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.employee.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage;

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;

    @FXML private JFXButton loginButton;
    @FXML private JFXButton cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleLoginButton();
        handleCancelButton();
    }

    private void handleLoginButton()
    {
        loginButton.setOnAction(e -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            Employee user = App.dataManager.getLoginManager().authenticate(username, password);

            if(user != null)
            {
                stage.close();
                App.setUser(user);
                App.loadMainWindow();
            }
            else
            {
                App.displayAlertingBox("Invalid !");
            }
        });
    }

    private void handleCancelButton()
    {
        cancelButton.setOnAction(e -> {

            int selection = App.displayConfirmBox("Are you sure to quit ?");

            if(selection == 1)
            {
                stage.close();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
