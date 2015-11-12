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
	
    public Set<Notification> getNotification(Short id) {
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
