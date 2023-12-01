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
}
