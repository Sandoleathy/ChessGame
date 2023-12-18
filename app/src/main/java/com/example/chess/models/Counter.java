package com.example.chess.models;

import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chess.R;

public class Counter implements Runnable{
    private int second;
    private int minute;
    private String timeString;
    private boolean stopFlag;
    public Counter(){
        this.second = 0;
        this.minute = 0;
        timeString = "00:00";
        stopFlag = false;
    }
    //counter timer
    @Override
    public void run() {
        while(!stopFlag){
            try {
                Thread.sleep(1000);
                second++;
                if(second >= 60){
                    if(minute <= 99){
                        second = 0;
                        this.minute++;
                    }
                }
                timeString = String.format("%02d:%02d",minute,second);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public String getTimeString(){
        return timeString;
    }
    public void stop(){
        this.stopFlag = true;
    }
    public void resume(){
        this.stopFlag = false;
    }
}
