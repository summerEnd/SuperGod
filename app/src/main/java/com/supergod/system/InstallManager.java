package com.supergod.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by acer on 2014/9/30.
 */
public class InstallManager {
    private Context context;

    public InstallManager(Context context) {
        this.context = context;
    }

    /**
     * 安装apk
     * @param file
     */
    public void install(File file){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public void unInstall(){
        Uri packageURI = Uri.parse("package:com.demo.CanavaCancel");
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }
}
