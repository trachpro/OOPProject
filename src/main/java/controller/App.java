package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class App extends Application {

    public static SceneManager sceneManager;
    public static DataManager dataManager;

    public static String defaultPath;

    @Override
    public void start(Stage primaryStage) throws Exception{


        dataManager = new DataManager();
        sceneManager = new SceneManager();

        setDefaultPath();
        System.out.println("get resource path: " + getResourcePath());

//        File f = new File(getClass().getResource("/data/products.txt").toURI());
//        FileReader fr = new FileReader(f);
//        char [] a = new char[50];
//        fr.read(a); // doc noi dung toi mang
//        for(char c : a)
//            System.out.print(c); //in tung ky tu mot
//        fr.close();



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainStage.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("MediaOne");
        Scene mainScene = new Scene(root, 1368, 700);

        sceneManager.setMainBodyPane((Pane) mainScene.lookup("#mainBody"));
        sceneManager.setPaneContent("Dashboard");

        sceneManager.setMainStage(primaryStage);

        primaryStage.setOnCloseRequest(e -> {
            e.consume();

            int selectionQuit = displayConfirmBox("Do you want to quit ?");
            if(selectionQuit == 1)
            {
                int selectionSave = displayConfirmBox("Do you want to save data ?");
                if(selectionSave == 1)
                {
                    dataManager.writeData();
                    primaryStage.close();
                }
                else if(selectionSave == 2)
                {
                    primaryStage.close();
                }
            }
        });

        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void setDefaultPath()
    {
//        File f = new File(String.valueOf(getClass().getResourceAsStream("/image/default.png")));

//        File f = new File(String.valueOf((getClass().getResource("/image/default.png"))));
//        defaultPath = f.getAbsolutePath();

        //System.out.println("default path:" + defaultPath);

        File f1 = new File("src/main/resources/image/default.png");
        defaultPath = f1.getAbsolutePath();

//        System.out.println("default path:" + f1.getAbsolutePath());
    }

    public static String[] getEnumConstants(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public static int displayConfirmBox(String _text)
    {
        AtomicInteger selection = new AtomicInteger(0);

        JFXButton yesButton;
        JFXButton noButton;
        Label textLabel;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm");
        window.setMinWidth(350);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/ConfirmBox.fxml"));
        AnchorPane confirmBoxLayout = null;
        try {
            confirmBoxLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(confirmBoxLayout);
        yesButton = (JFXButton) scene.lookup("#yesButton");
        noButton = (JFXButton) scene.lookup("#noButton");
        textLabel = (Label) scene.lookup("#textLabel");

        yesButton.setOnAction(e -> {
            selection.set(1);
            window.close();
        });

        noButton.setOnAction(e -> {
            selection.set(2);
            window.close();
        });

        textLabel.setText(_text);

        window.setScene(scene);

        window.showAndWait();
        return selection.get();
    }


//    public static void switchPane(Node newPane)
//    {
//        mainBodyPane.getChildren().removeAll(mainBodyPane.getChildren());
//        mainBodyPane.getChildren().add(newPane);
//    }

    private static String getResourcePath() {
        try {
            URI resourcePathFile = System.class.getResource("").toURI();
            String resourcePath = Files.readAllLines(Paths.get(resourcePathFile)).get(0);
            URI rootURI = new File("").toURI();
            URI resourceURI = new File(resourcePath).toURI();
            URI relativeResourceURI = rootURI.relativize(resourceURI);
            return relativeResourceURI.getPath();
        } catch (Exception e) {
            return null;
        }
    }

    public static void displayAlertingBox(String text)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert");
        window.setMinWidth(300);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/AlertBox.fxml"));
        AnchorPane alertBoxLayout = null;
        try {
            alertBoxLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(alertBoxLayout);

        Label alertText = (Label) scene.lookup("#alertText");
        JFXButton okButton =(JFXButton) scene.lookup("#okButton");

        alertText.setText(text);

        okButton.setOnAction(e -> {
            window.close();
        });

        window.setScene(scene);
        window.showAndWait();
    }

    public static String stringToAscii(String text)
    {
        String returnString ="";

        int length = text.length();
        for(int i = 0; i < length; i++)
        {
            char c = text.charAt(i);
            int j = (int) c;
            returnString = returnString.concat(String.valueOf(j)).concat(" ");
        }

        return returnString;
    }

    public static String asciiToString(String text)
    {
        String returnString = "";
        String[] parts = text.split(" ");
        int length = parts.length;
        for(int i = 0; i < length; i++)
        {
            int so = Integer.valueOf(parts[i]);
            String c = Character.toString((char) so);
            returnString = returnString.concat(c);
        }

        return returnString;
    }
}
