package com.smona.base.compress.mood;

import java.io.File;

import com.smona.base.compress.FileItem;
import com.smona.base.util.Constants;
import com.smona.base.util.FileOperator;

public class MoodFileItem extends FileItem {
    public File source_1080x1920;
    public File source_720x1280;
    public File source_540x960;
    public File source_480x854;
    public File source_360x640; // source up/impress down
    public File source_350x625;
    public File source_270x480;
    public File source_240x427;
    public File source_180x320;
    public File source_160x285;
    public File source_106x190;
    public File source_thumbnail;

    public File source_1080x1920_font;
    public File source_720x1280_font;
    public File source_540x960_font;
    public File source_480x854_font;
    public File source_360x640_font; // source up/impress down
    public File source_350x625_font;
    public File source_270x480_font;
    public File source_240x427_font;
    public File source_180x320_font;
    public File source_160x285_font;
    public File source_106x190_font;
    public File source_thumbnail_font;

    // xml
    public File source_xml;
    public File source_font;

    public void copy(String path) {
        // source
        copySource(path);

        // font
        copyFont(path);
        
        //copy
        copyXml(path);
    }

    private void copySource(String path) {
        String name = source.getName();
        FileOperator.fileChannelCopy(source, path + "/" + name);

        // every relution
        String reluPath = path + "/" + Constants.RELUTION_1080x1920;
        copyTemp(source_1080x1920, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_720x1280;
        copyTemp(source_720x1280, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_540x960;
        copyTemp(source_540x960, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_480x854;
        copyTemp(source_480x854, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_360x640;
        copyTemp(source_360x640, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_350x625;
        copyTemp(source_350x625, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_270x480;
        copyTemp(source_270x480, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_240x427;
        copyTemp(source_240x427, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_180x320;
        copyTemp(source_180x320, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_160x285;
        copyTemp(source_160x285, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_106x190;
        copyTemp(source_106x190, name, reluPath);

        // thumbnail == 168x149
        reluPath = path + "/" + "thumbnail";
        copyTemp(source_thumbnail, name, reluPath);
    }

    private void copyFont(String path) {
        String name = source_font.getName();
        FileOperator.fileChannelCopy(source_font, path + "/" + name);

        // every relution
        String reluPath = path + "/" + Constants.RELUTION_1080x1920;
        copyTemp(source_1080x1920_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_720x1280;
        copyTemp(source_720x1280_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_540x960;
        copyTemp(source_540x960_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_480x854;
        copyTemp(source_480x854_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_360x640;
        copyTemp(source_360x640_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_350x625;
        copyTemp(source_350x625_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_270x480;
        copyTemp(source_270x480_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_240x427;
        copyTemp(source_240x427_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_180x320;
        copyTemp(source_180x320_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_160x285;
        copyTemp(source_160x285_font, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_106x190;
        copyTemp(source_106x190_font, name, reluPath);

        // thumbnail == 168x149
        reluPath = path + "/" + "thumbnail";
        copyTemp(source_thumbnail_font, name, reluPath);
    }
    
    private void copyXml(String path) {
        String name = source_xml.getName();
        FileOperator.fileChannelCopy(source_xml, path + "/" + name);

    }

    private void copyTemp(File picName, String name, String reluPath) {
        File relution = new File(reluPath);
        relution.mkdir();
        FileOperator.fileChannelCopy(picName, reluPath + "/" + name);
    }

    public void println() {
        printlnSource();
        
        printlnFont();
    }

    private void printlnSource() {
        println(source);
        println(source_1080x1920);
        println(source_720x1280);
        println(source_540x960);
        println(source_480x854);
        println(source_360x640);
        println(source_350x625);
        println(source_270x480);
        println(source_240x427);
        println(source_180x320);
        println(source_160x285);
        println(source_106x190);
        println(source_thumbnail);
    }

    private void printlnFont() {
        println(source_font);
        println(source_1080x1920_font);
        println(source_720x1280_font);
        println(source_540x960_font);
        println(source_480x854_font);
        println(source_360x640_font);
        println(source_350x625_font);
        println(source_270x480_font);
        println(source_240x427_font);
        println(source_180x320_font);
        println(source_160x285_font);
        println(source_106x190_font);
        println(source_thumbnail_font);
    }

    private void println(File file) {
        String name;
        if (file == null) {
            name = "null";
        } else {
            name = file.getName();
        }
        System.out.println(name);
    }
}
