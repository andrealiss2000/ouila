package repositories;

import java.util.Date;
import java.util.List;

import domain.SClass;

public interface ClassRepository {
	
	Integer addClass(Integer idSubject, Integer idTeacher, Date start, Date end); 
	
	List<SClass> getSClass ();
}
