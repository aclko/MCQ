package results;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import mains.MCQConnection;

public class Result extends JDialog {
	JScrollPane jsp;
	String columns[] = new String[]{"Stream","Semester","Subject",
			"Attempted","Correct","Incorrect"};
	private static final long serialVersionUID = 1L;
	JTable table = new JTable();
	private JButton show, cancel;
	String stid="",name,gender,mobile,email,stream,subject;
	int semester,subid;
	private JTextField tstid;
	private JTextField tname;
	DefaultTableModel model = new DefaultTableModel();
	ButtonGroup bg=new ButtonGroup();
	public Result(String stid) {
		this.stid=stid;
		makeGUI();
	}
	public Result() {
		makeGUI();
	}
	void makeGUI() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		model.setColumnIdentifiers(columns);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sr=table.getSelectedRow();
				if(sr>-1)
					show.setEnabled(true);
			}
		});
		table.setFont(new Font("Tahoma",Font.BOLD,14));
		table.setBackground(new Color(255,228,181));
		table.setModel(model);
		table.setFillsViewportHeight(true);


		setTitle("New Question");
		getContentPane().setLayout(null);

		JLabel lblNewQuestionAddition = new JLabel("Showing Results");
		lblNewQuestionAddition.setBounds(247, 11, 282, 30);
		lblNewQuestionAddition.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblNewQuestionAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewQuestionAddition.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblSelectSubject = new JLabel("Registration No.");
		lblSelectSubject.setBounds(10, 66, 137, 28);
		lblSelectSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 15));

		show = new JButton("Show Details");
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getValuesAndShowDetails();
			}
		});
		show.setEnabled(false);
		show.setBounds(498, 419, 150, 28);
		getContentPane().add(show);
		show.setMnemonic('s');
		show.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewQuestionAddition);
		getContentPane().add(lblSelectSubject);

		cancel = new JButton("Close");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBounds(658, 419, 109, 28);
		getContentPane().add(cancel);
		cancel.setMnemonic('c');
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 105, 137, 28);
		getContentPane().add(lblName);

		tstid = new JTextField(stid.trim());
		tstid.setBackground(Color.WHITE);
		tstid.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				stid=tstid.getText();
				checkStudentInTable();
			}
			public void focusGained(FocusEvent fe) {
				show.setEnabled(false);
				tname.setText("");
				int row=model.getRowCount();
				for (int i = row - 1; i >= 0; i--) {
					model.removeRow(i);
				}		
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
		tname.setBounds(192, 105, 316, 30);
		getContentPane().add(tname);
		setSize(793, 508);
		setLocation(100, 80);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(10, 170, 757, 180);
		getContentPane().add(jsp);
		setVisible(true);
	}

	public boolean checkStudentInTable() { // whether the student is present or not
		System.out.println("Direct from main");
		if(stid.length()==0)
			return false;
		String st="select stid,stname from students where stid=?";
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
				//	Now checking if the student has attempted any quiz or not.
				tname.setText(rs.getString("stname"));
				st="select stid from results where stid=?";
				ps=con.prepareStatement(st);
				ps.setString(1, stid);
				rs=ps.executeQuery();
				if(!rs.next()) {
					JOptionPane.showMessageDialog(null, "Student Found\nNot attempted Any quiz"
							,"MCQ",JOptionPane.ERROR_MESSAGE);
					tstid.selectAll();
					tstid.requestFocusInWindow();
				}
				else {
					showTable();
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return true;
	}
	void showTable() {
		String stream=null;
		int semester=0, subid=0, attempted=0,left=0,correct=0,incorrect=0;
		try {
			Connection con=MCQConnection.connect();
			String st="select stream,semester,subid from results where stid = ? group by stream, semester, subid";
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, stid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				stream=rs.getString("stream");
				semester=rs.getInt("semester");
				subid=rs.getInt("subid");
				String st2="select count(*) from results where correctans=userans "
						+ "and subid=? and stream=? and semester=? and stid=?";
				PreparedStatement ps2=con.prepareStatement(st2);
				ps2.setInt(1, subid);
				ps2.setString(2, stream);
				ps2.setInt(3, semester);
				ps2.setString(4, stid);
				ResultSet rs2=ps2.executeQuery();
				rs2.next();

				correct=rs2.getInt(1);

				st2="select count(*) from results where userans is null "
						+ "and subid=? and stream=? and semester=? and stid=?";
				ps2=con.prepareStatement(st2);
				ps2.setInt(1, subid);
				ps2.setString(2, stream);
				ps2.setInt(3, semester);
				ps2.setString(4, stid);
				rs2=ps2.executeQuery();
				rs2.next();
				left=rs2.getInt(1);
				attempted=10-left;
				incorrect=attempted-correct;
				//	For Subject
				st2="select subject from subjects where subid=?";
				ps2=con.prepareStatement(st2);
				ps2.setInt(1, subid);
				rs2=ps2.executeQuery();
				rs2.next();
				subject=rs2.getString("Subject");
				//	For Subject End
				model.addRow(new Object[]{stream,semester,subject+" ("+subid+")",attempted,correct,incorrect});
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Result();
//		new Result("1100").checkStudentInTable();
	}
	void getValuesAndShowDetails() {
		int srow=table.getSelectedRow();
		stream=table.getValueAt(srow, 0).toString();
		semester=Integer.parseInt(table.getValueAt(srow, 1).toString());
		String text=table.getValueAt(srow, 2).toString();
		int l=text.length();
		String st=text.substring(text.indexOf("(")+1, l-1); 
		subid=Integer.parseInt(st);
		new DetailedResult(stid, stream, semester, subject, subid);
		
	}
}
