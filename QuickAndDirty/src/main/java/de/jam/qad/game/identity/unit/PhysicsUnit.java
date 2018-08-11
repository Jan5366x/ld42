package de.jam.qad.game.identity.unit;

import de.jam.qad.game.identity.Identity;
import de.jam.qad.game.world.WorldSpace;
import de.jam.qad.math.Vector2;

/**
 * Physics Unit
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class PhysicsUnit implements IIdentityUnit {
    public Vector2 force = new Vector2();
    public float decelerationFactor = 1F; // 0 to 1
    private boolean enabled = true;

    @Override
    public void init(WorldSpace worldSpace, Identity identity) {

    }

    @Override
    public void update(WorldSpace worldSpace, Identity identity) {
        // TODO process physic contacts


        identity.transform.location.add(force);
        force.multiply(decelerationFactor);
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

    public void calm() {
        force.set(Vector2.ZERO);
    }
}
