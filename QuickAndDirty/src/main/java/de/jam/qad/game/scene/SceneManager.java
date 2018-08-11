package de.jam.qad.game.scene;

import de.jam.qad.structure.IComponent;
import de.jam.qad.text.Translate;
import gnu.trove.map.hash.THashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;

/**
 * Scene Manager
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class SceneManager implements IComponent {
    private final THashMap<String,IScene> scenes = new THashMap<>();
    private IScene currentScene = null;
    private final Pane parentNode;

    public SceneManager(final Pane parent) {
        this.parentNode = parent;
    }


    public void addScene(final String name, final IScene scene) {
        scenes.put(name, scene);
    }


    public void switchTo(final String name) {
        switchScene(name);
    }

    @SuppressWarnings("unchecked")
    private void switchScene(final String name) {
        if (currentScene != null) {
            currentScene.onShutdown();
            parentNode.getChildren().clear();
        }

        currentScene = scenes.get(name);
        assert currentScene != null;

        try {
            if (currentScene.getBaseFxml() != null) {
                final URL resourceUrl = SceneManager.class.getResource(currentScene.getBaseFxml());
                parentNode.getChildren().add(FXMLLoader.load(resourceUrl, Translate.getResourceBundle()));
            }
        } catch (Exception e) {
            System.err.println("Error while switching scene!");
            e.printStackTrace();
        }


        currentScene.onSetup(parentNode);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        if (currentScene == null)
            return;

        currentScene.onUpdate();
    }

    @Override
    public void shutdown() {

    }
}
