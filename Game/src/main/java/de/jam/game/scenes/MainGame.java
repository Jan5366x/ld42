package de.jam.game.scenes;

import de.jam.qad.game.scene.IScene;
import javafx.scene.layout.Pane;

/**
 * Main Game
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class MainGame implements IScene {

    @Override
    public void onSetup(Pane sceneParent) {
        System.out.println("game miep!");
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public String getBaseFxml() {
        return "/ui/scene/base/main_game.fxml";
    }


}
