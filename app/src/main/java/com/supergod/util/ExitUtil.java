package com.supergod.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by acer on 2014/9/26.
 */
public class ExitUtil {
    private static long lastTime = 0;

    public static void exit(Activity activity, int duration) {
        long curTime=System.currentTimeMillis();
        if ( curTime -lastTime < duration) {
            activity.finish();
            System.exit(0);
            lastTime=0;
        }else{
            Toast.makeText(activity,"再按一次退出！",Toast.LENGTH_SHORT);
            lastTime=curTime;
        }
    }

}
