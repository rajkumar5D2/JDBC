import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student_Management {

	public static void main(String[] args) throws SQLException {
		String option="";
		Scanner scn = new Scanner(System.in);
		while(true) {
		System.out.println("\n1)ADD STUDENT\n2)SEARCH STUDENT\n3)UPDATE STUDENT\n4)DELETE STUDENT\n5)EXIT");
		System.out.print("\nENTER YOUR CHOICE: ");
		option = scn.next();
		switch(option) {
		case "1": 
			
			Add_Student.addStudent();
				break;
				
		case "2":
			System.out.println("\nSelect search method: ");
			System.out.println("1)searh by ID\n2)search by NAME\n3)search by address");
			System.out.print("enter your option: ");
			int option1 = scn.nextInt();
			if(option1==1) {
				System.out.print("\nEnter Student ID: ");
				 
				int sid = scn.nextInt();
				
				ResultSet rs = Search_Student.searchStudent(sid);
			}
			else if(option1==2) {
				Search_Student.searchByName();
			}
			else if(option1==3) {
				Search_Student.searchByAddress();
			}
			break;
			
		case "3":
			
			int update = Update_Student.updateStudent();
			if(update >=1) {
				System.out.println("Student Update details successful");
			}else System.out.println("Student Update details failed");
			break;
			
		case "4":
			
			Delete_Student.deleteStudent();
			break;
		default: System.out.println("invalid option");
	
		}
		if(option.equals("5")) {
			System.out.println("THANK YOU!!!");
			break;
		}
		else continue;
		}
		
	}

}
