//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public abstract class XMLReaderUtils {
	
	public static String readCharacters(XMLEventReader eventReader, String elementName) throws XMLStreamException {
		XMLEvent firstEvent = eventReader.nextEvent(); // gets the next event
		// first make sure that the current event is the start element of name
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a " + elementName + " but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(elementName)) {
			throw new IllegalStateException("Attempting to read a " + elementName + " at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		String chars = eventReader.nextEvent().asCharacters().getData();
		return chars;
	}

	public static Date readDate(XMLEventReader eventReader, String elementName, String dateFormat) throws XMLStreamException {
		//Date date= null;
		XMLEvent firstEvent = eventReader.nextEvent(); // gets the next event
		// first make sure that the current event is the start element of name
		Date date= null;
		if (!firstEvent.isStartElement()) {
			throw new IllegalStateException("Attempting to read a " + elementName + " but not a start element: found event of type " + firstEvent.getEventType());
		}
		else if (!firstEvent.asStartElement().getName().getLocalPart().equals(elementName)) {
			throw new IllegalStateException("Attempting to read a " + elementName + " at the wrong start element: found " + firstEvent.asStartElement().getName());
		}
		String dateStr = eventReader.nextEvent().asCharacters().toString();
		DateFormat df = new SimpleDateFormat(dateFormat);
		//Call a conversion of date string into a date and then make equal date to that
		try {
			date = (Date)df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		return date;
	}

}
