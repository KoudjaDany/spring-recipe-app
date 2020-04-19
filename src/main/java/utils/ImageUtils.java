package utils;

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

}
