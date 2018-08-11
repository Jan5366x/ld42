package de.jam.qad.io;

/**
 * IPathResource Interface
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public interface IPathResource extends IResource {
    void loadByPath(final String path) throws Exception;
}
