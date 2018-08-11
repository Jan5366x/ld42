package de.jam.qad.io;

import de.jam.qad.threading.Guard;
import gnu.trove.map.hash.THashMap;

/**
 * Resource Manager
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class ResourceManager {
    private final static THashMap<String, IResource> resources = new THashMap<>();
    private final static Guard guard = new Guard();

    @SuppressWarnings("unchecked")
    public static <T extends IResource> T get(final String resourceName, final Class<T> resourceType) {

        T result = guard.read(() -> (T) resources.get(resourceName));

        if (result != null)
            return result;

        // TODO upgrade to async loading if time is left ^^
        guard.writeLock();
        try {

            // recheck again to avoid timing issues
            result = (T) resources.get(resourceName);
            if (result != null)
                return result;

            result = autoLoad(resourceName, resourceType);
            if (result != null)
                resources.put(resourceName, result);

            return result;
        } finally {
            guard.writeUnlock();
        }
    }


    private static <T extends IResource> T autoLoad(final String resourceName, final Class<T> resourceType) {
        try {
            System.out.println("load: " + resourceName);

            // instantiate new resource
            final T resource = resourceType.getConstructor().newInstance();

            // load resource
            if (IStreamResource.class.isAssignableFrom(resourceType)) {
                final IStreamResource streamResource = (IStreamResource) resource;
                try (var is = ResourceManager.class.getResourceAsStream(resourceName)) {
                    streamResource.loadByStream(is);
                }
            } else if (IPathResource.class.isAssignableFrom(resourceType)) {
                final IPathResource pathResource = ((IPathResource) resource);
                pathResource.loadByPath(resourceName);
            }

            return resource;
        } catch (Exception e) {
            System.err.println("Can't load resource \"" + resourceName + "\"!");
            e.printStackTrace();

            return null;
        }
    }
}
