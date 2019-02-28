package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import mains.MCQConnection;

public class QuizPreparation {
	ResultSet rs;
	String stid, subject, stream, stname;
	int semester, subid;
	int qid; // id from question bank
	int qno; //running question number to show to the user

	Random r=new Random();
	int num;
	ArrayList<Integer>list=new ArrayList<Integer>();
	HashSet<Integer>hs=new HashSet<Integer>();
	QuizPreparation(String stid, String stname, String stream, String subject, int subid, int semester) {
		this.stid=stid;
		this.stname=stname;
		this.stream=stream;
		this.subject=subject;
		this.subid=subid;
		this.semester=semester;
		String st="select * from questionbank where subid=?";
		Connection con=MCQConnection.connect();
		try {
			PreparedStatement ps=con.prepareStatement(st);
			ps.setInt(1, subid);
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("qid"));
			}
		}

		catch(SQLException se) {
			se.printStackTrace();
		}
		while(hs.size()<10) {
			num=r.nextInt(list.size());
			if(num==0)
				continue;
			hs.add(list.get(num));
		}
//		System.out.println(list);
		System.out.println(hs);
		prepareQuestions(hs);
	}
	public static void main(String[] args) {
		//		Random r=new Random();
		//		int x=r.nextInt(10);
		//		System.out.println(x);
		//		HashSet<Integer>hs=new HashSet<Integer>();
		//		System.out.println(hs.size());
		new QuizPreparation("54212554", "Anupam", "B Sc IT", "Computer", 7, 2);
	}
	void prepareQuestions(HashSet<Integer> set) {
		Object[] q=set.toArray();
		String st="delete from questions where qid is not null";
		Connection con=MCQConnection.connect();
		try {
//			Deletion started from temporary table
			PreparedStatement ps=con.prepareStatement(st);
			int x=ps.executeUpdate();
			if(x>0)
				System.out.println(x + " Rows found, Deleted Successfully");
			else
				System.out.println("Question table already blank");
//			Deletion end from temporary table
//			Insertion in temporary table
			st="INSERT INTO questions(qid,subid,question,option1,option2, option3, "
					+ "option4, correctAns) SELECT * FROM questionbank"
					+ " WHERE qid in (?,?,?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(st);
			for(int i=0;i<10;i++) {
				ps.setInt(i+1, (int)q[i]);
			}
			ps.executeUpdate();
			System.out.println("Insertion Done");
//			End of insertion in temporary table
			new QuizPage(stid, stname, stream, subid, subject, semester,hs);
			
//			ResultSet rs;
//			String stid, subject, stream, stname;
//			int semester, subid;

		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
