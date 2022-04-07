import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class Search_Student {
	static Scanner scn = new Scanner(System.in);
	static ResultSet rs;
	static CachedRowSet crs;
	public static ResultSet searchStudent(int sid) {
		
		try {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raj");
				Statement st = con.createStatement();
				  rs = st.executeQuery(String.format("select * from student where sid = %d",sid));

					RowSetFactory rsf = RowSetProvider.newFactory();
					CachedRowSet crs = rsf.createCachedRowSet();
					crs.populate(rs);
					
				  Search_Student.display(crs);
					
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return crs;
	}
	
	public static void display(CachedRowSet crs) throws SQLException {
		if(!crs.isBeforeFirst()) {System.out.println("NO STUDENT FOUND");
		}else {
			while(crs.next()) {
				System.out.println("\nSTUDENT FOUND");
				System.out.println("Student ID: "+crs.getInt(1)+"\nStudent Name: "+crs.getString(2)+"\nStudent Address: "+crs.getString(3));
			}
		}
	}

	public static void searchByName() {
		System.out.print("Enter Student Name: ");
		String name = scn.nextLine();
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raj");
			Statement st = con.createStatement();
			  rs = st.executeQuery(String.format("select * from student where sname = '%s'",name));

				RowSetFactory rsf = RowSetProvider.newFactory();
				CachedRowSet crs = rsf.createCachedRowSet();
				crs.populate(rs);
				
				Search_Student.display(crs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void searchByAddress() {
		System.out.print("Enter Student Address: ");
		String name = scn.nextLine();
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raj");
			Statement st = con.createStatement();
			  rs = st.executeQuery(String.format("select * from student where saddr = '%s'",name));

				RowSetFactory rsf = RowSetProvider.newFactory();
				CachedRowSet crs = rsf.createCachedRowSet();
				crs.populate(rs);
				
				Search_Student.display(crs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
