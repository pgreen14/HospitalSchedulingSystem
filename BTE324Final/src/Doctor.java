import java.util.Date;



public interface Doctor {

	int getDoctorID();
	String getLName();
	String getFName();
	String getSsn();
	Date getDob();
	MedicalSpecialty getSpecialty();
	String getEmail();
	
}