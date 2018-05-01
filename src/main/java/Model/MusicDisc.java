package Model;

import java.util.Date;
import java.util.List;

public class MusicDisc extends Product {

    private List<String> listSingers;
    private Enum<Language> language;
    private Enum<MusicGenre> genre;
    private Date publicDate;


    public MusicDisc(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount, List<String> _listSingers, Enum<Language> _language, Enum<MusicGenre> _genre, Date _publicDate)
    {
        super(_productID, _name, _category, _status, _quantity, _buyingPrice, _sellingPrice, _nation, _imageUrl, _discount);

        this.setListSingers(_listSingers);
        this.setLanguage(_language);
        this.setGenre(_genre);
        this.setPublicDate(_publicDate);

    }

    public List<String> getListSingers() {
        return listSingers;
    }

    public void setListSingers(List<String> listSingers) {
        this.listSingers = listSingers;
    }

    public Enum<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Enum<Language> language) {
        this.language = language;
    }

    public Enum<MusicGenre> getGenre() {
        return genre;
    }

    public void setGenre(Enum<MusicGenre> genre) {
        this.genre = genre;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }
}
