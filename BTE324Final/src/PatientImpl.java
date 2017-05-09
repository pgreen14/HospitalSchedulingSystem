
import java.util.Date;


public final class PatientImpl implements Patient {
	int patientID;
	String lname;
	String fname;
	String ssn;
	Date dob;

	public PatientImpl(int patientID, String fname, String lname, String email, String ssn, Date dob) {
		// TODO Auto-generated constructor stub
		this.patientID = patientID;
		this.fname = fname;
		this.lname= lname;
		this.ssn = ssn;
		this.dob = dob;
	}
	@Override
	public int getPatientID(){
		return patientID;
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
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getSsn(){
		return ssn;
	}
	
	@Override
	public Date getDob(){
		return dob;
	}
	
	
	@Override
	public boolean equals (Object other){
		if (this == other){
			return true;
		}
		if (other == null || (this.getClass() != other.getClass())){
			return false;
		}
		
		Patient p = (Patient) other;
			return (this.patientID == p.getPatientID())&& (this.lname != null&&lname.equals(p.getLName()) &&(this.fname!= null&&fname.equals(p.getFName())&&(this.ssn != null && this.ssn==p.getSsn())&& (this.dob != null && this.dob==p.getDob())));
			}
	
	@Override 
	public int hashCode(){
		int result = 0;
		result = 31*result + patientID;
		result = 31*result + (this.fname != null ? this.fname.hashCode() : 0);
		result = 31*result + (this.lname != null ? this.lname.hashCode() : 0);
		result = 31*result + (this.ssn != null ? this.ssn.hashCode() : 0);
		result = 31*result + (this.dob != null ? this.dob.hashCode() : 0);
		return result;
		
	}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public void setPatientID(int patientID) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLName(String lname) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setFName(String fname) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}
	
}
