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
import domain.JSONOrderline;
import domain.Product;
import domain.User;
import domain.Userorder;

public class OrderModel extends AbstractModel<Userorder, Short> {

	public OrderModel(){
		super(Userorder.class);
	}
	
	public boolean createOrder(User u, Set<JSONOrderline> orderlines) throws Exception{
		
		Session session = HibernateUtil.currentSession();
		try{
			Transaction tx = session.beginTransaction();
			try{
				Userorder order = new Userorder();
				order.setOrderDate(new Date());
				order.setUser(u);
				
				Short order_id = (Short)session.save(order);
				
				Iterator<JSONOrderline> i = orderlines.iterator();
				while(i.hasNext()){
					JSONOrderline line = i.next();

					Orderline orderline = new Orderline();
					orderline.setId(new OrderlineId(line.getProductId(), order_id));
					orderline.setQuantity(line.getQuantity());
					session.save(orderline);
				}
				
        		tx.commit();
        		return true;
			}catch(Exception ex){
				tx.rollback();
				throw(ex);
			}
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void updateStock(Set<JSONOrderline> orderlines) throws Exception{
		
		ProductModel pm = new ProductModel();
				
		Iterator<JSONOrderline> i = orderlines.iterator();
		while(i.hasNext()){
			JSONOrderline line = i.next();

			Product p = pm.get(line.getProductId());
			p.setStockQuantity(p.getStockQuantity() - line.getQuantity());
			pm.update(p);
		}
	}
}
