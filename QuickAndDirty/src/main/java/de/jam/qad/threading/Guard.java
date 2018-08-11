package de.jam.qad.threading;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * Guard
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Guard {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void readLock() {
        rwl.readLock().lock();
    }

    public void readUnlock() {
        rwl.readLock().unlock();
    }

    public void writeLock() {
        rwl.writeLock().lock();
    }

    public void writeUnlock() {
        rwl.writeLock().unlock();
    }

    public void read(final Runnable runnable) {
        readLock();
        try {
            runnable.run();
        } finally {
            readUnlock();
        }
    }

    public <T> T read(final Supplier<T> supplier) {
        readLock();
        try {
            return supplier.get();
        } finally {
            readUnlock();
        }
    }

    public void write(final Runnable runnable) {
        writeLock();
        try {
            runnable.run();
        } finally {
            writeUnlock();
        }
    }

    public <T> T write(final Supplier<T> supplier) {
        writeLock();
        try {
            return supplier.get();
        } finally {
            writeUnlock();
        }
    }
}
