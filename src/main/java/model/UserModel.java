package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.HibernateUtil;
import domain.*;

public class UserModel extends AbstractModel<User, Short> {

	public UserModel(){
		super(User.class);
	}
	
    public User getByMail(String mail) throws Exception {
    	Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				User user = (User)
        	    session.createQuery("select us from User us where us.userMail = :mail")
                .setString("mail", mail)
                .uniqueResult();

				tx.commit();
        		return user;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
    
    public Set<Userorder> getOrders(Short id) throws Exception {
        User u = this.get(id);
		if(u == null)
			return null;
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Set<Userorder> orders = new HashSet<Userorder>();
				orders = u.getUserorders();

				tx.commit();
				return orders;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
        
    public Set<Inscription> getInscriptions(Short id) throws Exception {
        User u = this.get(id);
		if(u == null)
			return null;
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Set<Inscription> inscriptions = new HashSet<Inscription>();
		        inscriptions = u.getInscriptions();
		
		        tx.commit();
		        return inscriptions;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
    
    public Set<Cotisation> getCotisations(Short id) throws Exception {
        User u = this.get(id);
		if(u == null)
			return null;
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Set<Cotisation> cotisations = new HashSet<Cotisation>();
				cotisations = u.getCotisations();

				tx.commit(); 
        		return cotisations;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
    
    public void createSubscription(Short user_id, Formule f) throws Exception {
        User u = this.get(user_id);
		
        Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Cotisation cotisation = new Cotisation();
				cotisation.setUser(u);
				cotisation.setFormule(f);
				cotisation.setOrderDate(new Date());
        		session.save(cotisation); 
        		
        		u.setIsMember(true);
        		session.update(u);
        		
        		tx.commit();
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
    }
    
    public Set<Notification> getNotifications(Short id) throws Exception {
        User u = this.get(id);
		if(u == null)
			return null;
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Set<Notification> notifications = new HashSet<Notification>();
				notifications = u.getNotifications();

        		tx.commit(); 
        		return notifications;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}   
    }
    
    public void createNotification(Short id, Notification n) throws Exception {
        User u = this.get(id);
		
        Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Notification notif = n;
				notif.setUser(u);
        
				session.save(notif);
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
