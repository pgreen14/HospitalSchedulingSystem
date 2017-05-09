import java.util.Date;



public final class DoctorImpl implements Doctor  {
	
	int doctorID;
	String lname;
	String fname;
	String ssn;
	Date dob;
	MedicalSpecialty specialty = null;
	String specialtyName;

	
	public DoctorImpl(int doctorID, String fname, String lname, String email, String ssn, Date dob,
			MedicalSpecialty specialty) {
		// TODO Auto-generated constructor stub
		this.doctorID = doctorID;
		this.lname = lname;
		this.fname = fname;
		this.ssn = ssn;
		this.dob = dob;
		this.specialty = specialty;
	}

	@Override
	public int getDoctorID(){
		return doctorID;
	}
	
	@Override
	public String getLName(){
		return lname;
	}
	
	
	@Override
	public String getFName(){
		return fname;
	}
	
	@Override
	public String getSsn(){
		return ssn;
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Date getDob(){
		return dob;
	}
	
	@Override
	public MedicalSpecialty getSpecialty(){
		return specialty;
	}
	
	@Override
	public boolean equals (Object other){
		if (this == other){
			return true;
		}
		if (other == null || (this.getClass() != other.getClass())){
			return false;
		}
		
		Doctor d = (Doctor) other;
			return (this.doctorID == d.getDoctorID())&& (this.lname != null&&lname.equals(d.getLName()) &&(this.fname!= null&&fname.equals(d.getFName())&&(this.ssn != null && this.ssn==d.getSsn())&& (this.dob != null && this.dob==d.getDob())) && (this.getSpecialty() != null && this.specialty==d.getSpecialty()));
			}
	
	@Override 
	public int hashCode(){
		int result = 0;
		result = 31*result + doctorID;
		result = 31*result + (this.fname != null ? this.fname.hashCode() : 0);
		result = 31*result + (this.lname != null ? this.lname.hashCode() : 0);
		result = 31*result + (this.ssn != null ? this.ssn.hashCode() : 0);
		result = 31*result + (this.dob != null ? this.dob.hashCode() : 0);
		result = 31*result + (this.specialty != null ? this.specialty.hashCode() : 0);
		return result;
		
	}
	
	public void setDoctorID(int doctorID){
		this.doctorID= doctorID;
	}
	public void setLName(String lname){
		this.lname= lname;
	}
	public void setFName(String fname){
		this.fname= fname;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setSpecialty(MedicalSpecialty specialty) {
		this.specialty = specialty;
	}

}
