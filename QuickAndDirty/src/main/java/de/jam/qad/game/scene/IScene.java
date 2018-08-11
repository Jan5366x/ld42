package de.jam.qad.game.scene;

import javafx.scene.layout.Pane;

/**
 * Scene Interface
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public interface IScene {
    void onSetup(final Pane sceneParent);
    void onUpdate();
    void onShutdown();
    String getBaseFxml();
}
