package com.smona.base.compress;

import java.io.File;
import java.util.HashMap;

import com.smona.base.compress.FileItem;

public abstract class FileArray {
    protected HashMap<String, FileItem> mItemMap = new HashMap<String, FileItem>();
    
    abstract public void addFile(File file);

    abstract public void copy(File path);

    abstract public void compress(File sDir, File tDir);
    
    abstract public void println();
}
