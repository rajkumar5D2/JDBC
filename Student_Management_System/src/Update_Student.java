import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update_Student {
	static int update = 0;
	public static int updateStudent() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raj");
		Statement st = con.createStatement();
		
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter Student ID you want to update: ");
		int sid = scn.nextInt();
		ResultSet rs = st.executeQuery(String.format("select * from student where sid = %d",sid));
		if(!rs.isBeforeFirst()) {
			System.out.println("NO STUDENT FOUND, ADD STUDENT FIRST TO UPDATE LATER...");
		}else { 
			System.out.println("Student found");
			System.out.println("Select field you want to update: \n1)Student Name\n2)Student Address");
			System.out.print("\nENTER YOUR OPTION: ");
			int option = scn.nextInt();
			try {
			if(option==1) {
				
					if(rs.next()) {
					String oldName = rs.getString(2);
					System.out.print("Old Name is : "+oldName+" Enter New Name: ");
					scn.nextLine();
					String newName = scn.nextLine();
					update = st.executeUpdate(String.format("update student set sname = '%s' where sid = %d",newName,sid));
					}else System.out.println("fail");
				}
			else if(option==2) {
				if(rs.next()) {
				String oldAddr = rs.getString(3);
				System.out.print("Old Address is: "+oldAddr+" Enter New Address: ");
				String newAddr = scn.next();
				update = st.executeUpdate(String.format("update student set saddr = '%s' where sid = %d",newAddr,sid));
				}
			}
				}catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		return update;
	}

}
