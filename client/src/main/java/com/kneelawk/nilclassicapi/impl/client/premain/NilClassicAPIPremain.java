package com.kneelawk.nilclassicapi.impl.client.premain;

import nilloader.api.ClassTransformer;

import com.kneelawk.nilclassicapi.impl.NilClassicAPILog;

@SuppressWarnings("unused")
public class NilClassicAPIPremain implements Runnable {
    @Override
    public void run() {
        NilClassicAPILog.log.info("NilClassicAPI premain!");

        ClassTransformer.register(new MinecraftAppletTrans());
    }
}
