import java.util.Date;

public class Visit<V, T> {

	private V visitor;
	private T host;
	private Date visitDate;
	
	public Visit (V visitor, T host, Date visitDate){
		this.visitor = visitor;
		this.host = host;
		this.visitDate = visitDate;
	}
	
	
	public V getVisitor() {
		return visitor;
	}
	public T getHost() {
		return host;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	
	@Override
	public String toString(){
		return "Patient\nID=" + visitor + "Doctor\nID=" + host + "Visit\nDate=" + visitDate;
		
	}
	
}
