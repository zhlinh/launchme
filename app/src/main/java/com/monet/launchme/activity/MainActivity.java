package com.monet.launchme.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.monet.launchme.R;
import com.monet.launchme.util.FilesUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 去掉UI显示
        //TextView textView = (TextView) findViewById(R.id.blank_txt);
        String confName = "launchme.conf";
        File tmpFile = new File(MainActivity.this.getFilesDir().toString() + "/" + confName);
        String info = "";
        String res = "";
        if (!tmpFile.exists()) {
            /**
             * 写文件初始化
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
         * 读文件
         */
        try {
            info = FilesUtil.load(MainActivity.this, confName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 解析内容
         */
        String[] cmds = info.split(";");
        if (cmds.length > 0) {
            for (String cmd: cmds) {
                if (cmd != null || cmd != "") {
                    res = do_exec(cmd);
                }
            }
        }
        // 去掉UI显示
        //textView.setText(res);
        Boolean done = res == ""? false: true;
        sayGoodBye(done);
    }


    String do_exec(String cmd) {
        String output = "";
        try {
            Log.e("do_exec", "(cmd) " + cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                output += line + "\n";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.e("do_exec", "(output) " + output);
        // 去掉UI显示
        //Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();
        return output;
    }

    private void sayGoodBye(Boolean doneWell){
        // 退出程序
        if (doneWell) {
            // 立即退出
            finish();
        } else {
            try {
                //5秒后退出
                Thread.currentThread();
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            finish();
        }
    }
}
