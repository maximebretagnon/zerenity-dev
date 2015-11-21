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
	public Response findAll(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			RoomModel rm = new RoomModel();
			return Response.ok().entity(new GenericEntity<List<Room>>(rm.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{room_id}")
	public Response getRoomById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("room_id") Short room_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsMember() && u.getUserToken().equals(authToken)){
			RoomModel rm = new RoomModel();
			Room r = rm.get(room_id);
			if(r == null)
				return null;
			return Response.ok().entity(new GenericEntity<Room>(r){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoom(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Room r) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			RoomModel rm = new RoomModel();
			rm.save(r);
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{room_id}")
	public Response deleteRoom(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("room_id") Short room_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			RoomModel rm = new RoomModel();
			rm.delete(rm.get(room_id));
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editRoom(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Room r) throws Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			RoomModel rm = new RoomModel();
			rm.update(r);
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
}
