/*
 * Based on FabricMC's Event class.
 */

package com.kneelawk.nilclassicapi.impl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

public class Event<T> {
    private final Function<T[], T> invokerFactory;
    private final Object lock = new Object();
    private T[] handlers;
    private volatile T invoker;

    @SuppressWarnings("unchecked")
    public Event(Class<T> type, Function<T[], T> invokerFactory) {
        this.invokerFactory = invokerFactory;
        handlers = (T[]) Array.newInstance(type, 0);
        update();
    }

    private void update() {
        invoker = invokerFactory.apply(handlers);
    }

    public void register(T listener) {
        synchronized (lock) {
            int oldLength = handlers.length;
            handlers = Arrays.copyOf(handlers, oldLength + 1);
            handlers[oldLength] = listener;
            update();
        }
    }

    public T invoker() {
        return invoker;
    }
}
