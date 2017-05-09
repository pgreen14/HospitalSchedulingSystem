import java.util.Date;

public interface Visit<V, T> {
	
	V getVisitor();
	T getHost();
	Date getVisitDate();
	
	void setVisitor(V visitor);
	void setHost(T host);
	void setVisitDate(Date visitDate);


}
