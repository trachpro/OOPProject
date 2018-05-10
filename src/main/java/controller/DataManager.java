package controller;

import controller.products.ProductsManager;
import controller.sales.SalesManager;
import controller.cashflow.CashflowManager;
import model.product.Product;
import javafx.collections.ObservableList;

import java.io.*;

public class DataManager {

    private ProductsManager productsManager;
    private SalesManager salesManager;
    private CashflowManager cashflowManager;

    public CashflowManager getCashflowManager() {
        return cashflowManager;
    }
    //        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("Data/products").getFile());

    public DataManager()
    {


        setProductsManager(new ProductsManager());
        setSalesManager(new SalesManager());
        setCashflowManager(new CashflowManager());
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

    public void setCashflowManager(CashflowManager cashflowManager) {
        this.cashflowManager = cashflowManager;
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
