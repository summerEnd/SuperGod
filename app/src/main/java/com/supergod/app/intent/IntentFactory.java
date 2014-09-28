package com.supergod.app.intent;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * 创建Intent的Factory
 */
public class IntentFactory {
    /**
     * @param uri 要剪裁的图片
     * @param aspectX 宽比例
     * @param aspectY 高比例
     * @param outputX 宽度
     * @param outputY 高度
     * @return
     */
    public static Intent cropActivity(Uri uri,int aspectX,int aspectY,int outputX,int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 640);
        intent.putExtra("aspectY", 400);
        intent.putExtra("outputX", 640);
        intent.putExtra("outputY", 400);
        intent.putExtra("return-data", true);
        return intent;
    }

    /**
     * 拍照Intent
     * @param outPut
     * @return
     */
    public static Intent cameraActivity(Uri outPut){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPut);
        return intent;
    }
}
