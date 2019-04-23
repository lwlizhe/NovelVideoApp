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

        printN(5,7);


    }


    private static void printN(int x,int y) {
        int[][] arr = new int[x][y];
        int count = 1;
        int minTurns=Math.max(x,y);
        for (int i = 0; i < minTurns / 2; i++) {
            for (int j = i,k =i; k <y-i-1 ; k++) {
                if(arr[j][k]!=0){
                    continue;
                }
                arr[j][k] = count++;
            }
            for (int j = i,k =y-i-1 ; j <x-i-1 ; j++) {
                if(arr[j][k]!=0){
                    continue;
                }
                arr[j][k] = count++;
            }
            for (int j =x-i-1 ,k=y-i-1; k> i ; k--) {
                if(arr[j][k]!=0){
                    continue;
                }
                arr[j][k] = count++;
            }
            for (int j = x-i-1,k=i; j >i ; j--) {
                if(arr[j][k]!=0){
                    continue;
                }
                arr[j][k] = count++;
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print (arr[i][j] + " ");
            }
            System.out.println ();
        }
    }

}
