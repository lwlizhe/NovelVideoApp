package com.lwlizhe.novelvideoapp;


import android.text.Html;

import org.junit.Test;

import java.io.PrintStream;
import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2018/5/8 0008.
 */

public class Test2 {

    @Test
   public void test(){

        a(3,"fSDRQgpusmIbrzyc");


    }


    public static String a(int paramInt, String paramString)
    {
        StringBuilder localStringBuilder = new StringBuilder();
        byte[] arrayOfByte = paramString.getBytes();
        int i = arrayOfByte.length;
        int j = paramInt;
        for (paramInt = 0; paramInt < i; paramInt++)
        {
            int k = arrayOfByte[paramInt];
            int m = (k + j - 65) % 57 + 65;
            int n = 0;
            while ((m > 90) && (m < 97))
            {
                j += n * j;
                n++;
                m = (k + j - 65) % 57 + 65;
                PrintStream localPrintStream = System.out;

                StringBuilder sb=new StringBuilder(paramString);
                sb.append("t");
                sb.append(j);
                localPrintStream.println(sb.toString());
            }
            localStringBuilder.append((char)m);
        }
        return localStringBuilder.toString();
    }

}
