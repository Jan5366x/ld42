package de.jam.qad.game.world;

import de.jam.qad.structure.IRenderTarget;

/**
 * World Space
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class WorldSpace {
    private final IRenderTarget renderTarget;

    public WorldSpace(final IRenderTarget renderTarget) {
        this.renderTarget = renderTarget;
    }

    public IRenderTarget getRenderTarget() {
        return renderTarget;
    }
}
