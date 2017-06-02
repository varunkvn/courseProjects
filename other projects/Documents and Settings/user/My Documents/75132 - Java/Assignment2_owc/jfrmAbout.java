/**
* About box screen
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jfrmAbout extends JDialog implements ActionListener {
//-------------------------------------------- About Constructor
	public jfrmAbout(Frame frmOwner) {
		//<!-- INIT_CONTROLS
		super(frmOwner, true);
		setSize(357,250);
		setVisible(false);
		setTitle("USQ Faculty Book List - About...");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0,0));
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth())/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2);

		getContentPane().add(BorderLayout.CENTER, jpnlMain);
		jpnlMain.setBackground(Color.white);
		jpnlMain.setLayout(null);

		jlblImage.setBounds(0,0,357,58);
		jlblImage.setText("image");
		jpnlMain.add(jlblImage);

		jlblDesc.setHorizontalAlignment(SwingConstants.CENTER);
		jlblDesc.setText("USQ Faculty Book List System");
		jlblDesc.setForeground(Color.black);
		jlblDesc.setBounds(0,74,357,20);
		jpnlMain.add(jlblDesc);

		jlblDesc1.setHorizontalAlignment(SwingConstants.CENTER);
		jlblDesc1.setText("Version: 1.0");
		jlblDesc1.setForeground(Color.black);
		jlblDesc1.setBounds(0,90,357,20);
		jpnlMain.add(jlblDesc1);

		jlblDesc2.setHorizontalAlignment(SwingConstants.CENTER);
		jlblDesc2.setText("Created by: Ong Wai Chong (D11339554)");
		jlblDesc2.setForeground(Color.black);
		jlblDesc2.setFont(new Font("Dialog", Font.PLAIN, 11));
		jlblDesc2.setBounds(0,112,357,20);
		jpnlMain.add(jlblDesc2);

		jlblDesc3.setHorizontalAlignment(SwingConstants.CENTER);
		jlblDesc3.setText("Copyright © 2002-2003 Ong Wai Chong");
		jlblDesc3.setForeground(Color.black);
		jlblDesc3.setFont(new Font("Dialog", Font.PLAIN, 11));
		jlblDesc3.setBounds(0,127,357,20);
		jpnlMain.add(jlblDesc3);

		jbtnOK.setText("OK");
		jbtnOK.setMnemonic((int)'O');
		jbtnOK.setFont(new Font("Dialog", Font.PLAIN, 11));
		jbtnOK.setBounds(240,180,100,24);
		jpnlMain.add(jbtnOK);
		//-->

		//<!-- REGISTER_LISTENERS
		jbtnOK.addActionListener(this);
      //-->
	}

//-------------------------------------------- About Listener
	public void actionPerformed(ActionEvent event) {
		setVisible(false);
		dispose();
	}

	//<!-- DECLARE_CONTROLS
	JPanel jpnlMain  = new JPanel();
	JLabel jlblImage = new JLabel(new ImageIcon("images/FacultyBookList_Abt.jpg"));
	JLabel jlblDesc  = new JLabel();
	JLabel jlblDesc1 = new JLabel();
	JLabel jlblDesc2 = new JLabel();
	JLabel jlblDesc3 = new JLabel();
	JButton jbtnOK   = new JButton();
	//-->
}
