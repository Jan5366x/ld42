package de.jam.game;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * MainGame
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainGame extends Application {

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage stage) {
        loadTranslation();

        // set application title
        stage.setTitle("MIEP");

        // setup window size
        stage.setMinWidth(500);
        stage.setMinHeight(350);

        stage.show();
    }

    private void loadTranslation() {
        // TODO implement
    }
}
