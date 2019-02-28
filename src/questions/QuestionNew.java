package questions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mains.MCQConnection;
import subjects.SubjectNew;
import javax.swing.border.MatteBorder;

public class QuestionNew extends JDialog implements KeyListener {
	private static final long serialVersionUID = 1L;
	ArrayList<String>list=new ArrayList<String>();
	private JPanel panel1,panel2;
	private JComboBox <String>tsubject, tans;
	private JEditorPane tquestion, toption1, toption2, toption3, toption4;
	private JButton add, cancel, newsubject;
	String subject,question, option1, option2, option3, option4,correctAns;
	int subid,qid; // Subject ID, Question ID
	private JButton reset;
	public QuestionNew() {
		setTitle("New Question");
		getContentPane().setLayout(null);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(new Color(152, 251, 152));
		panel2.setBounds(0, 110, 700, 268);
		getContentPane().add(panel2);

		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setSize(80, 28);
		lblQuestion.setLocation(299, 0);
		panel2.add(lblQuestion);
		lblQuestion.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 15));

		toption1 = new JEditorPane();
		toption1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toption1.setBounds(98, 98, 592, 26);

		JLabel lblOption = new JLabel("Option A");
		lblOption.setBounds(7, 98, 81, 31);
		lblOption.setHorizontalAlignment(SwingConstants.LEFT);
		lblOption.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblOptionB = new JLabel("Option B");
		lblOptionB.setHorizontalAlignment(SwingConstants.LEFT);
		lblOptionB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOptionB.setBounds(7, 128, 81, 31);

		toption2 = new JEditorPane();
		toption2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toption2.setBounds(98, 128, 592, 26);

		JLabel lblOptionC = new JLabel("Option C");
		lblOptionC.setHorizontalAlignment(SwingConstants.LEFT);
		lblOptionC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOptionC.setBounds(7, 158, 81, 31);

		toption3 = new JEditorPane();
		toption3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toption3.setBounds(98, 158, 592, 26);

		JLabel lblOptionD = new JLabel("Option D");
		lblOptionD.setHorizontalAlignment(SwingConstants.LEFT);
		lblOptionD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOptionD.setBounds(7, 188, 81, 31);

		toption4 = new JEditorPane();
		toption4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toption4.setBounds(98, 188, 592, 26);

		JLabel lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorrectAnswer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCorrectAnswer.setBounds(7, 226, 137, 31);

		tans = new JComboBox<String>();
		tans.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D"}));
		tans.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tans.setBounds(139, 225, 80, 30);

		add = new JButton("Add");
		add.setMnemonic('a');
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tr=check();
				if(tr) {
					addQuestion();
				}
			}
		});
		add.setBounds(464, 225, 89, 28);
		panel2.add(add);
		add.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel2.add(toption1);
		panel2.add(lblOption);
		panel2.add(lblOptionB);
		panel2.add(toption2);
		panel2.add(lblOptionC);
		panel2.add(toption3);
		panel2.add(lblOptionD);
		panel2.add(toption4);
		panel2.add(lblCorrectAnswer);
		panel2.add(tans);

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setMnemonic('c');
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		cancel.setBounds(591, 225, 99, 28);
		panel2.add(cancel);

		reset = new JButton("Reset");
		reset.setMnemonic('r');
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		reset.setFont(new Font("Tahoma", Font.BOLD, 16));
		reset.setBounds(342, 225, 89, 28);
		panel2.add(reset);
		
				tquestion = new JEditorPane();
				tquestion.setForeground(new Color(0, 0, 139));
				tquestion.setBounds(10, 27, 680, 62);
				panel2.add(tquestion);
				tquestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
						tquestion.addKeyListener(this);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(new Color(173, 216, 230));
		panel1.setBounds(0, 0, 700, 114);
		getContentPane().add(panel1);

		JLabel lblNewQuestionAddition = new JLabel("New Question Addition");
		lblNewQuestionAddition.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblNewQuestionAddition.setSize(202, 30);
		lblNewQuestionAddition.setLocation(209, 11);
		panel1.add(lblNewQuestionAddition);
		lblNewQuestionAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewQuestionAddition.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblSelectSubject = new JLabel("Select Subject");
		lblSelectSubject.setSize(114, 36);
		lblSelectSubject.setLocation(0, 52);
		panel1.add(lblSelectSubject);
		lblSelectSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 15));

		tsubject = new JComboBox<String>();
		tsubject.setSize(436, 30);
		tsubject.setLocation(114, 55);
		panel1.add(tsubject);
		tsubject.setFont(new Font("Tahoma", Font.PLAIN, 16));

		newsubject = new JButton("New Subject");
		newsubject.setMnemonic('n');
		newsubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SubjectNew();
				fillSubjects();
			}
		});
		newsubject.setFont(new Font("Tahoma", Font.BOLD, 15));
		newsubject.setBounds(560, 52, 130, 36);
		panel1.add(newsubject);
		setSize(716, 422);
		setLocation(100, 80);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		toption1.addKeyListener(this);
		toption2.addKeyListener(this);
		toption3.addKeyListener(this);
		toption4.addKeyListener(this);

		fillSubjects();
		setModal(true);
		setVisible(true);
	}

	public static void main(String[] args) {
		new QuestionNew();
	}
	void fillSubjects() {
		tsubject.removeAllItems();
		String st="select * from subjects order by subject";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("subid");
				String sub=rs.getString("subject");
				list.add(id+"****"+sub);
				tsubject.addItem(sub);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		tquestion.requestFocusInWindow();
	}
	boolean check() {
		subject=tsubject.getSelectedItem().toString();
		question=tquestion.getText().trim();
		option1=toption1.getText().trim();
		option2=toption2.getText().trim();
		option3=toption3.getText().trim();
		option4=toption4.getText().trim();
		correctAns=tans.getSelectedItem().toString();
		if(question.trim().length()<10) {
			errorMessage("Question Not Proper",tquestion); return false;
		}
		else if(option1.trim().length()==0) {
			errorMessage("Option 1 Not given",toption1); return false;
		}
		else if(option2.trim().length()==0) {
			errorMessage("Option 2 Not given",toption2); return false;
		}
		else if(option3.trim().length()==0) {
			errorMessage("Option 3 Not given",toption3); return false;
		}
		if(option4.trim().length()==0) {
			errorMessage("Option 4 Not given",toption4); return false;
		}
		return true;
	}
	void errorMessage(String message, JEditorPane pane) {
		JOptionPane.showMessageDialog(null, message,"MCQ",JOptionPane.ERROR_MESSAGE);
		pane.requestFocusInWindow();
	}
	void addQuestion() {
		//		getting ID from the subject selected from ArrayList - list
		Iterator<String>it=list.iterator();
		while(it.hasNext()) {
			String item=it.next();
			int start=item.indexOf("****")+4;
			String subs=item.substring(start);
			if(subject.equals(subs)) {
				System.out.println("Selected Subject = "+subs);
				subid=Integer.parseInt(item.substring(0, item.indexOf("****")));
				System.out.println("Selected ID = "+subid);
				break;
			}
		}
		generateID();
		String st="insert into QuestionBank(qid,subid,question,option1,option2,option3,option4,"
				+ "CorrectAns) values(?,?,?,?,?,?,?,?)";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setInt(1, qid);
			ps.setInt(2, subid);
			ps.setString(3, question);
			ps.setString(4, option1);
			ps.setString(5, option2);
			ps.setString(6, option3);
			ps.setString(7, option4);
			ps.setString(8, correctAns);
			ps.executeUpdate();
			int more=JOptionPane.showConfirmDialog(null, "New Question Added\n Add More","MCQ",JOptionPane.YES_NO_OPTION);
			if(more==0) {
				clear();
			}
			else {
				dispose();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	void generateID() {
		String st="select max(qid) from QuestionBank";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			rs.next();
			qid=rs.getInt(1);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		++qid;
	}
	void clear() {
		tsubject.setSelectedIndex(0);
		tans.setSelectedIndex(0);
		toption1.setText("");
		toption2.setText("");
		toption3.setText("");
		toption4.setText("");
		tquestion.setText("");
		tsubject.requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		Object ob=e.getSource();
		if(ob==tquestion) 
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				toption1.requestFocusInWindow();
		if(ob==toption1)
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				toption2.requestFocusInWindow();
		if(ob==toption2)
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				toption3.requestFocusInWindow();
		if(ob==toption3)
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				toption4.requestFocusInWindow();
		if(ob==toption4)
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
				add.requestFocusInWindow();
	}
}
