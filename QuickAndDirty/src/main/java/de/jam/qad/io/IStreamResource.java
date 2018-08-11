package de.jam.qad.io;

import java.io.InputStream;

/**
 * IStreamResource Interface
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public interface IStreamResource extends IResource {
    void loadByStream(final InputStream stream) throws Exception;
}
