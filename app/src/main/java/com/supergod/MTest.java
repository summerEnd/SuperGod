package com.supergod;

import android.test.AndroidTestCase;

import com.supergod.net.util.JsonUtil;
import com.supergod.net.util.TestClass;
import com.supergod.util.SLog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by acer on 2014/9/30.
 */
public class MTest extends AndroidTestCase{
    public void test(){
        try {
            TestClass cls=JsonUtil.get(new JSONObject("{name=\"zhu\",age=7,zxc=99}"),TestClass.class);
            SLog.i("---->",cls+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
