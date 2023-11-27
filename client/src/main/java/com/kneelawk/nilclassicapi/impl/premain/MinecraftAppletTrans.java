package com.kneelawk.nilclassicapi.impl.premain;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("com.mojang.minecraft.MinecraftApplet")
public class MinecraftAppletTrans extends MiniTransformer {
    @Patch.Method("<init>()V")
    public void patchConstructor(PatchContext ctx) {
        ctx.jumpToLastReturn();
        ctx.addFireEntrypoint("client-init");
    }
}
