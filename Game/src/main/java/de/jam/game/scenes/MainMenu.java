package de.jam.game.scenes;

import de.jam.qad.game.scene.IScene;
import de.jam.qad.game.scene.SceneManager;
import de.jam.qad.io.ImageFxResource;
import de.jam.qad.io.ResourceManager;
import de.jam.qad.structure.Components;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Main Menu
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainMenu implements IScene {

    public Button startGameButton;
    public Button showCreditsButton;
    public Button exitButton;

    private ImageView testImg = null;

    @Override
    public void onSetup(Pane sceneParent) {
        System.out.println("menu miep!");
        testImg = new ImageView(ResourceManager.get("/texture/test.png", ImageFxResource.class).get());
        testImg.setSmooth(false);
        sceneParent.getChildren().add(testImg);

    }

    @Override
    public void onUpdate() {
        testImg.setX((int) (testImg.getX() + 1));
        testImg.setY((int) (testImg.getY() + 1));

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public String getBaseFxml() {
        return "/ui/scene/base/main_menu.fxml";
    }

    public void onExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onStartGame(ActionEvent actionEvent) {
        Components.get(SceneManager.class).switchTo("game");
    }
}
