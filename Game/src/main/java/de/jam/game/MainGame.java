package de.jam.game;

import de.jam.qad.io.RawAudioClip;
import de.jam.qad.io.ImageFxResource;
import de.jam.qad.io.ResourceManager;
import de.jam.qad.text.Translate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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


        // TODO test stuff >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        var root = new StackPane();
        var testImg = new ImageView(ResourceManager.get("./resources/texture/test.png", ImageFxResource.class).get());
        root.getChildren().add(testImg);


        new Thread(){
            @Override
            public void run() {
                super.run();
                final var testClip = ResourceManager.get("./resources/audio/test_audio.wav", RawAudioClip.class).getClip();
                testClip.loop(Integer.MAX_VALUE);
            }
        }.start();


        stage.setScene(new Scene(root,500,350));
        // TODO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
