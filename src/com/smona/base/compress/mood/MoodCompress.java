package com.smona.base.compress.mood;

import java.io.File;

import com.smona.base.compress.AbstractCompress;
import com.smona.base.util.Constants;

public class MoodCompress extends AbstractCompress {

    @Override
    protected void init() {
        mArrayFile = new MoodFileArray();
    }

    @Override
    protected void parse(File source) {
        String imagePath = source.getAbsolutePath() + Constants.IMAGE_PATH;
        File image = new File(imagePath);
        File[] picFiles = image.listFiles();
        for (File file : picFiles) {
            mArrayFile.addFile(file);
        }
        
        String xmlPath = source.getAbsolutePath() + Constants.XML_PATH;
        File xml = new File(xmlPath);
        File[] xmlFiles = xml.listFiles();
        for (File file : xmlFiles) {
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
