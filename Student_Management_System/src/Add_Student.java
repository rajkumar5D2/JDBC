import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Add_Student {
	public static void addStudent() {
		boolean b;
		Scanner scn = new Scanner(System.in);
		System.out.println("ENTER STUDENT DETAILS...");
		System.out.print("Student ID: ");
		int sid = scn.nextInt();
		System.out.print("Enter Student name: ");
		String sname = scn.next();
		System.out.print("Enter Student Address: ");
		String saddr = scn.next();
		
		try {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raj");
				Statement st = con.createStatement();
			
				ResultSet rs = st.executeQuery("select * from student");
				RowSetFactory rsf = RowSetProvider.newFactory();
				CachedRowSet crs = rsf.createCachedRowSet();
				crs.populate(rs);
				
				if(!crs.isBeforeFirst()) {
					Statement st4 = con.createStatement();
					int update3 = st4.executeUpdate(String.format("insert into student values(%d,'%s','%s')",sid,sname,saddr));
					if(update3 >=1) {
						System.out.println("Successfully inserted student details....");
					}
				}
				
				else 
					while(crs.next()) {
					
							b = crs.getInt(1)==sid;
							
							if(b==true) {
							throw new Exception();
							
							}else if(b==false) {
								
								if(crs.isLast()) {
									
								Statement st3 = con.createStatement();
								int update2 = st3.executeUpdate(String.format("insert into student values(%d,'%s','%s')",sid,sname,saddr));
								if(update2 >=1) {
									System.out.println("Successfully inserted student details...");
									break;
								}
								}else continue;
						}
				}
				
			}catch(Exception e) {
				//e.printStackTrace();
					System.out.println("Student already Exist with same ID!!!");
				}
				
			}
			
		
	}


