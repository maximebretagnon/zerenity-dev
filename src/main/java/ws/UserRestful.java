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
	public Response findAll() throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<List<User>>(um.findAll()){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response getUserById(@PathParam("User_id") Short User_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.get(User_id);
		if(u == null)
			return null;
		return Response.ok().entity(new GenericEntity<User>(u){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/formules")
	public Response getFormules() throws IllegalArgumentException, Exception {
		FormuleModel fm = new FormuleModel();
		return Response.ok().entity(new GenericEntity<List<Formule>>(fm.findAll()){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/orders")
	public Response getOrdersUser(@PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Userorder>>(um.getOrders(User_id)){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/events")
	public Response getInscriptionsUser(@PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Inscription>>(um.getInscriptions(User_id)){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions")
	public Response getSubscriptionstionsUser(@PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Cotisation>>(um.getCotisations(User_id)){})
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions")
	public Response createSubcriptionUser(@PathParam("User_id") Short User_id, Cotisation c) throws Exception {
		UserModel um = new UserModel();
		um.createSubscription(User_id, c);
		
		return Response.ok()
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions/{subscription_id}")
	public Response getCotisationUserById(@PathParam("User_id") Short User_id, @PathParam("subscription_id") Short subscription_id ) throws IllegalArgumentException, Exception {
		CotisationModel cm = new CotisationModel();
		return Response.ok().entity(new GenericEntity<Cotisation>(cm.get(subscription_id)){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications")
	public Response getNotificationsUser(@PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		return Response.ok().entity(new GenericEntity<Set<Notification>>(um.getNotifications(User_id)){})
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}")
	public Response getNotificationsUserById(@PathParam("User_id") Short User_id, @PathParam("notification_id") Short notification_id ) throws IllegalArgumentException, Exception {
		NotificationModel nm = new NotificationModel();
		return Response.ok().entity(new GenericEntity<Notification>(nm.get(notification_id)){})
				.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}")
	public Response deleteNotificationUser(@PathParam("User_id") Short User_id, @PathParam("notification_id") Short notification_id ) throws Exception {
		NotificationModel nm = new NotificationModel();
		nm.delete(nm.get(notification_id));
		return Response.ok()
				.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}/read")
	public Response editNotification(Notification n) throws Exception{
		NotificationModel nm = new NotificationModel();
		nm.update(n);
		
		return Response.ok()
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications")
	public Response createNotificationUser(@PathParam("User_id") Short User_id, Notification n) throws Exception {
		UserModel um = new UserModel();
		um.createNotification(User_id, n);
		
		return Response.ok()
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User u) throws Exception {
		UserModel um = new UserModel();
		String password = u.getUserPwd();
		String hashed = Utils.toSHA512(password);
		u.setUserPwd(hashed);
		um.save(u);
		
		return Response.ok()
				.build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response deleteUser(@PathParam("User_id") Short User_id) throws Exception {
		UserModel um = new UserModel();
		um.delete(um.get(User_id));
		return Response.ok()
				.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response editUser(User u) throws Exception{
		UserModel um = new UserModel();
		um.update(u);
		
		return Response.ok()
				.build();
	}
}
