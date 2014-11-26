/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Sylvio
 */
public class MainScreen extends JFrame{
    private final int WIN_W = 700;
    private final int WIN_H = 580;
    private final int GRID_SIZE = 520;
    private final String POLICE = new String("Thomas");
    
    public MainScreen(){
        this.setTitle("Candy Crush 2.0");
        this.setSize(WIN_W, WIN_H);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        
        
        ////////////////////////////////////////////////////
        ////                  Barre Menu                ////
        ////////////////////////////////////////////////////
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuGame = new JMenu("Partie");
        JMenuItem itemNwGame = new JMenuItem("Nouvelle partie", KeyEvent.VK_N);
        JMenuItem itemSaveGame = new JMenuItem("Sauvgarder partie", KeyEvent.VK_S);
        JMenuItem itemLoadGame = new JMenuItem("Charger partie", KeyEvent.VK_O);
        itemNwGame.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemSaveGame.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        itemLoadGame.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuGame.add(itemNwGame);
        menuGame.add(itemSaveGame);
        menuGame.add(itemLoadGame);
        
        JMenu menuOptions = new JMenu("Options");
        JMenu menuHelp = new JMenu("?");
        
        menuBar.add(menuGame);
        menuBar.add(menuOptions);
        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);
        
        
        JPanel gridPane = new JPanel(new GridLayout(10, 10));
        gridPane.setBackground(Color.red);
        gridPane.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
        JPanel menuPane = new JPanel();
        menuPane.setBackground(Color.blue);
        menuPane.setPreferredSize(new Dimension(130, GRID_SIZE));
        

        JLabel labTime = new JLabel("2:30");
        labTime.setFont(new Font(POLICE, 0, 18));
        JLabel labPoints = new JLabel("51");
        labPoints.setFont(new Font(POLICE, 0, 18));
        
        
        GroupLayout menuGrLayout = new GroupLayout(menuPane);
        menuPane.setLayout(menuGrLayout);
        menuGrLayout.setHorizontalGroup(
            menuGrLayout.createSequentialGroup()
                .addGap(15)
                .addGroup(menuGrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labTime)
                    .addComponent(labPoints)
                )
        );
        menuGrLayout.setVerticalGroup(
            menuGrLayout.createSequentialGroup()
                .addComponent(labTime)
                .addComponent(labPoints)
        );
        
        
        GroupLayout grLayout = new GroupLayout(this.getContentPane());
        this.setLayout(grLayout);
        grLayout.setHorizontalGroup(
            grLayout.createSequentialGroup()
                .addComponent(gridPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(15)
                .addComponent(menuPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        grLayout.setVerticalGroup(
            grLayout.createSequentialGroup()
                .addGroup(grLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(gridPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );
        this.setVisible(true);
    }

    private Font Font(String thomas, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
