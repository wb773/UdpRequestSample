package com.example.wb773.httprequestsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //受信待機
        new UdpConnectionReceiver().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new UdpConnectionSample().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // APIを飛ばす処理
//        new HttpURLConnectionSample().execute(); // executeメソッドでdoInBackgroundメソッドを別スレッドで実行

    }


}
