package com.lz.fram.download;


import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2017/12/26	9:24	    刘泽			    保存下载的文件
 */
public class DownloadManager {


    /**
     * 保存文件
     *
     * @param response     ResponseBody
     * @param destFileName 文件名（包括文件后缀）
     * @return 返回
     * @throws IOException
     */
    public File saveFile(ResponseBody response, final String destFileName, ProgressListener progressListener) {

        String destFileDir =  Environment.getExternalStorageDirectory().getAbsolutePath();

        long contentLength = response.contentLength();
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.byteStream();

            long sum = 0L;

            File file = new File(destFileDir, destFileName);

            if (!file.exists()) {
                file.createNewFile();
            }else if(file.length() == contentLength){
                progressListener.onResponseProgress(contentLength, contentLength, 100, true, file.toString());
                return file;
            }else{
                file.delete();
                file = new File(destFileDir, destFileName);
                file.createNewFile();
            }
            fos = new FileOutputStream(file);

            int progress = 0;

            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);

                final long finalSum = sum;

                int pro = (int) ((finalSum * 1.0f / contentLength) * 100);
                if(pro > progress){
                    progress = pro;
                    progressListener.onResponseProgress(finalSum, contentLength, progress, finalSum == contentLength, file.toString());
                }
            }
            fos.flush();

            return file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                response.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
            }

        }
    }


}
