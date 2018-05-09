package Controller;

import Controller.Products.ProductsManager;
import Controller.Sales.SalesManager;
import Model.Product.Product;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.URISyntaxException;

public class DataManager {

    private ProductsManager productsManager;
    private SalesManager salesManager;

//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("Data/Products").getFile());

    public DataManager()
    {


        setProductsManager(new ProductsManager());
        setSalesManager(new SalesManager());

        //productsManager.getProductByID("PR0002");
//        readProductsFile();
//        writeProductsFile();
        readProductsFile();
    }

    public ProductsManager getProductsManager() {
        return productsManager;
    }

    public void setProductsManager(ProductsManager productsManager) {
        this.productsManager = productsManager;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(SalesManager salesManager) {
        this.salesManager = salesManager;
    }

    private void readProductsFile()
    {
        try {
            File file = new File("src/main/resources/Data/Products.txt");
            System.out.println(file.getAbsolutePath());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            String[] parts = line.split("|");
            System.out.println(parts.toString());


            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeProductsFile()
    {
        String comment = "# Category|productID|name|status|quantity|sellingPrice|buyingPrice|nation|imageURL|discount\n";

        try {
            File file = new File("src/main/resources/Data/Products.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            ObservableList<Product> list = productsManager.getProducts();
            list.forEach(p -> {
                try {
                    bufferedWriter.write(p.toString());
                    bufferedWriter.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
