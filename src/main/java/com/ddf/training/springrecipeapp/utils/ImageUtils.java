package com.ddf.training.springrecipeapp.utils;


import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public static Byte[] getImage(String url) {
        File file = new File(url);
        Byte[] image = getBytesFrom(readAllBytes(file));
        return image;
    }


    /**
     * This method uses java.io.FileInputStream to read
     * file content into a byte array
     *
     * @param file
     * @return
     */
    private static byte[] readFileToByteArray(File file) {
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return bArray;
    }

    private static byte[] readAllBytes(File file) {
        Path path = file.toPath();
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new byte[0];
    }

}
