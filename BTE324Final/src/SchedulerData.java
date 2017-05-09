import java.util.ArrayList;

public class SchedulerData {
	
	//Patient pdata;
	//Doctor ddata;
	//Visit vdata;
	
	ArrayList<Patient> p = new ArrayList<Patient>();
	ArrayList<Doctor> d = new ArrayList<Doctor>();
	ArrayList<Visit<Integer,Integer>> v = new ArrayList<Visit<Integer,Integer>>();

	
	public SchedulerData(ArrayList<Patient> plist, ArrayList<Doctor> dlist, ArrayList<Visit<Integer,Integer>> vlist) {
		// TODO Auto-generated constructor stub
		p = plist;
		d = dlist;
		v = vlist;
	}
	
	public ArrayList<Patient> getPatientList()
	{
		return p;
	}
	
	public ArrayList<Doctor> getDoctorList(){
		
		return d;
	}
	
	public ArrayList<Visit<Integer,Integer>> getVisitList (){
		
		return v;
	}
	
	public void addPatient(Patient pdata){
		p.add(pdata);
	}
	
	public void addDoctor(Doctor ddata){
		d.add(ddata);
	}
	
	public void addVisit(Visit<Integer,Integer> vdata){
		v.add(vdata);
	}
	
}

