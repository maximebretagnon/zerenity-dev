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
	
	public Set<Excludeddate> getExcludedDates(Short id) {
        Event e = this.get(id);
		if(e == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Set<Excludeddate> dates = new HashSet<Excludeddate>();
        dates = e.getExcludeddates();

        tx.commit();
        HibernateUtil.closeSession();
        return dates;
    }
	
    public void addExcludedDate(Short id, Excludeddate ex) {
        Event e = this.get(id);
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Excludeddate exDate = ex;
        exDate.setEvent(e);
        
        session.save(exDate);
        tx.commit();
        HibernateUtil.closeSession();
    }
    
	public Set<Inscription> getRegistration(Short id) {
        Event e = this.get(id);
		if(e == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Set<Inscription> dates = new HashSet<Inscription>();
        dates = e.getInscriptions();

        tx.commit();
        HibernateUtil.closeSession();
        return dates;
    }
	
    public void addRegistration(Short id, Inscription i) {
        Event e = this.get(id);
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Inscription inscription = i;
        inscription.setEvent(e);
        
        session.save(inscription);
        tx.commit();
        HibernateUtil.closeSession();
    }
}
