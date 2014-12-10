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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Grid;
import model.PointsCounter;
import view.MainScreen;

public class MenuController implements ActionListener {

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
        if (ae.getSource() == this.mainscreen.getItemNwGame()) {
            this.grid.changeGrid();
            this.point.setPoints(0);

        } else if (ae.getSource() == this.mainscreen.getItemSaveGame()) {
            FileOutputStream fos = null;
            try {
                // Boite de dialogue
                JFileChooser jfc = new JFileChooser(file.getCanonicalFile());
                jfc.setDialogTitle("Sauvegarder la partie");
                jfc.setFileFilter(new FileNameExtensionFilter("Sauvegarde (.save)", "save"));
                jfc.showOpenDialog(null);

                // Sérialisation
                fos = new FileOutputStream(jfc.getSelectedFile() + ".save");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(grid);
                oos.writeObject(point);
                oos.flush();
                oos.close();
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
                jfc.setDialogTitle("Sauvegarder la partie");
                jfc.setFileFilter(new FileNameExtensionFilter("Sauvegarde (.save)", "save"));
                jfc.showOpenDialog(null);

                // Désérialisation
                FileInputStream fis = new FileInputStream(jfc.getSelectedFile());
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.grid.changeGrid((Grid) ois.readObject());
                this.point.setPoints(((PointsCounter) ois.readObject()).getPoints());
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
