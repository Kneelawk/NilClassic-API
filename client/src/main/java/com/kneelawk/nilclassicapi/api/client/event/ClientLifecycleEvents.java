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
}
