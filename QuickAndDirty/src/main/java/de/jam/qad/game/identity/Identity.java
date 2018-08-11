package de.jam.qad.game.identity;

import de.jam.qad.game.identity.unit.FxRendererUnit;
import de.jam.qad.game.identity.unit.IIdentityUnit;
import de.jam.qad.game.identity.unit.PhysicsUnit;
import de.jam.qad.game.world.WorldSpace;
import de.jam.qad.math.Transform2D;
import gnu.trove.map.hash.THashMap;

import java.util.ArrayList;

/**
 * Identity
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Identity {
    public final Transform2D transform = new Transform2D();
    private final THashMap<Class<? extends IIdentityUnit>, IIdentityUnit> identityUnits = new THashMap<>();

    private final ArrayList<Identity> children = new ArrayList<>();
    private Identity parent = null;

    public void init(final WorldSpace worldSpace) {
        identityUnits.forEach((c, unit) -> unit.init(worldSpace,this));
    }

    public void destroy(final WorldSpace worldSpace) {
        identityUnits.forEach((c, unit) -> unit.destroy(worldSpace,this));
    }

    public void update(final WorldSpace worldSpace) {
        // physics
        updateUnit(worldSpace,PhysicsUnit.class);

        // all other units
        updateNonComplexUnits(worldSpace);

        // render
        updateUnit(worldSpace,FxRendererUnit.class);

        // update children identities
        children.forEach(identity -> identity.update(worldSpace));
    }

    private void updateNonComplexUnits(WorldSpace worldSpace) {
        identityUnits.forEach((c, unit) -> {
            if (!isComplexUnit(unit) && unit.isEnabled()) {
                unit.update(worldSpace,this);
            }
        });
    }

    private void updateUnit(final WorldSpace worldSpace, final Class<? extends IIdentityUnit> type) {
        final var unit = identityUnits.get(type);
        if (unit != null && unit.isEnabled())
            unit.update(worldSpace,this);
    }

    public void addUnit(final IIdentityUnit unit) {
        assert unit != null;

        identityUnits.put(unit.getClass(),unit);
    }

    private boolean isComplexUnit(final IIdentityUnit unit) {
        return unit instanceof FxRendererUnit || unit instanceof PhysicsUnit;
    }

    public ArrayList<Identity> getChildren() {
        return children;
    }

    public Identity getParent() {
        return parent;
    }

    public void setParent(Identity parent) {
        this.parent = parent;
    }
}
