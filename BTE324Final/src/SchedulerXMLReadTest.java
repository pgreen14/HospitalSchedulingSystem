import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;




public class SchedulerXMLReadTest {
	private static final String INPUT_FILE = "resources//schedulerDataIn.xml";
	private static final String OUTPUT_FILE = "resources//schedulerDataOut.xml";
	
	
	public static void main(String[] args) throws XMLStreamException, IOException{
		
		SchedulerData sdList = SchedulerXMLReaderUtils.readSchedulingXML(INPUT_FILE);
		
		Map<Integer, Patient> patientMap = new HashMap<>();
		Map<Integer, Doctor> doctorMap = new HashMap<>();
		
		
		for (int i = 0; i<sdList.p.size(); i++){
			patientMap.put(sdList.p.get(i).getPatientID(), sdList.p.get(i));
		}
		
		for (int j=0; j<sdList.d.size(); j++){
			doctorMap.put(sdList.d.get(j).getDoctorID(), sdList.d.get(j));
		}
		
		
		
		
		SchedulerWriterUtils.writeSchedulerData(OUTPUT_FILE, sdList);
	}

}
