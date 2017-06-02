import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

public class jfrmFacultyBook extends JInternalFrame implements ItemListener {
//-------------------------------------------- Faculty Book Constructor
	public jfrmFacultyBook(String strTitle, clsBookListEntry[] arrFIn,
								  clsBookListEntry[] arrBIn,clsBookListEntry[] arrCIn) {
		//<!-- INIT_CONTROLS
		// super(title, resizable, closable, maximizable, iconifiable)
		super(strTitle, false, true, false, true);
		getContentPane().setLayout(null);
		setLocation(55, 52);
		setSize(480,315);
		setVisible(false);

		TitledBorder tbdrCourse = new TitledBorder(" Course Details ");
		tbdrCourse.setTitleColor(Color.black);
		jpnlCourseDtl.setBorder(tbdrCourse);
		jpnlCourseDtl.setBackground(Color.white);
		jpnlCourseDtl.setBounds(0,0,470,90);
		jpnlCourseDtl.setLayout(null);

		jlblCourseNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblCourseNo.setForeground(Color.black);
		jlblCourseNo.setText("Course Number:");
		jlblCourseNo.setBounds(12,20,100,20);
		jpnlCourseDtl.add(jlblCourseNo);

		jcboCourseNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		jcboCourseNo.setToolTipText("Choose a Course Number from the list");
		jcboCourseNo.setBackground(Color.white);
		jcboCourseNo.setBounds(108,20,110,24);
		jpnlCourseDtl.add(jcboCourseNo);

		jlblCourseName.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblCourseName.setForeground(Color.black);
		jlblCourseName.setText("Course Name:");
		jlblCourseName.setBounds(12,50,90,20);
		jpnlCourseDtl.add(jlblCourseName);

		jtxtCourseName.setBounds(108,50,350,24);
		jtxtCourseName.setEnabled(false);
		jpnlCourseDtl.add(jtxtCourseName);

		getContentPane().add(jpnlCourseDtl);

		TitledBorder tbdrBook = new TitledBorder(" Text Book Details ");
		tbdrBook.setTitleColor(Color.black);
		jpnlBookDtl.setBorder(tbdrBook);
		jpnlBookDtl.setBackground(Color.white);
		jpnlBookDtl.setBounds(0,90,470,190);
		jpnlBookDtl.setLayout(null);

		jlblBookNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblBookNo.setForeground(Color.black);
		jlblBookNo.setBounds(12,20,90,20);
		jlblBookNo.setText("Book Number:");
		jpnlBookDtl.add(jlblBookNo);

		jcboBookNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		jcboBookNo.setToolTipText("Choose a Book Number from the list");
		jcboBookNo.setBackground(Color.white);
		jcboBookNo.setBounds(108,20,110,24);
		jpnlBookDtl.add(jcboBookNo);

		jlblBookName.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblBookName.setForeground(Color.black);
		jlblBookName.setBounds(12,50,90,20);
		jlblBookName.setText("Book Name:");
		jpnlBookDtl.add(jlblBookName);

		jtxtBookName.setBounds(108,50,350,24);
		jtxtBookName.setEnabled(false);
		jpnlBookDtl.add(jtxtBookName);

		jlblPublisher.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblPublisher.setForeground(Color.black);
		jlblPublisher.setBounds(12,80,90,20);
		jlblPublisher.setText("Publisher:");
		jpnlBookDtl.add(jlblPublisher);

		jtxtPublisher.setBounds(108,80,350,24);
		jtxtPublisher.setEnabled(false);
		jpnlBookDtl.add(jtxtPublisher);

		jlblEdition.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblEdition.setForeground(Color.black);
		jlblEdition.setBounds(12,110,90,20);
		jlblEdition.setText("Edition:");
		jpnlBookDtl.add(jlblEdition);

		jtxtEdition.setBounds(108,110,110,24);
		jtxtEdition.setEnabled(false);
		jpnlBookDtl.add(jtxtEdition);

		jlblYear.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblYear.setForeground(Color.black);
		jlblYear.setBounds(235,110,110,20);
		jlblYear.setText("Year of Publication:");
		jpnlBookDtl.add(jlblYear);

		jtxtYear.setBounds(349,110,110,24);
		jtxtYear.setEnabled(false);
		jpnlBookDtl.add(jtxtYear);

		jlblPrice.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblPrice.setForeground(Color.black);
		jlblPrice.setBounds(12,140,90,20);
		jlblPrice.setText("Retail Price:");
		jpnlBookDtl.add(jlblPrice);

		jtxtPrice.setBounds(108,140,110,24);
		jtxtPrice.setEnabled(false);
		jpnlBookDtl.add(jtxtPrice);

		jlblType.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblType.setForeground(Color.black);
		jlblType.setBounds(235,140,90,20);
		jlblType.setText("Type:");
		jpnlBookDtl.add(jlblType);

		jtxtType.setBounds(349,140,110,24);
		jtxtType.setEnabled(false);
		jpnlBookDtl.add(jtxtType);

		jlblDesc.setText("R = Recommended Reading; P = Prescribed");
		jlblDesc.setFont(new Font("Dialog", Font.PLAIN, 10));
		jlblDesc.setForeground(Color.black);
		jlblDesc.setBounds(250,160,210,20);
		jpnlBookDtl.add(jlblDesc);
		getContentPane().add(jpnlBookDtl);

		arrFaculty   = arrFIn;
		arrBook      = arrBIn;
		arrCourse    = arrCIn;
		intIndex     = 0;
		intLastIndex = 0;
		intCPos 		 = objFileHdl.mtd_getLastIndex(arrCourse);
		intBPos 		 = objFileHdl.mtd_getLastIndex(arrBook);
		mtd_fillJCbo();
		// Make sure the array is not null
		if(arrFaculty != null) {
			if(arrFaculty[intIndex] != null) {
				// Get array last index
				intLastIndex = objFileHdl.mtd_getLastIndex(arrFaculty);
				// Assign 1st element into JTextField
				mtd_Search(intIndex);
			}
		}
		//<!-- REGISTER_LISTENERS
		jcboCourseNo.addItemListener(this);
		jcboBookNo.addItemListener(this);
		//-->
	}

//-------------------------------------------- Faculty Book Listener
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == 1) {
			if(event.getSource() == jcboCourseNo) {
				intCPos = jcboCourseNo.getSelectedIndex() - 1;
				mtd_fillCourseDtl(intCPos);
				if(!bIsInsert) jcboBookNo.showPopup();
			} else if(event.getSource() == jcboBookNo) {
				intBPos = jcboBookNo.getSelectedIndex() - 1;
				mtd_fillBookDtl(intBPos);
			}
		}
	}

