package com.kneelawk.nilclassicapi.impl.premain;

import nilloader.api.ClassTransformer;

@SuppressWarnings("unused")
public class NilClassicAPIPremain implements Runnable {
    @Override
    public void run() {
        NilClassicAPILog.log.info("NilClassicAPI premain!");

        ClassTransformer.register(new MinecraftAppletTrans());
    }
}
