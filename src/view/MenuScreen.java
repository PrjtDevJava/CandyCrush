package view;

import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuScreen extends JFrame implements ActionListener {

    private int WIN_H = 500;
    private int WIN_W = 380;
    private JButton jbNew = new JButton("Nouveau Jeu !");

    public MenuScreen() throws IOException {
        this.setTitle("Candy Crush Stone");
        this.setSize(WIN_W, WIN_H);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Objets
        JLabel logo = new JLabel(new ImageIcon("./src/images/logo.png"));
        jbNew.addActionListener(this);

        // Panel
        JPanel pan = new JPanel();
        pan.add(logo);
        pan.add(jbNew);
        this.setContentPane(pan);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbNew) {
            try {
                MainController main = new MainController(8, 8);
            } catch (IOException ex) {
                Logger.getLogger(MenuScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
