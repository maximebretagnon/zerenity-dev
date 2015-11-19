package ws;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.ActivityModel;
import domain.*;

@Path("activities")
public class ActivityRestful {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() throws IllegalArgumentException, Exception{
		ActivityModel am = new ActivityModel();
		return Response.ok().entity(new GenericEntity<List<Activity>>(am.findAll()){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createActivity(Activity a) throws Exception {
		ActivityModel am = new ActivityModel();
		am.save(a);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{activity_id}")
	public Response getActivityById(@PathParam("activity_id") Short activity_id) throws Exception {
		ActivityModel am = new ActivityModel();
		Activity a = am.get(activity_id);
		if(a == null)
			return null;
		return Response.ok().entity(new GenericEntity<Activity>(a){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{activity_id}/events")
	public Response getEventsActivityById(@PathParam("activity_id") Short activity_id) throws IllegalArgumentException, Exception {
		ActivityModel am = new ActivityModel();
		return Response.ok().entity(new GenericEntity<Set<Event>>(am.getEvents(activity_id)){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
}
