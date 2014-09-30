package com.supergod.net.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by acer on 2014/9/30.
 */
public class JsonUtil {

    /**
     * 通过反射用json构造指定的对象
     *
     * @param cls 必须要有一个空的构造方法，否则会返回null
     */
    public static <T> T parse(String json, Class<T> cls) throws JSONException {
        return parse(new JSONObject(json), cls);

    }

    /**
     * 通过反射用json构造指定的对象
     *
     * @param cls 必须要有一个空的构造方法，否则会返回null
     */
    public static <T> T parse(JSONObject object, Class<T> cls) {
        T t = null;
        try {
            Constructor<T> c = cls.getDeclaredConstructor();
            c.setAccessible(true);
            t = c.newInstance();
            String name;
            Object value;

            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                name = f.getName();
                try {
                    value = object.get(name);
                    f.set(t, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 获取列表
     * @return
     */
    public static List get(){

        return null;
    }

}
