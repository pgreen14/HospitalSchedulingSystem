import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;








public class SchedulerXMLReaderUtils extends SchedulerReadWriteUtils {


	//Patient Reader

	private final static String DOB_FORMAT = "yyyy-MM-dd";
	
	public static Patient readPatient(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a patient but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(PATIENT)) {
			throw new IllegalStateException("Attempting to read a patient at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		
		int patientID = 0;
		String ssn = null;
		@SuppressWarnings("unchecked")
		Iterator<Attribute> attributes = firstEvent.asStartElement().getAttributes();
		while (attributes.hasNext()) {
			Attribute attribute = attributes.next();
			if (attribute.getName().getLocalPart().equals(PATIENTID)) {
				patientID = Integer.valueOf(attribute.getValue()); // we know it is an integer from the schema
			}
			else if (attribute.getName().getLocalPart().equals(SSN)) {
				ssn = attribute.getValue();
			}
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		
		Patient p = null;
		String fname = null;
		String lname = null;
		String email = null;
		Date dob = null;
		boolean finished = false;
		while (!finished) {
			XMLEvent event = eventReader.peek();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
	
			//Name Element within Patient
				if (startElement.getName().getLocalPart().equals(NAME)) {
					XMLEvent firstNameEvent = eventReader.nextEvent();
					if (!firstNameEvent.isStartElement()) {
						throw new IllegalStateException("Attempting to read a name but not a start element: found event of type " + firstNameEvent.getEventType());
					}
					else if (!firstNameEvent.asStartElement().getName().getLocalPart().equals(NAME)) {
						throw new IllegalStateException("Attempting to read a name at the wrong start element: found " + firstEvent.asStartElement().getName());
					}
					boolean finishedName = false;
					while (!finishedName) {
						XMLEvent eventName = eventReader.nextEvent();
						// check the start elements for the nested elements inside NAME
						if (eventName.isStartElement()) {
							StartElement startElementName = eventName.asStartElement();
							if (startElementName.getName().getLocalPart().equals(FIRST_NAME)) {
								eventName = eventReader.nextEvent();
								fname = eventName.asCharacters().getData();
							}
							else if (startElementName.getName().getLocalPart().equals(LAST_NAME)) {
								eventName = eventReader.nextEvent();
								lname = eventName.asCharacters().getData();
							}
							else {
								System.err.println("Unrecognized element, ignoring: " + startElement.getName());
							}
						}
						// check the end elements to find where the name element is closed
						else if (eventName.isEndElement()) {
							EndElement endElementName = eventName.asEndElement();
							// when the end element is the name element, create the name return object;
							if (endElementName.getName().getLocalPart().equals(NAME)) {
								// Schema makes these required, so they must exist
								// would be a good practice to check for existence anyways
					
								finishedName = true;
							}
						}
						else {
							// ignore other events, such as character events with line feeds and tabs
						}
					}
				
			
				}
				else if (startElement.getName().getLocalPart().equals(EMAIL)) {
					email = XMLReaderUtils.readCharacters(eventReader, EMAIL);
				}
				else if (startElement.getName().getLocalPart().equals(DOB)) {
					dob = XMLReaderUtils.readDate(eventReader, DOB, DOB_FORMAT);
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent(); 
				}
				
		}
			else if (event.isEndElement()) {
				event = eventReader.nextEvent(); 
				EndElement endElement = event.asEndElement();
				
				if (endElement.getName().getLocalPart().equals(PATIENT)) {
					
					p = new PatientImpl(patientID, fname, lname, email, ssn, dob);
					finished = true;
				}
			}
			else {
				
				event = eventReader.nextEvent(); 
		}
	}
		return p;
	}

	//Doctor Reader
	public static Doctor readDoctor(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a doctor but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(DOCTOR)) {
			throw new IllegalStateException("Attempting to read a doctor at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		
		int doctorID = 0;
		String ssn = null;
		@SuppressWarnings("unchecked")
		Iterator<Attribute> attributes = firstEvent.asStartElement().getAttributes();
		while (attributes.hasNext()) {
			Attribute attribute = attributes.next();
			if (attribute.getName().getLocalPart().equals(DOCTORID)) {
				doctorID = Integer.valueOf(attribute.getValue()); // we know it is an integer from the schema
			}
			else if (attribute.getName().getLocalPart().equals(SSN)) {
				ssn = attribute.getValue();
			}
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
		
		Doctor doctor = null;
		String fname = null;
		String lname = null;
		String email = null;
		Date dob = null;
		MedicalSpecialty specialty = null;
		boolean finished = false;
		while (!finished) {
			XMLEvent event = eventReader.peek();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
			
				//Reads Name element
				if (startElement.getName().getLocalPart().equals(NAME)) {
						XMLEvent firstNameEvent = eventReader.nextEvent();
						if (!firstNameEvent.isStartElement()) {
							throw new IllegalStateException("Attempting to read a name but not a start element: found event of type " + firstNameEvent.getEventType());
						}
						else if (!firstNameEvent.asStartElement().getName().getLocalPart().equals(NAME)) {
							throw new IllegalStateException("Attempting to read a name at the wrong start element: found " + firstNameEvent.asStartElement().getName());
						}
						boolean finishedName = false;
						while (!finishedName) {
							XMLEvent eventName = eventReader.nextEvent();
							// check the start elements for the nested elements inside NAME
							if (eventName.isStartElement()) {
								StartElement startElementName = eventName.asStartElement();
								if (startElementName.getName().getLocalPart().equals(FIRST_NAME)) {
									eventName = eventReader.nextEvent();
									fname = eventName.asCharacters().getData();
								}
								else if (startElementName.getName().getLocalPart().equals(LAST_NAME)) {
									eventName = eventReader.nextEvent();
									lname = eventName.asCharacters().getData();
								}
								else {
									System.err.println("Unrecognized element, ignoring: " + startElement.getName());
								}
							}
							// check the end elements to find where the name element is closed
							else if (eventName.isEndElement()) {
								EndElement endElementName = eventName.asEndElement();
								// when the end element is the name element, create the name return object;
								if (endElementName.getName().getLocalPart().equals(NAME)) {
									// Schema makes these required, so they must exist
									// would be a good practice to check for existence anyways
						
									finishedName = true;
								}
							}
							else {
								// ignore other events, such as character events with line feeds and tabs
							}
						}
					
				}
				else if (startElement.getName().getLocalPart().equals(EMAIL)) {
					email = XMLReaderUtils.readCharacters(eventReader, EMAIL);
				}
				else if (startElement.getName().getLocalPart().equals(DOB)) {
					dob = XMLReaderUtils.readDate(eventReader, DOB, DOB_FORMAT);
				}
				else if (startElement.getName().getLocalPart().equals(SPECIALTY)) {
					String s = XMLReaderUtils.readCharacters(eventReader, SPECIALTY);
					specialty = MedicalSpecialty.valueOf(s);
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent(); 
				}
				
		}
			else if (event.isEndElement()) {
				event = eventReader.nextEvent(); 
				EndElement endElement = event.asEndElement();
				
				if (endElement.getName().getLocalPart().equals(DOCTOR)) {
					doctor = new DoctorImpl(doctorID, fname, lname, email, ssn, dob, specialty);
					
					finished = true;
				}
			}
			else {
				
				event = eventReader.nextEvent(); 
		}
	}
		return doctor;
	}
	
	//Visitor Reader
	public static Visit<Integer, Integer> readVisit(XMLEventReader eventReader) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent();
		if (!firstEvent.isStartElement()){
			throw new IllegalStateException("Attempting to read a visit but not a " + "start elemenet: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(VISIT)) {
			throw new IllegalStateException("Attempting to read a doctor at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		Visit <Integer, Integer> v = null;
		int patientID = 0;
		int doctorID = 0;
		Date visitDate = null;
		String dateFormat = "yyyy-MM-dd";
		
		@SuppressWarnings("unchecked")
		Iterator<Attribute> attributes = firstEvent.asStartElement().getAttributes();
		while (attributes.hasNext()) {
			Attribute attribute = attributes.next();
			if(attribute.getName().getLocalPart().equals(PATIENTID)) {
				patientID = Integer.valueOf(attribute.getValue());
				}
			else if (attribute.getName().getLocalPart().equals(DOCTORID)) {
				doctorID = Integer.valueOf(attribute.getValue());
			}
			else if (attribute.getName().getLocalPart().equals(DATE)){

					String dateStr = attribute.getValue();
					DateFormat df = new SimpleDateFormat(dateFormat);
					try {
						visitDate = (Date)df.parse(dateStr);
					} catch (ParseException e) {
						e.printStackTrace();
					}

			}
			else {
				System.err.println("Found unknown attribute, ignoring; found: " + attribute.getName());
			}
		}
			boolean finished = false;
			while(!finished){
				XMLEvent event = eventReader.peek();
				if (event.isEndElement()) {
					event = eventReader.nextEvent();
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart().equals("visit")){
						v = new VisitImpl<Integer,Integer>(patientID, doctorID, visitDate);
						finished = true;
					}
				}
				else {
					event = eventReader.nextEvent();
				}
		}
		return v;
	}
		

	
	//Scheduler Data Reader
	public static SchedulerData readSchedulerData(String xmlFile) throws XMLStreamException, IOException {
		
		ArrayList<Patient> p = new ArrayList<Patient>();
		ArrayList<Doctor> d = new ArrayList<Doctor>();
		ArrayList<Visit<Integer, Integer>> v = new ArrayList<Visit<Integer, Integer>>();
		SchedulerData scheduler = new SchedulerData(p, d, v);
		
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		Path xmlFilePath = Paths.get(xmlFile);
		Reader in = Files.newBufferedReader(xmlFilePath, StandardCharsets.UTF_8);
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		
		//Reads XML Document
		while(eventReader.hasNext()){
			//peek into next event to make sure the event happened
			XMLEvent event = eventReader.peek();
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == (SCHEDULER_DATA)) {
					event = eventReader.nextEvent();
				}
				
				else if (startElement.getName().getLocalPart() == (PATIENT)) {
					Patient pdata = readPatient(eventReader);
					p.add(pdata);
				}
				
				else if (startElement.getName().getLocalPart() == (DOCTOR)) {
					Doctor ddata = readDoctor(eventReader);
					d.add(ddata);
				}
				
				else if (startElement.getName().getLocalPart() == (VISIT)) {
					Visit<Integer, Integer> vdata = readVisit(eventReader);
				v.add(vdata);
				}
				else {
					System.err.println("Unrecognized element, ignoring: " + startElement.getName());
					event = eventReader.nextEvent(); // skip this event and read the next
				}
		}
			else {
				event = eventReader.nextEvent();
			}	
			
	}
		eventReader.close();
	
		return scheduler;
		
	}
}


