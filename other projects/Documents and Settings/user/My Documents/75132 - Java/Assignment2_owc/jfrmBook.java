/**
* A screen for user to key in book details
*/
import javax.swing.*;
import java.awt.*;

public class jfrmBook extends JInternalFrame {
//-------------------------------------------- Book Contructor
	public jfrmBook(String strTitle, clsBookListEntry[] arrIn)	{
		//<!-- INIT_CONTROLS
		// super(title, resizable, closable, maximizable, iconifiable)
		super(strTitle, false, true, false, true);
		setLocation(25, 27);
		setSize(495, 250);
		setVisible(false);
		intIndex = 0;
		arrBook  = arrIn;

		jpnlMain.setLayout(null);
		jpnlMain.setBackground(Color.white);
		getContentPane().add(jpnlMain, BorderLayout.CENTER);

		// group the radio button option
		ButtonGroup bgRadio = new ButtonGroup();
		bgRadio.add(jrdR);
		bgRadio.add(jrdP);

		jlblBookNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblBookNo.setText("Text Book Number:");
		jlblBookNo.setForeground(Color.black);
		jlblBookNo.setBounds(12,20,110,20);
		jpnlMain.add(jlblBookNo);

		jlblBookName.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblBookName.setText("Text Book Name:");
		jlblBookName.setForeground(Color.black);
		jlblBookName.setBounds(12,50,100,20);
		jpnlMain.add(jlblBookName);

		jlblPublisher.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblPublisher.setText("Publisher:");
		jlblPublisher.setForeground(Color.black);
		jlblPublisher.setBounds(12,80,100,20);
		jpnlMain.add(jlblPublisher);

		jlblEdition.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblEdition.setText("Edition:");
		jlblEdition.setForeground(Color.black);
		jlblEdition.setBounds(12,110,100,20);
		jpnlMain.add(jlblEdition);

		jlblPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblPrice.setText("Retail Price:");
		jlblPrice.setForeground(Color.black);
		jlblPrice.setBounds(12,140,100,20);
		jpnlMain.add(jlblPrice);

		jlblYear.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblYear.setText("Year of Publication:");
		jlblYear.setForeground(Color.black);
		jlblYear.setBounds(260,110,110,20);
		jpnlMain.add(jlblYear);

		jlblType.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblType.setText("Type:");
		jlblType.setForeground(Color.black);
		jlblType.setBounds(260,140,50,20);
		jpnlMain.add(jlblType);

		jtxtBookNo.setEnabled(false);
		jtxtBookNo.setBounds(125,20,100,24);
		jpnlMain.add(jtxtBookNo);

		jtxtBookName.setBounds(125,50,350,24);
		jpnlMain.add(jtxtBookName);

		jtxtPublisher.setBounds(125,80,350,24);
		jpnlMain.add(jtxtPublisher);

		jtxtYear.setBounds(375,110,100,24);
		jpnlMain.add(jtxtYear);

		jtxtPrice.setBounds(125,140,100,24);
		jpnlMain.add(jtxtPrice);

		jtxtEdition.setBounds(125,110,100,24);
		jpnlMain.add(jtxtEdition);

		jrdR.setFont(new Font("Dialog", Font.PLAIN, 11));
		jrdR.setText("Recommended Reading (R)");
		jrdR.setBackground(Color.white);
		jrdR.setBounds(309,140,170,24);
		jpnlMain.add(jrdR);

		jrdP.setFont(new Font("Dialog", Font.PLAIN, 11));
		jrdP.setText("Prescribed (P)");
		jrdP.setBackground(Color.white);
		jrdP.setBounds(309,161,100,24);
		jpnlMain.add(jrdP);

		// Make sure the array is not null
		if(arrBook != null) {
			if(arrBook[intIndex] != null) {
				// Assign 1st element into JTextField
				mtd_fillJTextField(intIndex);
				// Get array last index
				intLastIndex = objFileHdl.mtd_getLastIndex(arrBook);
			}
		}
		//-->
	}

//-------------------------------------------- Book New Record method
	public void mtd_New() {
		// Make sure the size doesn't over exceed the array
		if(arrBook != null) {
			if(intLastIndex >= arrBook.length) {
				String strErrMsg = "The program only allow you to insert 100 records " +
										 "for each run.\nYou have exceeded that amount, " +
										 "please save your work and\nre-run the program again.";
				objFileHdl.mtd_MsgBox(" - Book Details",strErrMsg,-1,0);
				return;
			}
		}
		mtd_fillJTextField(-1);
		bIsInsert = false;
	}

//-------------------------------------------- Book Save Record method
	public void mtd_Save() {
		if(mtd_Validate()) {
			if(!bIsInsert) {		// In inserting mode
				if(intLastIndex == 0) {
					// If the array is empty, create a new array with 100 elements
					arrBook = new clsBookListEntry[100];
				} else {
					// Make sure the CourseNo is unique
					int intPosition;
					intPosition = objFileHdl.mtd_SearchBook(arrBook,jtxtBookNo.getText().trim(),0);
					if(intPosition != -1) {
						jtxtBookNo.grabFocus();
						String strMsg = "The Text Book Number you have enter is already " +
											 "exists.\nDo you want to edit the details " +
											 "instead of creating?";
						if(objFileHdl.mtd_MsgBox(" - Book Details", strMsg, 0, 2) == 0) {
							mtd_Search(intPosition);
							bIsInsert = true;
							jtxtBookNo.setEnabled(false);
							jtxtBookName.grabFocus();
						}
						return;
					}
				}
				arrBook[intLastIndex] = new clsBookListEntry();
				arrBook[intLastIndex].setBookNo(strBookNo);
				arrBook[intLastIndex].setBookName(strBookName);
				arrBook[intLastIndex].setPublisher(strPublisher);
				arrBook[intLastIndex].setYear(intYear);
				arrBook[intLastIndex].setPrice(dblPrice);
				arrBook[intLastIndex].setEdition(intEdition);
				arrBook[intLastIndex].setType(chrType);
				intIndex = intLastIndex;
				// Update array last index
				intLastIndex++;
			} else {					// In updating mode
				arrBook[intIndex].setBookName(strBookName);
				arrBook[intIndex].setPublisher(strPublisher);
				arrBook[intIndex].setYear(intYear);
				arrBook[intIndex].setPrice(dblPrice);
				arrBook[intIndex].setEdition(intEdition);
				arrBook[intIndex].setType(chrType);
			}
			bIsInsert = true;
			jtxtBookNo.setEnabled(false);
			objFileHdl.mtd_MsgBox(" - Book Details", "Record saved successfully.",-1,1);
		}
	}

//-------------------------------------------- Book Delete Record method
	public void mtd_Delete() {
		// Array is empty, prompt msg
		if(arrBook == null || arrBook[0] == null) {
			objFileHdl.mtd_MsgBox(" - Book Details","There is no record in the file " +
										 "to delete.", -1, 0);
			return;
		}
		if(bIsInsert) {			// In updating mode, can delete
			int intChoice;
			intChoice = objFileHdl.mtd_MsgBox(" - Book Details","Are you sure you want " +
														 "to delete this record?", 0, 3);
			if(intChoice == 0) {
				if(objFileHdl.mtd_Delete(arrBook, intIndex)) {
					objFileHdl.mtd_MsgBox(" - Book Details","Record has been deleted.",-1,1);
					--intLastIndex;
					if(intLastIndex <=0) {
						objFileHdl.mtd_MsgBox(" - Book Details","There is no more record " +
													 "in the file.\nClick OK to create a new record.",
													 -1, 1);
						mtd_New();
					} else if(intIndex == 0) {
						mtd_Navigation(0);
					} else {
						mtd_Navigation(-1);
					}
				}
			}
		} else {						// In inserting mode, cannot delete
			objFileHdl.mtd_MsgBox(" - Book Details","You cannot delete record while you " +
										 "are in inserting mode.", -1, 2);
		}
	}

//-------------------------------------------- Book Search method
	public void mtd_Search(int intPosition) {
		if(intPosition != -1) {
			mtd_fillJTextField(intPosition);
			intIndex = intPosition;
		}
	}

//-------------------------------------------- Book Navigation (back/next) method
	public void mtd_Navigation(int intDirection) {
		intIndex  += intDirection;
		bIsInsert = true;
		jtxtBookNo.setEnabled(false);
		// Array is empty, prompt msg
		if(arrBook == null) {
			objFileHdl.mtd_MsgBox(" - Book Details","There is no record in the file, " +
										 "please insert record first.",-1,0);
			return;
		}
		if(intIndex >= 0) {
			if(intIndex < intLastIndex) {
				mtd_fillJTextField(intIndex);
			} else {
				objFileHdl.mtd_MsgBox(" - Book Details","End of file reached!\n" +
											 "You can't go to the specified record.",-1,2);
				intIndex -= intDirection;	// Undo the addition (frm above)
			}
		} else {
			objFileHdl.mtd_MsgBox(" - Book Details","Start of file reached!\n" +
										 "You can't go to the specified record.",-1,2);
			intIndex = 0;						// Point to the 1st element
		}
	}

//-------------------------------------------- Book Validate Record method
	private boolean mtd_Validate() {
		boolean bRet     = true;
		String strErrMsg = "";
		strBookNo    	  = jtxtBookNo.getText();
		strBookName  	  = jtxtBookName.getText();
		strPublisher 	  = jtxtPublisher.getText();
		String strYr  	  = jtxtYear.getText();
		String strPr     = jtxtPrice.getText();
		String strEd  	  = jtxtEdition.getText();

		if(strBookNo == null || strBookNo.length() < 1) {
			strErrMsg = "The Text Book Number field cannot be empty.";
			jtxtBookNo.grabFocus();
			bRet      = false;
		} else if(strBookName == null || strBookName.length() < 1) {
			strErrMsg = "The Text Book Name field cannot be empty.";
			jtxtBookName.grabFocus();
			bRet		 = false;
		} else if(strPublisher == null || strPublisher.length() < 1) {
			strErrMsg = "The Publisher field cannot be empty.";
			jtxtPublisher.grabFocus();
			bRet      = false;
		} else {
			try {
				intEdition = Integer.parseInt(strEd);
				try {
					// if year less than 4, make it invalid even user key in digit
					if(strYr == null || strYr.length() != 4) strYr = "abc";
					intYear = Integer.parseInt(strYr);
					try {
						dblPrice = Double.parseDouble(strPr);
					} catch(NumberFormatException ex) {
						strErrMsg = "The Price field only accept digit, e.g. 79.80";
						jtxtPrice.grabFocus();
						bRet       = false;
					}
				} catch(NumberFormatException ex) {
					strErrMsg = "The Year field only accept 4 digit year, e.g. 2002";
					jtxtYear.grabFocus();
					bRet      = false;
				}
			} catch(NumberFormatException ex) {
				strErrMsg  = "The Edition field only accept digit, e.g. 2";
				jtxtEdition.grabFocus();
				bRet      = false;
			}
		}
		if(jrdR.isSelected()) chrType = 'R';
		else						 chrType = 'P';
		if(!bRet) objFileHdl.mtd_MsgBox(" - Book Details",strErrMsg,-1,0);
		return bRet;
	}

//-------------------------------------------- Initialize value into all JTextField
	private void mtd_fillJTextField(int i) {
		if(i != -1) {
			// Take array value assign into JTextField
			jtxtBookNo.setText(arrBook[i].getBookNo());
			jtxtBookName.setText(arrBook[i].getBookName());
			jtxtPublisher.setText(arrBook[i].getPublisher());
			jtxtYear.setText(objFileHdl.mtd_formatValue(arrBook[i].getYear(), "####"));
			jtxtPrice.setText(objFileHdl.mtd_formatValue(arrBook[i].getPrice(), "#,###.00"));
			jtxtEdition.setText(objFileHdl.mtd_formatValue(arrBook[i].getEdition(), "###"));
			if(arrBook[i].getType() == 'P') jrdP.setSelected(true);
			else 			 					     jrdR.setSelected(true);
		} else {
			// Clear JTextField value
			jtxtBookNo.setText("");
			jtxtBookName.setText("");
			jtxtPublisher.setText("");
			jtxtYear.setText("");
			jtxtPrice.setText("");
			jtxtEdition.setText("");
			jrdR.setSelected(true);  // default
			jtxtBookNo.setEnabled(true);
			jtxtBookNo.grabFocus();
		}
	}

