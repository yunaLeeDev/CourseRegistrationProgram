import java.io.Serializable;
import java.util.StringTokenizer;

public class Registration implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String studentId;
    protected String courseId;

    public Registration(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();    	
    	this.courseId = stringTokenizer.nextToken();
    }
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
	public void addcourse(String inputcourseId) {
		this.courseId = courseId + inputcourseId;
	}
	public String toString() {
        String stringReturn = this.studentId + " " + this.courseId;
        return stringReturn;
    }
}
