package de.jam.qad.io;

import gnu.trove.map.hash.THashMap;

/**
 * Resource Manager
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class ResourceManager {
    private final static THashMap<String,IResource> resources = new THashMap<>();



    @SuppressWarnings("unchecked")
    public static <T extends IResource> T get(final String resourceName, final Class<T> resourceType) {
        return (T) resources.get(resourceName);
    }






}
