package ws;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
	public Response findAll(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<List<User>>(um.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response getUserById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id) throws Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<User>(user){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/formules")
	public Response getFormules(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.getUserToken().equals(authToken)){
			FormuleModel fm = new FormuleModel();
			return Response.ok().entity(new GenericEntity<List<Formule>>(fm.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/orders")
	public Response getOrdersUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<Set<Userorder>>(um.getOrders(User_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/events")
	public Response getInscriptionsUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<Set<Inscription>>(um.getInscriptions(User_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions")
	public Response getSubscriptionstionsUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<Set<Cotisation>>(um.getCotisations(User_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions")
	public Response createSubcriptionUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, Cotisation c) throws Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			um.createSubscription(User_id, c);
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/subscriptions/{subscription_id}")
	public Response getCotisationUserById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, @PathParam("subscription_id") Short subscription_id ) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			CotisationModel cm = new CotisationModel();
			return Response.ok().entity(new GenericEntity<Cotisation>(cm.get(subscription_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications")
	public Response getNotificationsUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			return Response.ok().entity(new GenericEntity<Set<Notification>>(um.getNotifications(User_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}")
	public Response getNotificationsUserById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, @PathParam("notification_id") Short notification_id ) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			NotificationModel nm = new NotificationModel();
			return Response.ok().entity(new GenericEntity<Notification>(nm.get(notification_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}")
	public Response deleteNotificationUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, @PathParam("notification_id") Short notification_id ) throws Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			NotificationModel nm = new NotificationModel();
			nm.delete(nm.get(notification_id));
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications/{notification_id}/read")
	public Response editNotification(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, Notification n) throws Exception{
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			NotificationModel nm = new NotificationModel();
			nm.update(n);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}/notifications")
	public Response createNotificationUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, Notification n) throws Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			um.createNotification(User_id, n);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
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
	public Response deleteUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id) throws Exception {
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			um.delete(um.get(User_id));
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{User_id}")
	public Response editUser(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("User_id") Short User_id, User u) throws Exception{
		UserModel um = new UserModel();
		User headerUser = um.getByMail(authUsername);
		User user = um.get(User_id);
		if ((headerUser.isIsAdmin() || headerUser.getUserMail().equals(user.getUserMail())) && headerUser.getUserToken().equals(authToken)){
			um.update(u);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
}
