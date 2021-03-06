package com.supergod.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Super God on 2014/9/28.
 */
public class ImageUtil {


    private static String getImageFile() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    /**
     * 保存方法
     */
    public static Uri saveBitmap(Bitmap bm, String picName) {
        if (TextUtils.isEmpty(picName) || bm == null) return null;
        File f = new File(getImageFile(), picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Uri.fromFile(f);
    }

    public static Bitmap readBitmap(String picName) {
        Bitmap bitmap = null;
        try {
            File f = new File(getImageFile(), picName);
            FileInputStream inputStream = new FileInputStream(f);
            bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static String base64Encode(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        String format;
        switch (compressFormat) {
            case WEBP:
                format = "webp";
                break;
            case JPEG:
                format = "jpeg";
                break;
            default:
                format = "png";
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, bos);
        byte[] bytes = bos.toByteArray();
        try {
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/" + format + ";base64," + Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static String base64Encode(Bitmap bitmap) {
        return base64Encode(bitmap, Bitmap.CompressFormat.PNG);
    }

    /**
     * 以一个bitmap的中心为圆点，以radius为半径，去截取
     *
     * @param src
     * @param radius
     * @return
     */
    public static Bitmap roundBitmap(Bitmap src, int radius) {
        int src_w = src.getWidth();
        int src_h = src.getHeight();
        int result_length = radius * 2;
        SLog.i("--->", "src_w=" + src_w + " src_h=" + src_h);
        float scale;
        if (src_w > src_h) {
            scale = result_length / (float) src_h;
        } else {
            scale = result_length / (float) src_w;
        }
        src_w *= scale;
        src_h *= scale;
        SLog.i("--->", "src_w=" + src_w + " src_h=" + src_h + " scale=" + scale + " radius=" + radius);
        Bitmap resizeSrc = Bitmap.createScaledBitmap(src, src_w, src_h, false);//缩放后的Bitmap

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap result = Bitmap.createBitmap(result_length, result_length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
//        canvas.drawARGB(0,0,0,0);//背景透明效果
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        float translate_x = (result.getWidth() - resizeSrc.getWidth()) / 2;
        float translate_y = (result.getHeight() - resizeSrc.getHeight()) / 2;

        canvas.save();
        canvas.translate(translate_x, translate_y);
        canvas.drawBitmap(resizeSrc, 0, 0, paint);
        canvas.restore();

        if (result != resizeSrc && !resizeSrc.isRecycled()) resizeSrc.recycle();
        if (result != src && !src.isRecycled()) src.recycle();

        return result;
    }

    /**
     * 耗时方法，不可放在UI线程上
     *
     * @param path
     * @return
     */
    public static Bitmap getImageFromWeb(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
