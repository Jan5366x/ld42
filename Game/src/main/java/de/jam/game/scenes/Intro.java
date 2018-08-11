package de.jam.game.scenes;

import de.jam.qad.game.scene.IScene;
import de.jam.qad.game.scene.SceneManager;
import de.jam.qad.structure.Components;
import javafx.scene.layout.Pane;

/**
 * Intro
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Intro implements IScene {
    @Override
    public void onSetup(Pane sceneParent) {

    }

    int pT = 0;
    @Override
    public void onUpdate() {


        // FIXME placeholder
        if (pT++ >= 70)
            Components.get(SceneManager.class).switchTo(MainScenes.menu.toString());

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public String getBaseFxml() {
        return "/ui/scene/base/intro.fxml";
    }
}
