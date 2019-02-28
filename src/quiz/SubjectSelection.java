package quiz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import mains.MCQConnection;

public class SubjectSelection extends JDialog {
	private static final long serialVersionUID = 1L;
	private JComboBox <String>tstream;
	private JButton add, cancel;
	String stid,name,gender,mobile,email,stream,subject;
	int semester,subid;
	private JTextField tstid;
	private JTextField tname;
	private JTextField tmobile;
	private JTextField temail;
	private JComboBox<String> tsemester;
	ButtonGroup bg=new ButtonGroup();
	private JRadioButton m;
	private JRadioButton f;
	private JComboBox<String> tsubject;
	public SubjectSelection() {
		setTitle("New Question");
		getContentPane().setLayout(null);

		JLabel lblNewQuestionAddition = new JLabel("Student Registration for Quiz");
		lblNewQuestionAddition.setBounds(123, 11, 282, 30);
		lblNewQuestionAddition.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblNewQuestionAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewQuestionAddition.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblSelectSubject = new JLabel("Registration No.");
		lblSelectSubject.setBounds(10, 66, 137, 28);
		lblSelectSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 15));

		tstream = new JComboBox<String>();
		tstream.setBackground(Color.WHITE);
		tstream.setForeground(new Color(0, 0, 205));
		tstream.setEnabled(false);
		tstream.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstream.setBounds(192, 327, 137, 30);
		tstream.setModel(new DefaultComboBoxModel<String>(new String[] {"BCA", "MCA", "B Tech (CS)", "B Tech (IT)", "B Sc IT", "M Sc IT"}));
		tstream.setFont(new Font("Tahoma", Font.BOLD, 16));

		add = new JButton("Register");
		add.setEnabled(false);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAtteptedOrNot();
			}
		});
		add.setBounds(48, 423, 109, 28);
		getContentPane().add(add);
		add.setMnemonic('a');
		add.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewQuestionAddition);
		getContentPane().add(lblSelectSubject);
		getContentPane().add(tstream);

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBounds(334, 423, 109, 28);
		getContentPane().add(cancel);
		cancel.setMnemonic('c');
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 162, 137, 28);
		getContentPane().add(lblName);
		
		tstid = new JTextField(stid);
		tstid.setBackground(Color.WHITE);
		tstid.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				stid=tstid.getText();
				boolean b=checkStudentInTable();
				if(!b)
					add.setEnabled(false);
				else
					add.setEnabled(true);
					
			}
			public void focusGained(FocusEvent fe) {
				add.setEnabled(false);
				reset();
			}
		});
		tstid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tstid.setBounds(192, 66, 251, 30);
		getContentPane().add(tstid);
		tstid.setColumns(10);
		
		tname = new JTextField();
		tname.setBackground(Color.WHITE);
		tname.setEditable(false);
		tname.setBorder(new LineBorder(new Color(0, 0, 255)));
		tname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tname.setColumns(10);
		tname.setBounds(192, 162, 316, 30);
		getContentPane().add(tname);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(10, 206, 137, 28);
		getContentPane().add(lblGender);
		
		m = new JRadioButton("Male");
		m.setForeground(Color.BLACK);
		m.setEnabled(false);
		m.setBorder(new LineBorder(new Color(0, 0, 255)));
		bg.add(m);
		m.setFont(new Font("Tahoma", Font.BOLD, 16));
		m.setBounds(192, 209, 89, 23);
		getContentPane().add(m);
		
		f = new JRadioButton("Female");
		f.setForeground(Color.BLACK);
		f.setEnabled(false);
		bg.add(f);
		f.setFont(new Font("Tahoma", Font.BOLD, 16));
		f.setBounds(333, 209, 89, 23);
		getContentPane().add(f);
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMobileNo.setBounds(10, 248, 137, 28);
		getContentPane().add(lblMobileNo);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 287, 137, 28);
		getContentPane().add(lblEmail);
		
		JLabel lblStream = new JLabel("Stream");
		lblStream.setHorizontalAlignment(SwingConstants.LEFT);
		lblStream.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStream.setBounds(10, 328, 137, 28);
		getContentPane().add(lblStream);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSemester.setBounds(10, 367, 137, 28);
		getContentPane().add(lblSemester);
		
		tsemester = new JComboBox<String>();
		tsemester.setForeground(new Color(0, 0, 205));
		tsemester.setEnabled(false);
		tsemester.setBorder(new LineBorder(new Color(0, 0, 255)));
		tsemester.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		tsemester.setFont(new Font("Tahoma", Font.BOLD, 16));
		tsemester.setBounds(192, 366, 137, 30);
		getContentPane().add(tsemester);
		
		tmobile = new JTextField();
		tmobile.setBackground(Color.WHITE);
		tmobile.setEditable(false);
		tmobile.setBorder(new LineBorder(new Color(0, 0, 255)));
		tmobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tmobile.setColumns(10);
		tmobile.setBounds(192, 246, 251, 30);
		getContentPane().add(tmobile);
		
		temail = new JTextField();
		temail.setBackground(Color.WHITE);
		temail.setEditable(false);
		temail.setBorder(new LineBorder(new Color(0, 0, 255)));
		temail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		temail.setColumns(10);
		temail.setBounds(192, 286, 251, 30);
		getContentPane().add(temail);
		
		JLabel lblSelectSubject_1 = new JLabel("Select Subject");
		lblSelectSubject_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectSubject_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelectSubject_1.setBounds(10, 118, 137, 28);
		getContentPane().add(lblSelectSubject_1);
		
		tsubject = new JComboBox<String>();
		tsubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tsubject.setBorder(new LineBorder(new Color(0, 0, 255)));
		tsubject.setBounds(192, 115, 251, 30);
		getContentPane().add(tsubject);
		setSize(543, 508);
		setLocation(100, 80);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SubjectSelection();
	}
	
	boolean checkStudentInTable() { // whether the student is present or not
		stid=stid.trim();
		if(stid.length()==0)
			return false;
		String st="select stid from students where stid=?";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, stid);
			ResultSet rs=ps.executeQuery();
			if(!(rs.next())) {
				JOptionPane.showMessageDialog(null, "Student Not Found","MCQ",JOptionPane.ERROR_MESSAGE);
				tstid.selectAll();
				tstid.requestFocusInWindow();
				return false;
			}
			else {
				fillSubjects();
				fillStudentInformation();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return true;
	}
	void fillSubjects() {
		tsubject.removeAllItems();
		String st="select * from subjects order by subject";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String sub=rs.getString("subject");
				tsubject.addItem(sub);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	boolean checkAtteptedOrNot() {
		if(stid.length()==0) {
			JOptionPane.showMessageDialog(null, "Student ID Not Given","MCQ",JOptionPane.ERROR_MESSAGE);
			tstid.requestFocusInWindow();
			return false;
		}
		subject=tsubject.getSelectedItem().toString();
		getSubjectID();
		System.out.println("Subject ID="+subid);
//		To check if number of questions in selected subject is less than
		Connection con=MCQConnection.connect();
		try {
			String cc="select count(*) from questionbank where subid=?";
			PreparedStatement pp=con.prepareStatement(cc);
			pp.setInt(1, subid);
			ResultSet tr=pp.executeQuery();
			tr.next();
			int count=tr.getInt(1);
			System.out.println("Row Count="+count);
			if(count < 10) {
				JOptionPane.showMessageDialog(null, "Question count is < 10 in Selected Subject","MCQ",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
//		Check End
		
		System.out.println("After getting subject ID");
		String st="select stid, Stream,subid, semester from results "
				+ "where stid=? and stream=? and subid=? and semester=?";
		
		try{
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, stid);
			ps.setString(2, stream);
			ps.setInt(4, semester);
			ps.setInt(3, subid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println("Already done");
				JOptionPane.showMessageDialog(null, "You have already attempted the quiz for the opted subject","MCQ",JOptionPane.ERROR_MESSAGE);
//				showResultsCoding
				dispose();
			}
			else {
				subject=tsubject.getSelectedItem().toString();
				name=tname.getText();
//				Quiz Preparation
				dispose();
				new QuizPreparation(stid, name, stream, subject, subid, semester);
				
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	void fillStudentInformation() {
		String st="select * from students where stid=?";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, stid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				tname.setText(rs.getString("StName"));
				if(rs.getString("Gender").equals("Male"))
					m.setSelected(true);
				else 
					f.setSelected(true);
				stream=rs.getString("StStream");
				semester=rs.getInt("semester");
				tmobile.setText(rs.getString("stmobile"));
				temail.setText(rs.getString("stemail"));
				tstream.setSelectedItem(stream);
				tsemester.setSelectedItem(semester);
				add.requestFocusInWindow();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	void getSubjectID() {
		String st="select subid from subjects where subject=?";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, subject);
			ResultSet rs=ps.executeQuery();
			rs.next();
			subid=rs.getInt("Subid");
			System.out.println("Subject ID: "+subid);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	void reset() {
		tsemester.setSelectedIndex(0);
		tstream.setSelectedIndex(0);
		tmobile.setText("");
		temail.setText("");
		tname.setText("");
		bg.clearSelection();
		tstid.setText("");
		tstid.requestFocusInWindow();
	}

}
