import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RegistrationList {
	protected ArrayList<Registration> vRegistration;

	public RegistrationList(String courseFileName) throws FileNotFoundException, IOException {
		BufferedReader registrationFile = new BufferedReader(new FileReader(courseFileName));
		this.vRegistration = new ArrayList<Registration>();
		while (registrationFile.ready()) {
			String registInfo = registrationFile.readLine();
			if (!registInfo.equals("")) this.vRegistration.add(new Registration(registInfo));
		}
		registrationFile.close();
	}

	public boolean addRegist(String studentId, String courseId) {
			for (int i = 0; i < this.vRegistration.size(); i++) {
				Registration registration = (Registration) this.vRegistration.get(i);
				if (registration.match(studentId)) {
					this.vRegistration.get(i).addcourse(courseId);
					updateFile("Registration.txt");
					return true;}
				} 
			String registInfo = studentId+" "+courseId; 
			if(this.vRegistration.add(new Registration(registInfo))) {
				updateFile("Registration.txt");
				return true;} 
			 return false;
	}

	public ArrayList<Registration> getAllRegistrationRecords() throws NullDataException{
		if(this.vRegistration.size() == 0) throw new NullDataException("RegistrationDataIsNull");
		return this.vRegistration;
	}
	
	private void updateFile(String string) {
		try {File file = new File(string);
			if (!file.exists()) 
				file.createNewFile();	
			String registInfo = "";
			BufferedWriter registrationFileWriter = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < this.vRegistration.size(); i++) 
				registInfo = registInfo + "\r\n" + vRegistration.get(i).toString();
			registrationFileWriter.write(registInfo);
			registrationFileWriter.flush();
			registrationFileWriter.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}

