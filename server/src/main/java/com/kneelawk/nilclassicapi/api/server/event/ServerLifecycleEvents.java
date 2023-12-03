package com.kneelawk.nilclassicapi.api.server.event;

import com.mojang.minecraft.server.MinecraftServer;

import com.kneelawk.nilclassicapi.impl.Event;

/**
 * Set of events for the server lifecycle.
 */
public class ServerLifecycleEvents {
    /**
     * Called when the {@link MinecraftServer} is first created but before the server thread has been started.
     */
    public static final Event<ServerCreated> SERVER_CREATED = new Event<>(ServerCreated.class, listeners -> server -> {
        for (ServerCreated listener : listeners) {
            listener.onServerCreated(server);
        }
    });

    /**
     * Called when the {@link MinecraftServer} thread first starts.
     * <p>
     * Note: the minecraft server does not do any initialization on the server thread, so this event is the same as
     * a <b>server-started</b> event.
     */
    public static final Event<ServerStarting> SERVER_STARTING =
        new Event<>(ServerStarting.class, listeners -> server -> {
            for (ServerStarting listener : listeners) {
                listener.onServerStarting(server);
            }
        });

    /**
     * Called when the {@link MinecraftServer} is first created but before the server thread has been started.
     */
    @FunctionalInterface
    public interface ServerCreated {
        /**
         * Called when the {@link MinecraftServer} is first create but before the server thread has been started.
         *
         * @param server the newly created minecraft server object.
         */
        void onServerCreated(MinecraftServer server);
    }

    /**
     * Called when the {@link MinecraftServer} thread first starts.
     * <p>
     * Note: the minecraft server does not do any initialization on the server thread, so this event is the same as
     * a <b>server-started</b> event.
     */
    @FunctionalInterface
    public interface ServerStarting {
        /**
         * Called when the {@link MinecraftServer} thread first starts.
         * <p>
         * Note: the minecraft server does not do any initialization on the server thread, so this event is the same as
         * a <b>server-started</b> event.
         *
         * @param server the server that is starting.
         */
        void onServerStarting(MinecraftServer server);
    }
}
