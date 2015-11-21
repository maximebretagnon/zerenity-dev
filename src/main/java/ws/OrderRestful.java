package ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.*;
import model.*;

@Path("orders")
public class OrderRestful {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			OrderModel om = new OrderModel();
			return Response.ok().entity(new GenericEntity<List<Userorder>>(om.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Userorder o) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			OrderModel om = new OrderModel();
			om.save(o);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{order_id}")
	public Response getOrderById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("order_id") Short order_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		OrderModel om = new OrderModel();
		Userorder o = om.get(order_id);
		if ((u.isIsAdmin() || u.isIsMember() && o.getUser().getUserMail().equals(authUsername)) && u.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<Userorder>(o){})
				.build();
		}else{
			return Response.status(403).build();
		}
	}
}
