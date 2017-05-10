import java.util.Date;



public final class DoctorImpl implements Doctor  {
	
	int doctorID;
	String lname;
	String fname;
	String email;
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
		this.email = email;
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
		return email;
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
		
		Doctor d1 = (Doctor) other;
			return (this.doctorID == d1.getDoctorID())&& (this.lname != null&&lname.equals(d1.getLName()) &&(this.fname!= null&&fname.equals(d1.getFName())&&(this.ssn != null && this.ssn==d1.getSsn())&& (this.dob != null && this.dob==d1.getDob())) && (this.getSpecialty() != null && this.specialty==d1.getSpecialty()));
			}
	
	@Override 
	public int hashCode(){
		int result = 3;
		result = 31*result + doctorID;
		result = 31*result + (this.fname != null ? this.fname.hashCode() : 0);
		result = 31*result + (this.lname != null ? this.lname.hashCode() : 0);
		result = 31*result + (this.email != null ? this.email.hashCode() : 0);
		result = 31*result + (this.ssn != null ? this.ssn.hashCode() : 0);
		result = 31*result + (this.dob != null ? this.dob.hashCode() : 0);
		result = 31*result + (this.specialty != null ? this.specialty.hashCode() : 0);
		return result;
		
	}
	
	@Override
	public void setDoctorID(int doctorID){
	
	
	}
	@Override
	public void setLName(String lname){

	}
	@Override
	public void setFName(String fname){

	}
	@Override
	public void setSsn(String ssn) {

	}
	@Override
	public void setDob(Date dob) {

	}
	@Override
	public void setSpecialty(MedicalSpecialty specialty) {

	}

	@Override
	public void setEmail(String email) {

		
	}
	
	

}
