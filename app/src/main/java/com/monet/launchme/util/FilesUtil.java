package com.monet.launchme.util;

import android.content.Context;
import android.util.Log;

import com.monet.launchme.activity.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Monet on 2016-12-18.
 */

public class FilesUtil {
    public static final String TAG = "launchme-" + FilesUtil.class.getSimpleName();
    private static final boolean IS_DEBUG = MainActivity.IS_DEBUG;

    /**
     * 文件存放在 /data/data/<package name>/files/ 下，需要root权限才能查看
     *
     * @param context
     * @param fileName
     * @param data
     * @return
     */
    public static void save(Context context, String fileName, String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            // 打开文件
            out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
            debug("Write configure file succeed.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件存放在 /data/data/<package name>/files/ 下，需要root权限才能查看
     *
     * @param context
     * @param fileName
     * @return String
     */
    public static String load(Context context, String fileName) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            // 打开文件
            in = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        debug("Read configure file succeed.");
        return content.toString();
    }

    /**
     * 打印debug信息
     *
     * @param message
     */
    private static void debug(String message) {
        if (IS_DEBUG) {
            Log.d(TAG, message);
        }
    }

}
