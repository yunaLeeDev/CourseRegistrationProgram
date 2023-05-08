import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote{
	ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
	ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException;
	ArrayList<Registration> getAllRegistrationData() throws RemoteException, NullDataException;
	boolean addStudent(String studentInfo) throws RemoteException;
	boolean deleteStudent(String studentId) throws RemoteException;
	boolean addCourse(String courseInfo) throws RemoteException;
	boolean deleteCourse(String courseId) throws RemoteException;
	String getCompletedCourses(String studentId) throws RemoteException, NullDataException;
	ArrayList<String> getneedToTakeCourseList(String courseId) throws RemoteException, NullDataException;
	boolean Registrate(String studentId, String courseId) throws RemoteException;
	boolean addLog(String logInfo) throws RemoteException;
}
