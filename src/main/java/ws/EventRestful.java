package ws;

import java.util.List;
import java.util.Set;

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

import model.EventModel;
import model.InscriptionModel;
import model.RepetitionModel;
import model.UserModel;
import domain.Event;
import domain.Excludeddate;
import domain.Inscription;
import domain.InscriptionId;
import domain.JSONEvent;
import domain.Repetition;
import domain.User;

@Path("events")
public class EventRestful {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			return Response.ok().entity(new GenericEntity<List<Event>>(em.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvent(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, JSONEvent e) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if ((u.isIsAdmin() || u.isIsManager()) && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			em.save(e);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}")
	public Response getActivityById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			Event e = em.get(event_id);
			if(e == null)
				return null;
			return Response.ok().entity(new GenericEntity<Event>(e){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editEvent(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Event e) throws Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if ((u.isIsAdmin() || u.isIsManager()) && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			em.update(e);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}")
	public Response deleteEvent(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			em.delete(em.get(event_id));
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/repetition")
	public Response getRepetition(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			RepetitionModel rm = new RepetitionModel();
			return Response.ok().entity(new GenericEntity<List<Repetition>>(rm.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/excluded_dates")
	public Response getExcludedDates(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			return Response.ok().entity(new GenericEntity<Set<Excludeddate>>(em.getExcludedDates(event_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/excluded_dates")
	public Response addExcludedDate(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id, Excludeddate ex) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if ((u.isIsAdmin() || u.isIsManager()) && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			em.addExcludedDate(event_id, ex);
			
			return Response.ok()
				.build();
		}else{
			return Response.status(403).build();
		}
			
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/register")
	public Response getRegistration(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if ((u.isIsAdmin() || u.isIsManager()) && u.getUserToken().equals(authToken)){
			EventModel em = new EventModel();
			return Response.ok().entity(new GenericEntity<Set<Inscription>>(em.getRegistration(event_id)){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/register")
	//		int userId = jsonNode.get("memberid").get;
	public Response addRegistration(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id, Inscription i) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken) && um.get(i.getId().getMemberId()).getUserMail().equals(authUsername)){
			EventModel em = new EventModel();
			em.addRegistration(event_id, i);
			
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/register")
	/**
	 * 
	 Format JSON de InscriptionId 
		 {
		  	"eventId": "event_id",
		    "memberId": "member_id"
		 }
	 * 
	 */
	public Response deleteRegistration(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("event_id") Short event_id, InscriptionId id ) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			InscriptionModel im = new InscriptionModel();
			Inscription inscript = im.get(id);
			String mail = inscript.getUser().getUserMail();
			if (mail.equals(authUsername)){
				im.delete(inscript);
				return Response.ok()
						.build();
			}else{
				return Response.status(403).build();
			}
		}else{
			return Response.status(403).build();
		}
	}
	
}