	// Return the latest array back to parent frame
	public clsBookListEntry[] mtd_getarrBook() { return arrBook; }

	//<!-- DECLARE_CONTROLS/VARIABLES
	private String strBookNo, strBookName, strPublisher;
	private int intYear, intEdition;
	private double dblPrice;
	private char chrType;
	private clsBookListEntry[] arrBook;
	private int intIndex		 		   = 0;
	private int intLastIndex		   = 0;
	private boolean bIsInsert			= true;
	private clsFileHdl objFileHdl    = new clsFileHdl();
	private JPanel jpnlMain  		   = new JPanel();
	private JLabel jlblBookNo			= new JLabel();
	private JLabel jlblBookName		= new JLabel();
	private JLabel jlblPublisher		= new JLabel();
	private JLabel jlblEdition		   = new JLabel();
	private JLabel jlblPrice			= new JLabel();
	private JLabel jlblYear 			= new JLabel();
	private JLabel jlblType 		   = new JLabel();
	private JTextField jtxtBookNo    = new JTextField();
	private JTextField jtxtBookName  = new JTextField();
	private JTextField jtxtPublisher = new JTextField();
	private JTextField jtxtYear      = new JTextField();
	private JTextField jtxtPrice     = new JTextField();
	private JTextField jtxtEdition   = new JTextField();
	private JRadioButton jrdR        = new JRadioButton();
	private JRadioButton jrdP        = new JRadioButton();
	//-->
}