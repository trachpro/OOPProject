package controller.products;

import controller.App;
import controller.FinalPaths;
import model.product.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateProductController {
    private Stage parentStage;
    private Scene parentScene;
    private FileChooser fileChooser;

    private Enum<Mode> mode;
    private Product product;

    @FXML private AnchorPane detailAnchorPane;

    @FXML private JFXTextField idTextField;
    @FXML private JFXTextField nameTextField;
    @FXML private JFXComboBox<String> categoryComboBox;
    @FXML private JFXComboBox<String> statusComboBox;
    @FXML private JFXTextField buyingPriceTextField;
    @FXML private JFXTextField sellingPriceTextField;
    @FXML private JFXTextField quantityTextField;
    @FXML private JFXTextField discountTextField;
    @FXML private JFXComboBox<String> nationComboBox;

    @FXML private ImageView image;

    @FXML private JFXButton fileButton;
    @FXML private JFXButton okButton;
    @FXML private JFXButton cancelButton;

    private FXMLLoader updateBookLoader;
    private FXMLLoader updateMusicDiscLoader;
    private FXMLLoader updateMovieDiscLoader;

    private Node updateBookNode;
    private Node updateMusicDiscNode;
    private Node updateMovieDiscNode;

    private String currentImgUrl;

    public void init(Enum<Mode> _mode, Product _product) {

        product = _product;
        mode = _mode;

        currentImgUrl = App.defaultPath;

        handleIdTextField();
        handleNameTextField();
        handleCategoryComboBox();
        handleStatusComboBox();
        handleNationComboBox();
        handleFileButton();

        handleTextFields();

        handleOkButton();
        handleCancelButton();
        handleImageView();

        initializeLoaders();

        loadDetailPane(product.getCategory());

//        try {
//            File f = new File(getClass().getResource("/image/default.png").toURI());
//            currentImgUrl = f.getAbsolutePath();
//            System.out.println("1: "+currentImgUrl);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        //File f = new File();
//        image g = new image("/image/default.png");
//        image.setImage(g);
    }

    private void loadDetailPane(Enum<Category> _category)
    {
        detailAnchorPane.getChildren().removeAll(detailAnchorPane.getChildren());

        if(_category == Category.BOOK)
        {
            detailAnchorPane.getChildren().add(updateBookNode);

            UpdateBookController updateBookController = updateBookLoader.getController();

            if(mode == Mode.ADD) product = new Book();

            updateBookController.init(detailAnchorPane, ((Book) product));
        }

        else if(_category == Category.MOVIE_DISC)
        {
            detailAnchorPane.getChildren().add(updateMovieDiscNode);

            UpdateMovieDiscController updateMovieDiscController = updateMovieDiscLoader.getController();

            if(mode == Mode.ADD) product = new MovieDisc();

            updateMovieDiscController.init(detailAnchorPane, ((MovieDisc) product));
        }

        else if(_category == Category.MUSIC_DISC)
        {
            detailAnchorPane.getChildren().add(updateMusicDiscNode);
            UpdateMusicDiscController updateMusicDiscController = updateMusicDiscLoader.getController();

            if(mode == Mode.ADD) product = new MusicDisc();

            updateMusicDiscController.init(detailAnchorPane, ((MusicDisc) product));
        }

    }

    private void initializeLoaders()
    {
        updateBookLoader = new FXMLLoader(getClass().getResource(FinalPaths.PRODUCTS_UPDATE_BOOK));

        updateMovieDiscLoader = new FXMLLoader(getClass().getResource(FinalPaths.PRODUCTS_UPDATE_MOVIE_DISC));

        updateMusicDiscLoader = new FXMLLoader(getClass().getResource(FinalPaths.PRODUCTS_UPDATE_MUSIC_DISC));

        try {
            updateBookNode = updateBookLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            updateMovieDiscNode = updateMovieDiscLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            updateMusicDiscNode = updateMusicDiscLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleIdTextField()
    {
        if(mode == Mode.ADD)
        {
            idTextField.setText(App.dataManager.getProductsManager().getNextProductID());
        }
        else
        {
            idTextField.setText(product.getProductID());
        }
    }

    private void handleNameTextField()
    {
        nameTextField.setText(product.getName());
    }

    private void handleCategoryComboBox()
    {
        String listCategories[] = App.getEnumConstants(Category.class);
        for(String c: listCategories)
        {
            categoryComboBox.getItems().add(c);
        }

        categoryComboBox.setValue(product.getCategory().toString());

        if(mode == Mode.ADD)
        {
            categoryComboBox.setOnAction(e -> {
                String currentCategory = categoryComboBox.getValue();
                loadDetailPane(Enum.valueOf(Category.class, currentCategory));
            });
        }
        else
        {
            categoryComboBox.setDisable(true);
        }

    }

    private void handleStatusComboBox()
    {
        String listStatus[] = App.getEnumConstants(Status.class);
        for(String c: listStatus)
        {
            statusComboBox.getItems().add(c);
        }

        statusComboBox.setValue(product.getStatus().toString());
    }

    private void handleTextFields()
    {
        buyingPriceTextField.setText(String.format("%.0f",product.getBuyingPrice()));
        sellingPriceTextField.setText(String.format("%.0f",product.getSellingPrice()));
        quantityTextField.setText(String.valueOf(product.getQuantity()));
        discountTextField.setText(String.valueOf(product.getDiscount()));
    }

    private void handleNationComboBox()
    {
        String listNations[] = App.getEnumConstants(Nation.class);
        for(String c: listNations)
        {
            nationComboBox.getItems().add(c);
        }

        nationComboBox.setValue(product.getNation().toString());
    }

    private void handleOkButton()
    {
        okButton.setOnAction(e -> {

            String text = "Are you sure to *** this product?";
            if(mode == Mode.ADD) text = text.replace("***", "add");
            else text = text.replace("***", "update");

            int selection = App.displayConfirmBox(text);

            if(selection == 1)
            {
                Product result = getProduct();

                if(mode == Mode.ADD) App.dataManager.getProductsManager().addProduct(result);
                else App.dataManager.getProductsManager().addUpdateProduct(result);


                if(result.getCategory() == Category.BOOK)
                {
                    Book book = (Book) result;
                    book.printDetail();
                }
                else if(result.getCategory() == Category.MOVIE_DISC)
                {
                    MovieDisc movieDisc = (MovieDisc) result;
                    movieDisc.printDetail();
                }
                else
                {
                    MusicDisc musicDisc = (MusicDisc) result;
                    musicDisc.printDetail();
                }

                getParentStage().close();

                ProductsController pc = App.sceneManager.getLoader("Products").getController();
                pc.refreshTable();
            }
        });
    }

    private void handleCancelButton()
    {
        cancelButton.setOnAction(e -> {
            String text = "Are you sure to cancel";
            if(mode == Mode.ADD)
            {
                text = text.concat(" adding this new product ?");
            }
            else text = text.concat(" updating this product ?");

            int selection = App.displayConfirmBox(text);

            if(selection == 1)
            {
                System.out.println("Cancel the process");
                getParentStage().close();
            }
        });
    }

    private void handleFileButton()
    {
        fileChooser = new FileChooser();

        fileButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(getParentStage());
                    if (file != null) {

                        currentImgUrl = file.getAbsolutePath();
                        //System.out.println("current img url :" +currentImgUrl);
                        setImage(file);

//                        try {
//                            saveImage(currentImgUrl, "hung");
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
                    }
                }
            }
        );
    }

    private void handleImageView()
    {
        File f = new File("src/main/resources/image/" + product.getImageUrl());
//        image g = new image(System.class.getResourceAsStream("/image/"+product.getImageUrl()));
        Image g = new Image(f.toURI().toString());
        image.setImage(g);
    }

    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Choose Picture");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void setImage(File file) {
        Image i = new Image(file.toURI().toString());
