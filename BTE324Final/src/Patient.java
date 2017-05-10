
import java.util.Date;

public interface Patient {
	
	int getPatientID();
	String getLName();
	String getFName();
	String getEmail();
	String getSsn();
	Date getDob();
	
	void setPatientID(int patientID);
	void setLName(String lname);
	void setFName(String fname);
	void setEmail(String email);
	void setSsn(String ssn);
	void setDob(Date dob);


	

}
