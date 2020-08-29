package com.example.aulathreads;

import android.util.Log;

public class MyThread extends Thread{

    public void run(){
        super.run();
        for(int i = 0 ;i<=15; i++){
            Log.i("Tread","Contador" + i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
