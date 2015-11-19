package model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.HibernateUtil;
import domain.Event;
import domain.Excludeddate;
import domain.Inscription;

public class EventModel extends AbstractModel<Event, Short> {

	public EventModel(){
		super(Event.class);
	}
	
	public Set<Excludeddate> getExcludedDates(Short id) throws Exception {
        Event e = this.get(id);
		if(e == null)
			return null;
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Set<Excludeddate> dates = new HashSet<Excludeddate>();
				dates = e.getExcludeddates();

				tx.commit();
				return dates;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    	
        
        

    }
	
    public void addExcludedDate(Short id, Excludeddate exd) throws Exception {
        Event e = this.get(id);
		
        Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Excludeddate exDate = exd;
				exDate.setEvent(e);
        
				session.save(exDate);
        		tx.commit();
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
    
	public Set<Inscription> getRegistration(Short id) throws Exception {
        Event e = this.get(id);
		if(e == null)
			return null;
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Set<Inscription> dates = new HashSet<Inscription>();
				dates = e.getInscriptions();

				tx.commit();
				return dates;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
	
    public void addRegistration(Short id, Inscription i) throws Exception {
        Event e = this.get(id);
		
        Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Inscription inscription = i;
				inscription.setEvent(e);
	        
				session.save(inscription);
				tx.commit();	
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
}
