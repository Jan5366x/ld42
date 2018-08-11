package de.jam.qad.game.identity.unit;

import de.jam.qad.game.identity.Identity;
import de.jam.qad.game.world.WorldSpace;
import javafx.scene.Node;

/**
 * FxRendererUnit
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class FxRendererUnit implements IIdentityUnit {
    private Node currentNode;
    private boolean enabled = true;

    @Override
    public void init(WorldSpace worldSpace, Identity identity) {

    }

    @Override
    public void update(WorldSpace worldSpace, Identity identity) {

    }

    @Override
    public void destroy(WorldSpace worldSpace, Identity identity) {

    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean value) {
        enabled = value;
    }
}
