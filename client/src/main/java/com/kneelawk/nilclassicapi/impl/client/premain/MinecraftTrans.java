package com.kneelawk.nilclassicapi.impl.client.premain;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

import com.mojang.minecraft.Minecraft;

import com.kneelawk.nilclassicapi.api.client.event.ClientLifecycleEvents;

@Patch.Class("com.mojang.minecraft.Minecraft")
public class MinecraftTrans extends MiniTransformer {
    public static class Hooks {
        public static void onMinecraftStarting(Minecraft client) {
            ClientLifecycleEvents.CLIENT_STARTING.invoker().onClientStarting(client);
        }

        public static void onMinecraftStarted(Minecraft client) {
            ClientLifecycleEvents.CLIENT_STARTED.invoker().onClientStarted(client);
        }
    }

    @Patch.Method("run()V")
    public void patchRun(PatchContext ctx) {
        // CLIENT_STARTING
        ctx.jumpToStart();
        ctx.add(
            ALOAD(0),
            INVOKESTATIC("com/kneelawk/nilclassicapi/impl/client/premain/MinecraftTrans$Hooks", "onMinecraftStarting",
                "(Lcom/mojang/minecraft/Minecraft;)V")
        );

        // CLIENT_STARTED
        ctx.search(INVOKESTATIC("java/lang/System", "currentTimeMillis", "()J")).jumpBefore();
        ctx.add(
            ALOAD(0),
            INVOKESTATIC("com/kneelawk/nilclassicapi/impl/client/premain/MinecraftTrans$Hooks", "onMinecraftStarted",
                "(Lcom/mojang/minecraft/Minecraft;)V")
        );
    }
}
