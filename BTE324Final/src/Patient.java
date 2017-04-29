
import java.util.Date;


public class Patient {
	private int patientID;
	private Name name;
	private String email;
	private String ssn;
	private Date dob;
	//private int age;
	
	
	public Patient(int patientID, Name name, String ssn, Date dob, String email){
		
		this.patientID = patientID;
		this.name = name;
		this.ssn = ssn;
		this.dob = dob;
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public int getPatientID()
	{
		return patientID;
	}
	
	public Name getName(){
		
		return name;
	}
	
	
	public String getSSN(){
		return ssn;
	}
	
	public Date getDob(){
		
		return dob;
	}
	
	/*public int getAge(){
		return age;
	}*/
	

	
	
	@Override
	public String toString() {
		return "Patient\nID=" + patientID + "Patient\nName=" + name + "\nssn=" + ssn + "\nEmail=" + email + "\ndob=" + dob;
	}


}
