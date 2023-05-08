import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String courseId;
    protected String professorName;
    protected String courseName;
    protected ArrayList<String> needToTakeCourse;

    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.courseId = stringTokenizer.nextToken();
    	this.professorName = stringTokenizer.nextToken();
    	this.courseName = stringTokenizer.nextToken();
    	this.needToTakeCourse = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens())
    		this.needToTakeCourse.add(stringTokenizer.nextToken());
    }  
    public boolean match(String courseId) {
        return this.courseId.equals(courseId);
    }
    public String getName() {
        return this.courseName;
    }
    public ArrayList<String> getneedToTakeCourseList() {
        return this.needToTakeCourse;
    }
    public String toString() {
        String stringReturn = this.courseId + " " + this.professorName + " " + this.courseName;
        for (int i = 0; i < this.needToTakeCourse.size(); i++)
            stringReturn = stringReturn + " " + this.needToTakeCourse.get(i).toString();
        return stringReturn;
    }
}