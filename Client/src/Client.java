import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
	private static String studentId ="";
	public static void main(String[] args) throws NotBoundException, IOException, LogFailException{
		ServerIF server;		
		String userChoice = "";
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); 		
		try { server = (ServerIF) Naming.lookup("Server");
			if(showLogin(server, inputReader)) 		
			while(userChoice != "x") {
				showMenu();				
				userChoice = inputReader.readLine().trim();
			switch(userChoice) {
			case "1":
				showList(server.getAllStudentData());
				break;
			case "2":
				showList(server.getAllCourseData());
				break;
			case "3":
				showList(server.getAllRegistrationData());
				break;
			case "4":
				addStudent(server, inputReader);
				break;
			case "5":
				deleteStudent(server, inputReader);
				break;
			case "6":
				addCourse(server, inputReader);
				break;
			case "7":
				deleteCourse(server, inputReader);
				break;
			case "8":
				registrateCourse(server, inputReader);
				break;
			case "x":
				return;				
			default:
				System.out.println("Invalid Choice !!!");
					}
				}
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NullDataException e) {
				e.printStackTrace();
				}	
	}

	private static void registrateCourse(ServerIF server, BufferedReader inputReader) throws IOException, LogFailException, NullDataException {
		System.out.println("****************** Resistration *******************");
		System.out.println("Course ID : ");
		String courseId = inputReader.readLine().trim();
		if(server.isRegisteredCourse(courseId)) {
			String completedCourseList = server.getCompletedCourses(studentId);
			ArrayList<String> needToTakeCourse = server.getneedToTakeCourseList(courseId);
			if(server.checkTakeAllCourse(completedCourseList, needToTakeCourse)) {	
				if(server.Registrate(studentId, courseId)) System.out.println("SUCCESS");		
				else System.out.println("FAIL");} 
			else System.out.println("Need To Take Other Course");} 	
		else System.out.println("Course Data Not Found");
	}

	private static boolean showLogin(ServerIF server, BufferedReader inputReader) throws IOException, NullDataException, LogFailException {
		System.out.println("****************** LOGIN MENU *******************");
		System.out.println("Student ID : ");
		String inputId = inputReader.readLine().trim();
		System.out.println("PW : ");
		String inputpassword = inputReader.readLine().trim();
		for(int i=1; i<5; i++) {	
			String loginToken = server.login(inputId, inputpassword);
			if(loginToken == null || server.validateLoginToken(loginToken) == false) {
				System.out.println("FAIL !! LOGIN CHANCE ("+ i + "/5)");
				System.out.println("Student ID : ");
				inputId = inputReader.readLine().trim();
				System.out.println("PW : ");
				inputpassword = inputReader.readLine().trim();
			} else if (server.validateLoginToken(loginToken)) {
					System.out.println("Success");
					studentId = inputId;
					return true;	
			} 	
		}
		System.out.println("FAIL ! ReRun System");
		return false;
	}	

	private static void addCourse(ServerIF server, BufferedReader inputReader) throws IOException, RemoteException, LogFailException {
		System.out.println("--------Course Info---------");
		System.out.println("Course ID: "); String courseId = inputReader.readLine().trim();
		System.out.println("Professor Name: "); String professorName = inputReader.readLine().trim();
		System.out.println("Course Name: "); String courseName = inputReader.readLine().trim();
		System.out.println("NeedToTakeCourse List: "); String courseList = inputReader.readLine().trim();
		if(server.addCourse(courseId+" "+professorName+" "+courseName+" "+courseList)) System.out.println("SUCCESS");
		else System.out.println("FAIL");	
	}
	
	private static void addStudent(ServerIF server, BufferedReader inputReader) throws IOException, RemoteException, LogFailException {
		System.out.println("--------Student Info---------");
		System.out.println("Student ID: "); String studentId = inputReader.readLine().trim();
		System.out.println("Student Name: "); String studentName = inputReader.readLine().trim();
		System.out.println("Password: "); String pw = inputReader.readLine().trim();
		System.out.println("Student Department: "); String studentDept = inputReader.readLine().trim();
		System.out.println("Student Completed Course List: "); String completedCourses = inputReader.readLine().trim();
		if(server.addStudent(studentId+" "+studentName+" "+pw+" "+studentDept+" "+completedCourses))  System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}
	
	private static void deleteCourse(ServerIF server, BufferedReader inputReader) throws RemoteException, IOException, LogFailException {
		System.out.println("Course ID: ");
		if(server.deleteCourse(inputReader.readLine().trim())) System.out.println("Success");
		else System.out.println("FAIL");	
	}
	
	private static void deleteStudent(ServerIF server, BufferedReader inputReader) throws RemoteException, IOException, LogFailException {
		System.out.println("Student ID: ");
		if(server.deleteStudent(inputReader.readLine().trim())) System.out.println("Success");
		else System.out.println("FAIL");
	}
	
	private static void showList(ArrayList<?> dataList) {
		String list ="";
		for(int i=0; i<dataList.size(); i++) {
			list += dataList.get(i)+ "\n";
		}
		System.out.println(list);
	}

	private static void showMenu() {
		System.out.println("****************** MENU *******************");
		System.out.println("1. List Student");
		System.out.println("2. List Courses");
		System.out.println("3. List Registration");
		System.out.println("4. Add Student");
		System.out.println("5. Delete Student");
		System.out.println("6. Add Courses");
		System.out.println("7. Delete Courses");
		System.out.println("8. Registrate");
		System.out.println("x. Exit");
	}
}