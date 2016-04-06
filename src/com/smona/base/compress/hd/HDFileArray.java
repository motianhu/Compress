package com.smona.base.compress.hd;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.smona.base.compress.FileArray;
import com.smona.base.compress.FileItem;
import com.smona.base.util.Constants;
import com.smona.base.util.FileOperator;
import com.smona.base.util.Logger;
import com.smona.base.util.ZipFileAction;

public class HDFileArray extends FileArray {

    private String[] fileSuffix = new String[] { " 拷贝", " 拷贝 2", " 拷贝 3",
            " 拷贝 4", " 拷贝 5", " 拷贝 6", " 拷贝 7", " 拷贝 8", " 拷贝 9", " 拷贝 10",
            " 拷贝 11", " 拷贝 12", " 拷贝 13" };

    HashMap<String, String> fileFix = new HashMap<String, String>();
    {
        fileFix.put(fileSuffix[0], Constants.RELUTION_1440x1280);
        fileFix.put(fileSuffix[1], Constants.RELUTION_1080x960);
        fileFix.put(fileSuffix[2], Constants.RELUTION_960x854);
        fileFix.put(fileSuffix[3], Constants.RELUTION_816x725);
        fileFix.put(fileSuffix[4], Constants.RELUTION_744x661);
        fileFix.put(fileSuffix[5], Constants.RELUTION_672x597);
        fileFix.put(fileSuffix[6], Constants.RELUTION_600x533);
        fileFix.put(fileSuffix[7], Constants.RELUTION_528x469);
        fileFix.put(fileSuffix[8], Constants.RELUTION_456x405);
        fileFix.put(fileSuffix[9], Constants.RELUTION_384x341);
        fileFix.put(fileSuffix[10], Constants.RELUTION_312x277);
        fileFix.put(fileSuffix[11], Constants.RELUTION_240x213);
        fileFix.put(fileSuffix[12], Constants.RELUTION_168x149);
    }

    @Override
    public void addFile(File file) {
        String name = file.getName();
        if (name.contains(fileSuffix[12])) {
            HDFileItem item = addFileItem(name, fileSuffix[12]);
            item.source_thumbnail = file;
        } else if (name.contains(fileSuffix[11])) {
            HDFileItem item = addFileItem(name, fileSuffix[11]);
            item.source_240x213 = file;
        } else if (name.contains(fileSuffix[10])) {
            HDFileItem item = addFileItem(name, fileSuffix[10]);
            item.source_312x277 = file;
        } else if (name.contains(fileSuffix[9])) {
            HDFileItem item = addFileItem(name, fileSuffix[9]);
            item.source_384x341 = file;
        } else if (name.contains(fileSuffix[8])) {
            HDFileItem item = addFileItem(name, fileSuffix[8]);
            item.source_456x405 = file;
        } else if (name.contains(fileSuffix[7])) {
            HDFileItem item = addFileItem(name, fileSuffix[7]);
            item.source_528x469 = file;
        } else if (name.contains(fileSuffix[6])) {
            HDFileItem item = addFileItem(name, fileSuffix[6]);
            item.source_600x533 = file;
        } else if (name.contains(fileSuffix[5])) {
            HDFileItem item = addFileItem(name, fileSuffix[5]);
            item.source_672x597 = file;
        } else if (name.contains(fileSuffix[4])) {
            HDFileItem item = addFileItem(name, fileSuffix[4]);
            item.source_744x661 = file;
        } else if (name.contains(fileSuffix[3])) {
            HDFileItem item = addFileItem(name, fileSuffix[3]);
            item.source_816x725 = file;
        } else if (name.contains(fileSuffix[2])) {
            HDFileItem item = addFileItem(name, fileSuffix[2]);
            item.source_960x854 = file;
        } else if (name.contains(fileSuffix[1])) {
            HDFileItem item = addFileItem(name, fileSuffix[1]);
            item.source_1080x960 = file;
        } else if (name.contains(fileSuffix[0])) {
            HDFileItem item = addFileItem(name, fileSuffix[0]);
            item.source_1440x1280 = file;
        } else {
            try {
                HDFileItem item = addFileItem(name, Constants.JPG);
                item.source = file;
            } catch (Exception e) {
                e.printStackTrace();
                Logger.printDetail("excption: " + e);
            }
        }

    }

    @Override
    public void copy(File path) {
        Iterator<Entry<String, FileItem>> iter = mItemMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, FileItem> entry = (Entry<String, FileItem>) iter
                    .next();
            String key = entry.getKey();
            File dir = new File(path + "/" + key);
            boolean isCreate = dir.mkdir();
            try {
                if (isCreate) {
                    FileItem val = entry.getValue();
                    val.copy(dir.getAbsolutePath());
                } else {
                    Logger.printDetail("Dir " + key + " create failed!!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.printReport("Dir " + key + " 不规范! "
                        + FileOperator.deleteDirectory(dir));
                Logger.printDetail("Exception " + e.getMessage());
            }

        }
    }

    @Override
    public void compress(File sDir, File tDir) {
        File[] sZipFile = sDir.listFiles();
        ZipFileAction zip = new ZipFileAction();
        for (File file : sZipFile) {
            try {
                zip.zip(file.getAbsolutePath(), tDir.getAbsolutePath() + "/"
                        + file.getName() + Constants.ZIP);
                Logger.printDetail("File " + file.getName()
                        + " compress successfully!");
            } catch (Exception e) {
                Logger.printDetail("File " + file.getName()
                        + " compress failed======================");
                e.printStackTrace();
                Logger.printDetail("exception : " + e.toString());
            }
        }
    }

    private HDFileItem addFileItem(String name, String suffix) {
        int endIndex = name.indexOf(suffix);
        Logger.printDetail("exception : " + name + ", endIndex: " + endIndex);
        String fileName = name.substring(0, endIndex);
        HDFileItem item = (HDFileItem) mItemMap.get(fileName);
        if (item == null) {
            item = new HDFileItem();
            mItemMap.put(fileName, item);
        }
        return item;
    }

    @Override
    public void println() {
        Iterator<Entry<String, FileItem>> iter = mItemMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, FileItem> entry = (Entry<String, FileItem>) iter
                    .next();
            String key = entry.getKey();
            System.out.println(key);
            FileItem val = (FileItem) entry.getValue();
            val.println();
        }
    }
}
