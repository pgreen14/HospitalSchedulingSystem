import java.util.Date;



public interface Doctor {

	int getDoctorID();
	String getLName();
	String getFName();
	String getSsn();
	Date getDob();
	MedicalSpecialty getSpecialty();
	String getEmail();
	
	void setDoctorID(int doctorID);
	void setLName(String lname);
	void setFName(String fname);
	void setEmail(String email);
	void setSsn(String ssn);
	void setDob(Date dob);
	void setSpecialty (MedicalSpecialty specialty);
	
}