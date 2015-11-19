package ws;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.CotisationModel;
import model.FormuleModel;
import model.NotificationModel;
import model.UserModel;
import utils.Utils;
import domain.*;

@Path("users")
public class UserRestful {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<List<User>>(um.findAll()){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response getUserById(@PathParam("User_id") Short User_id) {
		UserModel um = new UserModel();
		User u = um.get(User_id);
		if(u == null)
			return null;
		return Response.ok().entity(new GenericEntity<User>(u){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/formules")
	public Response getFormules() {
		FormuleModel fm = new FormuleModel();
		return Response.ok().entity(new GenericEntity<List<Formule>>(fm.findAll()){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/orders")
	public Response getOrdersUser(@PathParam("User_id") Short User_id) {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Userorder>>(um.getOrders(User_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/events")
	public Response getInscriptionsUser(@PathParam("User_id") Short User_id) {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Inscription>>(um.getInscriptions(User_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions")
	public Response getSubscriptionstionsUser(@PathParam("User_id") Short User_id) {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Cotisation>>(um.getCotisations(User_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions")
	public Response createSubcriptionUser(@PathParam("User_id") Short User_id, Cotisation c) {
		UserModel um = new UserModel();
		um.createSubscription(User_id, c);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions/{subscription_id}")
	public Response getCotisationUserById(@PathParam("User_id") Short User_id, @PathParam("subscription_id") Short subscription_id ) {
		CotisationModel cm = new CotisationModel();
		return Response.ok().entity(new GenericEntity<Cotisation>(cm.get(subscription_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications")
	public Response getNotificationsUser(@PathParam("User_id") Short User_id) {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Notification>>(um.getNotifications(User_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}")
	public Response getNotificationsUserById(@PathParam("User_id") Short User_id, @PathParam("notification_id") Short notification_id ) {
		NotificationModel nm = new NotificationModel();
		return Response.ok().entity(new GenericEntity<Notification>(nm.get(notification_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}")
	public Response deleteNotificationUser(@PathParam("User_id") Short User_id, @PathParam("notification_id") Short notification_id ) {
		NotificationModel nm = new NotificationModel();
		nm.delete(nm.get(notification_id));
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}/read")
	public Response editNotification(Notification n){
		NotificationModel nm = new NotificationModel();
		nm.update(n);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications")
	public Response createNotificationUser(@PathParam("User_id") Short User_id, Notification n) {
		UserModel um = new UserModel();
		um.createNotification(User_id, n);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User u) {
		UserModel um = new UserModel();
		String password = u.getUserPwd();
		String hashed = Utils.toSHA512(password);
		u.setUserPwd(hashed);
		um.save(u);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response deleteUser(@PathParam("User_id") Short User_id) {
		UserModel um = new UserModel();
		um.delete(um.get(User_id));
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response editUser(User u){
		UserModel um = new UserModel();
		um.update(u);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
}
