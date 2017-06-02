/**
* A general file handler class that manage reading, writing, etc
*/
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.Vector;
import java.io.*;

public class clsFileHdl extends JFrame {
//-------------------------------------------- File Handler misc method
	/**
	* A custom msg/alert box that make use of JOptionPane class.
	* @param  strTitle   - Title of the alert box
	*         strErrMsg  - The msg or error msg that will shown in the alert box
	*         intOptType - Operation type:
	*							 -1 = DEFAULT_OPTION	      0 = YES_NO_OPTION
	*							  1 = YES_NO_CANCEL_OPTION	2 = OK_CANCEL_OPTION
	*		    intMsgType - Type of the msg:
   *							  0 = ERROR_MESSAGE			1 = INFORMATION_MESSAGE
	*							  2 = WARNING_MESSAGE		3 = QUESTION_MESSAGE
	*							  4 = PLAIN_MESSAGE
	* @return integer    - Which represent the choice of the user choose (if any)
	*/
	public int mtd_MsgBox(String strErrMsg, int intOptType, int intMsgType) {
		return mtd_MsgBox(" - Error", strErrMsg, intOptType, intMsgType);
	}
	public int mtd_MsgBox(String strTitle, String strErrMsg,
								 int intOptType, int intMsgType) {
		return JOptionPane.showConfirmDialog(this, strErrMsg, "USQ Faculty Book List" +
														 strTitle, intOptType, intMsgType);
	}

	/**
	* Format the double/integer value according to the pattern passed in.
	* @param  dblIn/intIn - Double/integer value to be format
	* 		    strPattern  - Pattern of the value need to be format
	* @return string      - a formatted double/integer value
	*/
	public String mtd_formatValue(int intIn, String strPattern) {
		return mtd_formatValue((double)intIn, strPattern);
	}
	public String mtd_formatValue(double dblIn, String strPattern) {
		String strRet = "";
		if (strPattern != null) {
			DecimalFormat dfObj = new DecimalFormat(strPattern);
			if (dfObj != null) strRet = dfObj.format(dblIn);
		}
		return strRet;
	}

	/**
	* To check whether a file exists or not
	* @param  strFileName - Name of the file to check
	* @return boolean     - true if exists else false
	*/
	private boolean mtd_FileExists(String strFileName) {
   	boolean bRet = false;
		File file    = new File(strFileName);
		if(file.exists()) bRet = true;
		return bRet;
   }

	/**
	* Return the last index of an array
	* @param  arrIn   - Array to be check
	* @return integer - The last index of the array
	*/
	public int mtd_getLastIndex(clsBookListEntry[] arrIn) {
		i = 0;
		if(arrIn != null) {
			for(i=0; i < arrIn.length; i++) if(arrIn[i] == null) break;
		}
		return i;
	}

	/**
	* Open a file for further read or write operation
	* @param  strFileName - Name of the file to be open
	*         chrMode     - Operation type; 'r' = read, 'w' = write
	* @return boolean     - true if success else false
	*/
	private boolean mtd_OpenTextFile(String strFileName, char chrMode) {
		br				 = null;
		pw				 = null;
		fos			 = null;
		boolean bRet = false;
		try {
			if(chrMode == 'r') {				// read
				if(mtd_FileExists(strFileName)) {
					FileReader fr = new FileReader(strFileName);
					br 			  = new BufferedReader(fr);
					bRet          = true;
				}
			} else if(chrMode == 'w') {	// write
				File file = new File(strFileName);
				fos  		 = new FileOutputStream(file);
				pw   		 = new PrintWriter(fos, true);
				bRet 		 = true;
			}
		} catch(IOException ex) {
			mtd_MsgBox("Cannot open '" + strFileName + "' for '" + chrMode +
						  "' operation.\n" + "The program return the following error:\n"
						  + ex.getMessage(),-1,0);
		}
		return bRet;
	}

