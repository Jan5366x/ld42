package de.jam.game.scenes;

import de.jam.qad.game.scene.IScene;
import de.jam.qad.game.scene.SceneManager;
import de.jam.qad.structure.Components;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

/**
 * MainGame
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
        return "/ui/game_overlay.fxml";
    }

    public void onTest(ActionEvent actionEvent) {
        System.out.println("TTTTTTTTTTTESSSSSSSSSSSSSSSSSSSSSTTTTT");
        Components.get(SceneManager.class).switchTo("menu");
    }
}
