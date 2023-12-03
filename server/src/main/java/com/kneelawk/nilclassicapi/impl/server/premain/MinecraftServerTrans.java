package com.kneelawk.nilclassicapi.impl.server.premain;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

import com.mojang.minecraft.server.MinecraftServer;

import com.kneelawk.nilclassicapi.api.server.event.ServerLifecycleEvents;

@Patch.Class("com.mojang.minecraft.server.MinecraftServer")
public class MinecraftServerTrans extends MiniTransformer {
    public static class Hooks {
        public static void onMinecraftServerCreated(MinecraftServer server) {
            ServerLifecycleEvents.SERVER_CREATED.invoker().onServerCreated(server);
        }

        public static void onMinecraftServerStarting(MinecraftServer server) {
            ServerLifecycleEvents.SERVER_STARTING.invoker().onServerStarting(server);
        }
    }

    @Patch.Method("<clinit>()V")
    public void patchClinit(PatchContext ctx) {
        ctx.jumpToStart();
        ctx.addFireEntrypoint("server-init");
    }

    @Patch.Method("main([Ljava/lang/String;)V")
    public void patchMain(PatchContext ctx) {
        ctx.search(INVOKESPECIAL("com/mojang/minecraft/server/MinecraftServer", "<init>", "()V")).jumpAfter();
        ctx.add(
            DUP(),
            INVOKESTATIC("com/kneelawk/nilclassicapi/impl/server/premain/MinecraftServerTrans$Hooks",
                "onMinecraftServerCreated", "(Lcom/mojang/minecraft/server/MinecraftServer;)V")
        );
    }

    @Patch.Method("run()V")
    public void patchRun(PatchContext ctx) {
        // SERVER_STARTING
        ctx.jumpToStart();
        ctx.add(
            ALOAD(0),
            INVOKESTATIC("com/kneelawk/nilclassicapi/impl/server/premain/MinecraftServerTrans$Hooks",
                "onMinecraftServerStarting", "(Lcom/mojang/minecraft/server/MinecraftServer;)V")
        );
    }
}
