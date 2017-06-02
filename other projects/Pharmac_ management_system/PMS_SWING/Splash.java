import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class Splash extends JWindow implements Runnable
{
	public void run()
	{
		JLabel SplashLabel = new JLabel(new ImageIcon("Splash.jpg"));
		Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
		Color cl = new Color (0, 0, 0);
		SplashLabel.setBorder (new LineBorder (cl, 1));

		getContentPane().add(SplashLabel,BorderLayout.CENTER);

		setSize(490,300);
		setLocation((screen.width - 490)/2,((screen.height-300)/2));
		show();
	}
}