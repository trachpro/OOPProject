package model.product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Book extends Product {
    private ArrayList<String> listAuthors;

    private Enum<Language> language;
    private Enum<BookGenre> genre;
    private int length;
    private LocalDate publicDate;

    public Book(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount, ArrayList<String> _listAuthors, Enum<Language> _language, Enum<BookGenre> _genre, int _length, LocalDate _publicDate)
    {
        super(_productID, _name, _category, _status, _quantity, _buyingPrice, _sellingPrice, _nation, _imageUrl, _discount);

        this.setListAuthors(_listAuthors);
        this.setLanguage(_language);
        this.setGenre(_genre);
        this.setLength(_length);
        this.setPublicDate(_publicDate);
    }

    public Book(Product _product)
    {
        super(_product.getProductID(), _product.getName(), _product.getCategory(), _product.getStatus(), _product.getQuantity(), _product.getBuyingPrice(), _product.getSellingPrice(), _product.getNation(), _product.getImageUrl(), _product.getDiscount());
        this.setListAuthors(new ArrayList<String>());
        this.setLanguage(Language.ENGLISH);
        this.setGenre(BookGenre.THRILLER);
        this.setLength(0);
        this.setPublicDate(LocalDateTime.now().toLocalDate());
    }

    public Book()
    {
        super();
        this.setCategory(Category.BOOK);
        this.setListAuthors(new ArrayList<String>(Arrays.asList("", "", "", "", "")));
        this.setLanguage(Language.ENGLISH);
        this.setGenre(BookGenre.THRILLER);
        this.setLength(0);
        this.setPublicDate(LocalDateTime.now().toLocalDate());
    }

    public ArrayList<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(ArrayList<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public Enum<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Enum<Language> language) {
        this.language = language;
    }

    public Enum<BookGenre> getGenre() {
        return genre;
    }

    public void setGenre(Enum<BookGenre> genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public void printDetail()
    {
        super.printDetail();
        System.out.println("List Authors: "+getListAuthors());
        System.out.println("Language: "+getLanguage().toString());
        System.out.println("Genre: "+getGenre().toString());
        System.out.println("Length: "+getLength());
        System.out.println("Date: "+getPublicDate().toString());
    }

    public String toString()
    {
        String product = super.toString();

        product = product.concat("|");
        product = product.concat(Integer.toString(listAuthors.size()));
        product = product.concat("|");

        for(int i = 0; i < listAuthors.size(); i++)
        {
            product = product.concat(listAuthors.get(i));
            product = product.concat("|");
        }

        product = product.concat(getLanguage().toString());
        product = product.concat("|");

        product = product.concat(getGenre().toString());
        product = product.concat("|");

        product = product.concat(Integer.toString(getLength()));
        product = product.concat("|");

        product = product.concat(getPublicDate().toString());

        return product;
    }

    public static Book valueOf(String line)
    {
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

        Book b = new Book(p);

        int curPos = 10;
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

        return b;
    }
}
