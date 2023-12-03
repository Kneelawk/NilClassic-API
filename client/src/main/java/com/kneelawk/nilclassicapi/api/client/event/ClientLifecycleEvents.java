package com.kneelawk.nilclassicapi.api.client.event;

import com.mojang.minecraft.Minecraft;

import com.kneelawk.nilclassicapi.impl.Event;

/**
 * Set of events for the client lifecycle.
 */
public class ClientLifecycleEvents {
    /**
     * Called when the {@link Minecraft} object is first created but before the client thread is started.
     */
    public static final Event<ClientCreated> CLIENT_CREATED =
        new Event<>(ClientCreated.class, listeners -> minecraft -> {
            for (ClientCreated listener : listeners) {
                listener.onClientCreated(minecraft);
            }
        });

    /**
     * Called when the {@link Minecraft} thread is first starting.
     */
    public static final Event<ClientStarting> CLIENT_STARTING =
        new Event<>(ClientStarting.class, listeners -> minecraft -> {
            for (ClientStarting listener : listeners) {
                listener.onClientStarting(minecraft);
            }
        });

    /**
     * Called when the {@link Minecraft} has finished initializing on its own thread.
     */
    public static final Event<ClientStarted> CLIENT_STARTED =
        new Event<>(ClientStarted.class, listeners -> minecraft -> {
            for (ClientStarted listener : listeners) {
                listener.onClientStarted(minecraft);
            }
        });

    /**
     * Called when the {@link Minecraft} object is first created but before the client thread is started.
     */
    @FunctionalInterface
    public interface ClientCreated {
        /**
         * Called when the {@link Minecraft} object is first created but before the client thread is started.
         *
         * @param minecraft the newly created minecraft object.
         */
        void onClientCreated(Minecraft minecraft);
    }

    /**
     * Called when the {@link Minecraft} thread is first starting.
     */
    @FunctionalInterface
    public interface ClientStarting {
        /**
         * Called when the {@link Minecraft} thread is first starting.
         *
         * @param minecraft the minecraft object that is starting up.
         */
        void onClientStarting(Minecraft minecraft);
    }

    /**
     * Called when the {@link Minecraft} has finished initializing on its own thread.
     */
    @FunctionalInterface
    public interface ClientStarted {
        /**
         * Called when the {@link Minecraft} has finished initializing on its own thread.
         *
         * @param minecraft the minecraft object that has finished initializing.
         */
        void onClientStarted(Minecraft minecraft);
    }
}
