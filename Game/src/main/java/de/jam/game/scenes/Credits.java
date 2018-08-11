package de.jam.game.scenes;

import de.jam.qad.game.scene.IScene;
import de.jam.qad.game.scene.SceneManager;
import de.jam.qad.structure.Components;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

/**
 * Credits
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Credits implements IScene {
    @Override
    public void onSetup(Pane sceneParent) {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public String getBaseFxml() {
        return "/ui/scene/base/credits.fxml";
    }

    public void onBackToMenu(ActionEvent actionEvent) {
        Components.get(SceneManager.class).switchTo(MainScenes.menu.toString());
    }
}
