package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.MenuListener;
import model.Grid;

public class MainScreen extends JFrame {

    private int WIN_H;
    private int WIN_W;
    private int GRID_H;
    private int GRID_W;
    public static Color BACKGROUND_COLOR = CasePane.HOVER_COLOR;
    private final String POLICE = "Thomas";
    private final JPanel gridPane;
    private final JLabel labPoints;
    private JMenuItem itemNwGame;
    private JMenuItem itemSaveGame;
    private JMenuItem itemLoadGame;
    private JMenu menuOptions;
    private JMenu menuHelp;

    public MainScreen(int x, int y) throws IOException {
        this.setTitle("Candy Crush Stone");
        this.GRID_H = 60 * y;
        this.GRID_W = 60 * x;
        this.WIN_H = 51 + GRID_H;
        this.WIN_W = 170 + GRID_W;
        this.setSize(WIN_W, WIN_H);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(BACKGROUND_COLOR);

        ////////////////////////////////////////////////////
        ////                  Barre Menu                ////
        ////////////////////////////////////////////////////
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Partie");
        this.itemNwGame = new JMenuItem("Nouvelle partie", KeyEvent.VK_N);
        this.itemSaveGame = new JMenuItem("Sauvgarder partie", KeyEvent.VK_S);
        this.itemLoadGame = new JMenuItem("Charger partie", KeyEvent.VK_O);
        this.itemNwGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.itemSaveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.itemLoadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuGame.add(itemNwGame);
        menuGame.add(itemSaveGame);
        menuGame.add(itemLoadGame);

        this.menuOptions = new JMenu("Options");
        this.menuHelp = new JMenu("?");
//        ActionListener actionMenu = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                if (ae.getSource() == itemNwGame) {
//                    Grid g = new Grid(5, 5, 5);
//                    g.initGrid();try {
//                        MainController game = new MainController(8, 8, 4);
////                    MainScreen.this.addGridListener(new GridController(g), g);
//                    } catch (IOException ex) {
//                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        };

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
        tc.setFont(new Font("Impact", 0, 20));
        jpTimer.add(new JLabel("<html><div style=\"padding-left:15px;color:#222222;margin-top:50px;\">temps restant :</div></html>"));
        jpTimer.add(tc);

// ------------------- Au cas ou on veut afficher quelque chose sous le timer : -------------------------
//        JPanel jj = new JPanel();
//        jj.setBackground(Color.yellow);
//        jj.setAlignmentX(Component.LEFT_ALIGNMENT);
//        GroupLayout menuGrLayout = new GroupLayout(jj);
//        jj.setLayout(menuGrLayout);
//        menuGrLayout.setHorizontalGroup(
//                menuGrLayout.createSequentialGroup()
//                .addGroup(menuGrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addComponent(tc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                )
//        );
//        menuGrLayout.setVerticalGroup(
//                menuGrLayout.createSequentialGroup()
//                .addComponent(tc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//        );
//        jpTimer.add(jj);
        //tc.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        // Scores
        JPanel jpScore = new JPanel();
        jpScore.setLayout(new BoxLayout(jpScore, BoxLayout.Y_AXIS));
        labPoints = new JLabel();
        labPoints.setFont(new Font("Impact", 0, 20));
        labPoints.setForeground(new Color(50, 50, 50));
        labPoints.setSize(new Dimension(5000, 550));
        labPoints.setHorizontalTextPosition(JLabel.CENTER);
        jpScore.add(new JLabel("<html><div style=\"padding-left:35px;color:#222222;margin-top:10px;\">score :</div></html>"));
        jpScore.add(labPoints);
        // Logo
        JLabel logo = new JLabel(new ImageIcon("./src/images/logopetit.png"));

        menuPane.setLayout(new BoxLayout(menuPane, BoxLayout.PAGE_AXIS));
        menuPane.add(jpScore);
        menuPane.add(jpTimer);
        menuPane.add(logo);

        menuPane.setBackground(BACKGROUND_COLOR);
        for (Component c : menuPane.getComponents()) {
            c.setBackground(BACKGROUND_COLOR);
        }

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

    public void addItemMenuListener(ActionListener ae) {
        this.itemNwGame.addActionListener(ae);
        this.itemLoadGame.addActionListener(ae);
        this.itemSaveGame.addActionListener(ae);
    }

    public void addMenuListener(MenuListener ml) {
        this.menuHelp.addMenuListener(ml);
    }

    public JLabel getLabPoints() {
        return this.labPoints;
    }

    public JMenuItem getItemNwGame() {
        return itemNwGame;
    }

    public JMenuItem getItemSaveGame() {
        return itemSaveGame;
    }

    public JMenuItem getItemLoadGame() {
        return itemLoadGame;
    }

    public JMenu getMenuOptions() {
        return menuOptions;
    }

    public JMenu getMenuHelp() {
        return menuHelp;
    }

}
