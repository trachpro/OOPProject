package Model;

import java.util.Date;
import java.util.List;

public class Book extends Product {
    private List<String> listAuthors;

    private String language;
    private int length;
    private Date publicationDate;

    public Book(String _productID, String _name, String _category, String _nation, int _remaining, double _salesPrice, double _receivedPrice, List<String> _listAuthors, String _language, int _length, Date _publicationDate)
    {
        super(_productID, _name, _category, _nation, _remaining, _salesPrice, _receivedPrice);
        this.listAuthors = _listAuthors;
        this.language = _language;
        this.length = _length;
        this.publicationDate = _publicationDate;
    }


    public String getTitle() {
        return getName();
    }

    public void setTitle(String title) {
        this.setName(title);
    }

    public List<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(List<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
