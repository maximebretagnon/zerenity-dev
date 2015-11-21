package ws;

import java.util.List;
import java.util.Set;

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

import model.ActivityModel;
import model.UserModel;
import domain.*;

@Path("activities")
public class ActivityRestful {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			ActivityModel am = new ActivityModel();
			return Response.ok().entity(new GenericEntity<List<Activity>>(am.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createActivity(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Activity a) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			ActivityModel am = new ActivityModel();
			am.save(a);
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{activity_id}")
	public Response getActivityById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("activity_id") Short activity_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			ActivityModel am = new ActivityModel();
			Activity a = am.get(activity_id);
			if(a == null)
				return null;
			return Response.ok().entity(new GenericEntity<Activity>(a){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{activity_id}/events")
	public Response getEventsActivityById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("activity_id") Short activity_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			ActivityModel am = new ActivityModel();
			return Response.ok().entity(new GenericEntity<Set<Event>>(am.getEvents(activity_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
}
