package de.jam.qad.threading;

/**
 * Atomic
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Atomic <T> {
    private T content = null;
    private final Guard guard = new Guard();

    public Atomic() {

    }

    public Atomic(final T obj) {
        set(obj);
    }

    public T get() {
        return guard.read(() -> content);
    }

    public void set(final T newContent) {
        guard.write(() -> content = newContent);
    }
}