//-------------------------------------------- Faculty Book New Record method
	public void mtd_New() {
		// Make sure the size doesn't over exceed the array
		if(arrFaculty != null) {
			if(intLastIndex >= arrBook.length) {
				String strErrMsg = "The program only allow you to insert 100 records " +
										 "for each run.\nYou have exceeded that amount, " +
										 "please save your work and\nre-run the program again.";
				objFileHdl.mtd_MsgBox(" - Faculty Books Details",strErrMsg,-1,0);
				return;
			}
		}
		jcboCourseNo.setSelectedIndex(0);
		jcboBookNo.setSelectedIndex(0);
		mtd_fillBookDtl(-1);
		mtd_fillCourseDtl(-1);
		jcboCourseNo.showPopup();
		bIsInsert = false;
	}

//-------------------------------------------- Faculty Book Save Record method
	public void mtd_Save() {
		if(mtd_Validate()) {
			String strCourseNo = jcboCourseNo.getSelectedItem().toString();
			String strBookNo   = jcboBookNo.getSelectedItem().toString();
			if(!bIsInsert) {		// In inserting mode
				if(intLastIndex == 0) {
					// If the array is empty, create a new array with 100 elements
					arrFaculty = new clsBookListEntry[100];
				} else {
					// Make sure the record is unique
					for(int f=0; f < intLastIndex; f++) {
						if(arrFaculty[f].getCourseNo().equalsIgnoreCase(strCourseNo) &&
							arrFaculty[f].getBookNo().equalsIgnoreCase(strBookNo)) {
							String strMsg = "The record you have enter is already " +
												 "exists.\nDo you want to edit its details?";
							if(objFileHdl.mtd_MsgBox(" - Faculty Books Details", strMsg, 0, 2)
								== 0) {
								mtd_Search(f);
								bIsInsert = true;
							}
							jcboCourseNo.showPopup();
							return;
						}
					}
				}
				arrFaculty[intLastIndex] = new clsBookListEntry();
				arrFaculty[intLastIndex].setCourseNo(strCourseNo);
				arrFaculty[intLastIndex].setBookNo(strBookNo);
				intIndex = intLastIndex;
				// Update array last index
				intLastIndex++;
			} else {					// In updating mode
				if(intLastIndex == 0) {
					String strMsg = "You cannot edit this record because the file is empty.\n"
										 + "Please create new record first.";
					objFileHdl.mtd_MsgBox(" - Faculty Books Details",strMsg,-1,0);
					return;
				}
				arrFaculty[intIndex].setCourseNo(strCourseNo);
				arrFaculty[intIndex].setBookNo(strBookNo);
			}
			bIsInsert = true;
			objFileHdl.mtd_MsgBox(" - Faculty Books Details", "Record saved successfully.",-1,1);
		}
	}

