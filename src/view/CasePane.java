/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Case;
import model.Shape;

/**
 *
 * @author Sylvio
 */
public class CasePane extends JPanel implements Observer{
    
    public CasePane(){
        
    }
    
    public void init(Shape shape){
        this.setBackground(shape.getColor());
    }
    
    @Override
    public void update(Observable obs, Object obj) {
        if(obs instanceof Case){
            Case c = (Case)obs;
        }
    }
    
}
