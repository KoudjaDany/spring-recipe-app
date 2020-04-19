package com.ddf.training.springrecipeapp.utils;

import javax.validation.constraints.NotNull;

public class ImageUtils {

    public static byte[] getBytesFrom(@NotNull Byte[] sourceBytes) {
        if (sourceBytes == null) {
            return new byte[0];
        }
        byte[] byteBoxed = new byte[sourceBytes.length];
        int i = 0;
        for (byte primByte : sourceBytes) {
            byteBoxed[i++] = primByte;
        }

        return byteBoxed;
    }

    public static Byte[] getBytesFrom(@NotNull byte[] sourceBytes) {
        if (sourceBytes == null) {
            return new Byte[0];
        }
        Byte[] byteBoxed = new Byte[sourceBytes.length];
        int i = 0;
        for (byte primByte : sourceBytes) {
            byteBoxed[i++] = primByte;
        }

        return byteBoxed;
    }

}
