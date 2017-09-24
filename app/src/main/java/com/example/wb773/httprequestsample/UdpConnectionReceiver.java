package com.example.wb773.httprequestsample;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by wb773 on 2017/09/24.
 */

public class UdpConnectionReceiver extends AsyncTask<Void, Void, String>  {

    //-----------------------------------------------------
    // UDP 受信
    // ■ Android実機によるUDP受信ができません
    // https://groups.google.com/forum/#!topic/android-group-japan/EhZ_2g6Zwfo
    // telnet localhost 5554　で
    // redir add udp:9999:9999
    //
    // ■ JavaでUDP通信をしてみた
    // https://qiita.com/niusounds/items/7cb0989aa7792bcb0681
    //
    //-----------------------------------------------------


    @Override
    protected String doInBackground(Void... voids) {

        try{
            // UDPチャンネルを作成
            DatagramChannel channel = DatagramChannel.open();

            // 9999番ポート宛のUDPメッセージを受け取るようにする
            channel.socket().bind(new InetSocketAddress(9999));

            // メッセージを受け取るためのバッファを用意する。
            // バッファよりメッセージが大きい場合、入り切らなかったメッセージは破棄される。
            ByteBuffer buf = ByteBuffer.allocate(453);
            buf.clear();



            while(true){
                // メッセージの受信を待機する
                channel.receive(buf);

                // データをbyte[]に受け取る
                buf.flip();
                byte[] data = new byte[buf.limit()];

                ByteBuffer byteBuffer = buf.get(data);

                System.out.println("■■■ byteBuffer ■■■");
                System.out.println(new String(data)); // 受信したメッセージを文字列に変換して表示する
                System.out.println("■■■■■■■■■■■■■■■■■■");

                buf.clear();

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return null;
    }
}
