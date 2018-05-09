package controller;

import controller.products.ProductsManager;
import controller.sales.SalesManager;
import model.product.*;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DataManager {

    private ProductsManager productsManager;
    private SalesManager salesManager;

//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("data/products").getFile());

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
            File file = new File("src/main/resources/data/products.txt");
            System.out.println(file.getAbsolutePath());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();

            while(line.length() != 0)
            {
                System.out.println("\""+line+"\"");
                String[] parts = line.split(Pattern.quote("|"));

                Product p = new Product();
                p.setProductID(parts[1]);
                p.setName(parts[2]);
                p.setCategory(Category.valueOf(parts[0]));
                p.setStatus(Status.valueOf(parts[3]));
                p.setQuantity(Integer.valueOf(parts[4]));
                p.setBuyingPrice(Double.valueOf(parts[5]));
                p.setSellingPrice(Double.valueOf(parts[6]));
                p.setNation(Nation.valueOf(parts[7]));
                p.setImageUrl(parts[8]);
                p.setDiscount(Integer.valueOf(parts[9]));

                int curPos = 10;

                if(Category.valueOf(parts[0]) == Category.BOOK)
                {
                    Book b = new Book(p);

                    int nAuthors = Integer.valueOf(parts[curPos]);
                    curPos += 1;
                    for(int i = 0; i < nAuthors; i++)
                    {
                        b.getListAuthors().add(parts[curPos]);
                        curPos += 1;
                    }

                    b.setLanguage(Language.valueOf(parts[curPos])); curPos += 1;
                    b.setGenre(BookGenre.valueOf(parts[curPos])); curPos += 1;
                    b.setLength(Integer.valueOf(parts[curPos])); curPos += 1;
                    b.setPublicDate(LocalDate.parse(parts[curPos]));

                    productsManager.addProduct(b);
                }
                else if(Category.valueOf(parts[0]) == Category.MOVIE_DISC)
                {
                    MovieDisc m = new MovieDisc(p);

                    int nActors = Integer.valueOf(parts[curPos]);
                    curPos += 1;
                    for(int i = 0; i < nActors; i++)
                    {
                        m.getListActors().add(parts[curPos]);
                        curPos += 1;
                    }

                    m.setDirector(parts[curPos]); curPos += 1;
                    m.setLanguage(Language.valueOf(parts[curPos])); curPos += 1;
                    m.setSubtitle(Language.valueOf(parts[curPos])); curPos += 1;
                    m.setGenre(MovieGenre.valueOf(parts[curPos])); curPos += 1;
                    m.setLength(Integer.valueOf(parts[curPos])); curPos += 1;
                    m.setImdbPoint(Float.valueOf(parts[curPos])); curPos += 1;
                    m.setPublicDate(LocalDate.parse(parts[curPos]));

                    productsManager.addProduct(m);
                }
                else
                {
                    MusicDisc m = new MusicDisc(p);

                    int nSingers = Integer.valueOf(parts[curPos]);
                    curPos += 1;
                    for(int i = 0; i < nSingers; i++)
                    {
                        m.getListSingers().add(parts[curPos]);
                        curPos += 1;
                    }
                    m.setLanguage(Language.valueOf(parts[curPos])); curPos += 1;
                    m.setGenre(MusicGenre.valueOf(parts[curPos])); curPos += 1;
                    m.setPublicDate(LocalDate.parse(parts[curPos]));
                }


                line = bufferedReader.readLine();
                if(line == null) break;
            }
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
            File file = new File("src/main/resources/data/products.txt");
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
