package com.smona.base.compress.mood;

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

public class MoodFileArray extends FileArray {

    private String[] fileSuffix = new String[] { " 拷贝", " 拷贝 2", " 拷贝 3",
            " 拷贝 4", " 拷贝 5", " 拷贝 6", " 拷贝 7", " 拷贝 8", " 拷贝 9", " 拷贝 10",
            " 拷贝 11" };

    HashMap<String, String> fileFix = new HashMap<String, String>();
    {
        fileFix.put(fileSuffix[0], "720x1280");
        fileFix.put(fileSuffix[1], "540x960");
        fileFix.put(fileSuffix[2], "480x854");
        fileFix.put(fileSuffix[3], "360x640");
        fileFix.put(fileSuffix[4], "350x625");
        fileFix.put(fileSuffix[5], "270x480");
        fileFix.put(fileSuffix[6], "240x427");
        fileFix.put(fileSuffix[7], "180x320");
        fileFix.put(fileSuffix[8], "160x285");
        fileFix.put(fileSuffix[9], "106x190");
        fileFix.put(fileSuffix[10], "thumbnail"); // 90x160
    }

    @Override
    public void addFile(File file) {
        String name = file.getName();
        Logger.printDetail("addFile : " + name);
        if (name.endsWith(Constants.XML)) {
            addXml(file, name);
        } else if (name.endsWith(Constants.PROPERTIES)) {
            addProperties(file, name);
        } else {
            addJpg(file, name);
        }
    }

    private void addXml(File file, String name) {
        int endIndex = name.indexOf(Constants.XML);
        String fileName = name.substring(0, endIndex);
        MoodFileItem item = (MoodFileItem) mItemMap.get(fileName);
        if (item == null) {
            item = new MoodFileItem();
            mItemMap.put(fileName, item);
        }
        item.source_xml = file;
    }

    private void addProperties(File file, String name) {
        int endIndex = name.indexOf(Constants.PROPERTIES);
        String fileName = name.substring(0, endIndex);
        MoodFileItem item = (MoodFileItem) mItemMap.get(fileName);
        if (item == null) {
            item = new MoodFileItem();
            mItemMap.put(fileName, item);
        }
        item.source_properties = file;
    }

    private void addJpg(File file, String name) {
        if (name.contains(fileSuffix[10])) {
            MoodFileItem item = addFileItem(name, fileSuffix[10]);
            if (name.contains(Constants.FONT + fileSuffix[10])) {
                item.source_thumbnail_font = file;
            } else {
                item.source_thumbnail = file;
            }
        } else if (name.contains(fileSuffix[9])) {
            MoodFileItem item = addFileItem(name, fileSuffix[9]);
            if (name.contains(Constants.FONT + fileSuffix[9])) {
                item.source_106x190_font = file;
            } else {
                item.source_106x190 = file;
            }
        } else if (name.contains(fileSuffix[8])) {
            MoodFileItem item = addFileItem(name, fileSuffix[8]);
            if (name.contains(Constants.FONT + fileSuffix[8])) {
                item.source_160x285_font = file;
            } else {
                item.source_160x285 = file;
            }
        } else if (name.contains(fileSuffix[7])) {
            MoodFileItem item = addFileItem(name, fileSuffix[7]);
            if (name.contains(Constants.FONT + fileSuffix[7])) {
                item.source_180x320_font = file;
            } else {
                item.source_180x320 = file;
            }
        } else if (name.contains(fileSuffix[6])) {
            MoodFileItem item = addFileItem(name, fileSuffix[6]);
            if (name.contains(Constants.FONT + fileSuffix[6])) {
                item.source_240x427_font = file;
            } else {
                item.source_240x427 = file;
            }
        } else if (name.contains(fileSuffix[5])) {
            MoodFileItem item = addFileItem(name, fileSuffix[5]);
            if (!name.contains(Constants.FONT + fileSuffix[5])) {
                item.source_270x480_font = file;
            } else {
                item.source_270x480 = file;
            }
        } else if (name.contains(fileSuffix[4])) {
            MoodFileItem item = addFileItem(name, fileSuffix[4]);
            if (name.contains(Constants.FONT + fileSuffix[4])) {
                item.source_350x623_font = file;
            } else {
                item.source_350x623 = file;
            }
        } else if (name.contains(fileSuffix[3])) {
            MoodFileItem item = addFileItem(name, fileSuffix[3]);
            if (name.contains(Constants.FONT + fileSuffix[3])) {
                item.source_360x640_font = file;
            } else {
                item.source_360x640 = file;
            }
        } else if (name.contains(fileSuffix[2])) {
            MoodFileItem item = addFileItem(name, fileSuffix[2]);
            if (name.contains(Constants.FONT + fileSuffix[2])) {
                item.source_480x854_font = file;
            } else {
                item.source_480x854 = file;
            }
        } else if (name.contains(fileSuffix[1])) {
            MoodFileItem item = addFileItem(name, fileSuffix[1]);
            if (name.contains(Constants.FONT + fileSuffix[1])) {
                item.source_540x960_font = file;
            } else {
                item.source_540x960 = file;
            }
        } else if (name.contains(fileSuffix[0])) {
            MoodFileItem item = addFileItem(name, fileSuffix[0]);
            if (name.contains(Constants.FONT + fileSuffix[0])) {
                item.source_720x1280_font = file;
            } else {
                item.source_720x1280 = file;
            }
        } else if (name.contains(Constants.FONT)) {
            try {
                // font.jpg
                MoodFileItem item = addFileItem(name, Constants.JPG);
                item.source_font = file;
                item.source_1080x1920_font = file;
            } catch (Exception e) {
                e.printStackTrace();
                Logger.printDetail("excption: " + e);
            }
        } else {
            try {
                // .jpg
                MoodFileItem item = addFileItem(name, Constants.JPG);
                item.source = file;
                item.source_1080x1920 = file;
                Logger.printDetail("addJpg : " + name);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.printDetail("excption: " + e);
            }
        }
    }

    private MoodFileItem addFileItem(String name, String suffix) {
        int endIndex = name.indexOf(suffix);
        String fileName = name.substring(0, endIndex);
        MoodFileItem item = (MoodFileItem) mItemMap.get(fileName);

        // impress jpg
        if (item == null) {
            endIndex = fileName.indexOf(Constants.FONT);
            if (endIndex != -1) {
                fileName = fileName.substring(0, endIndex);
                item = (MoodFileItem) mItemMap.get(fileName);
            }
        }

        if (item == null) {
            item = new MoodFileItem();
            mItemMap.put(fileName, item);
        }
        return item;
    }

    @Override
    public void copy(File path) {
        Iterator<Entry<String, FileItem>> iter = mItemMap.entrySet().iterator();
        Logger.printDetail("Dir " + mItemMap.size());
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

    @Override
    public void println() {

    }

}
