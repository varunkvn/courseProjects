import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class TestJDBC extends JFrame implements ActionListener
{

	JTextField textFieldId;
	JTextField textFieldName;
	JTextField textFieldFatherName;
	JTextField textFieldIdCardNo;
	JTextField textFieldDateOfBirth;
	JTextField textFieldAddress;
	JTextField textFieldEmail;


	JLabel labelId;
	JLabel labelName;
	JLabel labelFatherName;
	JLabel labelIdCardNo;
	JLabel labelDateOfBirth;
	JLabel labelAddress;
	JLabel labelEmail;

	JLabel labelTitle;


	JButton buttonNext;
	JButton buttonPrevious;
	JButton buttonNew;
	JButton buttonInsert;
	JButton buttonDelete;
	JButton buttonExit;


	Cursor cursor;
	Container c = getContentPane();

	Statement st;
	ResultSet rs;


	TestJDBC()
	{
		super("Data Base");
		setBounds(140,50,525,450);
		c.setLayout(null);
		cursor = new Cursor(cursor.HAND_CURSOR);
		c.setBackground(new Color(214,35,147));


		textFieldId = new JTextField();
		textFieldName = new JTextField();
		textFieldFatherName = new JTextField();
		textFieldIdCardNo = new JTextField();
		textFieldDateOfBirth = new JTextField();
		textFieldAddress = new JTextField();
		textFieldEmail = new JTextField();



		labelId = new JLabel("Member ID");
		labelName = new JLabel("Name");
		labelFatherName = new JLabel("Father Name");
		labelIdCardNo = new JLabel("ID Card No.");
		labelDateOfBirth = new JLabel("Date Of Birth");
		labelAddress = new JLabel("Address");
		labelEmail = new JLabel("E-mail");
		labelTitle = new JLabel("Library Member's Information");
		labelTitle.setFont(new Font("Georgia",1,25));


		labelId.setBounds(50,100,140,20);
		labelName.setBounds(50,130,140,20);
		labelFatherName.setBounds(50,160,140,20);
		labelIdCardNo.setBounds(50,190,140,20);
		labelDateOfBirth.setBounds(50,220,140,20);
		labelAddress.setBounds(50,250,140,20);
		labelEmail.setBounds(50,280,140,20);
		labelTitle.setBounds(53,0,450,50);



		textFieldId.setBounds(170,100,160,20);
		textFieldName.setBounds(170,130,160,20);
		textFieldFatherName.setBounds(170,160,160,20);
		textFieldIdCardNo.setBounds(170,190,160,20);
		textFieldDateOfBirth.setBounds(170,220,160,20);
		textFieldAddress.setBounds(170,250,160,20);
		textFieldEmail.setBounds(170,280,160,20);


		textFieldId.setBackground(new Color(240,164,211));
		textFieldName.setBackground(new Color(240,164,211));
		textFieldFatherName.setBackground(new Color(240,164,211));
		textFieldIdCardNo.setBackground(new Color(240,164,211));
		textFieldDateOfBirth.setBackground(new Color(240,164,211));
		textFieldAddress.setBackground(new Color(240,164,211));
		textFieldEmail.setBackground(new Color(240,164,211));


		textFieldId.setEditable(false);
		textFieldName.setEditable(false);
		textFieldFatherName.setEditable(false);
		textFieldIdCardNo.setEditable(false);
		textFieldDateOfBirth.setEditable(false);
		textFieldAddress.setEditable(false);
		textFieldEmail.setEditable(false);


		buttonNext = new JButton("Next");
		buttonPrevious = new JButton("Previous");
		buttonNew = new JButton("New");
		buttonInsert = new JButton("Insert");
		buttonDelete = new JButton("Delete");
		buttonExit = new JButton("Exit");


		buttonNext.setBounds(370,130,100,20);
		buttonPrevious.setBounds(370,160,100,20);
		buttonNew.setBounds(370,190,100,20);
		buttonInsert.setBounds(370,220,100,20);
		buttonDelete.setBounds(370,250,100,20);
		buttonExit.setBounds(200,320,100,20);


		c.add(buttonNext);
		c.add(buttonPrevious);
		c.add(buttonNew);
		c.add(buttonInsert);
		c.add(buttonDelete);
		c.add(buttonExit);


		c.add(labelId);
		c.add(labelName);
		c.add(labelFatherName);
		c.add(labelIdCardNo);
		c.add(labelDateOfBirth);
		c.add(labelAddress);
		c.add(labelEmail);
		c.add(labelTitle);


		c.add(textFieldId);
		c.add(textFieldName);
		c.add(textFieldFatherName);
		c.add(textFieldIdCardNo);
		c.add(textFieldDateOfBirth);
		c.add(textFieldAddress);
		c.add(textFieldEmail);


		buttonNext.addActionListener(this);
		buttonPrevious.addActionListener(this);
		buttonNew.addActionListener(this);
		buttonInsert.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonExit.addActionListener(this);


		buttonNext.setBackground(new Color(231,109,185));
		buttonPrevious.setBackground(new Color(231,109,185));
		buttonNew.setBackground(new Color(231,109,185));
		buttonInsert.setBackground(new Color(231,109,185));
		buttonDelete.setBackground(new Color(231,109,185));
		buttonExit.setBackground(new Color(231,109,185));


		buttonNext.setCursor(cursor);
		buttonPrevious.setCursor(cursor);
		buttonNew.setCursor(cursor);
		buttonInsert.setCursor(cursor);
		buttonDelete.setCursor(cursor);
		buttonExit.setCursor(cursor);


		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String dsnSource = "jdbc:odbc:asim";
			Connection con = DriverManager.getConnection(dsnSource);
			String query = "select * from MemberInfo";
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(query);
		}

		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}

		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}


		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}



	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();

		try
		{
			if(source == buttonNext)
			{
				textFieldId.setEditable(false);
				textFieldName.setEditable(false);
				textFieldFatherName.setEditable(false);
				textFieldIdCardNo.setEditable(false);
				textFieldDateOfBirth.setEditable(false);
				textFieldAddress.setEditable(false);
				textFieldEmail.setEditable(false);


				rs.next();
				textFieldId.setText(rs.getInt("Member_Id") + "");
				textFieldName.setText(rs.getString("Member_Name"));
				textFieldFatherName.setText(rs.getString("Member_Father_Name") + "");
				textFieldIdCardNo.setText(rs.getString("Member_IdCardNo"));
				textFieldDateOfBirth.setText(rs.getString("Member_DateOfBirth") + "");
				textFieldAddress.setText(rs.getString("Member_Address") + "");
				textFieldEmail.setText(rs.getString("Member_Email") + "");

			}
		}
		catch(SQLException sqle)
		{
			JOptionPane.showMessageDialog(this,"No Next Record Found....","Record",JOptionPane.INFORMATION_MESSAGE);
		}

		try
		{
			if(source == buttonPrevious)
			{
				textFieldId.setEditable(false);
				textFieldName.setEditable(false);
				textFieldFatherName.setEditable(false);
				textFieldIdCardNo.setEditable(false);
				textFieldDateOfBirth.setEditable(false);
				textFieldAddress.setEditable(false);
				textFieldEmail.setEditable(false);


				rs.previous();
				textFieldId.setText(rs.getInt("Member_Id") + "");
				textFieldName.setText(rs.getString("Member_Name"));
				textFieldFatherName.setText(rs.getString("Member_Father_Name") + "");
				textFieldIdCardNo.setText(rs.getString("Member_IdCardNo"));
				textFieldDateOfBirth.setText(rs.getString("Member_DateOfBirth") + "");
				textFieldAddress.setText(rs.getString("Member_Address") + "");
				textFieldEmail.setText(rs.getString("Member_Email") + "");
			}
		}
		catch(SQLException sqle)
		{
			JOptionPane.showMessageDialog(this,"No Previous Record Found....","Record",JOptionPane.INFORMATION_MESSAGE);
		}

		if(source == buttonNew)
		{
			textFieldId.setEditable(true);
			textFieldName.setEditable(true);
			textFieldFatherName.setEditable(true);
			textFieldIdCardNo.setEditable(true);
			textFieldDateOfBirth.setEditable(true);
			textFieldAddress.setEditable(true);
			textFieldEmail.setEditable(true);

			textFieldId.setText(null);
			textFieldName.setText(null);
			textFieldFatherName.setText(null);
			textFieldIdCardNo.setText(null);
			textFieldDateOfBirth.setText(null);
			textFieldAddress.setText(null);
			textFieldEmail.setText(null);
		}

		try
		{
			if(source == buttonInsert)
			{
				String values = textFieldId.getText() + ",'" + textFieldName.getText() + "','" + textFieldFatherName.getText() + "','" + textFieldIdCardNo.getText() + "','" + textFieldDateOfBirth.getText() + "','" + textFieldAddress.getText() + "','" + textFieldEmail.getText() + "'";
				System.out.println(values);

				st.executeUpdate("insert into MemberInfo values(" + values + ")");

				rs = st.executeQuery("select * from MemberInfo");

				textFieldId.setText(null);
				textFieldName.setText(null);
				textFieldFatherName.setText(null);
				textFieldIdCardNo.setText(null);
				textFieldDateOfBirth.setText(null);
				textFieldAddress.setText(null);
				textFieldEmail.setText(null);
			}
		}
		catch(SQLException sqle)
		{
			JOptionPane.showMessageDialog(this,"Couldn't Enter the Record....","Insertion Of Record",JOptionPane.INFORMATION_MESSAGE);
		}

		try
		{
			if(source == buttonDelete)
			{
				String MemberId = textFieldId.getText();

				st.executeUpdate("delete from MemberInfo where Member_Id = " + MemberId);

				JOptionPane.showMessageDialog(this,"Record Deleted...","Confirmation",JOptionPane.INFORMATION_MESSAGE);

				rs = st.executeQuery("select * from MemberInfo");
			}
		}
		catch(SQLException sqle)
		{
			JOptionPane.showMessageDialog(this,"Couldn't Delete the Record....","Deletion Of Record",JOptionPane.INFORMATION_MESSAGE);
		}


		if(source == buttonExit)
		{
			System.exit(0);
		}
	}

	public static void main(String args[])
	{
		TestJDBC dataBase = new TestJDBC();
	}
}