package com.smona.base.compress.hd;

import java.io.File;

import com.smona.base.compress.AbstractCompress;

public class HDCompress extends AbstractCompress {

    @Override
    protected void init() {
        mArrayFile = new HDFileArray();
    }

    @Override
    protected void parse(File source) {
        File[] picFiles = source.listFiles();
        for (File file : picFiles) {
            mArrayFile.addFile(file);
        }
    }

    @Override
    protected void copy(File temp) {
        mArrayFile.copy(temp);
    }

    @Override
    protected void compress(File temp, File target) {
        mArrayFile.compress(temp, target);
    }

    @Override
    protected void clear(File temp) {
    }
}
