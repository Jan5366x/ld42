package de.jam.game;

import de.jam.qad.text.Translate;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Properties;

/**
 * Main Game<br>
 * Ludum Dare 42!
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainGame extends Application {

    public static void main(String[] args) {

        System.out.println("init javafx");
        launch(args);


    }

    @Override
    public void start(Stage stage) {
        System.out.println("load translation");
        loadTranslation();

        System.out.println("prepare main game window");
        // set application title
        stage.setTitle(Translate.get("game.title"));

        // setup window size
        stage.setMinWidth(500);
        stage.setMinHeight(350);

        stage.show();
    }

    private void loadTranslation() {
        try(var input = getClass().getResourceAsStream("/lang_en.properties")) {
            final var prop = new Properties();
            prop.load(input);
            Translate.add(prop);
        } catch (Exception e) {
            System.err.println("Can't read translation!");
            e.printStackTrace();
        }
    }
}
