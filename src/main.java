import controller.MainController;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MainController c = new MainController();

    }
}
