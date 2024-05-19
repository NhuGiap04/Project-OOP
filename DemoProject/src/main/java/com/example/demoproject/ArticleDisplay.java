package com.example.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ArticleDisplay extends Scenes {

    @FXML
    VBox cardLayout = new VBox();
    public void displayResult(List<Article> list) {
        try {
            for (Article article : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("articleCard.fxml"));
                HBox box = fxmlLoader.load();
                ArticleCardController articleCardController = fxmlLoader.getController();
                articleCardController.setData(article);
                cardLayout.getChildren().add(box);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
