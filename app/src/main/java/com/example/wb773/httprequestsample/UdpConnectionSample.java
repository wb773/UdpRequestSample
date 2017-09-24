package com.example.wb773.httprequestsample;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

/**
 * Created by wb773 on 2017/09/19.
 */

public class UdpConnectionSample extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {

//        try (DatagramSocket clientSocket = new DatagramSocket()) {
//
//            byte[] obj = "Hello".getBytes(); // 適当なデータを用意
//            String address = "127.0.0.1"; // 受信側端末の実際のアドレスに書き換える
//            int port = 12345;                // 受信側と揃える
//
//            InetAddress IPAddress = InetAddress.getByName(address);
//            byte[] sendData = convertToBytes(obj);
//
//            System.out.println("■■■ SendData ■■■");
//            System.out.println(obj.toString());
//            System.out.println("■■■■■■■■■■■■■■■■");
//
//            DatagramPacket sendPacket = new DatagramPacket(obj, obj.length, IPAddress, port);
//
//
//            System.out.println("■■■ SemndUDP ■■■");
//            for (int i = 0; i < 10; i++){
//                clientSocket.send(sendPacket);
//            }
//            System.out.println("■■■■■■■■■■■■■■■■");
//
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }




        try {

            //------------------------------------------
            // 送信処理
            //------------------------------------------

            // UDPチャンネルを作成
            DatagramChannel channel = DatagramChannel.open();
            // 9999番ポートからUDPメッセージを送るようにする
            channel.socket().bind(new InetSocketAddress(9998));

            // 送信するデータをByteBufferの形にする。
            // ここではString->byte[]->ByteBufferと変換している。
            String newData = "New String to write to file..." + System.currentTimeMillis();

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();

            // 送信
            int bytesSent = channel.send(buf, new InetSocketAddress("10.0.2.2", 12345)); //Emulatorから見たらLocalhost(127.0.0.1が10.0.2.2となるので注意)

            //------------------------------------------
            // 受信の待機
            //------------------------------------------
            //バッファのクリア
            buf.clear();

            // メッセージの受信を待機する
            channel.receive(buf);

            // データをbyte[]に受け取る
            buf.flip();
            byte[] data = new byte[buf.limit()];
            buf.get(data); //これをしないとデータが正しく入らない

            System.out.println("■■■ byteBuffer ■■■");
            System.out.println(new String(data));
            System.out.println("■■■■■■■■■■■■■■■■■■■");


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    /**
     * オブジェクトをバイト配列に変換する。
     *
     * @param  object Serializableを実装していなければいけない。
     * @return バイト配列
     * @throws IOException シリアライズに失敗した時に発生する
     */
    private static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

}
