package de.jam.game.scenes;

import de.jam.qad.game.scene.IScene;
import de.jam.qad.io.ImageFxResource;
import de.jam.qad.io.ResourceManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Main Menu
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainMenu implements IScene {

    private ImageView testImg = null;

    @Override
    public void onSetup(Pane sceneParent) {
        System.out.println("menu miep!");
        testImg = new ImageView(ResourceManager.get("/texture/test.png", ImageFxResource.class).get());
        sceneParent.getChildren().add(testImg);

    }

    @Override
    public void onUpdate() {
        testImg.setX(testImg.getX() + 1D);
        testImg.setY(testImg.getY() + 1D);

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public String getBaseFxml() {
        return "/ui/main_menu.fxml";
    }
}
