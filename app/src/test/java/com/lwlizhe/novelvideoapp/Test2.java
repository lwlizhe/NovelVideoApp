package com.lwlizhe.novelvideoapp;


import android.text.Html;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class Test2 {

    @Test
   public void test(){

        long start = System.currentTimeMillis();


        int test =0xac4c;

        int result=test&0xff;

        Integer.toHexString((Integer.parseInt("664a",16)&0xff));
        Integer.toHexString((Integer.parseInt("664a",16)&(Integer.parseInt("ffff",16))>>8));

        ByteBuffer byteBuffer=ByteBuffer.allocate(1);

        byteBuffer.put( Integer.valueOf("664a", 16).byteValue());

        byte[] test2=new byte[1];

        byteBuffer.get(test2);

        BinaryToHexString(test2);


    }

    private static String hexStr =  "0123456789ABCDEF";

    private static String[] binaryArray =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};

    /**
     *
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    public static byte[] HexStringToBinary(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位

        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    /**
     *
     * @param bArray
     * @return 转换为二进制字符串
     */
    public static String bytes2BinaryStr(byte[] bArray){

        String outStr = "";
        int pos = 0;
        for(byte b:bArray){
            //高四位
            pos = (b&0xF0)>>4;
            outStr+=binaryArray[pos];
            //低四位
            pos=b&0x0F;
            outStr+=binaryArray[pos];
        }
        return outStr;

    }
    /**
     *
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */
    public static String BinaryToHexString(byte[] bytes){

        String result = "";
        String hex = "";
        for(int i=0;i<bytes.length;i++){
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
            result +=hex+" ";
        }
        return result;
    }


        public static char asciiToChar(int ascii) {
            char ch = (char) ascii;
            return ch;
        }

        public static String toLHHex(int num, int len) {
            byte[] bytes = toLH(num, len);
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < bytes.length; j++) {
                builder.append(String.format("%02x", bytes[j] & 0xff));
            }
            return builder.toString();
        }

        /**
         * 将int转为低字节在前，高字节在后的byte数组
         */
        public static byte[] toLH(int n, int len) {
            byte[] b = new byte[len];
            for (int i = 0; i < b.length; i++) {
                b[i] = (byte) (n >> (i * 8) & 0xff);
            }
            return b;
        }

        public static byte[] intToByteArray(int i) {
            byte[] result = new byte[4];
            //由高位到低位
            result[0] = (byte) ((i >> 24) & 0xFF);
            result[1] = (byte) ((i >> 16) & 0xFF);
            result[2] = (byte) ((i >> 8) & 0xFF);
            result[3] = (byte) (i & 0xFF);
            return result;
        }

        public static int byteArrayToInt(byte[] b) {
            return b[3] & 0xFF |
                    (b[2] & 0xFF) << 8 |
                    (b[1] & 0xFF) << 16 |
                    (b[0] & 0xFF) << 24;
        }

        public static int byteArrayToInt(byte[] bytes, int len) {
            int value = 0;
            //由高位到低位
            for (int i = 0; i < len; i++) {
                int shift = (len - 1 - i) * 8;
                value += (bytes[i] & 0x000000FF) << shift;
            }
            return value;
        }

        /**
         * 十进制卡转16进制卡
         *
         * @param cardId
         * @return
         */
        public static String decimal2Hex(String cardId) {
            String hexCardId = Long.toHexString(Long.parseLong(cardId.trim()));
            if (hexCardId.length() < 8) {
                int spaceZeroCount = 8 - hexCardId.length();
                for (int i = 0; i < spaceZeroCount; i++) {
                    hexCardId = "0" + hexCardId;
                }
            }
            return hexCardId.toUpperCase();
        }

        public static String bytesToHexString(byte[] src) {
            StringBuilder stringBuilder = new StringBuilder("");
            if (src == null || src.length <= 0) {
                return null;
            }
            for (int i = 0; i < src.length; i++) {
                int v = src[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
            return stringBuilder.toString();
        }

        /**
         * 16进制转byte[]
         *
         * @param hexString
         * @return
         */
        public static byte[] hexStringToBytes(String hexString) {
            if (hexString == null || hexString.equals("")) {
                return null;
            }
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];
            for (int i = 0; i < length; i++) {
                int pos = i * 2;
                d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }
            return d;
        }

        /**
         * 半角转全角：
         */
        public static String toSBC(String input) {
            char[] c = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 32) {
                    c[i] = (char) 12288;
                    continue;
                }
                if (c[i] < 127) {
                    c[i] = (char) (c[i] + 65248);
                }
            }
            return new String(c);
        }

        private static byte charToByte(char c) {
            return (byte) "0123456789ABCDEF".indexOf(c);
        }


}
