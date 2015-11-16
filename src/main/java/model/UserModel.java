package model;

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
	
    public User getByMail(String mail) {
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        User user = (User)
        	    session.createQuery("select us from User us where us.userMail = :mail")
                .setString("mail", mail)
                .uniqueResult();

        tx.commit();
        HibernateUtil.closeSession();
        return user;
    }
    
    public Set<Userorder> getOrders(Short id) {
        User u = this.get(id);
		if(u == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Set<Userorder> orders = new HashSet<Userorder>();
        orders = u.getUserorders();

        tx.commit();
        HibernateUtil.closeSession();
        return orders;
    }
        
    public Set<Inscription> getInscriptions(Short id) {
        User u = this.get(id);
		if(u == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Set<Inscription> inscriptions = new HashSet<Inscription>();
        inscriptions = u.getInscriptions();

        tx.commit();
        HibernateUtil.closeSession();
        return inscriptions;
    }
    
    public Set<Cotisation> getCotisations(Short id) {
        User u = this.get(id);
		if(u == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Set<Cotisation> cotisations = new HashSet<Cotisation>();
        cotisations = u.getCotisations();

        tx.commit();
        HibernateUtil.closeSession();
        return cotisations;
    }
    
    public void createSubscription(Short id, Cotisation c) {
        User u = this.get(id);
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Cotisation cotisation = c;
        cotisation.setUser(u);
        
        session.save(cotisation);
        tx.commit();
        HibernateUtil.closeSession();
    }
    
    public Set<Notification> getNotifications(Short id) {
        User u = this.get(id);
		if(u == null)
			return null;
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Set<Notification> notifications = new HashSet<Notification>();
        notifications = u.getNotifications();

        tx.commit();
        HibernateUtil.closeSession();
        return notifications;
    }
    
    public void createNotification(Short id, Notification n) {
        User u = this.get(id);
		
    	Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        
        Notification notif = n;
        notif.setUser(u);
        
        session.save(notif);
        tx.commit();
        HibernateUtil.closeSession();
    }
	
}