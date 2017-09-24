package com.example.wb773.httprequestsample;


import android.os.AsyncTask;

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

/**
 * Created by wb773 on 2017/09/18.
 */

public class HttpURLConnectionSample extends AsyncTask<Void, Void, String> {


    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection urlConnection = null;

        try {

            // ベースURL
            URL url = new URL("https://api.twitter.com/1.1/search/tweets.json?q=%40twitterapi");

            // 接続用HttpURLConnectionオブジェクト作成
            urlConnection = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            urlConnection.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            urlConnection.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            urlConnection.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
            urlConnection.setDoOutput(true);

            // 接続
            urlConnection.connect(); // ①
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String is = readInputStream(in);
            System.out.println("■■■ InputStream ■■■");
            System.out.println(is);
            System.out.println("■■■■■■■■■■■■■■■■■■■");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){


            InputStream in = new BufferedInputStream(urlConnection.getErrorStream());


            try {
                String is = readInputStream(in);

                System.out.println("■■■ ErrorStream ■■■");
                System.out.println(is);
                System.out.println("■■■■■■■■■■■■■■■■■■■");


            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }



    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null) {
            sb.append(st);
        }

        try {
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
