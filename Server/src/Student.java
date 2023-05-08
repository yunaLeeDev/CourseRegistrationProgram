
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String studentId;
    protected String name;
    protected String Fname;
    protected String Lname;
    protected String pw;
    protected String department;
    protected ArrayList<String> completedCoursesList;

    public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.Fname = stringTokenizer.nextToken();
    	this.Lname = stringTokenizer.nextToken();
    	this.name = this.Fname + this.Lname;
    	this.pw = stringTokenizer.nextToken();
    	this.department = stringTokenizer.nextToken();
    	this.completedCoursesList = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) 
    		this.completedCoursesList.add(stringTokenizer.nextToken());
    }
    public boolean matchId(String inputId) {
        return this.studentId.equals(inputId);
    }
    public boolean matchPw(String inputPw) {
        return this.pw.equals(inputPw);
    }
    public String getName() {
        return this.name;
    }
    public String getCompletedCourses() {
    	String result="";
    	for (int i = 0; i < this.completedCoursesList.size(); i++)
    		result = result + " " + this.completedCoursesList.get(i).toString();    
        return result;
    }
    public String toString() {
        String stringReturn = this.studentId + " " + this.Fname + " " + this.Lname + " "+ this.pw + " " + this.department;
        for (int i = 0; i < this.completedCoursesList.size(); i++)
            stringReturn = stringReturn + " " + this.completedCoursesList.get(i).toString();
        return stringReturn;
    }
}