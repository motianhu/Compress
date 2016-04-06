package com.smona.base.compress;

import java.io.File;

public abstract class FileItem implements IFileInfo {
    public File source;

    abstract public void copy(String path);

    abstract public void println();
}
