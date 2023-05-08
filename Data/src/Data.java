import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Data extends UnicastRemoteObject implements DataIF{	
	
	private static final long serialVersionUID = 1L;
	protected static StudentList studentList;
	protected static CourseList courseList;
	protected static RegistrationList registrationList;
	protected static LogList logList;
	
	protected Data() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try {
			Data data = new Data();
			Naming.rebind("Data", data);
			System.out.println("Data is ready !!!");			
			studentList = new StudentList("Students.txt");
			courseList = new CourseList("Courses.txt");
			registrationList = new RegistrationList("Registration.txt");
			logList = new LogList("Log.txt");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}
	
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
		return studentList.getAllStudentRecords();
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException{
		return courseList.getAllCourseRecords();
	}
	
	@Override
	public ArrayList<Registration> getAllRegistrationData() throws RemoteException, NullDataException{
		return registrationList.getAllRegistrationRecords();
	}
	
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException {
		if(studentList.addStudentRecords(studentInfo)) return true;
		else return false;
		}

	@Override
	public boolean deleteStudent(String studentId) throws RemoteException {
		if(studentList.deleteStudentRecords(studentId)) return true;
		else return false;}

	@Override
	public boolean addCourse(String courseInfo) throws RemoteException {
		if(courseList.addCourseRecords(courseInfo)) return true;
		else return false;}

	@Override
	public boolean deleteCourse(String courseId) throws RemoteException {
		if(courseList.deleteCourseRecords(courseId)) return true;
		else return false;}

	@Override
	public String getCompletedCourses(String studentId) throws RemoteException, NullDataException{	
		return studentList.getCompletedCourses(studentId);}

	@Override
	public ArrayList<String> getneedToTakeCourseList(String courseId) throws RemoteException, NullDataException{
		return courseList.getneedToTakeCourseList(courseId);}

	@Override
	public boolean Registrate(String studentId, String courseId) throws RemoteException {
		if(registrationList.addRegist(studentId, courseId)) return true;
		else return false;}

	@Override
	public boolean addLog(String logInfo) throws RemoteException {
		if(logList.addLog(logInfo)) return true;
		else return false;
		}
}