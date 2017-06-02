/**
* A "splash screen" to show while waiting program to load
*/
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class jfrmSplashScreen extends JWindow {
//-------------------------------------------- Splash Screen Constructor
    public jfrmSplashScreen(String filename) {
        JLabel jlbl    = new JLabel(new ImageIcon(filename));
        Color clrBlack = new Color(0, 0, 0);
        jlbl.setBorder(new LineBorder(clrBlack, 1));
        getContentPane().add(jlbl, BorderLayout.CENTER);
        pack();
        Dimension dim_scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim_lblSize = jlbl.getPreferredSize();
        setLocation(dim_scrSize.width/2  - (dim_lblSize.width/2),
                    dim_scrSize.height/2 - (dim_lblSize.height/2));
    }

	 // Close the screen
    public void mtd_Close() {
       setVisible(false);
    	 dispose();
    }
}