	/**
	* Delete a elements in an array by moving up the element
	* to replace one and another
	* @param  arrIn       - Array to be delete
	*         intPosition - At which element to delete/replace
	* @return boolean     - true if success else false
	*/
	public boolean mtd_Delete(clsBookListEntry[] arrIn, int intPosition) {
		boolean bRet = false;
		try {
			if(arrIn != null) {
				for(i=intPosition; i < arrIn.length; i++) {
					arrIn[i] = arrIn[i+1];
					if(arrIn[i] == null) break;
				}
				bRet = true;
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			mtd_MsgBox("Cannot delete data from text file.\n" +
						  "The program return the following error:\n" + ex.getMessage(),-1,0);
		}
		return bRet;
	}

	/**
	* Search an array against the data passed in & Return the position of a
	* record in an array
	* @param  arrIn        - Array to be search
	*         strFilter    - Data need to be search
	*         intAtColumn  - Which column in the array need to be search
	*         intWhichFile - Which to search, Book or Course
	* @return integer      - The position of the matching data
	*/
	public int mtd_SearchBook(clsBookListEntry[] arrIn, String strFilter, int intAtColumn) {
		return mtd_Search(arrIn, strFilter, intAtColumn, 1);
	}
	public int mtd_SearchCourse(clsBookListEntry[] arrIn, String strFilter,
										 int intAtColumn) {
		return mtd_Search(arrIn, strFilter, intAtColumn, 2);
	}
	public int mtd_Search(clsBookListEntry[] arrIn, String strFilter,
								 int intAtColumn, int intWhichFile) {
		boolean boolFound = false;
		if(arrIn != null) {
			for(i=0; i < arrIn.length; i++) {
				if(arrIn[i] == null) break;			// Last element reached, do nothing
				if(intWhichFile == 1) {					// Book
					if(intAtColumn == 0) {			   // Search by Book No
						if(arrIn[i].getBookNo().equalsIgnoreCase(strFilter)) {
							boolFound = true;
							break;
						}
					} else if(intAtColumn == 1) {		// Search by Book Name
						if(arrIn[i].getBookName().equalsIgnoreCase(strFilter)) {
							boolFound = true;
							break;
						}
					}
				} else if(intWhichFile == 2) {		// Course
					if(intAtColumn == 0) {			   // Search by Course No
						if(arrIn[i].getCourseNo().equalsIgnoreCase(strFilter)) {
							boolFound = true;
							break;
						}
					} else if(intAtColumn == 1) {		// Search by Course Name
						if(arrIn[i].getCourseName().equalsIgnoreCase(strFilter)) {
							boolFound = true;
							break;
						}
					}
				}
			}
		}
		if(boolFound == false) i = -1;
		return i;
	}

//-------------------------------------------- File Handler reading file method
	/**
	* Read all record in a text file into a Vector than convert it into array,
	* this to make sure that all record in a text file can be read out.
	* @return clsBookListEntry Array - array contains all record in the text file
	*/
	public clsBookListEntry[] mtd_BookFile() {
		i 									= 0;
		clsBookListEntry[] arrBook = null;
		Vector vtrBook 				= new Vector();
		mtd_OpenTextFile(BOOK_FILE, 'r');
		if(br != null) {
			try {
				while((strCurrLine = br.readLine()) != null) {
					strTokenizer     = new StringTokenizer(strCurrLine, ";");
					objBookListEntry = new clsBookListEntry();
					objBookListEntry.setBookNo(strTokenizer.nextToken());
					objBookListEntry.setBookName(strTokenizer.nextToken());
					objBookListEntry.setPublisher(strTokenizer.nextToken());
					objBookListEntry.setEdition(Integer.parseInt(strTokenizer.nextToken()));
					objBookListEntry.setYear(Integer.parseInt(strTokenizer.nextToken()));
					objBookListEntry.setPrice(Double.parseDouble(strTokenizer.nextToken()));
					objBookListEntry.setType(strTokenizer.nextToken().charAt(0));
					vtrBook.add(objBookListEntry);
					i++;
				}
				br.close();
				// Create an array with total record in the file + 100 blank element for insert
				arrBook = new clsBookListEntry[i + 100];
				// Convert the vector to array
				vtrBook.toArray(arrBook);
			} catch(IOException ex) {
				mtd_MsgBox("Cannot read data from '" + BOOK_FILE + "' file.\n" +
							  "The program return the following error:\n" + ex.getMessage(),-1,0);
			}
		}
		return arrBook;
	}

	/**
	* Same as mtd_BookFile() method but this one for Course
	*/
	public clsBookListEntry[] mtd_CourseFile() {
		i				 					  = 0;
		clsBookListEntry[] arrCourse = null;
		Vector vtrCourse             = new Vector();
		mtd_OpenTextFile(COURSE_FILE, 'r');
		if(br != null) {
			try {
				while((strCurrLine = br.readLine()) != null) {
					strTokenizer     = new StringTokenizer(strCurrLine, ";");
					objBookListEntry = new clsBookListEntry();
					objBookListEntry.setCourseNo(strTokenizer.nextToken());
					objBookListEntry.setCourseName(strTokenizer.nextToken());
					vtrCourse.add(objBookListEntry);
					i++;
				}
				br.close();
				// Create an array with total record in the file + 100 blank element for insert
				arrCourse = new clsBookListEntry[i + 100];
				// Convert the vector to array
				vtrCourse.toArray(arrCourse);
			} catch(IOException ex) {
				mtd_MsgBox("Cannot read data from '" + COURSE_FILE + "' file.\n" +
							  "The program return the following error:\n" + ex.getMessage(),-1,0);
			}
		}
		return arrCourse;
	}

	/**
	* Same as mtd_BookFile() method but this one for FacultyBook
	*/
	public clsBookListEntry[] mtd_FacultyBookFile() {
		i				 					   = 0;
		clsBookListEntry[] arrFaculty = null;
		Vector vtrFaculty             = new Vector();
		mtd_OpenTextFile(FACULTY_BOOK_FILE, 'r');
		if(br != null) {
			try {
				while((strCurrLine = br.readLine()) != null) {
					strTokenizer     = new StringTokenizer(strCurrLine, ";");
					objBookListEntry = new clsBookListEntry();
					objBookListEntry.setCourseNo(strTokenizer.nextToken());
					objBookListEntry.setBookNo(strTokenizer.nextToken());
					vtrFaculty.add(objBookListEntry);
					i++;
				}
				br.close();
				// Create array to store all record in the file + 100 blank element for insert
				arrFaculty = new clsBookListEntry[i + 100];
				// Convert the vector to array
				vtrFaculty.toArray(arrFaculty);
			} catch(IOException ex) {
				mtd_MsgBox("Cannot read data from '" + FACULTY_BOOK_FILE + "' file.\n" +
							  "The program return the following error:\n" + ex.getMessage(),-1,0);
			}
		}
		return arrFaculty;
	}

//-------------------------------------------- File Handler writing file method
	/**
	* Write the record inside an array into a text file with ; as the delimiter
	* @param  arrBook - Array need to be write into text file
	* @return boolean - true if success, else false
	*/
	public boolean mtd_BookFile(clsBookListEntry[] arrBook) {
		boolean bRet = false;
		mtd_OpenTextFile(BOOK_FILE, 'w');
		if((pw != null) && (fos != null) && (arrBook != null)) {
			try {
				for(i=0; i < arrBook.length; i++) {
					if(arrBook[i] == null) break; // Last element reached
					strCurrLine = arrBook[i].getBookNo()    + ";" +
									  arrBook[i].getBookName()  + ";" +
									  arrBook[i].getPublisher() + ";" +
									  arrBook[i].getEdition()   + ";" +
									  arrBook[i].getYear()      + ";" +
									  arrBook[i].getPrice()     + ";" +
									  arrBook[i].getType();
					pw.println(strCurrLine);
				}
				fos.close();
				pw.close();
				bRet = true;
			} catch(Exception ex) {
				mtd_MsgBox("Cannot write data to '" + BOOK_FILE + "' file.\n" +
						     "The program return the following error:\n" + ex.getMessage(),-1,0);
			}
		}
		return bRet;
	}

	/**
	* Write the record inside an array into a text file with ; as the delimiter
	* @param  arrCourse - Array need to be write into text file
	* @return boolean   - true if success, else false
	*/
	public boolean mtd_CourseFile(clsBookListEntry[] arrCourse) {
		boolean bRet = false;
		mtd_OpenTextFile(COURSE_FILE, 'w');
		if((pw != null) && (fos != null) && (arrCourse != null)) {
			try {
				for(i=0; i < arrCourse.length; i++) {
					if(arrCourse[i] == null) break; // Last element reached
					strCurrLine = arrCourse[i].getCourseNo() + ";" +
									  arrCourse[i].getCourseName();
					pw.println(strCurrLine);
				}
				fos.close();
				pw.close();
				bRet = true;
			} catch(Exception ex) {
				mtd_MsgBox("Cannot write data to '" + COURSE_FILE + "' file.\n" +
						     "The program return the following error:\n" + ex.getMessage(),-1,0);
			}
		}
		return bRet;
	}

	/**
	* Write the record inside an array into a text file with ; as the delimiter
	* @param  arrFaculty - Array need to be write into text file
	* @return boolean    - true if success, else false
	*/
	public boolean mtd_FacultyBookFile(clsBookListEntry[] arrFaculty) {
		boolean bRet = false;
		mtd_OpenTextFile(FACULTY_BOOK_FILE, 'w');
		if((pw != null) && (fos != null) && (arrFaculty != null)) {
			try {
				for(i=0; i < arrFaculty.length; i++) {
					if(arrFaculty[i] == null) break; // Last element reached
					strCurrLine = arrFaculty[i].getCourseNo() + ";" +
									  arrFaculty[i].getBookNo();
					pw.println(strCurrLine);
				}
				fos.close();
				pw.close();
				bRet = true;
			} catch(Exception ex) {
				mtd_MsgBox("Cannot write data to '" + FACULTY_BOOK_FILE + "' file.\n" +
						     "The program return the following error:\n" + ex.getMessage(),-1,0);
			}
		}
		return bRet;
	}

	//<!-- DECLARE_VARIABLES
	private int i;
	private String strCurrLine;
	private StringTokenizer strTokenizer;
	private clsBookListEntry objBookListEntry;
	private PrintWriter pw					   = null;
	private BufferedReader br 				   = null;
	private FileOutputStream fos           = null;
	private final String BOOK_FILE         = "Book.txt";
	private final String COURSE_FILE       = "Course.txt";
	private final String FACULTY_BOOK_FILE = "FacultyBook.txt";
	//-->
}