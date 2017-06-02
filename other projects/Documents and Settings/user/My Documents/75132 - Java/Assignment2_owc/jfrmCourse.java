/**
* A screen for user to key in Course details
*/
import javax.swing.*;
import java.awt.*;

public class jfrmCourse extends JInternalFrame {
//-------------------------------------------- Course Constructor
	public jfrmCourse(String strTitle, clsBookListEntry[] arrIn) {
		//<!-- INIT_CONTROLS
		// super(title, resizable, closable, maximizable, iconifiable)
		super(strTitle, false, true, false, true);
		setSize(380, 170);
		setVisible(false);
		intIndex  = 0;
		arrCourse = arrIn;

		jpnlMain.setLayout(null);
		jpnlMain.setBackground(Color.white);
		getContentPane().add(jpnlMain, BorderLayout.CENTER);

		jlblCourseNo.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblCourseNo.setText("Course Number:");
		jlblCourseNo.setForeground(Color.black);
		jlblCourseNo.setBounds(12,20,108,20);
		jpnlMain.add(jlblCourseNo);

		jlblCourseName.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlblCourseName.setText("Course Name:");
		jlblCourseName.setForeground(Color.black);
		jlblCourseName.setBounds(12,50,108,20);
		jpnlMain.add(jlblCourseName);
		// Make sure the array is not null
		if(arrCourse != null) {
			if(arrCourse[intIndex] != null) {
				// Assign 1st element into JTextField
				mtd_Search(intIndex);
				// Get array last index
				intLastIndex = objFileHdl.mtd_getLastIndex(arrCourse);
			}
		}
		jtxtCourseNo.setEnabled(false);
		jtxtCourseNo.setBounds(108,20,250,24);
		jpnlMain.add(jtxtCourseNo);

		jtxtCourseName.setBounds(108,50,250,24);
		jpnlMain.add(jtxtCourseName);
		//-->
	}

//-------------------------------------------- Course Search method
	public void mtd_Search(int intPosition) {
		if(intPosition != -1) {
			jtxtCourseNo.setText(arrCourse[intPosition].getCourseNo());
			jtxtCourseName.setText(arrCourse[intPosition].getCourseName());
			intIndex = intPosition;
		}
	}

//-------------------------------------------- Course Navigation (back/next) method
	public void mtd_Navigation(int intDirection) {
		intIndex  += intDirection;
		bIsInsert = true;
		jtxtCourseNo.setEnabled(false);
		// Array is empty, prompt msg
		if(arrCourse == null) {
			objFileHdl.mtd_MsgBox(" - Course Details","There is no record in the file, " +
										 "please insert record first.",-1,0);
			return;
		}
		if(intIndex >= 0) {
			if(intIndex < intLastIndex) {
				jtxtCourseNo.setText(arrCourse[intIndex].getCourseNo());
				jtxtCourseName.setText(arrCourse[intIndex].getCourseName());
			} else {
				objFileHdl.mtd_MsgBox(" - Course Details","End of file reached!\n" +
										    "You can't go to the specified record.",-1,2);
				intIndex -= intDirection;	// Undo the addition (frm above)
			}
		} else {
			objFileHdl.mtd_MsgBox(" - Course Details","Start of file reached!\n" +
										 "You can't go to the specified record.",-1,2);
			intIndex = 0;						// Point to the 1st element
		}
	}

//-------------------------------------------- Course New Record method
	public void mtd_New() {
		// Make sure the size doesn't over exceed the array
		if(arrCourse != null) {
			if(intLastIndex >= arrCourse.length) {
				String strErrMsg = "The program only allow you to insert 100 records " +
										 "for each run.\nYou have exceeded that amount, " +
										 "please save your work and\nre-run the program again.";
				objFileHdl.mtd_MsgBox(" - Course Details",strErrMsg,-1,0);
				return;
			}
		}
		jtxtCourseNo.setEnabled(true);
		jtxtCourseNo.setText("");
		jtxtCourseName.setText("");
		jtxtCourseNo.grabFocus();
		bIsInsert = false;
	}

//-------------------------------------------- Course Save Record method
	public void mtd_Save() {
		if(mtd_Validate()) {
			if(!bIsInsert) {		// In inserting mode
				if(intLastIndex == 0) {
					// If the array is empty, create a new array with 100 elements
					arrCourse = new clsBookListEntry[100];
				} else {
					// Make sure the CourseNo is unique
					int intPosition;
					intPosition = objFileHdl.mtd_SearchCourse(arrCourse,
																			jtxtCourseNo.getText().trim(), 0);
					if(intPosition != -1) {
						jtxtCourseNo.grabFocus();
						String strMsg = "The Course Number you have enter is already exists.\n"
										  + "Do you want to edit the details instead of creating?";
						if(objFileHdl.mtd_MsgBox(" - Course Details", strMsg, 0, 2) == 0) {
							mtd_Search(intPosition);
							bIsInsert = true;
							jtxtCourseNo.setEnabled(false);
							jtxtCourseName.grabFocus();
						}
						return;
					}
				}
				arrCourse[intLastIndex] = new clsBookListEntry();
				arrCourse[intLastIndex].setCourseNo(jtxtCourseNo.getText());
				arrCourse[intLastIndex].setCourseName(jtxtCourseName.getText());
				intIndex = intLastIndex;
				// Update array last index
				intLastIndex++;
			} else {					// In updating mode
				arrCourse[intIndex].setCourseName(jtxtCourseName.getText());
			}
			bIsInsert  = true;
			jtxtCourseNo.setEnabled(false);
			objFileHdl.mtd_MsgBox(" - Course Details", "Record saved successfully.",-1,1);
		}
	}

//-------------------------------------------- Course Delete Record method
	public void mtd_Delete() {
		// Array is empty, prompt msg
		if(arrCourse == null || arrCourse[0] == null) {
			objFileHdl.mtd_MsgBox(" - Course Details","There is no record in the file " +
										 "to delete.",-1,0);
			return;
		}
		if(bIsInsert) {			// In updating mode, can delete
			int intChoice;
			intChoice = objFileHdl.mtd_MsgBox(" - Course Details","Are you sure you want " +
														 "to delete this record?",0,3);
			if(intChoice == 0) {
				if(objFileHdl.mtd_Delete(arrCourse, intIndex)) {
					objFileHdl.mtd_MsgBox(" - Course Details","Record has been deleted.",-1,1);
					--intLastIndex;
					if(intLastIndex <=0) {
						objFileHdl.mtd_MsgBox(" - Course Details","There is no more record " +
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
			objFileHdl.mtd_MsgBox(" - Course Details","You cannot delete record while you " +
										 "are in inserting mode.",-1,2);
		}
	}

//-------------------------------------------- Course Validate Record method
	private boolean mtd_Validate() {
		boolean bRet         = true;
		String strErrMsg     = "";
		String strCourseNo   = jtxtCourseNo.getText();
		String strCourseName = jtxtCourseName.getText();
		if(strCourseNo == null || strCourseNo.length() < 1) {
			strErrMsg = "The Course Number field cannot be empty.";
			jtxtCourseNo.grabFocus();
			bRet      = false;
		} else if(strCourseName == null || strCourseName.length() < 1) {
			strErrMsg = "The Course Name field cannot be empty.";
			jtxtCourseName.grabFocus();
			bRet		 = false;
		}
		if(!bRet) objFileHdl.mtd_MsgBox(" - Course Details",strErrMsg,-1,0);
		return bRet;
	}

	// Return the latest array back to parent frame
	public clsBookListEntry[] mtd_getarrCourse() { return arrCourse; }

	//<!-- DECLARE_CONTROLS/VARIABLES
	private clsBookListEntry[] arrCourse;
	private int intIndex		 		    = 0;
	private int intLastIndex		    = 0;
	private boolean bIsInsert			 = true;
	private clsFileHdl objFileHdl     = new clsFileHdl();
	private JPanel jpnlMain 			 = new JPanel();
	private JLabel jlblCourseNo 		 = new JLabel();
	private JLabel jlblCourseName     = new JLabel();
	private JTextField jtxtCourseNo   = new JTextField();
	private JTextField jtxtCourseName = new JTextField();
	//-->
}
