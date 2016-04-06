package com.smona.base.compress.hd;

import java.io.File;

import com.smona.base.compress.FileItem;
import com.smona.base.util.Constants;
import com.smona.base.util.FileOperator;

public class HDFileItem extends FileItem {
    public File source_1440x1280;
    public File source_1080x960;
    public File source_960x854;
    public File source_816x725;
    public File source_744x661;
    public File source_672x597;
    public File source_600x533;
    public File source_528x469;
    public File source_456x405;
    public File source_384x341;
    public File source_312x277;
    public File source_240x213;
    public File source_thumbnail;

    public void copy(String path) {
        String name = source.getName();
        FileOperator.fileChannelCopy(source, path + "/" + name);

        String reluPath = path + "/" + Constants.RELUTION_2160x1920;
        copyTemp(source, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_1440x1280;
        copyTemp(source_1440x1280, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_1080x960;
        copyTemp(source_1080x960, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_960x854;
        copyTemp(source_960x854, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_816x725;
        copyTemp(source_816x725, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_744x661;
        copyTemp(source_744x661, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_672x597;
        copyTemp(source_672x597, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_600x533;
        copyTemp(source_600x533, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_528x469;
        copyTemp(source_528x469, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_456x405;
        copyTemp(source_456x405, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_384x341;
        copyTemp(source_384x341, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_312x277;
        copyTemp(source_312x277, name, reluPath);

        reluPath = path + "/" + Constants.RELUTION_240x213;
        copyTemp(source_240x213, name, reluPath);

        // thumbnail == 168x149
        reluPath = path + "/" + Constants.RELUTION_THUMBNAIl;
        copyTemp(source_thumbnail, name, reluPath);
    }

    private void copyTemp(File picName, String name, String reluPath) {
        File relution = new File(reluPath);
        relution.mkdir();
        FileOperator.fileChannelCopy(picName, reluPath + "/" + name);
    }

    public void println() {
        println(source);
        println(source_1440x1280);
        println(source_1080x960);
        println(source_960x854);
        println(source_816x725);
        println(source_744x661);
        println(source_672x597);
        println(source_600x533);
        println(source_528x469);
        println(source_456x405);
        println(source_384x341);
        println(source_312x277);
        println(source_240x213);
        println(source_thumbnail);
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
