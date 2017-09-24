package com.example.wb773.httprequestsample;

/**
 * Created by wb773 on 2017/09/18.
 */

public class HexStringUtil {


    /**
     * 与えられたバイト配列を16進表記の文字列に変換します。
     * 2バイト目以降には、前のバイトとの区切りのために
     * 半角空白を付与します。
     * 変換例。入力:[愛植岡]のシフトJIS化バイト配列
     * → 出力：[88 a4 90 41 89 aa]
     *
     * @param argBytes バイト配列。
     * @return 16進表記の文字列。
     */
    public static String bytesToHexString(final byte[] argBytes) {

        final StringBuffer buffer = new StringBuffer();
        for(int byteIndex=0;byteIndex<argBytes.length;byteIndex++){
            if (byteIndex != 0) {
                //2バイト目以降には半角空白を区切り文字として付与
                buffer.append(' ');
            }
            buffer.append(byteToHexString(argBytes[byteIndex]));
        }
        return buffer.toString();
    }
    /**
     * 与えられたバイトを16進表記の文字列に変換します。
     * 必ず2文字が戻ります。
     *
     * @param argByte バイト値。
     * @return 16進表記の文字列。
     */
    public static String byteToHexString(final byte argByte) {
        int wrkValue = argByte;
        if (wrkValue < 0) {
            // 負の値の場合には正の値に変換します。
            wrkValue += 0x100;
        }
        String result = Integer.toHexString(wrkValue);
        if (result.length() < 2) {
            //1文字の場合には0の文字を加えて2文字になるようにします
            result = "0" + result;
        }
        return result;
    }

}
