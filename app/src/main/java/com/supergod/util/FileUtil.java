package com.supergod.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by acer on 2014/9/26.
 */
public class FileUtil {  /**
 * 读取缓存文件
 *
 * @param context
 * @param name
 * @return
 */
public Object readCache(Context context, String name) {
    return read(getCacheFile(context, name));
}

    /**
     * @param context
     * @param name
     */
    public void saveCache(Context context, String name, Object o) {
        save(getCacheFile(context, name), o);
    }

    /**
     * 读取一个非缓存文件
     *
     * @param context
     * @param name
     * @return
     */
    public Object readFile(Context context, String name) {
        return read(getFile(context, name));
    }

    /**
     * 讲一个对象保存到 一个非缓存文件中
     *
     * @param context
     * @param name
     * @param o
     */
    public void saveFile(Context context, String name, Object o) {
        save(getFile(context, name), o);
    }

    /**
     * 将一个文件读出，这个文件必须为一个序列化的对象
     *
     * @param file
     * @return
     */
    public Object read(File file) {
        Object obj = null;
        try {
            FileInputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("--->", "read:" + file + "===" + obj);
        return obj;
    }

    /**
     * 讲一个对象o写入到file中
     *
     * @param file
     * @param o
     */
    public void save(File file, Object o) {
        Log.i("--->", "save:" + file + "--->" + o);
        try {
            FileOutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 缓存文件路径 cache/<company_id>/name
     *
     * @param context
     * @param name
     * @return
     */
    private File getCacheFile(Context context, String name) {
        //创建用户目录
        File dir = new File(context.getCacheDir(), "directory_name");
        if (!dir.exists()) dir.mkdir();
        //创建缓存文件
        return  new File(dir, name);
    }

    private File getFile(Context context, String name) {
        return new File(context.getFilesDir(), name);
    }
}
