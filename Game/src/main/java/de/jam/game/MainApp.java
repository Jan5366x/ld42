package de.jam.game;

import de.jam.game.scenes.*;
import de.jam.qad.game.scene.SceneManager;
import de.jam.qad.io.ResourceManager;
import de.jam.qad.structure.Components;
import de.jam.qad.text.Translate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Properties;

/**
 * Main Game<br>
 * Ludum Dare 42!
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainApp extends Application {

    private final static int START_WIDTH = 1000;
    private final static int START_HEIGHT = 780;

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
        stage.setMinWidth(START_WIDTH);
        stage.setMinHeight(START_HEIGHT);

        var root = new AnchorPane();
        stage.setScene(new Scene(root,START_WIDTH,START_HEIGHT));

        setupComponents(root);
        setupScenes();

        stage.show();
    }


    private void setupScenes() {
        final var sceneManager = Components.get(SceneManager.class);
        sceneManager.addScene(MainScenes.menu.toString(),new MainMenu());
        sceneManager.addScene(MainScenes.game.toString(),new MainGame());
        sceneManager.addScene(MainScenes.intro.toString(),new Intro());
        sceneManager.addScene(MainScenes.credits.toString(),new Credits());

        // start with intro
        sceneManager.switchTo(MainScenes.intro.toString());
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
