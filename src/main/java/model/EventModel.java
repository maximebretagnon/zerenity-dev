package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.HibernateUtil;
import domain.*;

public class EventModel extends AbstractModel<Event, Short> {

	public EventModel(){
		super(Event.class);
	}
	
	public Short save(JSONEvent e) throws Exception{
		Event event = new Event();
		event.setEventName(e.getEventName());
		event.setEventPrice(e.getEventPrice());
		event.setEventStartDate(e.getEventStartDate());
		event.setEventEndDate(e.getEventEndDate());
		
		if(e.getRepetitionId() != 0){
			RepetitionModel rm = new RepetitionModel();
			Repetition r = rm.get(e.getRepetitionId());
			event.setRepetition(r);
		}
		UserModel um = new UserModel();
		User u = um.get(e.getUserId());
		event.setUser(u);
		ActivityModel am = new ActivityModel();
		Activity a = am.get(e.getActivityId());
		event.setActivity(a);
		RoomModel rom = new RoomModel();
		Room room = rom.get(e.getRoomId());
		event.setRoom(room);
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Short eventId = (Short)session.save(event);		
        		tx.commit();
        		return eventId;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
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
				inscription.setInscriptionDate(new Date());
	        
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
