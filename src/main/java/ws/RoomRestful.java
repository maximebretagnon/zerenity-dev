package ws;

import domain.*;
import model.*;
import java.util.*;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

@Path("rooms")
public class RoomRestful {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(){
		RoomModel rm = new RoomModel();
		return Response.ok().entity(new GenericEntity<List<Room>>(rm.findAll()){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{room_id}")
	public Response getRoomById(@PathParam("room_id") Short room_id) {
		RoomModel rm = new RoomModel();
		Room r = rm.get(room_id);
		if(r == null)
			return null;
		return Response.ok().entity(new GenericEntity<Room>(r){})
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoom(Room r) {
		RoomModel rm = new RoomModel();
		rm.save(r);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{room_id}")
	public Response deleteRoom(@PathParam("room_id") Short room_id) {
		RoomModel rm = new RoomModel();
		rm.delete(rm.get(room_id));
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editRoom(Room r){
		RoomModel rm = new RoomModel();
		rm.update(r);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, access-control-allow-origin")
				.build();
	}
}
