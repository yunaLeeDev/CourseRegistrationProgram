import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogList {

		protected ArrayList<String> vLog;

		public LogList(String logFileName) throws FileNotFoundException, IOException {
			BufferedReader logFile = new BufferedReader(new FileReader(logFileName));
			this.vLog = new ArrayList<String>();
			while (logFile.ready()) {
				String logInfo = logFile.readLine();
				if (!logInfo.equals("")) {
					this.vLog.add(logInfo);
				}
			}
			logFile.close();
		}	

		public boolean addLog(String logInfo) {
				if(this.vLog.add(logInfo)) {
				updateFile("Log.txt");
				return true;
				} 
				else return false;
		}

		public ArrayList<String> getAllRegistrationRecords() {
			return this.vLog;
		}
		
		private void updateFile(String string) {
			try {File file = new File(string);
				if (!file.exists()) {
					file.createNewFile();
				}
				String logInfo = "";
				BufferedWriter logFileWriter = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < this.vLog.size(); i++) 
					logInfo = logInfo + "\r\n" + vLog.get(i).toString();
				logFileWriter.write(logInfo);
				logFileWriter.flush();
				logFileWriter.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		}
	}



