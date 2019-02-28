package quiz;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import mains.MCQConnection;
import results.Result;

public class QuizPage implements ItemListener, ActionListener 
{
	int timeCounter;
	JDialog dialog=new JDialog();
//	private static final long serialVersionUID = 1L;
	ButtonGroup bg=new ButtonGroup();
	String stid, stream, stname, subject;
	int subid,  semester;
	HashSet<Integer> hs;
	ResultSet rs;
	int w,h;
	int qno=1; //For Serial Number
	int qid; //Question ID from database
	private JTextField tstid,tname;
	private JTextField tstream;
	private JTextField tsemester;
	private JTextField tsubject;
	private JTextField tqno;
	private JTextField tqid;
	private JPanel panel3;
	private JPanel panel1;
	private JPanel panel2;
	private JRadioButton topt4;
	private JRadioButton topt3;
	private JRadioButton topt2;
	private JRadioButton topt1;
	private JEditorPane tquestion;
	private JLabel lblQuestionNo;
	private JLabel lblName;
	private JLabel lblSubject;
	private JLabel lblSemester;
	private JLabel lblStream;
	private JButton previous;
	private JButton next;
	private JButton submit;
	private JEditorPane tans1;
	private JEditorPane tans2;
	private JEditorPane tans3;
	private JEditorPane tans4;
	private JButton unselect;
	private JTextField txtThisWindowsWill;
	private JTextField ttime;
	public QuizPage(String stid, String stname,String stream, int subid, 
			String subject,int semester, HashSet<Integer> hs) {
		dialog.setTitle("Quiz");
		dialog.setSize(1000, 659);
		w=dialog.getWidth();
		h=dialog.getHeight();
		this.stid=stid;
		this.stname=stname;
		this.stream=stream;
		this.subid=subid;
		this.subject=subject;
		this.semester=semester;
		this.hs=hs;

		dialog.getContentPane().setLayout(null);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(new Color(176, 224, 230));
		panel1.setBounds(0, 0, w-16, 132);
		dialog.getContentPane().add(panel1);

		JLabel label = new JLabel("Registration No.");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(10, 11, 130, 28);
		panel1.add(label);

		tstid = new JTextField(stid);
		tstid.setEditable(false);
		tstid.setFont(new Font("Courier New", Font.BOLD, 20));
		tstid.setColumns(10);
		tstid.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstid.setBackground(new Color(127, 255, 212));
		tstid.setBounds(148, 11, 196, 30);
		panel1.add(tstid);

		tname = new JTextField(stname);
		tname.setFont(new Font("Courier New", Font.BOLD, 20));
		tname.setEditable(false);
		tname.setColumns(10);
		tname.setBorder(new LineBorder(new Color(0, 0, 255)));
		tname.setBackground(new Color(127, 255, 212));
		tname.setBounds(443, 11, 531, 30);
		panel1.add(tname);

		lblStream = new JLabel("Stream");
		lblStream.setHorizontalAlignment(SwingConstants.LEFT);
		lblStream.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStream.setBounds(10, 49, 74, 28);
		panel1.add(lblStream);

		tstream = new JTextField(stream);
		tstream.setFont(new Font("Courier New", Font.BOLD, 20));
		tstream.setEditable(false);
		tstream.setColumns(10);
		tstream.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstream.setBackground(new Color(127, 255, 212));
		tstream.setBounds(148, 49, 196, 30);
		panel1.add(tstream);

		lblSemester = new JLabel("Semester");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSemester.setBounds(354, 47, 81, 28);
		panel1.add(lblSemester);

		tsemester = new JTextField(""+semester);
		tsemester.setHorizontalAlignment(SwingConstants.CENTER);
		tsemester.setFont(new Font("Courier New", Font.BOLD, 20));
		tsemester.setEditable(false);
		tsemester.setColumns(10);
		tsemester.setBorder(new LineBorder(new Color(0, 0, 255)));
		tsemester.setBackground(new Color(127, 255, 212));
		tsemester.setBounds(441, 49, 74, 30);
		panel1.add(tsemester);

		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(354, 11, 81, 28);
		panel1.add(lblName);

		lblSubject = new JLabel("Subject");
		lblSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubject.setBounds(10, 88, 74, 28);
		panel1.add(lblSubject);

		tsubject = new JTextField(subject);
		tsubject.setFont(new Font("Courier New", Font.BOLD, 20));
		tsubject.setEditable(false);
		tsubject.setColumns(10);
		tsubject.setBorder(new LineBorder(new Color(0, 0, 255)));
		tsubject.setBackground(new Color(127, 255, 212));
		tsubject.setBounds(148, 88, 489, 30);
		panel1.add(tsubject);
		
		JLabel lblTime = new JLabel("Time : ");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTime.setBounds(641, 49, 81, 28);
		panel1.add(lblTime);
		
		ttime = new JTextField("0");
		ttime.setHorizontalAlignment(SwingConstants.CENTER);
		ttime.setFont(new Font("Courier New", Font.BOLD, 20));
		ttime.setEditable(false);
		ttime.setColumns(10);
		ttime.setBorder(new LineBorder(new Color(0, 0, 255)));
		ttime.setBackground(new Color(127, 255, 212));
		ttime.setBounds(697, 49, 74, 30);
		panel1.add(ttime);
		
		txtThisWindowsWill = new JTextField();
		txtThisWindowsWill.setText("Windows will automatically close after 5 minutes");
		txtThisWindowsWill.setForeground(Color.RED);
		txtThisWindowsWill.setFont(new Font("Courier New", Font.BOLD, 20));
		txtThisWindowsWill.setEditable(false);
		txtThisWindowsWill.setColumns(10);
		txtThisWindowsWill.setBorder(new LineBorder(new Color(0, 0, 255)));
		txtThisWindowsWill.setBackground(new Color(127, 255, 212));
		txtThisWindowsWill.setBounds(529, 49, 445, 30);
//		panel1.add(txtThisWindowsWill);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(new Color(250, 240, 230));
		panel2.setBounds(0, 131, 984, 531);
		dialog.getContentPane().add(panel2);

		lblQuestionNo = new JLabel("Question No.");
		lblQuestionNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestionNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuestionNo.setBounds(10, 11, 109, 28);
		panel2.add(lblQuestionNo);

		tqid = new JTextField("");
		tqid.setBorder(null);
		tqid.setHorizontalAlignment(SwingConstants.CENTER);
		tqid.setFont(new Font("Courier New", Font.BOLD, 20));
		tqid.setEditable(false);
		tqid.setColumns(10);
		tqid.setBackground(new Color(250, 240, 230));
		tqid.setForeground(new Color(250, 240, 230));
		tqid.setBounds(195, 11, 55, 30);
		panel2.add(tqid);

		tquestion = new JEditorPane();
		tquestion.setFont(new Font("Courier New", Font.PLAIN, 20));
		tquestion.setBorder(new LineBorder(new Color(0, 0, 139)));
		tquestion.setEditable(false);
		tquestion.setBackground(new Color(250, 235, 215));
		tquestion.setBounds(10, 49, 964, 107);
		panel2.add(tquestion);

		panel3 = new JPanel();
		panel3.setBounds(0, 444, 984, 52);
		panel2.add(panel3);
		panel3.setBackground(new Color(147, 112, 219));
		panel3.setLayout(null);

		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setMnemonic('a');
		submit.setFont(new Font("Tahoma", Font.BOLD, 16));
		submit.setBounds(865, 11, 109, 28);
		panel3.add(submit);

		next = new JButton("Next");
		next.setMnemonic('a');
		next.setFont(new Font("Tahoma", Font.BOLD, 16));
		next.setBounds(733, 11, 109, 28);
		panel3.add(next);

		previous = new JButton("Previous");
		previous.setMnemonic('a');
		previous.setFont(new Font("Tahoma", Font.BOLD, 16));
		previous.setBounds(600, 11, 109, 28);
		panel3.add(previous);

		next.addActionListener(this);
		previous.addActionListener(this);

		JLabel lblA = new JLabel("A.");
		lblA.setHorizontalAlignment(SwingConstants.LEFT);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblA.setBounds(10, 183, 23, 28);
		panel2.add(lblA);

		topt1 = new JRadioButton("");
		topt1.setOpaque(false);
		topt1.setFont(new Font("Courier New", Font.PLAIN, 20));
		topt1.setBounds(28, 186, 23, 23);
		panel2.add(topt1);

		topt2 = new JRadioButton("");
		topt2.setOpaque(false);
		topt2.setFont(new Font("Courier New", Font.PLAIN, 20));
		topt2.setBounds(28, 259, 23, 23);
		panel2.add(topt2);

		JLabel lblB = new JLabel("B.");
		lblB.setHorizontalAlignment(SwingConstants.LEFT);
		lblB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblB.setBounds(10, 255, 23, 28);
		panel2.add(lblB);

		topt4 = new JRadioButton("");
		topt4.setOpaque(false);
		topt4.setFont(new Font("Courier New", Font.PLAIN, 20));
		topt4.setBounds(28, 395, 23, 23);
		panel2.add(topt4);

		JLabel lblD = new JLabel("D.");
		lblD.setHorizontalAlignment(SwingConstants.LEFT);
		lblD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblD.setBounds(10, 392, 23, 28);
		panel2.add(lblD);

		topt3 = new JRadioButton("");
		topt3.setOpaque(false);
		topt3.setFont(new Font("Courier New", Font.PLAIN, 20));
		topt3.setBounds(28, 325, 23, 23);
		panel2.add(topt3);

		JLabel lblC = new JLabel("C.");
		lblC.setHorizontalAlignment(SwingConstants.LEFT);
		lblC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblC.setBounds(10, 322, 23, 28);
		panel2.add(lblC);

		tqno = new JTextField(""+qno);
		tqno.setHorizontalAlignment(SwingConstants.CENTER);
		tqno.setFont(new Font("Courier New", Font.BOLD, 20));
		tqno.setEditable(false);
		tqno.setColumns(10);
		tqno.setBorder(new LineBorder(new Color(0, 0, 255)));
		tqno.setBackground(new Color(250, 235, 215));
		tqno.setBounds(117, 9, 55, 30);
		panel2.add(tqno);
		dialog.setModal(true);
		previous.setEnabled(false);
		
		unselect = new JButton("De-Select");
		unselect.addActionListener(this);
		unselect.setMnemonic('a');
		unselect.setFont(new Font("Tahoma", Font.BOLD, 16));
		unselect.setBounds(451, 11, 120, 28);
		panel3.add(unselect);

		tans1 = new JEditorPane();
		tans1.setBackground(new Color(240, 255, 255));
		tans1.setEditable(false);
		tans1.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		tans1.setFont(new Font("Courier New", Font.PLAIN, 20));
		tans1.setBounds(52, 167, 922, 60);
		panel2.add(tans1);

		tans2 = new JEditorPane();
		tans2.setBackground(new Color(240, 255, 255));
		tans2.setFont(new Font("Courier New", Font.PLAIN, 20));
		tans2.setEditable(false);
		tans2.setBounds(52, 238, 922, 60);
		panel2.add(tans2);

		tans3 = new JEditorPane();
		tans3.setBackground(new Color(240, 255, 255));
		tans3.setFont(new Font("Courier New", Font.PLAIN, 20));
		tans3.setEditable(false);
		tans3.setBounds(52, 307, 922, 60);
		panel2.add(tans3);

		tans4 = new JEditorPane();
		tans4.setBackground(new Color(240, 255, 255));
		tans4.setFont(new Font("Courier New", Font.PLAIN, 20));
		tans4.setEditable(false);
		tans4.setBounds(52, 374, 922, 60);
		panel2.add(tans4);

		bg.add(topt1);
		bg.add(topt2);
		bg.add(topt3);
		bg.add(topt4);

		topt1.addItemListener(this);
		topt2.addItemListener(this);
		topt3.addItemListener(this);
		topt4.addItemListener(this);

		dialog.setLocationRelativeTo(null);
		prepareResultSet();
		showQuestion();
//		For disposing after stipulated time
		ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
		s.schedule(new Runnable() {
		    public void run() {
		    	dialog.setVisible(false); //should be invoked on the EDT
		    	dialog.dispose();
		    }
		}, 300, TimeUnit.SECONDS);

//		For disposing after stipulated time end
		
			
//		For Showing Seconds
//		int timeCounter = 0;
//        new Timer(1000, this::updateGUI).start();
//        new Timer(1000, this::updateGUI).start();
        dialog.setVisible(true);
//		For Showing Seconds End
	}
    void updateGUI(java.awt.event.ActionEvent e) {
        ttime.setText("" + (++timeCounter));
        System.out.println("Timer: "+timeCounter);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		if(ob==next || ob==previous) {
			try {
				if(ob==next) {
					++qno;
					tqno.setText(""+qno);
					rs.next();
					showQuestion();
				}
				else if(ob==previous) {
					--qno;
					tqno.setText(""+qno);
					rs.previous();
					showQuestion();
				}
				int row=rs.getRow();
				if(row>1 && row<10) {
					next.setEnabled(true);
					previous.setEnabled(true);
				}
				else if(row==1) {
					previous.setEnabled(false);
				}
				else if(row==10) {
					next.setEnabled(false);
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else if(ob==submit) {
			saveResult();
		}
		else if(ob==unselect) {
			bg.clearSelection();
		}
	}
	void showQuestion() {
		bg.clearSelection();
		try {
			qid=rs.getInt("qid");
			tqid.setText(""+qid);
			tquestion.setText(rs.getString("question"));
			tans1.setText(rs.getString("option1"));
			tans2.setText(rs.getString("option2"));
			tans3.setText(rs.getString("option3"));
			tans4.setText(rs.getString("option4"));
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		getSelectionIfAlreadySelected();
	}
	void prepareResultSet() {
		String st="select * from questions";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			rs=ps.executeQuery();
			rs.next();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		String sel=null;
		if(topt1.isSelected())
			sel="A";
		else if(topt2.isSelected())
			sel="B";
		else if(topt3.isSelected())
			sel="C";
		else if(topt4.isSelected())
			sel="D";
		qid=Integer.parseInt(tqid.getText());
		if(sel!=null) {
			String st="update questions set UserAns=? where qid=?";
			Connection con=MCQConnection.connect();
			try {
				PreparedStatement ps=con.prepareStatement(st);
				ps.setString(1, sel);
				ps.setInt(2, qid);
				ps.executeUpdate();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	void getSelectionIfAlreadySelected() {
		qid=Integer.parseInt(tqid.getText());
		String st="select userans from questions where qid=? and userans is not null";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setInt(1, qid);
			ResultSet rs3=ps.executeQuery();
			if(rs3.next()) {
				String ab=rs3.getString("UserAns");
				System.out.println("User Answer="+ab);
				if(ab.equalsIgnoreCase("A"))
					topt1.setSelected(true);
				else if(ab.equalsIgnoreCase("B"))
					topt2.setSelected(true);
				else if(ab.equalsIgnoreCase("C"))
					topt3.setSelected(true);
				else if(ab.equalsIgnoreCase("D"))
					topt4.setSelected(true);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	void saveResult() {
		String st="select * from questions";
		String st2="insert into results(StID, Stream, Semester, SubID,QID,CorrectAns,UserAns)"
				+ " values(?,?,?,?,?,?,?)";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			PreparedStatement ps2=con.prepareStatement(st2);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ps2.setString(1, stid);
				ps2.setString(2, stream);
				ps2.setInt(3, semester);
				ps2.setInt(4, subid);
				ps2.setInt(5, rs.getInt("qid"));
				ps2.setString(6, rs.getString("CorrectAns"));
				ps2.setString(7, rs.getString("UserAns"));
				ps2.addBatch();
			}
			ps2.executeBatch();
			JOptionPane.showMessageDialog(null, "Successfully Submitted","MCQ",JOptionPane.OK_OPTION);
			dialog.dispose();
			new Result(stid);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}