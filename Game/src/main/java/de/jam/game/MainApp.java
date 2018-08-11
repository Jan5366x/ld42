package de.jam.game;

import de.jam.game.scenes.MainGame;
import de.jam.game.scenes.MainMenu;
import de.jam.qad.game.scene.SceneManager;
import de.jam.qad.io.RawAudioClip;
import de.jam.qad.io.ImageFxResource;
import de.jam.qad.io.ResourceManager;
import de.jam.qad.structure.Components;
import de.jam.qad.text.Translate;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Properties;

/**
 * Main Game<br>
 * Ludum Dare 42!
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainApp extends Application {

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

        var root = new AnchorPane();
        stage.setScene(new Scene(root,500,350));

        setupComponents(root);

        Components.get(SceneManager.class).addScene("menu",new MainMenu());
        Components.get(SceneManager.class).addScene("game",new MainGame());
        Components.get(SceneManager.class).switchTo("menu");

        stage.show();
    }

    private void setupComponents(Pane root) {
        Components.add(new SceneManager(root));

        Components.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        ResourceManager.shutdown();
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
