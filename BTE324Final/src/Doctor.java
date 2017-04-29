import java.util.Date;


public class Doctor {
	
	private int doctorID;
	private Name name;
	private String email;
	private String ssn;
	private Date dob;
	private MedicalSpecialty specialty;
	
	public Doctor(int doctorID, Name name, String email, String ssn, Date dob, MedicalSpecialty specialty){
		this.doctorID= doctorID;
		this.name= name;
		this.email= email;
		this.ssn= ssn;
		this.dob= dob;
		this.specialty= specialty;
	}

	public int getDoctorID() {
		return doctorID;
	}
	public Name getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getSsn() {
		return ssn;
	}
	public Date getDob() {
		return dob;
	}
	public MedicalSpecialty getSpecialty() {
		return specialty;
	}

	@Override
	public String toString() {
		//DateFormat df = new SimpleDateFormat(DOB_FORMAT);
		return "Doctor\nID=" + doctorID + "Doctor\nName=" + name + "\nssn=" + ssn + "\nEmail=" + email + "\ndob=" + dob;
	}



}
