package Model;

import java.util.Date;
import java.util.List;

public class Book extends Product {
    private List<String> listAuthors;

    private Enum<Language> language;
    private Enum<BookGenre> genre;
    private int length;
    private Date publicDate;

    public Book(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount, List<String> _listAuthors, Enum<Language> _language, Enum<BookGenre> _genre, int _length, Date _publicDate)
    {
        super(_productID, _name, _category, _status, _quantity, _buyingPrice, _sellingPrice, _nation, _imageUrl, _discount);

        this.setListAuthors(_listAuthors);
        this.setLanguage(_language);
        this.setGenre(_genre);
        this.setLength(_length);
        this.setPublicDate(_publicDate);
    }


    public List<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(List<String> listAuthors) {
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

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }
}
