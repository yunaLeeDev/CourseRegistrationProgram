import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Course> vCourse;
	
	public CourseList(String courseFileName) throws FileNotFoundException, IOException {
		BufferedReader courseFile = new BufferedReader(new FileReader(courseFileName));
		this.vCourse = new ArrayList<Course>();
		while (courseFile.ready()) {
			String courseInfo = courseFile.readLine();
			if (!courseInfo.equals("")) this.vCourse.add(new Course(courseInfo));
		}
		courseFile.close();
	}
	public boolean addCourseRecords(String courseInfo) {
		if(this.vCourse.add(new Course(courseInfo))) {
			updateFile("Courses.txt");
			return true;
		}
		else return false;
	}
	
	private void updateFile(String string) {
		try {
			File file = new File(string);
			if (!file.exists()) 
			file.createNewFile();
			String courseInfo = "";
			BufferedWriter courseFileWriter = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < this.vCourse.size(); i++) 
				courseInfo = courseInfo + "\r\n" + vCourse.get(i).toString();
			courseFileWriter.write(courseInfo);
			courseFileWriter.flush();
			courseFileWriter.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public boolean deleteCourseRecords(String courseId) {
		for(int i=0;i<this.vCourse.size();i++) {
			Course course = (Course) this.vCourse.get(i);
			if(course.match(courseId))
				if(this.vCourse.remove(course)) return true;
				else return false;			
		}
		return false;
	}
	
	public ArrayList<Course> getAllCourseRecords() {
		return this.vCourse;
	}
	
	public ArrayList<String> getneedToTakeCourseList(String courseId) throws NullDataException{
		if(this.vCourse.size() == 0) {
			throw new NullDataException("Course Need To Take List is Null");
		}
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.match(courseId)) return course.getneedToTakeCourseList();
		}
		return null;
	}
}
