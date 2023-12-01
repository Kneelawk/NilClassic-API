package com.kneelawk.nilclassicapi.impl.client.premain;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

import com.mojang.minecraft.Minecraft;

import com.kneelawk.nilclassicapi.api.client.event.ClientLifecycleEvents;

@Patch.Class("com.mojang.minecraft.MinecraftApplet")
public class MinecraftAppletTrans extends MiniTransformer {
    public static class Hooks {
        public static void onMinecraftCreated(Minecraft minecraft) {
            ClientLifecycleEvents.CLIENT_CREATED.invoker().onClientCreated(minecraft);
        }
    }

    @Patch.Method("<init>()V")
    public void patchConstructor(PatchContext ctx) {
        ctx.jumpToLastReturn();
        ctx.addFireEntrypoint("client-init");
    }

    @Patch.Method("init()V")
    public void patchInit(PatchContext ctx) {
        ctx.search(PUTFIELD("com/mojang/minecraft/MinecraftApplet", "minecraft", "Lcom/mojang/minecraft/Minecraft;"))
            .jumpAfter();
        ctx.add(
            ALOAD(0),
            GETFIELD("com/mojang/minecraft/MinecraftApplet", "minecraft", "Lcom/mojang/minecraft/Minecraft;"),
            INVOKESTATIC("com/kneelawk/nilclassicapi/impl/client/premain/MinecraftAppletTrans$Hooks",
                "onMinecraftCreated", "(Lcom/mojang/minecraft/Minecraft;)V")
        );
    }
}
