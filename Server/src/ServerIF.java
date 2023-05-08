import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote{
	ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException, LogFailException;
	ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException, LogFailException;
	ArrayList<Registration> getAllRegistrationData() throws RemoteException, NullDataException, LogFailException;
	boolean addStudent(String studentInfo) throws RemoteException, LogFailException;
	boolean deleteStudent(String studentId) throws RemoteException, LogFailException;
	boolean addCourse(String courseInfo) throws RemoteException, LogFailException;
	boolean deleteCourse(String courseId) throws RemoteException, LogFailException;
	String login(String inputId, String inputpassword) throws RemoteException, NullDataException, LogFailException;
	boolean isRegisteredStudent(String inputId, String inputpassword) throws RemoteException, NullDataException, LogFailException;
	boolean validateLoginToken(String loginToken) throws RemoteException;
	boolean isRegisteredCourse(String courseId) throws RemoteException, NullDataException, LogFailException;
	String getCompletedCourses(String studentId) throws RemoteException, NullDataException;
	ArrayList<String> getneedToTakeCourseList(String courseId) throws RemoteException, NullDataException;
	boolean checkTakeAllCourse(String completedCourseList, ArrayList<String> needToTakeCourse) throws RemoteException, NullDataException;
	boolean Registrate(String studentId, String courseId) throws RemoteException, LogFailException;
}

