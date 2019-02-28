package subjects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mains.MCQConnection;

public class SubjectNew extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tsubject;
	private int sid;
	private String subject;

	public SubjectNew() {
		setTitle("New Subject Addition");
		getContentPane().setLayout(null);

		JLabel lblNewSubjectAddition = new JLabel("New Subject Addition");
		lblNewSubjectAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewSubjectAddition.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewSubjectAddition.setBounds(242, 22, 225, 31);
		getContentPane().add(lblNewSubjectAddition);

		JLabel lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubjectName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubjectName.setBounds(10, 85, 129, 31);
		getContentPane().add(lblSubjectName);

		tsubject = new JTextField();
		tsubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tsubject.setBounds(134, 85, 552, 31);
		getContentPane().add(tsubject);
		tsubject.setColumns(10);

		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkAndSave();
			}
		});
		add.setFont(new Font("Tahoma", Font.BOLD, 16));
		add.setBounds(148, 177, 89, 23);
		getContentPane().add(add);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		cancel.setBounds(442, 179, 89, 23);
		getContentPane().add(cancel);
		setSize(727, 287);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(80, 80);
		setModal(true);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SubjectNew();
	}
	void generateID() {
		String st="select max(subid) from subjects";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			rs.next();
			sid=rs.getInt(1);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		++sid;
	}
	void checkAndSave() {
		subject=tsubject.getText().toUpperCase().trim().replace("  ", " ");
		String st="select * from subjects";
		Connection con=MCQConnection.connect();
		int found=0;
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String res=rs.getString("subject");
				if(res.contains(subject)) {
					++found;
					break;
				}
			}
			if(found>0) {
				JOptionPane.showMessageDialog(null, "This subject or similar to this already exists","MCQ",JOptionPane.ERROR_MESSAGE);
				tsubject.requestFocusInWindow();
				return;
			}
			generateID();
			st="insert into subjects (subid,subject)values(?,?)";
			ps=con.prepareStatement(st);
			ps.setInt(1, sid);
			ps.setString(2, subject);
			ps.executeUpdate();
			int option=JOptionPane.showConfirmDialog(null, "New Subject Added\n Add More ?","MCQ",JOptionPane.YES_NO_OPTION);
			if(option==0) {
				tsubject.setText("");
				tsubject.requestFocusInWindow();
			}
			else {
				dispose();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
