package demohibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Room;
import persistence.HibernateUtil;

public class DemoHibernate {
	
	public static void main (String[] args){
		
		// 1: ouverture unité de travail hibernate
		Session session = HibernateUtil.currentSession();
		
		//2: ouverture transaction
		Transaction tx = session.beginTransaction();
		
		//3:instanciation objet métier
		Room room = new Room();

		//4:création d'un objet en base
		room.setCapacity((short) 30);
		room.setRoomArea("10 m");
		room.setRoomType("office");
		session.save(room);
		
		//5:fermeture transaction
		tx.commit();
		
		//6:fermeture unité de travail hibernate
		HibernateUtil.closeSession();
	}

}
