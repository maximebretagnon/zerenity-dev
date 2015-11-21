package ws;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
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
import domain.Event;
import domain.Excludeddate;
import domain.Inscription;
import domain.InscriptionId;
import domain.Repetition;

@Path("events")
public class EventRestful {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() throws IllegalArgumentException, Exception{
		EventModel em = new EventModel();
		return Response.ok().entity(new GenericEntity<List<Event>>(em.findAll()){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvent(Event e) throws Exception {
		EventModel em = new EventModel();
		em.save(e);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}")
	public Response getActivityById(@PathParam("event_id") Short event_id) throws Exception {
		EventModel em = new EventModel();
		Event e = em.get(event_id);
		if(e == null)
			return null;
		return Response.ok().entity(new GenericEntity<Event>(e){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editEvent(Event e) throws Exception{
		EventModel em = new EventModel();
		em.update(e);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}")
	public Response deleteEvent(@PathParam("event_id") Short event_id) throws Exception {
		EventModel em = new EventModel();
		em.delete(em.get(event_id));
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/repetition")
	public Response getRepetition() throws IllegalArgumentException, Exception {
		RepetitionModel rm = new RepetitionModel();
		return Response.ok().entity(new GenericEntity<List<Repetition>>(rm.findAll()){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/excluded_dates")
	public Response getExcludedDates(@PathParam("event_id") Short event_id) throws IllegalArgumentException, Exception {
		EventModel em = new EventModel();
		return Response.ok().entity(new GenericEntity<Set<Excludeddate>>(em.getExcludedDates(event_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/excluded_dates")
	public Response addExcludedDate(@PathParam("event_id") Short event_id, Excludeddate ex) throws Exception {
		EventModel em = new EventModel();
		em.addExcludedDate(event_id, ex);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/register")
	public Response getRegistration(@PathParam("event_id") Short event_id) throws IllegalArgumentException, Exception {
		EventModel em = new EventModel();
		return Response.ok().entity(new GenericEntity<Set<Inscription>>(em.getRegistration(event_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{event_id}/register")
	public Response addRegistration(@PathParam("event_id") Short event_id, Inscription i) throws Exception {
		EventModel em = new EventModel();
		em.addRegistration(event_id, i);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
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
	public Response deleteRegistration(@PathParam("event_id") Short event_id, InscriptionId id ) throws Exception {
		InscriptionModel im = new InscriptionModel();
		im.delete(im.get(id));
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	
}
