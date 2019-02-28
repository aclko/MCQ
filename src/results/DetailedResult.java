package results;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import mains.MCQConnection;
import javax.swing.border.LineBorder;

public class DetailedResult extends JDialog {
	JScrollPane jsp;
	String columns[] = new String[]{"Question","Option1","Option2","Option3","Option4",
			"Correct","Your Answer"};
	private static final long serialVersionUID = 1L;
	JTable table = new JTable();
	private JButton cancel;
	String stid="",name,gender,mobile,email,stream,subject;
	String question, option1, option2, option3, option4;
	int semester,subid;
	PreparedStatement ps2;
	ResultSet rs2;
	String query2;
	DefaultTableModel model = new DefaultTableModel();
	ButtonGroup bg=new ButtonGroup();
	private JButton enable;
	public DetailedResult(String stid, String stream,int semester,String subject, int subid) {
		table.setEnabled(false);
		this.stid=stid;
		this.stream=stream;
		this.semester=semester;
		this.subject=subject;
		this.subid=subid;
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setBorder(new LineBorder(Color.BLUE));
		
		table.setDefaultRenderer(String.class, centerRenderer);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		table.getTableHeader().setForeground(Color.blue);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model.setColumnIdentifiers(columns);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setBackground(new Color(255,228,181));
		table.setModel(model);
		table.setFillsViewportHeight(true);


		setTitle("Detailed Result View");
		getContentPane().setLayout(null);

		JLabel lblNewQuestionAddition = new JLabel("Showing Detailed Results");
		lblNewQuestionAddition.setBounds(385, 20, 282, 30);
		lblNewQuestionAddition.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblNewQuestionAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewQuestionAddition.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNewQuestionAddition);

		cancel = new JButton("Close");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBounds(136, 384, 109, 28);
		getContentPane().add(cancel);
		cancel.setMnemonic('c');
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		setSize(1000, 457);
		setLocation(100, 80);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(15, 72, 947, 301);
		getContentPane().add(jsp);
		
		enable = new JButton("Enable");
		enable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enable.getText().equals("Enable")) {
					table.setEnabled(true);
					enable.setMnemonic('d');
					enable.setText("Disable");
				}
				else if(enable.getText().equals("Disable")) {
					table.setEnabled(false);
					enable.setMnemonic('e');
					enable.setText("Enable");
				}
			}
		});
		enable.setMnemonic('e');
		enable.setFont(new Font("Tahoma", Font.BOLD, 16));
		enable.setBounds(17, 384, 109, 28);
		getContentPane().add(enable);
		showTable();
		setVisible(true);
	}
	void showTable() {
		int qid=0;
//		int i=0;
		table.setRowHeight(25);
		String correct=null,userans=null;
		query2="select question, option1, option2, option3, option4 from questionbank where qid=?";
		try {
			Connection con=MCQConnection.connect();
			ps2=con.prepareStatement(query2);
			String st="select * from results where "
					+ "stid = ? and stream=? and semester=? and subid=?";
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, stid);
			ps.setString(2, stream);
			ps.setInt(3, semester);
			ps.setInt(4, subid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				correct=rs.getString("CorrectAns");
				userans=rs.getString("UserAns");
				qid=rs.getInt("qid");
				ps2.setInt(1, qid);
				rs2=ps2.executeQuery();
				while(rs2.next()) {
					question=rs2.getString("question");
					option1=rs2.getString("Option1");
					option2=rs2.getString("Option2");
					option3=rs2.getString("Option3");
					option4=rs2.getString("Option4");
				}
				model.addRow(new Object[]{question,option1,option2,option3,option4,correct,userans});
				table.getColumnModel().getColumn(0).setPreferredWidth(300);
				table.getColumnModel().getColumn(1).setPreferredWidth(200);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(200);
				table.getColumnModel().getColumn(4).setPreferredWidth(200);
				table.getColumnModel().getColumn(5).setPreferredWidth(70);
				table.getColumnModel().getColumn(6).setPreferredWidth(110);
//				table.setRowHeight(i, 30);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DetailedResult("1144", "B Tech (CS)", 4, "COMPUTERS", 7);
	}
}
