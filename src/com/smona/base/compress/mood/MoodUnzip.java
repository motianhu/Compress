package com.smona.base.compress.mood;

import java.io.File;

import com.smona.base.compress.IUnZip;
import com.smona.base.util.Constants;
import com.smona.base.util.FileOperator;
import com.smona.base.util.Logger;
import com.smona.base.util.ZipFileAction;

public class MoodUnzip implements IUnZip {

    @Override
    public void unzip(String source) {
        initEnv(source);
        unzip(source, source + Constants.IMAGE_PATH, source
                + Constants.XML_PATH);
    }

    private void initEnv(String source) {
        String image = source + Constants.IMAGE_PATH;
        String xml = source + Constants.XML_PATH;
        
        deleteFolder(image);
        deleteFolder(xml);
        
        mkdir(image);
        mkdir(xml);
    }
    
    private static void deleteFolder(String path) {
        File dirFile = new File(path);
        FileOperator.deleteDirectory(dirFile);
    }

    private File mkdir(String target) {
        File tempDir = new File(target);
        tempDir.mkdir();
        return tempDir;
    }

    private void unzip(String source, String imagePath, String xmlPath) {
        ZipFileAction action = new ZipFileAction();
        File sourceFile = new File(source);
        File[] picFiles = sourceFile.listFiles();
        for (File file : picFiles) {
            if (file.isFile() && file.getName().endsWith(Constants.ZIP)) {
                try {
                    action.unZip(file.getAbsolutePath(), imagePath, xmlPath);
                } catch (Exception e) {
                    Logger.printDetail("unzip failed! file: " + file);
                    e.printStackTrace();
                }
            }
        }
    }
}
