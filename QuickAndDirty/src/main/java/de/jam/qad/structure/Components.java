package de.jam.qad.structure;

import de.jam.qad.threading.Guard;
import gnu.trove.map.hash.THashMap;
import javafx.animation.AnimationTimer;

/**
 * Components
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Components {
    private final static THashMap<Class<? extends IComponent>,IComponent> components = new THashMap<>();
    private final static Guard guard = new Guard();

    public static void add(final IComponent component) {
        guard.write(() -> components.put(component.getClass(),component));
    }

    @SuppressWarnings("unchecked")
    public static <T extends IComponent> T get(final Class<T> componentType) {
        return guard.read(() -> ((T) components.get(componentType)));
    }

    public static void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                guard.readLock();
                try {
                    components.forEach((aClass, component) -> component.update());
                    Time.notifyFrame();
                } finally {
                    guard.readUnlock();
                }
            }
        } .start();

    }
}