//        image i = new image(file.getAbsolutePath());
        image.setImage(i);
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

//    private void setProduct()
//    {
//        idTextField.setText(product.getProductID());
//        nameTextField.setText(product.getName());
//
//    }

    public Product getProduct()
    {
        String currentCategory = categoryComboBox.getValue();

        Product result = new Product();

        result.setProductID(idTextField.getText());
        result.setName(nameTextField.getText());
        result.setCategory(Enum.valueOf(Category.class, categoryComboBox.getValue()));
        result.setStatus(Enum.valueOf(Status.class, statusComboBox.getValue()));
        result.setBuyingPrice(Double.valueOf(buyingPriceTextField.getText()));
        result.setSellingPrice(Double.valueOf(sellingPriceTextField.getText()));
        result.setQuantity(Integer.valueOf(quantityTextField.getText()));
        result.setDiscount(Integer.valueOf(discountTextField.getText()));
        result.setNation(Enum.valueOf(Nation.class, nationComboBox.getValue()));

        try {
            String imgUrl = saveImage(currentImgUrl, result.getProductID());
            result.setImageUrl(imgUrl);
        } catch (IOException e1) {
            e1.printStackTrace();
            result.setImageUrl("default.png");
        }

        if(currentCategory.equals(Category.BOOK.toString()))
        {
            UpdateBookController updateBookController = updateBookLoader.getController();
            Book detailedBook = updateBookController.getDetailedBook(result);

            return (Product) detailedBook;
        }

        else if(currentCategory.equals(Category.MOVIE_DISC.toString()))
        {
            UpdateMovieDiscController updateMovieDiscController = updateMovieDiscLoader.getController();
            MovieDisc detailedMovieDisc = updateMovieDiscController.getDetailedMovieDisc(result);

            return (Product) detailedMovieDisc;
        }

        else
        {
            UpdateMusicDiscController updateMusicDiscController = updateMusicDiscLoader.getController();
            MusicDisc detailedMusicDisc = updateMusicDiscController.getDetailedMovieDisc(result);

            return (Product) detailedMusicDisc;
        }
    }

    private String saveImage(String sourcePath, String destPath) throws IOException {

        if(sourcePath.equals(App.defaultPath))
        {
            return "default.png";
        }

//        File f = File.createTempFile("/image/destPath", "png");
//        OutputStream f1 = new FileOutputStream(f);
//        Files.copy(Paths.get(sourcePath), f1);
//        f1.close();


        String relativeImgUrl;

        if((sourcePath.indexOf(".jpg") == (sourcePath.length() - 4)) || (sourcePath.indexOf(".JPG") == (sourcePath.length() - 4)))
        {
            destPath = destPath.concat(".jpg");
        }
        else if( (sourcePath.indexOf(".png") == (sourcePath.length() - 4)) || (sourcePath.indexOf(".PNG") == (sourcePath.length() - 4)))
        {
            destPath = destPath.concat(".png");
        }

        relativeImgUrl = destPath;

//        InputStream in = getClass().getResourceAsStream("/image/"+relativeImgUrl);
//        BufferedWriter bw = new BufferedWriter(new InputStreamW);

        System.out.println(destPath);

//    src/main/resources
        destPath = "src/main/resources/image/".concat(destPath);
        //File f = File.createTempFile("/image/")

        OutputStream destFile = new FileOutputStream(destPath);
        Files.copy(Paths.get(sourcePath), destFile);

//        OutputStream destFile1 = new FileOutputStream("out/artifacts/MediaOne_jar/MediaOne/image/"+relativeImgUrl);
//        Files.copy(Paths.get(sourcePath), destFile1);
//        getClass().getResourceAsStream("/image/"+product.getImageUrl()
//
//        File temp = new File(String.valueOf(System.class.getResource("/image/"+relativeImgUrl)));
//        //image g =  new image(getClass().getResourceAsStream("/image/"+relativeImgUrl));
//
//        if(temp.createNewFile())
//        {
//            System.out.println("Ok created");
//        }
//        else System.out.println("failed created");
//
//        OutputStream destFile1 = new FileOutputStream(temp);
//        Files.copy(Paths.get(sourcePath), destFile1);

        destFile.close();

        return relativeImgUrl;
    }

    public Scene getParentScene() {
        return parentScene;
    }

    public void setParentScene(Scene parentScene) {
        this.parentScene = parentScene;
    }
}
