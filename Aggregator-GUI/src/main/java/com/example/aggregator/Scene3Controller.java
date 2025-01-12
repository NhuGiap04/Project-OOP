package com.example.aggregator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene3Controller extends Scenes implements Initializable {
    private Article article;


    @FXML
    private Label title;

    @FXML
    private Label description;

    @FXML
    private Label date;

    @FXML
    private Label content;

    @FXML
    private ImageView bookmarkImage;
    Image unsaved = new Image("com/example/aggregator/images/Scene3Unsaved.png");
    Image saved   = new Image("com/example/aggregator/images/Scene3Saved.png");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void bookmark (ActionEvent event) throws IOException {
        if (article.getBookmarked()) {
            bookmarkImage.setImage(unsaved);
            article.setBookmarked(false);
            Scene4Controller.removeBookmark(this.article);
        } else {
            bookmarkImage.setImage(saved);
            article.setBookmarked(true);
            Scene4Controller.addBookmark(this.article);
        }
    }

    public void setArticle(Article article) {
        this.article = article;

        title.setText(article.getTitle());
        description.setText(article.getSummary());
        date.setText(article.getDate());
        content.setText(article.getContent());

        if (article.getBookmarked()) {
            bookmarkImage.setImage(saved);
        } else {
            bookmarkImage.setImage(unsaved);
        }
    }
}
