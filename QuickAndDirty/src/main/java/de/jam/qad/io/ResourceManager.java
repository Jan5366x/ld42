package de.jam.qad.io;

import de.jam.qad.threading.Guard;
import gnu.trove.map.hash.THashMap;

import java.io.File;
import java.io.FileInputStream;

/**
 * Resource Manager
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class ResourceManager {
    private final static THashMap<String, IResource> resources = new THashMap<>();
    private final static Guard guard = new Guard();
    private final static String EXTERNAL_RESOURCE_PATH = "./resources";

    public static <T extends IResource> T get(final String resourceName, final Class<T> resourceType) {
        return get(resourceName, resourceType,false);

    }

    @SuppressWarnings("unchecked")
    public static <T extends IResource> T get(final String resourceName, final Class<T> resourceType, final boolean internal) {
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

            result = load(resourceName, resourceType,internal);
            if (result != null)
                resources.put(resourceName, result);

            return result;
        } finally {
            guard.writeUnlock();
        }
    }


    private static String patchExternalResourceName(final String resourceName) {
        if (resourceName.startsWith(EXTERNAL_RESOURCE_PATH)) {
            return resourceName;
        } else {
            return EXTERNAL_RESOURCE_PATH + resourceName;
        }
    }


    private static <T extends IResource> T load(final String resourceName, final Class<T> resourceType, boolean internal) {
        try {

            // instantiate new resource
            final T resource = resourceType.getConstructor().newInstance();

            // load resource
            if (IStreamResource.class.isAssignableFrom(resourceType)) {
                final var streamResource = (IStreamResource) resource;
                if (internal) {
                    try (var is = ResourceManager.class.getResourceAsStream(resourceName)) {
                        streamResource.loadByStream(is);
                    }
                } else {
                    final var resourceFile = new File(patchExternalResourceName(resourceName));
                    try (var is = new FileInputStream(resourceFile)) {
                        streamResource.loadByStream(is);
                    }
                }

            } else if (IPathResource.class.isAssignableFrom(resourceType)) {
                final IPathResource pathResource = ((IPathResource) resource);
                pathResource.loadByPath(resourceName);
            }

            System.out.println("Resource \"" + resourceName + "\" loaded!");
            return resource;
        } catch (Exception e) {
            System.err.println("Can't load resource \"" + resourceName + "\"!");
            e.printStackTrace();

            return null;
        }
    }

    public static void shutdown() {
        resources.forEach((s, r) -> {
            try {
                r.dispose();
                System.out.println("Resource \"" + s + "\" released!");
            } catch (Exception e) {
                System.err.println("Resource \"" + s + "\" can't be released!");
            }
        });
    }
}
