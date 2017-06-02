import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelListener;

public class jfrmFacultyBookList extends JInternalFrame implements ActionListener {
//-------------------------------------------- FacultyBookList Constructor
	public jfrmFacultyBookList(String strTitle, clsBookListEntry[] arrBook,
										clsBookListEntry[] arrCourse,clsBookListEntry[] arrFaculty) {
		//<!-- INIT_CONTROLS
		super(strTitle, false, true, false, true);
		setLocation(70, 74);
		setSize(525,352);
		setVisible(false);

		jpnlMain.setLayout(null);
		jpnlMain.setBackground(Color.white);
		getContentPane().add(BorderLayout.CENTER, jpnlMain);

		jlblSortby.setText("Sort By:");
		jlblSortby.setForeground(Color.black);
		jlblSortby.setBounds(12,20,50,20);
		jlblSortby.setFont(new Font("Dialog", Font.PLAIN, 12));
		jpnlMain.add(jlblSortby);

		jlstSortby.setFont(new Font("Dialog", Font.PLAIN, 12));
		jlstSortby.setSelectionBackground(new Color(204,204,255));
		jlstSortby.setToolTipText("Select a sorting criteria from the list");
		jlstSortby.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlstSortby.setBorder(new javax.swing.border.LineBorder(new Color(204,204,255)));
		jlstSortby.setBounds(60,20,110,40);
		jpnlMain.add(jlstSortby);

		// Creating data to put inside the JList
		String[] arrListData = {"  Course Number", "  Text Book Name"};
		jlstSortby.setListData(arrListData);
		jlstSortby.setSelectedIndex(0);

		jbtnGO.setText("GO");
		jbtnGO.setMnemonic((int)'G');
		jbtnGO.setFont(new Font("Dialog", Font.PLAIN, 11));
		jbtnGO.setBounds(432,40,70,20);
		jbtnGO.setToolTipText("Click here to perform the sorting");
		jpnlMain.add(jbtnGO);

		// Creating data to put inside the JTable
		clsFileHdl objFileHdl = new clsFileHdl();
		int intArrSize 		 = objFileHdl.mtd_getLastIndex(arrFaculty);
		int intTmp				 = 0;
		arrRowData 				 = new String[intArrSize][9];
		for(int i=0; i < intArrSize; i++) {
			arrRowData[i][0]   = arrFaculty[i].getCourseNo();
			// Get the Course Name
			intTmp				 = objFileHdl.mtd_SearchCourse(arrCourse,arrFaculty[i].getCourseNo(),0);
			arrRowData[i][1]   = arrCourse[intTmp].getCourseName();
			arrRowData[i][2]   = arrFaculty[i].getBookNo();
			// Get the Book details
			intTmp				 = objFileHdl.mtd_SearchBook(arrBook,arrFaculty[i].getBookNo(),0);
			arrRowData[i][3]   = arrBook[intTmp].getBookName();
			arrRowData[i][4]   = arrBook[intTmp].getPublisher();
			arrRowData[i][5]   = objFileHdl.mtd_formatValue(arrBook[intTmp].getEdition(),
																			"###");
			arrRowData[i][6]   = objFileHdl.mtd_formatValue(arrBook[intTmp].getYear(),
																			"####");
			arrRowData[i][7]   = objFileHdl.mtd_formatValue(arrBook[intTmp].getPrice(),
																			"RM#,###.00");
			arrRowData[i][8]   = String.valueOf(arrBook[intTmp].getType());
		}

		// Sort by Course Number; default
		objSortArray = new clsSortArray();
		objSortArray.setSortColumn(0);
		Arrays.sort(arrRowData, objSortArray);

		// Creating JTable
		jtblFaculty = mtd_createJTblFaculty();
		jspFaculty  = new JScrollPane(jtblFaculty);
		jspFaculty.setBounds(12,70,490,230);
		jpnlMain.add(jspFaculty);

		//<!-- REGISTER_LISTENERS
		jbtnGO.addActionListener(this);
		//-->
	}

	// Refresh the data inside JTable
	public void mtd_refresh() {
		jspFaculty.getViewport().remove(jtblFaculty);
		jspFaculty.getViewport().add(mtd_createJTblFaculty());
		this.repaint();

	}

//-------------------------------------------- FacultyBookList Listener
	public void actionPerformed(ActionEvent event) {
		String strSelected = jlstSortby.getSelectedValue().toString().trim();
		if(strSelected.equalsIgnoreCase("Course Number"))
			objSortArray.setSortColumn(0);
		else
			objSortArray.setSortColumn(3);
		Arrays.sort(arrRowData, objSortArray);
		mtd_refresh();
	}

//-------------------------------------------- FacultyBookList Sort Array method
	public class clsSortArray implements Comparator {
		private int sortColumn = 0;
		public void setSortColumn(int intIn) { sortColumn = intIn; }
		public int compare(Object o1, Object o2) {
			String[] s1 = (String[])o1;
			String[] s2 = (String[])o2;
			// There will no elements at position sortColumn is
			// not null nor out of bounds.
			return s1[sortColumn].compareTo(s2[sortColumn]);
	  	}
	}

//-------------------------------------------- FacultyBookList Create JTable
	private JTable mtd_createJTblFaculty() {
		String[] arrColName = {"Course No","Course Name","Text Book No","Text Book Name",
									  "Publisher","Edition","Year","Retail Price","Type"};
		dtmFaculty  = new DefaultTableModel(arrRowData, arrColName);
		jtblFaculty = new JTable(dtmFaculty) {
			public boolean isCellEditable(int iRow, int iCol) {
				return false; // Disable all column
			}
		};
		// Sizing the table column
		(jtblFaculty.getColumnModel().getColumn(1)).setPreferredWidth(196);
		(jtblFaculty.getColumnModel().getColumn(2)).setPreferredWidth(80);
		(jtblFaculty.getColumnModel().getColumn(3)).setPreferredWidth(196);
		(jtblFaculty.getColumnModel().getColumn(4)).setPreferredWidth(196);
		jtblFaculty.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtblFaculty.setRowHeight(20);
		jtblFaculty.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return jtblFaculty;
	}

	//<!-- DECLARE_CONTROLS/VARIABLES
	private String[][] arrRowData;
	private clsSortArray objSortArray;
	private DefaultTableModel dtmFaculty;
	private JScrollPane jspFaculty = new JScrollPane();
	private JPanel jpnlMain   	    = new JPanel();
	private JLabel jlblSortby 	    = new JLabel();
	private JList  jlstSortby 	    = new JList();
	private JTable jtblFaculty     = new JTable();
	private JButton jbtnGO			 = new JButton();
	//-->
}