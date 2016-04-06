package com.smona.base.compress;

import java.io.File;

import com.smona.base.util.FileOperator;
import com.smona.base.util.Logger;

public abstract class AbstractCompress implements ICompress {
    protected FileArray mArrayFile;

    @Override
    public void compress(String source, String temp, String target) {
        deleteFolder(temp);
        deleteFolder(target);
        
        File sFile = new File(source);
        File tmpFile = mkdir(temp);
        File tFile = mkdir(target);
        initEnv(source);
        if (sFile.isDirectory()) {
            processSource(sFile, tmpFile, tFile);
        } else {
            Logger.printReport(source + " is not directory!");
        }
    }

    private static void deleteFolder(String path) {
        File dirFile = new File(path);
        FileOperator.deleteDirectory(dirFile);
    }
    
    protected void initEnv(String source){};

    protected File mkdir(String target) {
        File tempDir = new File(target);
        tempDir.mkdir();
        return tempDir;
    }

    public void processSource(File source, File temp, File target) {
        Logger.printReport("start!");
        init();
        Logger.printReport("start parse!");
        parse(source);
        Logger.printReport("parse end. start copy!");
        copy(temp);
        Logger.printReport("copy end. start compress!");
        compress(temp, target);
        Logger.printReport("compress end. start clear!");
        clear(temp);
        Logger.printReport("finish!");
    }

    abstract protected void init();

    abstract protected void parse(File source);

    abstract protected void copy(File temp);

    abstract protected void compress(File temp, File target);

    abstract protected void clear(File temp);
}
