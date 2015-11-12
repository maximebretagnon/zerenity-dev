package model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.*;
import persistence.HibernateUtil;

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
	
}
