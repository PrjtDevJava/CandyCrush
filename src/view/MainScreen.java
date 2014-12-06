package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import model.Grid;

public class MainScreen extends JFrame {

    private int WIN_H;
    private int WIN_W;
    private int GRID_H;
    private int GRID_W;
    private final String POLICE = "Thomas";
    private final JPanel gridPane;
    private final JLabel labPoints;

    public MainScreen(int x, int y) throws IOException {
        this.setTitle("Candy Crush Stone");
        this.GRID_H = 60 * y;
        this.GRID_W = 60 * x;
        this.WIN_H = 61 + GRID_H;
        this.WIN_W = 190 + GRID_W;
        this.setSize(WIN_W, WIN_H);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ////////////////////////////////////////////////////
        ////                  Barre Menu                ////
        ////////////////////////////////////////////////////
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Partie");
        JMenuItem itemNwGame = new JMenuItem("Nouvelle partie", KeyEvent.VK_N);
        JMenuItem itemSaveGame = new JMenuItem("Sauvgarder partie", KeyEvent.VK_S);
        JMenuItem itemLoadGame = new JMenuItem("Charger partie", KeyEvent.VK_O);
        itemNwGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemSaveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        itemLoadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuGame.add(itemNwGame);
        menuGame.add(itemSaveGame);
        menuGame.add(itemLoadGame);

        JMenu menuOptions = new JMenu("Options");
        JMenu menuHelp = new JMenu("?");

        menuBar.add(menuGame);
        menuBar.add(menuOptions);
        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);

        ////////////////////////////////////////////////////////////
        ////                 Init Grille layout                 ////   
        ////////////////////////////////////////////////////////////
        gridPane = new JPanel();
        gridPane.setPreferredSize(new Dimension(GRID_W, GRID_H));

        JPanel menuPane = new JPanel();
        menuPane.setPreferredSize(new Dimension(150, GRID_H));

        JLabel labTime = new JLabel("2:30");
        labTime.setFont(new Font(POLICE, 0, 18));

        // Timer
        JPanel jpTimer = new JPanel();
        jpTimer.setLayout(new BoxLayout(jpTimer, BoxLayout.Y_AXIS));
        TimeCounter tc = new TimeCounter(99);
        jpTimer.add(new JLabel("Temps restant :"));
        jpTimer.add(tc);
        // Scores
        JPanel jpScore = new JPanel();
        jpScore.setLayout(new BoxLayout(jpScore, BoxLayout.Y_AXIS));
        labPoints = new JLabel();
        labPoints.setFont(new Font(POLICE, 0, 18));
        jpScore.add(new JLabel("Score :"));
        jpScore.add(labPoints);
        // Logo
        JLabel logo = new JLabel(new ImageIcon("./src/images/logopetit.png"));

        menuPane.setLayout(new BoxLayout(menuPane, BoxLayout.PAGE_AXIS));
        menuPane.add(jpTimer);
        menuPane.add(jpScore);
        menuPane.add(logo);

//        GroupLayout menuGrLayout = new GroupLayout(menuPane);
//        menuGrLayout.setHorizontalGroup(
//                menuGrLayout.createSequentialGroup()
//                .addGap(15)
//                .addGroup(menuGrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addComponent(jp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                        .addComponent(labPoints)
//                )
//        );
//        menuGrLayout.setVerticalGroup(
//                menuGrLayout.createSequentialGroup()
//                .addComponent(jp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                .addComponent(labPoints)
//        );
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
    }

    public void addGridListener(MouseListener ml, Grid grid) {
        this.gridPane.setLayout(new GridLayout(grid.getHeight(), grid.getWidth()));
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                CasePane cp = new CasePane();
                grid.getCase(j, i).addObserver(cp);
                cp.init(j, i, grid.getCase(j, i).getShape());
                cp.addMouseListener(ml);
                gridPane.add(cp);
            }
        }
    }

    public JLabel getLabPoints() {
        return this.labPoints;
    }

}
