package model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.HibernateUtil;
import domain.Activity;
import domain.Event;

public class ActivityModel extends AbstractModel<Activity, Short> {

	public ActivityModel(){
		super(Activity.class);
	}
	
    public Set<Event> getEvents(Short id) throws Exception {
        Activity a = this.get(id);
		if(a == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
    	try {
	        Transaction tx = session.beginTransaction();
	        try{
		        Set<Event> events = new HashSet<Event>();
		        events = a.getEvents();
		
		        tx.commit();
		        return events;
	        }catch(Exception ex){
	        	tx.rollback();
	        	throw(ex);
	        }
    	}finally{
    		HibernateUtil.closeSession();
    	}
    }
}
