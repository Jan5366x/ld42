package de.jam.qad.game.identity.unit;

import de.jam.qad.game.identity.Identity;
import de.jam.qad.game.world.WorldSpace;

/**
 * IdentityUnit Interface
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public interface IIdentityUnit {
    void init(final WorldSpace worldSpace, final Identity identity);
    void update(final WorldSpace worldSpace, final Identity identity);
    void destroy(final WorldSpace worldSpace, final Identity identity);
    boolean isEnabled();
    void setEnabled(final boolean value);
}
