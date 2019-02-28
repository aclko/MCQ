package mains;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import questions.QuestionNew;
import quiz.SubjectSelection;
import results.Result;
import students.StudentNew;
import students.StudentUpdate;
import subjects.SubjectNew;

public class QuizHome extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	int w,h;
	JLabel label;
	ImageIcon ii;
	private JMenuItem quizresult,quizreg,addques,stadd,stupdate	,subadd, close;
	public QuizHome() {
		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		h=(int)dim.getHeight();
		w=(int)dim.getWidth();
		setSize(465,248);
//		setSize(900,600);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		ii=new ImageIcon(QuizHome.class.getResource("/images/back4.jpg"));
		label.setIcon(ii);
		label.setBounds(0, 0, w, h);
		getContentPane().add(label);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnStudent = new JMenu("Student");
		mnStudent.setMnemonic('s');
		mnStudent.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnStudent);
		
		stadd = new JMenuItem("Add New");
		stadd.setMnemonic('a');
		stadd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnStudent.add(stadd);
		
		stupdate = new JMenuItem("Update");
		stupdate.setMnemonic('u');
		stupdate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnStudent.add(stupdate);
		
		JMenu mnSubject = new JMenu("Subject");
		mnSubject.setMnemonic('u');
		mnSubject.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnSubject);
		
		subadd = new JMenuItem("Add New");
		subadd.setMnemonic('a');
		subadd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnSubject.add(subadd);
		
		JMenu mnQuestions = new JMenu("Questions");
		mnQuestions.setMnemonic('q');
		mnQuestions.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnQuestions);
		
		addques = new JMenuItem("Add New");
		addques.setMnemonic('a');
		addques.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuestions.add(addques);
		
		JMenu mnQuiz = new JMenu("Quiz");
		mnQuiz.setMnemonic('z');
		mnQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnQuiz);
		
		quizreg = new JMenuItem("Register");
		quizreg.setMnemonic('r');
		quizreg.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuiz.add(quizreg);
		
		quizresult = new JMenuItem("Vew Result");
		quizresult.setMnemonic('v');
		quizresult.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnQuiz.add(quizresult);
		
		JMenu mnExit = new JMenu("Exit");
		mnExit.setMnemonic('e');
		mnExit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnExit);
		
		close = new JMenuItem("Close");
		close.setMnemonic('c');
		close.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnExit.add(close);
//		setSize(900, 700);
		quizresult.addActionListener(this);
		quizreg.addActionListener(this);
		addques.addActionListener(this);
		stadd.addActionListener(this);
		stupdate.addActionListener(this);	
		subadd.addActionListener(this); 
		close.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent we) {
				changeImage();
			}
		});
		setVisible(true);
	}

	public static void main(String[] args) {
		new QuizHome();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
//		quizresult,quizreg,addques,stadd,stupdate	,subadd, close;		
		if(ob==quizresult)
			new Result();
		else if(ob==quizreg)
			new SubjectSelection();
		else if(ob==addques)
			new QuestionNew();
		else if(ob==stadd)
			new StudentNew();
		else if(ob==stupdate)
			new StudentUpdate();
		else if(ob==subadd)
			new SubjectNew();
		else if(ob==close) {
			int rt=JOptionPane.showConfirmDialog(null, "Close Application","MCQ",JOptionPane.YES_NO_OPTION);
			if(rt==0)
				System.exit(0);
		}
	}
	void changeImage() {
		Random r=new Random();
		int num=r.nextInt(4);
		String name="back"+(num+1)+".jpg";
		System.out.println("Name: "+name);
		ii=new ImageIcon(getClass().getResource("/images/"+name));
		label.setIcon(ii);
	}
}
