package com.smona.base;

import com.smona.base.compress.ICompress;
import com.smona.base.compress.IUnZip;
import com.smona.base.compress.hd.HDCompress;
import com.smona.base.compress.mood.MoodCompress;
import com.smona.base.compress.mood.MoodUnzip;
import com.smona.base.util.Constants;
import com.smona.base.util.Logger;

public class Main {

    public static void main(String[] args) {
        Logger.init();
        String encode = System.getProperty("file.encoding");
        println(encode);
        String path = System.getProperty("user.dir");
        println(path);
        action(path, args[0]);
    }

    private static void action(String path, String cmd) {
        if (Constants.CMD_HD.equals(cmd)) {
            compressHD(path);
        } else if (Constants.CMD_MOOD_UNZIP.equals(cmd)) {
            unzip(path);
        } else if (Constants.CMD_MOOD_ZIP.equals(cmd)) {
            compressMood(path);
        }
    }

    private static void unzip(String path) {
        String source = path + "/source";
        IUnZip unzip = new MoodUnzip();
        unzip.unzip(source);
    }

    private static void compressMood(String path) {
        String source = path + "/source";
        String temp = path + "/temp";
        String target = path + "/target";

        ICompress compress = new MoodCompress();
        compress.compress(source, temp, target);
    }

    private static void compressHD(String path) {
        String source = path + "/source";
        String temp = path + "/temp";
        String target = path + "/target";

        ICompress compress = new HDCompress();
        compress.compress(source, temp, target);
    }

    private static void println(String msg) {
        Logger.printDetail(msg);
    }

}
