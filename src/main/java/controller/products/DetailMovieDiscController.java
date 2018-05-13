package controller.products;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.product.Book;
import model.product.MovieDisc;

public class DetailMovieDiscController {
    @FXML private Label actorsLabel;
    @FXML private Label directorLabel;
    @FXML private Label languageLabel;
    @FXML private Label subtitleLabel;
    @FXML private Label genreLabel;
    @FXML private Label lengthLabel;
    @FXML private Label pointLabel;
    @FXML private Label publicationDateLabel;

    public void displayDetail(MovieDisc movieDisc)
    {
        String actors = "";
        for(String actor: movieDisc.getListActors())
        {
            actors = actors.concat(actor).concat("\n");
        }

        actorsLabel.setText(actors);
        directorLabel.setText(movieDisc.getDirector());
        languageLabel.setText(movieDisc.getLanguage().toString());
        subtitleLabel.setText(movieDisc.getSubtitle().toString());
        genreLabel.setText(movieDisc.getGenre().toString());
        lengthLabel.setText(String.valueOf(movieDisc.getLength()));
        pointLabel.setText(String.valueOf(movieDisc.getImdbPoint()));
        publicationDateLabel.setText(String.valueOf(movieDisc.getPublicDate()));
    }
}
