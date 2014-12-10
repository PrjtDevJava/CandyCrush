package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Grid;
import model.PointsCounter;
import view.HelpScreen;
import view.MainScreen;
import view.TimeCounter;

public class MenuController implements ActionListener, MenuListener {

    private Grid grid;
    private PointsCounter point;
    private MainScreen mainscreen;
    private final File file = new File("./src/save/");

    MenuController(Grid grid, PointsCounter pc, MainScreen ms) {
        this.grid = grid;
        this.point = pc;
        this.mainscreen = ms;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        MainScreen.timer.stop();
        if (ae.getSource() == this.mainscreen.getItemNwGame()) {
            MainScreen.timer.setTime(30);
            this.grid.changeGrid();
            this.point.setPoints(0);

        } else if (ae.getSource() == this.mainscreen.getItemSaveGame()) {
            FileOutputStream fos = null;
            try {
                // Boite de dialogue
                JFileChooser jfc = new JFileChooser(file.getCanonicalFile());
                jfc.setDialogTitle("Sauvegarder la partie");
                jfc.setFileFilter(new FileNameExtensionFilter("Sauvegarde (.save)", "save"));
                jfc.showSaveDialog(null);

                // Sérialisation
                fos = new FileOutputStream(jfc.getSelectedFile() + ".save");
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(grid);
                    oos.writeObject(point);
                    oos.writeObject(MainScreen.timer);
                    oos.flush();
                }
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == this.mainscreen.getItemLoadGame()) {
            try {
                // Boite de dialogue
                JFileChooser jfc = new JFileChooser(file.getCanonicalFile());
                jfc.setDialogTitle("Charger une partie");
                jfc.setFileFilter(new FileNameExtensionFilter("Sauvegarde (.save)", "save"));
                jfc.showOpenDialog(null);

                // Désérialisation
                FileInputStream fis = new FileInputStream(jfc.getSelectedFile());
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.grid.changeGrid((Grid) ois.readObject());
                this.point.setPoints(((PointsCounter) ois.readObject()).getPoints());
                TimeCounter tc = (TimeCounter) ois.readObject();
                MainScreen.timer.setTime(tc.getTime());
                MainScreen.timer.setTimeRest(tc.getTimeRest());
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void menuSelected(MenuEvent me) {
        MainScreen.timer.stop();
        if (me.getSource() == this.mainscreen.getMenuHelp()) {
            HelpScreen hs = new HelpScreen();
        }
    }

    @Override
    public void menuDeselected(MenuEvent me) {
        MainScreen.timer.start();
    }

    @Override
    public void menuCanceled(MenuEvent me) {
    }
}
