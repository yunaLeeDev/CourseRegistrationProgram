
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentList {
	protected ArrayList<Student> vStudent;
	
	public StudentList(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) this.vStudent.add(new Student(stuInfo));	
		}
		objStudentFile.close();
	}

	public ArrayList<Student> getAllStudentRecords() throws NullDataException{
		if(this.vStudent.size() == 0) throw new NullDataException("StudentDataIsNull");
		return this.vStudent;
	}
	public boolean addStudentRecords(String studentInfo) {
		if(this.vStudent.add(new Student(studentInfo))) {
			updateFile("Students.txt");
			return true;
		}
		else return false;
	}
	
	private void updateFile(String string) {
		try { File file = new File(string);
			if (!file.exists())
				file.createNewFile();
			String studentInfo = "";
			BufferedWriter studentFileWriter = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < this.vStudent.size(); i++) 
				studentInfo = studentInfo + "\r\n" + vStudent.get(i).toString();
			studentFileWriter.write(studentInfo);
			studentFileWriter.flush();
			studentFileWriter.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	public boolean deleteStudentRecords(String studentId) {
		for(int i=0;i<this.vStudent.size();i++) {
			Student student = (Student) this.vStudent.get(i);
			if(student.matchId(studentId)) 
				if(this.vStudent.remove(student)) {
					updateFile("Students.txt");
					return true;
				}
				else return false;			
		}
		return false;
	}

	public String getCompletedCourses(String studentId) throws NullDataException{
		if(this.vStudent.size() == 0) {
			throw new NullDataException("Complete course List is Null");
		}
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student student = (Student) this.vStudent.get(i);
			if (student.matchId(studentId)) {
				return student.getCompletedCourses();		
			}
		}
		return null;
	}
}