//-------------------------------------------- Faculty Book Delete Record method
	public void mtd_Delete() {
		// Array is empty, prompt msg
		if(arrFaculty == null || arrFaculty[0] == null) {
			objFileHdl.mtd_MsgBox(" - Faculty Books Details",
										 "There is no record in the file " +
										 "to delete.", -1, 0);
			return;
		}
		if(bIsInsert) {			// In updating mode, can delete
			int intChoice;
			intChoice = objFileHdl.mtd_MsgBox(" - Faculty Books Details",
														 "Are you sure you want " +
														 "to delete this record?", 0, 3);
			if(intChoice == 0) {
				if(objFileHdl.mtd_Delete(arrFaculty, intIndex)) {
					objFileHdl.mtd_MsgBox(" - Faculty Books Details",
												 "Record has been deleted.",-1,1);
					--intLastIndex;
					if(intLastIndex <=0) {
						objFileHdl.mtd_MsgBox(" - Faculty Books Details",
													 "There is no more record " +
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
			objFileHdl.mtd_MsgBox(" - Faculty Books Details",
										 "You cannot delete record while you " +
										 "are in inserting mode.", -1, 2);
		}
	}

//-------------------------------------------- Faculty Book Search method
	public void mtd_Search(int intPosition) {
		if(intPosition != -1) {
			intCPos = objFileHdl.mtd_SearchCourse(arrCourse,
															  arrFaculty[intPosition].getCourseNo(), 0);
			intBPos = objFileHdl.mtd_SearchBook(arrBook,
															arrFaculty[intPosition].getBookNo(), 0);
			jcboCourseNo.setSelectedIndex(intCPos + 1);
			jcboBookNo.setSelectedIndex(intBPos + 1);
			mtd_fillCourseDtl(intCPos);
			mtd_fillBookDtl(intBPos);
			intIndex = intPosition;
		}
	}

//-------------------------------------------- Faculty Book back/next method
	public void mtd_Navigation(int intDirection) {
		intIndex  += intDirection;
		bIsInsert = true;
		// Array is empty, prompt msg
		if(arrFaculty == null) {
			objFileHdl.mtd_MsgBox(" - Faculty Books Details",
										 "There is no record in the file, " +
										 "please insert record first.",-1,0);
			return;
		}
		if(intIndex >= 0) {
			if(intIndex < intLastIndex) {
				intCPos = objFileHdl.mtd_SearchCourse(arrCourse,
																  arrFaculty[intIndex].getCourseNo(), 0);
				intBPos = objFileHdl.mtd_SearchBook(arrBook,
																arrFaculty[intIndex].getBookNo(), 0);
				jcboCourseNo.setSelectedIndex(intCPos + 1);
				jcboBookNo.setSelectedIndex(intBPos + 1);
			} else {
				objFileHdl.mtd_MsgBox(" - Faculty Books Details","End of file reached!\n" +
											 "You can't go to the specified record.",-1,2);
				intIndex -= intDirection;	// Undo the addition (frm above)
			}
		} else {
			objFileHdl.mtd_MsgBox(" - Faculty Books Details","Start of file reached!\n" +
										 "You can't go to the specified record.",-1,2);
			intIndex = 0;						// Point to the 1st element
		}
	}

//--------------------------------------- Initialize value into all JTextField/JCombo
	private void mtd_fillJCbo() {
		jcboCourseNo.addItem("[Choose One]");
		for(int c=0; c < intCPos; c++)
			jcboCourseNo.addItem(arrCourse[c].getCourseNo());
		jcboBookNo.addItem("[Choose One]");
		for(int b=0; b < intBPos; b++)
			jcboBookNo.addItem(arrBook[b].getBookNo());
	}

	private void mtd_fillCourseDtl(int i) {
		if(i != -1) {
			// Take Course array value assign into JTextField
			jtxtCourseName.setText(arrCourse[i].getCourseName());
		} else {
			// Clear JTextField value
			jtxtCourseName.setText("");
		}
	}

	private void mtd_fillBookDtl(int i) {
		if(i != -1) {
			// Take Book array value assign into JTextField
			jtxtBookName.setText(arrBook[i].getBookName());
			jtxtPublisher.setText(arrBook[i].getPublisher());
			jtxtYear.setText(objFileHdl.mtd_formatValue(arrBook[i].getYear(), "####"));
			jtxtPrice.setText(objFileHdl.mtd_formatValue(arrBook[i].getPrice(),
																		"#,###.00"));
			jtxtEdition.setText(objFileHdl.mtd_formatValue(arrBook[i].getEdition(),
																		  "###"));
			jtxtType.setText(String.valueOf(arrBook[i].getType()));
		} else {
			// Clear JTextField value
			jtxtBookName.setText("");
			jtxtPublisher.setText("");
			jtxtYear.setText("");
			jtxtPrice.setText("");
			jtxtEdition.setText("");
			jtxtType.setText("");
		}
	}

//-------------------------------------------- Faculty Book Validate Record method
	private boolean mtd_Validate() {
		boolean bRet     = true;
		String strErrMsg = "";
		if(jcboCourseNo.getSelectedIndex() == 0) {
			strErrMsg = "Please choose a Course Number from the list.";
			jcboCourseNo.showPopup();
			bRet 		 = false;
		} else if(jcboBookNo.getSelectedIndex() == 0) {
			strErrMsg = "Please choose a Book Number from the list.";
			jcboBookNo.showPopup();
			bRet 		 = false;
		}
		if(!bRet) objFileHdl.mtd_MsgBox(" - Faculty Books Details",strErrMsg,-1,0);
		return bRet;
	}

	// Return the latest array back to parent frame
	public clsBookListEntry[] mtd_getarrFacultyBook() { return arrFaculty; }
	//<!-- DECLARE_CONTROLS/VARIABLES
	private int intIndex, intLastIndex, intBPos, intCPos;
	private clsBookListEntry[] arrFaculty, arrBook, arrCourse;
	private clsFileHdl objFileHdl     = new clsFileHdl();
	private boolean bIsInsert			 = true;
	private JPanel jpnlCourseDtl      = new JPanel();
	private JPanel jpnlBookDtl 		 = new JPanel();
	private JComboBox jcboCourseNo    = new JComboBox();
	private JComboBox jcboBookNo		 = new JComboBox();
	private JTextField jtxtCourseName = new JTextField();
	private JTextField jtxtBookName   = new JTextField();
	private JTextField jtxtPublisher  = new JTextField();
	private JTextField jtxtEdition    = new JTextField();
	private JTextField jtxtYear       = new JTextField();
	private JTextField jtxtPrice		 = new JTextField();
	private JTextField jtxtType 		 = new JTextField();
	private JLabel jlblCourseNo 		 = new JLabel();
	private JLabel jlblCourseName	    = new JLabel();
	private JLabel jlblBookNo 		    = new JLabel();
	private JLabel jlblBookName 	    = new JLabel();
	private JLabel jlblPublisher      = new JLabel();
	private JLabel jlblEdition        = new JLabel();
	private JLabel jlblYear           = new JLabel();
	private JLabel jlblPrice          = new JLabel();
	private JLabel jlblType           = new JLabel();
	private JLabel jlblDesc 			 = new JLabel();
	//-->
}