/**
* A general search JDialog for all
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class jfrmFind extends JDialog implements ActionListener, DocumentListener {
//-------------------------------------------- Find Constructor
	public jfrmFind(Frame frmOwner, boolean boolModal, clsBookListEntry[] arrIn) {
		super(frmOwner, boolModal);
		jfrmFind_Init(arrIn, false);
	}

	public jfrmFind(Frame frmOwner, boolean boolModal,
						 clsBookListEntry[] arrIn, boolean bIn) {
		super(frmOwner, boolModal);
		jfrmFind_Init(arrIn, bIn);
	}

	private void jfrmFind_Init(clsBookListEntry[] arrIn, boolean bIn) {
		//<!-- INIT_CONTROLS
		arrData = arrIn;
		setSize(375,130);
		setVisible(false);
		setResizable(false);
		setTitle("USQ Faculty Book List - Find");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth())/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2);
		getContentPane().setLayout(new BorderLayout(0,0));
		getContentPane().add(BorderLayout.CENTER, jpnlMain);

		jpnlMain.setLayout(null);
		jpnlMain.setBackground(Color.white);

		jlblFind.setForeground(Color.black);
		jlblFind.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblFind.setBounds(12,20,60,20);
		jlblFind.setText("Find what:");
		jpnlMain.add(jlblFind);

		jtxtFind.setBounds(75,20,200,24);
		jpnlMain.add(jtxtFind);

		// Group the check box option
		bgChk = new ButtonGroup();
		bgChk.add(jchkCourseNo);
		bgChk.add(jchkCourseName);
		bgChk.add(jchkBookNo);
		bgChk.add(jchkBookName);

		jchkCourseNo.setSelected(true);
		jchkCourseNo.setFont(new Font("Dialog", Font.PLAIN, 11));
		jchkCourseNo.setBackground(Color.white);
		jchkCourseNo.setBounds(12,50,110,20);
		jchkCourseNo.setText("Course Number");
		jchkCourseNo.setActionCommand("20");
		jchkCourseNo.setMnemonic((int)'U');
		jpnlMain.add(jchkCourseNo);

		jchkCourseName.setBackground(Color.white);
		jchkCourseName.setFont(new Font("Dialog", Font.PLAIN, 11));
		jchkCourseName.setBounds(12,72,108,20);
		jchkCourseName.setText("Course Name");
		jchkCourseName.setActionCommand("21");
		jchkCourseName.setMnemonic((int)'A');
		jpnlMain.add(jchkCourseName);

		jchkBookNo.setBackground(Color.white);
		jchkBookNo.setFont(new Font("Dialog", Font.PLAIN, 11));
		jchkBookNo.setBounds(132,50,130,20);
		jchkBookNo.setText("Text Book Number");
		jchkBookNo.setActionCommand("10");
		jchkBookNo.setMnemonic((int)'E');
		jpnlMain.add(jchkBookNo);

		jchkBookName.setBackground(Color.white);
		jchkBookName.setFont(new Font("Dialog", Font.PLAIN, 11));
		jchkBookName.setBounds(132,72,115,20);
		jchkBookName.setText("Text Book Name");
		jchkBookName.setActionCommand("11");
		jchkBookName.setMnemonic((int)'O');
		jpnlMain.add(jchkBookName);
		if(bIn) {
			jchkBookName.setEnabled(false);
			jchkCourseName.setEnabled(false);
		}
		jbtnFind.setFont(new Font("Dialog", Font.PLAIN, 11));
		jbtnFind.setEnabled(false);
		jbtnFind.setBounds(288,20,70,24);
		jbtnFind.setMnemonic((int)'F');
		jbtnFind.setText("Find");
		jpnlMain.add(jbtnFind);

		jbtnClose.setFont(new Font("Dialog", Font.PLAIN, 11));
		jbtnClose.setBounds(288,50,70,24);
		jbtnClose.setMnemonic((int)'C');
		jbtnClose.setText("Close");
		jpnlMain.add(jbtnClose);
		//-->

		//<!-- REGISTER_LISTENERS
		// Listen whether the JTextField value chged or not
		jtxtFind.getDocument().addDocumentListener(this);
		jbtnFind.addActionListener(this);
		jbtnClose.addActionListener(this);
		//-->
	}

//-------------------------------------------- Find Listener/Performer
	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();
		if(object == jbtnFind) {
			clsFileHdl objFileHdl = new clsFileHdl();
			String strFind = jtxtFind.getText().trim();
			int intChoice  = Integer.parseInt(bgChk.getSelection().getActionCommand());
			switch(intChoice) {
				// Search Book No
				case 10: intPosition = objFileHdl.mtd_SearchBook(arrData,strFind,0);break;
				// Search Book Name
				case 11: intPosition = objFileHdl.mtd_SearchBook(arrData,strFind,1);break;
				// Search Course No
				case 20: intPosition = objFileHdl.mtd_SearchCourse(arrData,strFind,0);break;
				// Search Course Name
				case 21: intPosition = objFileHdl.mtd_SearchCourse(arrData,strFind,1);break;
			}
			// Not found
			if(intPosition == -1) {
				objFileHdl.mtd_MsgBox(" - Find","Record not found.",-1,1);
				return;
			}
		}
		setVisible(false);
	}

	// For Document Listener
	public void changedUpdate(DocumentEvent e) {
		// Required by the Listener
	}
	public void insertUpdate(DocumentEvent e) {
		jbtnFind.setEnabled(true);
	}
	public void removeUpdate(DocumentEvent e) {
		if(e.getDocument().getLength() == 0)
			jbtnFind.setEnabled(false);
	}

	// Return the position of the record in the array
	public int mtd_getPosition() { return intPosition; }

	//<!-- DECLARE_CONTROLS/VARIABLES
	private ButtonGroup bgChk;
	private clsBookListEntry[] arrData;
	private int intPosition				= -1;
	private JPanel jpnlMain 			= new JPanel();
	private JLabel jlblFind 			= new JLabel();
	private JTextField jtxtFind 		= new JTextField();
	private JCheckBox jchkCourseNo   = new JCheckBox();
	private JCheckBox jchkCourseName = new JCheckBox();
	private JCheckBox jchkBookNo     = new JCheckBox();
	private JCheckBox jchkBookName   = new JCheckBox();
	private JButton jbtnFind         = new JButton();
	private JButton jbtnClose 		   = new JButton();
	//-->
}
