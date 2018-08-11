package de.jam.qad.game.identity;

import de.jam.qad.game.world.WorldSpace;
import de.jam.qad.math.Transform2D;
import gnu.trove.map.hash.THashMap;

/**
 * Identity
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Identity {
    public final Transform2D transform = new Transform2D();
    private final THashMap<Class<? extends IIdentityUnit>, IIdentityUnit> identityUnits = new THashMap<>();

    public void init(final WorldSpace worldSpace) {
        identityUnits.forEach((c, unit) -> unit.init(worldSpace,this));
    }

    public void destroy(final WorldSpace worldSpace) {
        identityUnits.forEach((c, unit) -> unit.destroy(worldSpace,this));
    }

    public void update(final WorldSpace worldSpace) {

        // process physics
        final var physics = identityUnits.get(PhysicsUnit.class);
        if (physics != null && physics.isEnabled())
            physics.update(worldSpace,this);

        // run all other units
        identityUnits.forEach((c, unit) -> {
            if (!isComplexUnit(unit) && unit.isEnabled()) {
                unit.update(worldSpace,this);
            }
        });

        // render
        final var render = identityUnits.get(FxRendererUnit.class);
        if (render != null && render.isEnabled())
            render.update(worldSpace,this);
    }

    public void addUnit(final IIdentityUnit unit) {
        assert unit != null;

        identityUnits.put(unit.getClass(),unit);
    }

    private boolean isComplexUnit(final IIdentityUnit unit) {
        return unit instanceof FxRendererUnit || unit instanceof PhysicsUnit;
    }
}
