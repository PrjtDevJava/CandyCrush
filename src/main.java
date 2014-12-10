import controller.MainController;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Params;

public class main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Params.loadParams();
        //Sound.playSound(null);
        //Params.saveParams();
        MainController game = new MainController();
    }
}
