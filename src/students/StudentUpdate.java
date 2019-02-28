package students;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

import com.github.sarxos.webcam.Webcam;

public class StudentUpdate extends JDialog {
	private static final long serialVersionUID = 1L;
	private JComboBox <String>tstream;
	private JButton add, cancel;
	String stid,name,gender,mobile,email,stream;
	int semester;
	private JButton reset;
	private JTextField tstid;
	private JTextField tname;
	private JTextField tmobile;
	private JTextField temail;
	private JComboBox<String> tsemester;
	ButtonGroup bg=new ButtonGroup();
	private JRadioButton m;
	private JRadioButton f;
	private JLabel timage;
	Webcam webcam;
	Dimension [] nonStandardResolutions;
	InputStream img;
	public StudentUpdate() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				tstid.addFocusListener(null);
			}
		});
		setTitle("New Question");
		getContentPane().setLayout(null);

		JLabel lblNewQuestionAddition = new JLabel("Student Registration");
		lblNewQuestionAddition.setBounds(203, 11, 202, 30);
		lblNewQuestionAddition.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		lblNewQuestionAddition.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewQuestionAddition.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblSelectSubject = new JLabel("Registration No.");
		lblSelectSubject.setBounds(10, 66, 137, 28);
		lblSelectSubject.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD, 15));

		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		reset.setBounds(357, 366, 99, 28);
		reset.setMnemonic('r');
		reset.setFont(new Font("Tahoma", Font.BOLD, 16));

		tstream = new JComboBox<String>();
		tstream.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstream.setBounds(192, 270, 137, 30);
		tstream.setModel(new DefaultComboBoxModel<String>(new String[] {"BCA", "MCA", "B Tech (CS)", "B Tech (IT)", "B Sc IT", "M Sc IT"}));
		tstream.setFont(new Font("Tahoma", Font.PLAIN, 16));

		add = new JButton("Update");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check())
					updateStudent();
			}
		});
		add.setBounds(48, 366, 99, 28);
		getContentPane().add(add);
		add.setMnemonic('a');
		add.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewQuestionAddition);
		getContentPane().add(lblSelectSubject);
		getContentPane().add(reset);
		getContentPane().add(tstream);

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBounds(192, 366, 99, 28);
		getContentPane().add(cancel);
		cancel.setMnemonic('c');
		cancel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 105, 137, 28);
		getContentPane().add(lblName);

		tstid = new JTextField();
		tstid.setBorder(new LineBorder(new Color(0, 0, 255)));
		tstid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				stid=tstid.getText();
				checkStudentInTable();
			}
			public void focusGained(FocusEvent e) {
				clear();
			}
		});
		tstid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tstid.setBounds(192, 66, 251, 30);
		getContentPane().add(tstid);
		tstid.setColumns(10);

		tname = new JTextField();
		tname.setBorder(new LineBorder(new Color(0, 0, 255)));
		tname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tname.setColumns(10);
		tname.setBounds(192, 105, 316, 30);
		getContentPane().add(tname);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(10, 149, 137, 28);
		getContentPane().add(lblGender);

		m = new JRadioButton("Male");
		m.setOpaque(false);
		m.setBorder(new LineBorder(new Color(0, 0, 255)));
		bg.add(m);
		m.setFont(new Font("Tahoma", Font.BOLD, 16));
		m.setBounds(192, 152, 89, 23);
		getContentPane().add(m);

		f = new JRadioButton("Female");
		f.setOpaque(false);
		bg.add(f);
		f.setFont(new Font("Tahoma", Font.BOLD, 16));
		f.setBounds(333, 152, 89, 23);
		getContentPane().add(f);

		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMobileNo.setBounds(10, 191, 137, 28);
		getContentPane().add(lblMobileNo);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 230, 137, 28);
		getContentPane().add(lblEmail);

		JLabel lblStream = new JLabel("Stream");
		lblStream.setHorizontalAlignment(SwingConstants.LEFT);
		lblStream.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStream.setBounds(10, 271, 137, 28);
		getContentPane().add(lblStream);

		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemester.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSemester.setBounds(10, 310, 137, 28);
		getContentPane().add(lblSemester);

		tsemester = new JComboBox<String>();
		tsemester.setBorder(new LineBorder(new Color(0, 0, 255)));
		tsemester.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		tsemester.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tsemester.setBounds(192, 309, 137, 30);
		getContentPane().add(tsemester);

		tmobile = new JTextField();
		tmobile.setBorder(new LineBorder(new Color(0, 0, 255)));
		tmobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tmobile.setColumns(10);
		tmobile.setBounds(192, 189, 251, 30);
		getContentPane().add(tmobile);

		temail = new JTextField();
		temail.setBorder(new LineBorder(new Color(0, 0, 255)));
		temail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		temail.setColumns(10);
		temail.setBounds(192, 229, 251, 30);
		getContentPane().add(temail);

		JButton btnTakeImage = new JButton("Change Image");
		btnTakeImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getImage();
			}
		});
		btnTakeImage.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTakeImage.setBounds(559, 315, 171, 23);
		getContentPane().add(btnTakeImage);

		timage = new JLabel("");
		timage.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		timage.setForeground(Color.RED);
		timage.setHorizontalAlignment(SwingConstants.CENTER);
		timage.setFont(new Font("Tahoma", Font.BOLD, 20));
		timage.setBackground(Color.GREEN);
		timage.setBounds(548, 105, 182, 194);
		getContentPane().add(timage);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(StudentUpdate.class.getResource("/images/back3.jpg")));
		label.setBounds(0, 0, 754, 419);
		getContentPane().add(label);
		setSize(770, 457);
		setLocation(100, 80);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setModal(true);
		setVisible(true);
	}

	public static void main(String[] args) {
		new StudentUpdate();
	}

	boolean checkStudentInTable() {
		stid=stid.trim();
		String st="select stid from students where stid=?";
		Connection con=MCQConnection.connect();
		int found=0;
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(1, stid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				++found;
				fillStudentInformation();
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		if(found==0) {
			JOptionPane.showMessageDialog(null, "Student Not Found","MCQ",JOptionPane.ERROR_MESSAGE);
			tstid.selectAll();
			tstid.requestFocusInWindow();
			return false;
		}
		System.out.println("Found="+found);
		return true;
	}
	boolean check() {
		mobile=tmobile.getText().trim();
		email=temail.getText().trim().replace(" ", "");
		stream=tstream.getSelectedItem().toString();
		semester=Integer.parseInt(tsemester.getSelectedItem().toString());
		return true;
	}
	void clear() {
		tsemester.setSelectedIndex(0);
		tstream.setSelectedIndex(0);
		tmobile.setText("");
		temail.setText("");
		tname.setText("");
		bg.clearSelection();
		tstid.setText("");
		timage.setIcon(null);
		timage.setText("");
		tstid.requestFocusInWindow();
	}
	void updateStudent() {
		String st="update Students set stmobile=?, stemail=?, ststream=?, semester=?,"
				+ " stimage=? where stid=?";
		Connection con=MCQConnection.connect();
		try {
			img=new FileInputStream(new File("D:/Test.jpg"));
			PreparedStatement ps=con.prepareStatement(st);
			ps.setString(6, stid);
			ps.setBinaryStream(5, img);
			ps.setInt(4, semester);
			ps.setString(3, stream);
			ps.setString(2, email);
			ps.setString(1, mobile);
			ps.executeUpdate();
			int more=JOptionPane.showConfirmDialog(null, "Student Information Updated\n "
					+ "More Updattions","MCQ",JOptionPane.YES_NO_OPTION);
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
		catch(IOException se) {
			se.printStackTrace();
		}
	}
	void getImage() {
		// get default webcam and open it
		webcam = Webcam.getDefault();
		//		nonStandardResolutions = new Dimension[] {
		//				WebcamResolution.PAL.getSize(),
		//				WebcamResolution.HD.getSize(), 
		//				new Dimension(640, 480), 
		//				new Dimension(640, 480), };
		//		
		//		webcam.setCustomViewSizes(nonStandardResolutions);
		webcam.setCustomViewSizes(new Dimension(176,144));
		//		Incorrect dimension [1280x720] possible ones are [176x144] [320x240] [640x480] 

		//		webcam.setViewSize(new Dimension(176, 144));
		//		Dimension[]w=webcam.getViewSizes();
		//		for(Dimension d:w) {
		//			System.out.println(d);
		//		}
		//		webcam.setViewSize(WebcamResolution.HD.getSize());
		webcam.setViewSize(new Dimension(176,144));
		webcam.open();

		// get image
		BufferedImage image = webcam.getImage();
		webcam.close();
		// save image to JPG file
		try {

			File f=new File("D:/Test.jpg");
			if(f.exists())
				f.delete();
			ImageIO.write(image, "JPG", f);
			BufferedImage bufImg=ImageIO.read(f);
			//			ImageIcon ii=new ImageIcon("D:/Test.jpg");
			ImageIcon ii=new ImageIcon(bufImg);
			timage.setIcon(ii);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		webcam.close();
	}
	void fillStudentInformation() {
		timage.setText("");
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
				Blob blob=rs.getBlob("StImage");
				File tmpFile = new File("D:/Test.jpg");

				FileOutputStream fos = new FileOutputStream(tmpFile);
				fos.write(blob.getBytes(1L, (int)blob.length()) );
				fos.close();
				if(tmpFile.length()>0) {
					ImageIcon ii=new ImageIcon("D:/Test.jpg");
					timage.setIcon(ii);
				}
				else {
					timage.setText("Image Not Found");
				}
				add.requestFocusInWindow();

			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(IOException se) {
			se.printStackTrace();
		}
	}

}
