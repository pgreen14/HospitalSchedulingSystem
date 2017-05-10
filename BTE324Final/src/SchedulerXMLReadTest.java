import java.io.IOException;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;





public class SchedulerXMLReadTest {
	private static final String INPUT_FILE = "resources//schedulerDataIn.xml";
	private static final String OUTPUT_FILE = "resources//schedulerDataOut.xml";
	
	
	public static void main(String[] args) throws XMLStreamException, IOException{
		
		SchedulerData sdList = SchedulerXMLReaderUtils.readSchedulerData(INPUT_FILE);
		SimpleDateFormat df2 = new SimpleDateFormat("MMMM dd, yyyy");
		
		Map<Integer, Patient> patientMap = new HashMap<>();
		Map<Integer, Doctor> doctorMap = new HashMap<>();
		
		
		for (int i = 0; i<sdList.getPatientList().size(); i++){
			patientMap.put(sdList.getPatientList().get(i).getPatientID(), sdList.getPatientList().get(i));

		}
		
		for (int j=0; j<sdList.getDoctorList().size(); j++){
			doctorMap.put(sdList.getDoctorList().get(j).getDoctorID(), sdList.getDoctorList().get(j));
		}
		
		System.out.println("Upcoming Visits Ordered by Visit Date");
		
		for (int i = 0; i < sdList.getVisitList().size(); i++){
			System.out.println("Visit date: \t\t" + df2.format(sdList.getVisitList().get(i).getVisitDate()));
				System.out.println("Doctor: \t\t" + doctorMap.get(sdList.v.get(i).getHost()).getFName()
									+ " " + doctorMap.get(sdList.v.get(i).getHost()).getLName());
				System.out.println("Specialty: \t\t" + doctorMap.get(sdList.v.get(i).getHost()).getSpecialty());
				System.out.println("\n");
		
		}
		SchedulerWriterUtils.writeSchedulerData(OUTPUT_FILE, sdList);
	}

}
