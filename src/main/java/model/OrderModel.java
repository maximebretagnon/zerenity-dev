package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.HibernateUtil;
import domain.Excludeddate;
import domain.Orderline;
import domain.OrderlineId;
import domain.SimpleOrderline;
import domain.User;
import domain.Userorder;

public class OrderModel extends AbstractModel<Userorder, Short> {

	public OrderModel(){
		super(Userorder.class);
	}
	
	public void createOrder(User u, Set<SimpleOrderline> orderlines) throws Exception{
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Userorder order = new Userorder();
				order.setOrderDate(new Date());
				order.setUser(u);
				
				Short order_id = (Short)session.save(order);
				
				Iterator<SimpleOrderline> i = orderlines.iterator();
				while(i.hasNext()){
					SimpleOrderline line = i.next();

					Orderline orderline = new Orderline();
					orderline.setId(new OrderlineId(line.getProductId(), order_id));
					orderline.setQuantity(line.getQuantity());
					session.save(orderline);
				}
				
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
