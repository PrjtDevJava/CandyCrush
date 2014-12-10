/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

/**
 * Structure time : Minute et secondes
 * @author Sylvio
 */
public class TimeMS {
    public int min;
    public int sec;

    
    public TimeMS(int min, int sec){
        this.min = min + (sec / 60);
        this.sec = sec % 60;
    }
    
    public TimeMS(int sec){
        this.min = sec / 60;
        this.sec = sec % 60;
    }
    
    public void substractTime(int sec){
        if(sec >= timeToSec()){
            this.min = 0;
            this.sec = 0;
        }
        else{
            this.min -= sec / 60;
            if(sec % 60 > this.sec){
                this.min--;
                this.sec = (60 + this.sec) - (sec % 60);
            }
            else{
                this.sec -= sec % 60;
            }
        }
    }
    
    public int timeToSec(){
        return min*60 + sec;
    }
    
    public void setTime(int secTotal){
        this.min = secTotal / 60;
        this.sec = secTotal % 60;
    }

    @Override
    public String toString() {
        return ""+this.min + ":"+((this.sec > 9)? "" : "0") + this.sec;
    }
    
    
}
