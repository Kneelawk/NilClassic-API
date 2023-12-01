package com.kneelawk.nilclassicapi.impl.server.premain;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("com.mojang.minecraft.server.MinecraftServer")
public class MinecraftServerTrans extends MiniTransformer {
    @Patch.Method("<clinit>()V")
    public void patchClinit(PatchContext ctx) {
        ctx.jumpToStart();
        ctx.addFireEntrypoint("server-init");
    }
}
