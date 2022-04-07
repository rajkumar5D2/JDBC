import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Delete_Student {
	
	public static void deleteStudent() {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter Student name or ID to delete: ");
		int sid = scn.nextInt();
		String sname = "";

		
			try {
					ResultSet rs = Search_Student.searchStudent(sid);
					
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","raj");
					Statement st = con.createStatement();
					ResultSet rs2 =st.executeQuery(String.format("select * from student where sid = %d",sid));
					if(rs2.next()) {
						 sname = rs2.getString(2);
					}
					
					int update = st.executeUpdate(String.format("delete from student where sid = %d ",sid));
					
					if(update>=1) {
						System.out.println("Student "+ sname+" deleted from Database");
					}else System.out.println("delete failed");
				
			}catch(Exception e) {
		e.printStackTrace();
			}
	}
		
}
