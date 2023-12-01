package com.kneelawk.nilclassicapi.impl.server.premain;

import com.kneelawk.nilclassicapi.impl.NilClassicAPILog;

@SuppressWarnings("unused")
public class NilClassicAPIPremain implements Runnable {
    @Override
    public void run() {
        NilClassicAPILog.log.info("NilClassicAPI premain!");
    }
}
