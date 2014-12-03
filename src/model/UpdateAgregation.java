/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Sylvio
 */
public class UpdateAgregation extends java.lang.Thread {
    public static int nbThread = 0;
    public static Lock mut = new ReentrantLock();
    public static Condition cond = UpdateAgregation.mut.newCondition();
    
    public UpdateAgregation(){
        
    }
    
    @Override
    public void run(){
        
    }
}
