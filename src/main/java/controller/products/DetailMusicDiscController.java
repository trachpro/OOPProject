package controller.products;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.product.MovieDisc;
import model.product.MusicDisc;

public class DetailMusicDiscController {
    @FXML
    private Label singersLabel;
    @FXML private Label languageLabel;
    @FXML private Label genreLabel;
    @FXML private Label publicationDateLabel;

    public void displayDetail(MusicDisc musicDisc)
    {
        String singers = "";
        for(String singer: musicDisc.getListSingers())
        {
            singers = singers.concat(singer).concat("\n");
        }

        singersLabel.setText(singers);
        languageLabel.setText(musicDisc.getLanguage().toString());
        genreLabel.setText(musicDisc.getGenre().toString());
        publicationDateLabel.setText(String.valueOf(musicDisc.getPublicDate()));
    }
}
