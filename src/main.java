import controller.Controller;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.*;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Controller c = new Controller();
        
    }
}
 