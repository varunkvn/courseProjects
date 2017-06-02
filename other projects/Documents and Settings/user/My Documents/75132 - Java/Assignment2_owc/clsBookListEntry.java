/**
* A class used to set and get all the necessary field
* required by the program.
*/
public class clsBookListEntry {
//-------------------------------------------- Book List Entry Constructor
	public clsBookListEntry() {
		strCourseNo   = "";
		strCourseName = "";
		strBookNo     = "";
		strBookName   = "";
		strPublisher  = "";
		intEdition    = 0;
		intYear 		  = 0;
		dblPrice		  = 0.00;
		chrType 		  = ' ';
	}

//-------------------------------------------- Book List Entry Setter method
	public void setCourseNo(String strIn) 	 { strCourseNo   = strIn.trim(); }
	public void setCourseName(String strIn) { strCourseName = strIn.trim(); }
	public void setBookNo(String strIn) 	 { strBookNo     = strIn.trim(); }
	public void setBookName(String strIn)   { strBookName   = strIn.trim(); }
	public void setPublisher(String strIn)  { strPublisher  = strIn.trim(); }
	public void setEdition(int intIn)       { intEdition    = intIn; }
	public void setYear(int intIn)          { intYear       = intIn; }
	public void setPrice(double dblIn)      { dblPrice      = dblIn; }
	public void setType(char chrIn)         { chrType       = chrIn; }

//-------------------------------------------- Book List Entry Getter method
	public String getCourseNo() 	{ return strCourseNo; }
	public String getCourseName() { return strCourseName; }
	public String getBookNo() 	   { return strBookNo; }
	public String getBookName()   { return strBookName; }
	public String getPublisher()  { return strPublisher; }
	public int getEdition()       { return intEdition; }
	public int getYear()          { return intYear; }
	public double getPrice()      { return dblPrice; }
	public char getType()         { return chrType; }

   //<!-- DECLARE_VARIABLES
	private String strBookNo, strBookName, strPublisher;
	private String strCourseNo, strCourseName;
	private int intEdition, intYear;
	private double dblPrice;
	private char chrType;
	//-->
}