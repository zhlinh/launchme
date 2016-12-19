package com.monet.launchme.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.monet.launchme.R;
import com.monet.launchme.util.CommandUtil;
import com.monet.launchme.util.FilesUtil;

import java.io.File;

public class MainActivity extends Activity {
    public static final boolean IS_DEBUG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 去除UI显示
        //TextView textView = (TextView) findViewById(R.id.blank_txt);
        String confName = "launchme.conf";
        File tmpFile = new File(MainActivity.this.getFilesDir().toString() + "/" + confName);
        String info = "";
        int res = -1;
        if (!tmpFile.exists()) {
            /**
             * 若无配置文件，则生成默认的配置文件
             */
            Log.e("file", tmpFile.toString() + " does not exists.");
            String defaultData = "/data/system/launchme_bin";
            try {
                FilesUtil.save(MainActivity.this, confName, defaultData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 读配置文件
         */
        try {
            info = FilesUtil.load(MainActivity.this, confName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 解析内容，并执行命令
         */
        String[] cmds = info.split(";");
        CommandUtil.execute(cmds);
        // 去除UI显示
        //textView.setText(res);

        /**
         * 退出程序
         */
        finish();
    }
}
