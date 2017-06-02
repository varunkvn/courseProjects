/**
* Main screen/program, using MDI method
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class FacultyBookList_Main extends JFrame implements ActionListener,
												 					  InternalFrameListener {
//-------------------------------------------- FacultyBookList Constructor
	public FacultyBookList_Main() {
		//<!-- INIT_MENUS - For File Menu
		jmnuFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuFile.setText("File");
		jmnuFile.setActionCommand("File");
		jmnuFile.setBorderPainted(false);
		jmnuFile.setMnemonic((int)'F');
		jmnuMain.add(jmnuFile);

		jmnuiNew.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiNew.setText("New");
		jmnuiNew.setActionCommand("New");
		jmnuiNew.setBorderPainted(false);
		jmnuiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		jmnuiNew.setMnemonic((int)'N');
		jmnuFile.add(jmnuiNew);

		jmnuiSave.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiSave.setText("Save");
		jmnuiSave.setActionCommand("Save");
		jmnuiSave.setBorderPainted(false);
		jmnuiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		jmnuiSave.setMnemonic((int)'S');
		jmnuFile.add(jmnuiSave);

		jmnuFile.addSeparator();

		jmnuiDelete.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiDelete.setText("Delete");
		jmnuiDelete.setActionCommand("Delete");
		jmnuiDelete.setBorderPainted(false);
		jmnuiDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		jmnuiDelete.setMnemonic((int)'D');
		jmnuFile.add(jmnuiDelete);

		jmnuiCancel.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiCancel.setText("Cancel");
		jmnuiCancel.setActionCommand("Cancel");
		jmnuiCancel.setBorderPainted(false);
		jmnuiCancel.setMnemonic((int)'A');
		jmnuFile.add(jmnuiCancel);

		jmnuFile.addSeparator();

		jmnuiExit.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiExit.setText("Exit");
		jmnuiExit.setActionCommand("Exit");
		jmnuiExit.setBorderPainted(false);
		jmnuiExit.setMnemonic((int)'X');
		jmnuFile.add(jmnuiExit);

		// For Edit Menu
		jmnuEdit.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuEdit.setText("Edit");
		jmnuEdit.setActionCommand("Edit");
		jmnuEdit.setBorderPainted(false);
		jmnuEdit.setMnemonic((int)'E');
		jmnuMain.add(jmnuEdit);

		jmnuiFind.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiFind.setText("Find");
		jmnuiFind.setActionCommand("Find");
		jmnuiFind.setBorderPainted(false);
		jmnuiFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
		jmnuiFind.setMnemonic((int)'F');
		jmnuEdit.add(jmnuiFind);

		// For View Menu
		jmnuView.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuView.setText("View");
		jmnuView.setActionCommand("View");
		jmnuView.setBorderPainted(false);
		jmnuView.setMnemonic((int)'V');
		jmnuMain.add(jmnuView);

		jmnuiFB.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiFB.setText("Faculty Books Details");
		jmnuiFB.setActionCommand("Faculty Books Details");
		jmnuiFB.setBorderPainted(false);
		jmnuiFB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK));
		jmnuiFB.setMnemonic((int)'U');
		jmnuView.add(jmnuiFB);

		jmnuiBooks.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiBooks.setText("Book Details");
		jmnuiBooks.setActionCommand("Book Details");
		jmnuiBooks.setBorderPainted(false);
		jmnuiBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
		jmnuiBooks.setMnemonic((int)'B');
		jmnuView.add(jmnuiBooks);

		jmnuiCourses.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiCourses.setText("Course Details");
		jmnuiCourses.setActionCommand("Course Details");
		jmnuiCourses.setBorderPainted(false);
		jmnuiCourses.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		jmnuiCourses.setMnemonic((int)'O');
		jmnuView.add(jmnuiCourses);

		jmnuView.addSeparator();

		jmnuiFBL.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiFBL.setText("Faculty Books List");
		jmnuiFBL.setActionCommand("Faculty Books List");
		jmnuiFBL.setBorderPainted(false);
		jmnuiFBL.setMnemonic((int)'L');
		jmnuView.add(jmnuiFBL);

		jmnuView.addSeparator();

		jmnuiBack.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiBack.setText("Back");
		jmnuiBack.setActionCommand("Back");
		jmnuiBack.setBorderPainted(false);
		jmnuiBack.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, Event.CTRL_MASK));
		jmnuiBack.setMnemonic((int)'k');
		jmnuView.add(jmnuiBack);

		jmnuiNext.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiNext.setText("Next");
		jmnuiNext.setActionCommand("Next");
		jmnuiNext.setBorderPainted(false);
		jmnuiNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK));
		jmnuiNext.setMnemonic((int)'t');
		jmnuView.add(jmnuiNext);

		// For Help Menu
		jmnuHelp.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuHelp.setText("Help");
		jmnuHelp.setActionCommand("Help");
		jmnuHelp.setBorderPainted(false);
		jmnuHelp.setMnemonic((int)'H');
		jmnuMain.add(jmnuHelp);

		jmnuiAbout.setFont(new Font("Dialog", Font.PLAIN, 12));
		jmnuiAbout.setText("About...");
		jmnuiAbout.setActionCommand("About...");
		jmnuiAbout.setBorderPainted(false);
		jmnuiAbout.setMnemonic((int)'A');
		jmnuHelp.add(jmnuiAbout);
		//-->

		//<!-- INIT_TOOLBARS
		jbtnNew.setToolTipText("Create new record");
		jbtnSave.setToolTipText("Save the change(s)");
		jbtnDelete.setToolTipText("Delete this record");
		jbtnCancel.setToolTipText("Cancel the change(s) and/or close");
		jbtnFind.setToolTipText("Find");
		jbtnFB.setToolTipText("Faculty books details");
		jbtnBooks.setToolTipText("Book details");
		jbtnCourses.setToolTipText("Course details");
		jbtnFBL.setToolTipText("View faculty books list");
		jbtnBack.setToolTipText("Show previous record");
		jbtnNext.setToolTipText("Show next record");
		jtbrMain.add(jbtnNew);
		jtbrMain.add(jbtnSave);
		jtbrMain.addSeparator();
		jtbrMain.add(jbtnDelete);
		jtbrMain.add(jbtnCancel);
		jtbrMain.addSeparator();
		jtbrMain.add(jbtnFB);
		jtbrMain.add(jbtnBooks);
		jtbrMain.add(jbtnCourses);
		jtbrMain.add(jbtnFBL);
		jtbrMain.addSeparator();
		jtbrMain.add(jbtnFind);
		jtbrMain.add(jbtnBack);
		jtbrMain.add(jbtnNext);
		//-->

		//<!-- REGISTER_LISTENERS
		addWindowListener(new WindowAdapter() {
      	public void windowClosing(WindowEvent e) {
         	mtd_exitApplication();
         }
      });

		// For Menu Listeners
		jmnuiNew.addActionListener(this);
		jmnuiSave.addActionListener(this);
		jmnuiDelete.addActionListener(this);
		jmnuiCancel.addActionListener(this);
		jmnuiExit.addActionListener(this);
		jmnuiFind.addActionListener(this);
		jmnuiFB.addActionListener(this);
		jmnuiBooks.addActionListener(this);
		jmnuiCourses.addActionListener(this);
		jmnuiFBL.addActionListener(this);
		jmnuiBack.addActionListener(this);
		jmnuiNext.addActionListener(this);
		jmnuiAbout.addActionListener(this);

		// For Toolbars Listeners
		jbtnNew.addActionListener(this);
		jbtnSave.addActionListener(this);
		jbtnDelete.addActionListener(this);
		jbtnCancel.addActionListener(this);
		jbtnFind.addActionListener(this);
		jbtnFB.addActionListener(this);
		jbtnBooks.addActionListener(this);
		jbtnCourses.addActionListener(this);
		jbtnFBL.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnNext.addActionListener(this);
		//-->

		//<!-- INIT_CONTROLS
		setSize(600,500);
		setVisible(false);
		setResizable(false);
		setJMenuBar(jmnuMain);
		setTitle("USQ Faculty Book List");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Made the screen appear center
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth())/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight())/2);
		// Show time at the status bar (JLabel)
		jlblStatusBar.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblStatusBar.setForeground(Color.black);
		jlblStatusBar.setFont(new Font("Dialog", Font.PLAIN, 10));
		new clsTimerTime(jlblStatusBar, 1);
		// When you drag a internal frame you will only see the outline
		// and it doesnt repaint until you stop dragging this makes dragging faster.
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
		getContentPane().add(jtbrMain, BorderLayout.NORTH);
		getContentPane().add(jdpDesktop, BorderLayout.CENTER);
		getContentPane().add(jlblStatusBar, BorderLayout.SOUTH);
		// Load data from text file into array(s)
		objFileHdl = new clsFileHdl();
		arrBook    = objFileHdl.mtd_BookFile();
		arrCourse  = objFileHdl.mtd_CourseFile();
		arrFaculty = objFileHdl.mtd_FacultyBookFile();
		//-->
	}

//-------------------------------------------- FacultyBookList Action performer
	private void mtd_exitApplication() {
		try {
	    	// Show a confirmation dialog
	    	int reply = JOptionPane.showConfirmDialog(this,
	    	                                          "Do you really want to exit?",
	    	                                          "USQ Faculty Book List - Exit" ,
	    	                                          JOptionPane.YES_NO_OPTION,
	    	                                          JOptionPane.QUESTION_MESSAGE);
			// If the confirmation was affirmative, handle exiting.
			if (reply == JOptionPane.YES_OPTION) {
				// Make sure all array have the latest copy
				mtd_JInternalFrame("");
				// Write Array into text file
				objFileHdl.mtd_BookFile(arrBook);
				objFileHdl.mtd_CourseFile(arrCourse);
				objFileHdl.mtd_FacultyBookFile(arrFaculty);
		    	setVisible(false);    					// hide the Frame
		    	dispose();            					// free the system resources
		    	System.exit(0);            			// close the application
			}
		} catch(Exception e) {}
	}

	// Make sure there is a selected JInternalFrame & compare which
	// JInternalFrame is selected & perform action accordingly
	private void mtd_doSelectedFrame(int intEvent) {
		String strFrameTitle;
		if(jdpDesktop.getSelectedFrame() != null) {
			strFrameTitle = jdpDesktop.getSelectedFrame().getTitle();
			if(strFrameTitle.equalsIgnoreCase("Faculty Books Details"))
				mtd_jfrmFacultyBook(intEvent);
			else if(strFrameTitle.equalsIgnoreCase("Book Details"))
				mtd_jfrmBook(intEvent);
			else if(strFrameTitle.equalsIgnoreCase("Course Details"))
				mtd_jfrmCourse(intEvent);
		}
	}

	private void mtd_jfrmFacultyBook(int intEvent) {
		switch(intEvent) {
			case 0:		// New event
				objFacultyBook.mtd_New(); break;
			case 1:		// Save event
				objFacultyBook.mtd_Save(); break;
			case 2:		// Delete event
				objFacultyBook.mtd_Delete(); break;
			case 3: 		// Cancel event
				objFacultyBook.setVisible(false);
				arrFaculty = objFacultyBook.mtd_getarrFacultyBook();
				objFacultyBook.dispose(); break;
			case 4:		// Find event
				int intPosition = -1;
				arrFaculty = objFacultyBook.mtd_getarrFacultyBook();
				jfrmFind objFind = new jfrmFind(this, true, arrFaculty, true);
				objFind.show();
				intPosition = objFind.mtd_getPosition();
				objFind.dispose();
				objFacultyBook.mtd_Search(intPosition);
				break;
			case 5:		// Back event
				objFacultyBook.mtd_Navigation(-1); break;
			case 6:		// Next event
				objFacultyBook.mtd_Navigation(1); break;
		}
	}

	private void mtd_jfrmBook(int intEvent) {
		switch(intEvent) {
			case 0:		// New event
				objBook.mtd_New(); break;
			case 1:		// Save event
				objBook.mtd_Save(); break;
			case 2:		// Delete event
				objBook.mtd_Delete(); break;
			case 3: 		// Cancel event
				objBook.setVisible(false);					   // Get the frame invisible
				arrBook = objBook.mtd_getarrBook();       // Get latest Array frm class
				objBook.dispose(); break;				  	   // release system resources
			case 4:		// Find event
				int intPosition = -1;
				arrBook = objBook.mtd_getarrBook();	   	// Get latest Array frm class
				jfrmFind objFind = new jfrmFind(this, true, arrBook);
				objFind.show();									// Show the Find frame
				intPosition = objFind.mtd_getPosition();  // Get the position of data
				objFind.dispose();								// release system resources
				objBook.mtd_Search(intPosition);	break;	// Reflecting the find
			case 5:		// Back event
				objBook.mtd_Navigation(-1); break;
			case 6:		// Next event
				objBook.mtd_Navigation(1); break;
		}
	}

	private void mtd_jfrmCourse(int intEvent) {
		switch(intEvent) {
			case 0:		// New event
				objCourse.mtd_New(); break;
			case 1:		// Save event
				objCourse.mtd_Save(); break;
			case 2:		// Delete event
				objCourse.mtd_Delete(); break;
			case 3: 		// Cancel event
				objCourse.setVisible(false);					// Get the frame invisible
				arrCourse = objCourse.mtd_getarrCourse(); // Get latest Array frm class
				objCourse.dispose(); break;					// release system resources
			case 4:		// Find event
				int intPosition = -1;
				arrCourse = objCourse.mtd_getarrCourse();	// Get latest Array frm class
				jfrmFind objFind = new jfrmFind(this, true, arrCourse);
				objFind.show();									// Show the Find frame
				intPosition = objFind.mtd_getPosition();  // Get the position of data
				objFind.dispose();								// release system resources
				objCourse.mtd_Search(intPosition);			// Reflecting the find
				break;
			case 5:		// Back event
				objCourse.mtd_Navigation(-1); break;
			case 6:		// Next event
				objCourse.mtd_Navigation(1); break;
		}
	}

	// Loop through all the opened JInternalFrame and perform required tasks
	private boolean mtd_JInternalFrame(String strTitle) {
		JInternalFrame[] jifChild = jdpDesktop.getAllFrames();
		for(int i=0; i < jifChild.length; i++) {
			if(strTitle.length() < 1) {
				// Make sure the array have latest copy from the respective class
				if(jifChild[i].getTitle().equalsIgnoreCase("Course Details"))
					arrCourse  = objCourse.mtd_getarrCourse();
				else if(jifChild[i].getTitle().equalsIgnoreCase("Book Details"))
					arrBook    = objBook.mtd_getarrBook();
				else if(jifChild[i].getTitle().equalsIgnoreCase("Faculty Books Details"))
					arrFaculty = objFacultyBook.mtd_getarrFacultyBook();
			} else if(jifChild[i].getTitle().equalsIgnoreCase(strTitle)) {
				// Check whether the JInterFrame is already open,
				// if yes, brings it to the front instead open.
				jifChild[i].show();
				return true;
			}
		}
		return false;
	}

	// For opening JInternalFrame
	private void jmnuiOpen_actionPerformed(ActionEvent event, String strTitle, int intWhich) {
		if(mtd_JInternalFrame(strTitle)) return;
		// Check which frame want to open
		switch(intWhich) {
			case 0:			// Faculty Books Details frame
				mtd_JInternalFrame("");								  // Get the latest array
				objFacultyBook = new jfrmFacultyBook(strTitle, arrFaculty,
															    arrBook, arrCourse);
				jdpDesktop.add(objFacultyBook);
				objFacultyBook.addInternalFrameListener(this); // Listen for events
				objFacultyBook.setVisible(true);
				break;
			case 1:			// Book Details frame
				objBook = new jfrmBook(strTitle, arrBook);
				jdpDesktop.add(objBook);
				objBook.addInternalFrameListener(this); 			// Listen for events
				objBook.setVisible(true);
				break;
			case 2:			// Course Details frame
				objCourse = new jfrmCourse(strTitle, arrCourse);
				jdpDesktop.add(objCourse);
				objCourse.addInternalFrameListener(this); 		// Listen for events
				objCourse.setVisible(true);
				break;
			case 3:			// Faculty Books List
				mtd_JInternalFrame("");								  // Get the latest array
				objFacultyBookList = new jfrmFacultyBookList(strTitle, arrBook,
																			arrCourse, arrFaculty);
				jdpDesktop.add(objFacultyBookList);
				objFacultyBookList.addInternalFrameListener(this);
				objFacultyBookList.setVisible(true);
				break;
			case 4:			// About box...
				objAbout = new jfrmAbout(this); objAbout.setVisible(true);
				break;
		}
	}

//-------------------------------------------- FacultyBookList Listener
	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();
		// For Menu/Toolbar Action listener
		if(object == jmnuiNew          || object == jbtnNew)		mtd_doSelectedFrame(0);
		else if(object == jmnuiSave    || object == jbtnSave)		mtd_doSelectedFrame(1);
		else if(object == jmnuiDelete  || object == jbtnDelete) 	mtd_doSelectedFrame(2);
		else if(object == jmnuiCancel  || object == jbtnCancel) 	mtd_doSelectedFrame(3);
		else if(object == jmnuiFind    || object == jbtnFind)   	mtd_doSelectedFrame(4);
		else if(object == jmnuiBack    || object == jbtnBack)   	mtd_doSelectedFrame(5);
		else if(object == jmnuiNext    || object == jbtnNext)   	mtd_doSelectedFrame(6);
		else if(object == jmnuiFB      || object == jbtnFB)
			jmnuiOpen_actionPerformed(event, "Faculty Books Details", 0);
		else if(object == jmnuiBooks   || object == jbtnBooks)
			jmnuiOpen_actionPerformed(event, "Book Details", 1);
		else if(object == jmnuiCourses || object == jbtnCourses)
			jmnuiOpen_actionPerformed(event, "Course Details", 2);
		else if(object == jmnuiFBL     || object == jbtnFBL)
			jmnuiOpen_actionPerformed(event, "Faculty Books List", 3);
		else if(object == jmnuiAbout)
			jmnuiOpen_actionPerformed(event, "About", 4);
		else if(object == jmnuiExit)	  									mtd_exitApplication();
	}

	// For JInternalFrameListener
	public void internalFrameClosing(InternalFrameEvent e) {
		Object object = e.getSource();
		if(object == objFacultyBook)
			mtd_jfrmFacultyBook(3);
		else if(object == objBook)
			mtd_jfrmBook(3);
		else if(object == objCourse)
			mtd_jfrmCourse(3);
	}
	// Empty method required by InternalFrameListener
	public void internalFrameClosed(InternalFrameEvent e) 	  { }
	public void internalFrameOpened(InternalFrameEvent e) 	  { }
	public void internalFrameIconified(InternalFrameEvent e)   { }
	public void internalFrameDeiconified(InternalFrameEvent e) { }
	public void internalFrameActivated(InternalFrameEvent e)   { }
	public void internalFrameDeactivated(InternalFrameEvent e) { }

//-------------------------------------------- FacultyBookList Main
	public static void main(String[] args) {
		try {
			// Create a "Splash Screen" and make it visible while wait for program to load
			jfrmSplashScreen objSplash = new jfrmSplashScreen("images/FacultyBookList.jpg");
      	objSplash.setVisible(true);
      	// Create a new instance of our application's frame, and make it visible.
	      FacultyBookList_Main objMain = new FacultyBookList_Main();
	      for(int i=0; i< 200; i++) System.out.println("loading...");
	      objSplash.mtd_Close();
	      objMain.setVisible(true);
		} catch(Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	//<!-- DECLARE_CONTROLS/VARIABLES
	private jfrmBook objBook;
	private jfrmAbout objAbout;
	private jfrmCourse objCourse;
	private jfrmFacultyBook objFacultyBook;
	private jfrmFacultyBookList objFacultyBookList;
	private clsFileHdl objFileHdl;
	private clsBookListEntry[] arrBook, arrCourse, arrFaculty;
	private JLabel jlblStatusBar	  = new JLabel();
	private JDesktopPane jdpDesktop = new JDesktopPane();
	//-->

	//<!-- DECLARE_MENUS
	private JMenuBar jmnuMain 		  = new JMenuBar();
	private JMenu jmnuFile 			  = new JMenu();
	private JMenuItem jmnuiNew 	  = new JMenuItem();
	private JMenuItem jmnuiSave 	  = new JMenuItem();
	private JMenuItem jmnuiDelete   = new JMenuItem();
	private JMenuItem jmnuiCancel   = new JMenuItem();
	private JMenuItem jmnuiExit     = new JMenuItem();
	private JMenu jmnuEdit 			  = new JMenu();
	private JMenuItem jmnuiFind 	  = new JMenuItem();
	private JMenu jmnuView 			  = new JMenu();
	private JMenuItem jmnuiFB 		  = new JMenuItem();
	private JMenuItem jmnuiBooks 	  = new JMenuItem();
	private JMenuItem jmnuiCourses  = new JMenuItem();
	private JMenuItem jmnuiFBL		  = new JMenuItem();
	private JMenuItem jmnuiBack	  = new JMenuItem();
	private JMenuItem jmnuiNext 	  = new JMenuItem();
	private JMenu jmnuHelp			  = new JMenu();
	private JMenuItem jmnuiAbout 	  = new JMenuItem();
	//-->

	//<!-- DECLARE_BUTTONS - For Toolbar
	private JToolBar jtbrMain	  	  = new JToolBar();
	private JButton jbtnNew	  		  = new JButton(new ImageIcon("images/new.gif"));
	private JButton jbtnSave		  = new JButton(new ImageIcon("images/save.gif"));
	private JButton jbtnDelete		  = new JButton(new ImageIcon("images/delete.gif"));
	private JButton jbtnCancel		  = new JButton(new ImageIcon("images/cancel.gif"));
	private JButton jbtnFB			  = new JButton(new ImageIcon("images/fb.gif"));
	private JButton jbtnBooks		  = new JButton(new ImageIcon("images/books.gif"));
	private JButton jbtnCourses	  = new JButton(new ImageIcon("images/courses.gif"));
	private JButton jbtnFBL	  		  = new JButton(new ImageIcon("images/fbl.gif"));
	private JButton jbtnFind		  = new JButton(new ImageIcon("images/find.gif"));
	private JButton jbtnBack		  = new JButton(new ImageIcon("images/back.gif"));
	private JButton jbtnNext		  = new JButton(new ImageIcon("images/next.gif"));
	//-->
}