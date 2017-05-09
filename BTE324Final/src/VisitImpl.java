import java.util.Date;



public class VisitImpl<V, T> implements Visit<V,T> {

	V visitor;
	T host;
	Date visitDate;
	public VisitImpl(V visitor, T host, Date visitDate) {
		// TODO Auto-generated constructor stub
		this.visitor = visitor;
		this.host = host;
		this.visitDate = visitDate;
	}

	@Override
	public V getVisitor(){
		return visitor;
	}
	@Override
	public T getHost(){
		return host;
	}
	@Override
	public Date getVisitDate(){
		return visitDate;
	}
	
	@Override
	public boolean equals (Object other){
		if (this == other){
			return true;
		}
		if (other == null || (this.getClass() != other.getClass())){
			return false;
		}
		
		Visit<?, ?> v = (Visit<?,?>) other;
			return (this.visitor != null&&visitor.equals(v.getVisitor()) &&(this.host!= null&&host.equals(v.getHost())&&(this.visitDate != null && this.visitDate==v.getVisitDate())));
			}

	public int hashCode(){
		int result = 0;
		result = 31*result + (this.visitor != null ? this.visitor.hashCode() : 0);
		result = 31*result + (this.host != null ? this.host.hashCode() : 0);
		result = 31*result + (this.visitDate != null ? this.visitDate.hashCode() : 0);
		return result;
		
	}
	@Override
	public void setVisitor(V visitor){
		this.visitor = visitor;
	}
	@Override
	public void setHost(T host){
		this.host=host;
	}
	@Override
	public void setVisitDate(Date visitDate){
		this.visitDate = visitDate;
	}
	
	
	
}
