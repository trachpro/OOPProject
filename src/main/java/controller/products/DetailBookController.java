package controller.products;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.product.Book;

public class DetailBookController {
    @FXML private Label authorsLabel;
    @FXML private Label languageLabel;
    @FXML private Label genreLabel;
    @FXML private Label lengthLabel;
    @FXML private Label publicationDateLabel;

    public void displayDetail(Book _book)
    {
        String authors = "";
        for(String author: _book.getListAuthors())
        {
            authors = authors.concat(author).concat("\n");
        }

        authorsLabel.setText(authors);

        languageLabel.setText(_book.getLanguage().toString());
        genreLabel.setText(_book.getGenre().toString());
        lengthLabel.setText(String.valueOf(_book.getLength()));
        publicationDateLabel.setText(String.valueOf(_book.getPublicDate()));
    }
}
