import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class Server extends UnicastRemoteObject implements ServerIF{
	private static final long serialVersionUID = 1L;
	private static DataIF data;
	private String logintoken;
	private static String studentId;
	
	protected Server() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws NotBoundException {
		try {
			Server server = new Server();
			Naming.rebind("Server", server);
			System.out.println("RMI Server is ready !!!");		
			data = (DataIF) Naming.lookup("Data");		
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}

	private static void addLog(String time, String className, String methodName) throws RemoteException, LogFailException{
		String logInfo = studentId + " " + time + " " + className + " " + methodName;
			if(data.addLog(logInfo));
			else {
				throw new LogFailException("Fail Logging");
			}
	}
	
	private String createToken(String inputId, String inputpassword) {
		this.logintoken = inputId+"qwer"+inputpassword;
		return this.logintoken;
	}
	
	private static String time(){   
		Date now = new Date();        
		String time = now.toString();        
		return time;    
	}
	
	@Override
	public boolean validateLoginToken(String loginToken) throws RemoteException {
		if(logintoken.equals(loginToken)) return true;
		else return false;
	}
	
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException, LogFailException{
		addLog(time(), new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
		return data.getAllStudentData();}
	
	@Override
	public ArrayList<Registration> getAllRegistrationData() throws RemoteException, NullDataException, LogFailException{
		addLog(time(), new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
		return data.getAllRegistrationData();}
	
	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException, LogFailException{
		addLog(time(), new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
		return data.getAllCourseData();}
	
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException, LogFailException {
		if(data.addStudent(studentInfo)) {
			addLog(time(),new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
			return true;
		}
		else return false;}

	@Override
	public boolean deleteStudent(String studentId) throws RemoteException, LogFailException {
		if(data.deleteStudent(studentId)) {
			addLog(time(),new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
			return true;
		}
		else return false;}

	@Override
	public boolean addCourse(String courseInfo) throws RemoteException, LogFailException {
		if(data.addCourse(courseInfo)) {
			addLog(time(),new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
			return true;
		}
		else return false;}

	@Override
	public boolean deleteCourse(String courseId) throws RemoteException, LogFailException {
		if(data.deleteCourse(courseId)) {
			addLog(time(),new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
			return true;
		}
		else return false;}

	@Override
	public String login(String inputId, String inputpassword) throws RemoteException, NullDataException, LogFailException {	
		if(this.isRegisteredStudent(inputId, inputpassword)) {
			studentId = inputId;
			this.createToken(inputId, inputpassword);
			return this.logintoken;
		}
		else return null;
		}
	
	@Override
	public boolean isRegisteredStudent(String inputId, String inputpassword) throws RemoteException, NullDataException, LogFailException {
		ArrayList<Student> vStudent = this.getAllStudentData();
		for (int i = 0; i < vStudent.size(); i++) {
			Student student = (Student) vStudent.get(i);
			if (student.matchId(inputId) && student.matchPw(inputpassword)) return true;
		}
		return false;
	}

	@Override
	public boolean isRegisteredCourse(String courseId) throws RemoteException, NullDataException, LogFailException {
			ArrayList<Course> vCourse = this.getAllCourseData();
			for (int i = 0; i < vCourse.size(); i++) {
				Course course = (Course) vCourse.get(i);
				if (course.match(courseId)) return true;
			}
			return false;	
		}

	@Override
	public String getCompletedCourses(String studentId) throws RemoteException, NullDataException{
		return data.getCompletedCourses(studentId);}

	@Override
	public ArrayList<String> getneedToTakeCourseList(String courseId) throws RemoteException, NullDataException {
		return data.getneedToTakeCourseList(courseId);}

	@Override
	public boolean checkTakeAllCourse(String completedCourseList, ArrayList<String> needToTakeCourse) throws RemoteException, NullDataException {
		for (int i = 0; i < needToTakeCourse.size(); i++) 
			if(!(completedCourseList.contains(needToTakeCourse.get(i)))) return false;
		return true;
	}

	@Override
	public boolean Registrate(String studentId, String courseId) throws RemoteException, LogFailException {
		if(data.Registrate(studentId, courseId)) {
			addLog(time(),new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
			return true;}
		else return false;}
}