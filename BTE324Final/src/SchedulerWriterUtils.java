import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;


public final class SchedulerWriterUtils extends SchedulerReadWriteUtils {
	
	private final static String NAMESPACE = "http://SCHEDULER";
	private final static String SCHEMA_INSTANCE_PREFIX = "xsi";
	private final static String SCHEMA_INSTANCE_NS = "http://www.w3.org/2001/XMLSchema-instance";
	private final static String SCHEMA_LOCATION_ATTRNAME = "schemaLocation";
	private final static String SCHEMA_FILE_NAME = "scheduling.xsd";

	public static void writeName(XMLEventFactory eventFactory, XMLEventWriter eventWriter, String fname, String lname , int level) throws XMLStreamException {
		// first, write as many tabs as levels needed
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		// start element
		eventWriter.add(eventFactory.createStartElement("", "", NAME));
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
		// first name
		XMLWriterUtils.writeNode(eventFactory, eventWriter, FIRST_NAME, fname, level + 1);
				// last name
		XMLWriterUtils.writeNode(eventFactory, eventWriter, LAST_NAME, lname, level + 1);
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level)); // also indent it
		eventWriter.add(eventFactory.createEndElement("", "", NAME));
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
	}

	public static void writePatient(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Patient e, int level) throws XMLStreamException {
		// writes a single patient through to the XML event writer
		// creating the patient start element
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		StartElement patientStart = eventFactory.createStartElement("", "", PATIENT);
		eventWriter.add(patientStart);
		// creating the id attribute
		// note the use of Integer.toString to get a string representation
		Attribute patientID = eventFactory.createAttribute(PATIENTID, Integer.toString(e.getPatientID()));
		eventWriter.add(patientID);
		// creating the SSN attribute
		Attribute ssn = eventFactory.createAttribute(SSN, e.getSsn());
		eventWriter.add(ssn);
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
		// now creating the nested elements
		writeName(eventFactory, eventWriter, e.getFName(), e.getLName(), level);
		XMLWriterUtils.writeNode(eventFactory, eventWriter, EMAIL, e.getEmail(), level + 1);
		XMLWriterUtils.writeDate(eventFactory, eventWriter, DOB, e.getDob(), level + 1);
		// create the patient end element
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		EndElement patientEnd = eventFactory.createEndElement("", "", PATIENT);
		eventWriter.add(patientEnd);
	}
	
	public static void writeDoctor(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Doctor e, int level) throws XMLStreamException {
		// writes a single doctor through to the XML event writer
		// create the doctor start element
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
	    StartElement doctorStart = eventFactory.createStartElement("", "", DOCTOR);
	    eventWriter.add(doctorStart);
	    // create the id attribute
	    // note the use of Integer.toString to get a string representation
	    Attribute doctorID = eventFactory.createAttribute(DOCTORID, Integer.toString(e.getDoctorID()));
	    eventWriter.add(doctorID);
	    // create the SSN attribute
	    Attribute ssn = eventFactory.createAttribute(SSN, e.getSsn());
	    eventWriter.add(ssn);
		eventWriter.add(eventFactory.createIgnorableSpace("\n")); // line feed for readability
	    // now create the nested elements
	    writeName(eventFactory, eventWriter, e.getFName(), e.getLName(), level);
	    XMLWriterUtils.writeNode(eventFactory, eventWriter, EMAIL, e.getEmail(), level + 1);
	    XMLWriterUtils.writeDate(eventFactory, eventWriter, DOB, e.getDob(), level + 1);
	    // create the doctor end element
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
	    EndElement doctorEnd = eventFactory.createEndElement("", "", DOCTOR);
	    eventWriter.add(doctorEnd);
	}
	
	public static void writeVisit(XMLEventFactory eventFactory, XMLEventWriter eventWriter, Visit<Integer, Integer> e,
			int level) throws XMLStreamException {
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
		StartElement visitStart = eventFactory.createStartElement("", "", VISIT);
		eventWriter.add(visitStart);
		
		Attribute patientID = eventFactory.createAttribute(PATIENTID, Integer.toString(e.getVisitor()));
		eventWriter.add(patientID);
		
		Attribute doctorID = eventFactory.createAttribute(DOCTORID, Integer.toString(e.getHost()));
		eventWriter.add(doctorID);
		
		XMLWriterUtils.writeDate(eventFactory, eventWriter, DATE, e.getVisitDate(), level + 1);
		
		eventWriter.add(XMLWriterUtils.getIndentation(eventFactory, level));
	    EndElement visitEnd = eventFactory.createEndElement("", "", VISIT);
	    eventWriter.add(visitEnd);
		
		
	}
	

	public static void writeSchedulerData (String outFile, SchedulerData sdList) throws XMLStreamException, IOException {
	    // Create a XMLOutputFactory
	    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	    // Create XMLEventWriter
	    Path outputFilePath = Paths.get(outFile);
	    Writer outputFile = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
	    XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputFile);
	    // Create an XMLEventFactory
	    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	    // Create and write Start Tag
	    StartDocument startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
	    eventWriter.add(startDocument);
	    // put a linefeed for readability
	    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	 // create the root element
	 		StartElement root = eventFactory.createStartElement("", "", "schedulerList");
	 		eventWriter.add(root);
	 		eventWriter.setDefaultNamespace(NAMESPACE); // set the default namespace for the root before add it
	 		// add any other namespaces to the root
	 		eventWriter.add(eventFactory.createNamespace(NAMESPACE));
	 		eventWriter.add(eventFactory.createNamespace(SCHEMA_INSTANCE_PREFIX, 
	 				SCHEMA_INSTANCE_NS));
	 		// add the schema attributes to the root element
	 		String schemaLocationArg = NAMESPACE + " " + SCHEMA_FILE_NAME;
	 		eventWriter.add(eventFactory.createAttribute(SCHEMA_INSTANCE_PREFIX, 
	 				SCHEMA_INSTANCE_NS, SCHEMA_LOCATION_ATTRNAME, schemaLocationArg));
	 		// iterate over the list of students and create an element for each
	  for (Patient pdata : sdList.p) {
			writePatient(eventFactory, eventWriter, pdata, 1); // write the student with one level of indentation
		    eventWriter.add(eventFactory.createIgnorableSpace("\n"));
		}
	  
	  for (Doctor ddata : sdList.d){
		  writeDoctor(eventFactory, eventWriter, ddata, 1);
		  eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	  }
	  for (Visit<Integer,Integer> vdata: sdList.v){
		  writeVisit(eventFactory, eventWriter, vdata, 1);
		  eventWriter.add(eventFactory.createIgnorableSpace("\n"));
	  }
	  eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	

	


}


