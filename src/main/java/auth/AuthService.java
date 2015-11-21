package auth;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import auth.Credentials;
import domain.User;
import model.UserModel;
import utils.Utils;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthService{

 
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(Credentials cred) throws Exception {
		UserModel um = new UserModel();
		User user = um.getByMail(cred.getUsername());
		if (user != null && user.getUserPwd().equals(Utils.toSHA512(cred.getPassword()))){
			String token = UUID.randomUUID().toString() + "wKcGt" + user.getUserPwd();
	        String hashed = Utils.toSHA512(token);
            user.setUserToken(hashed);
            AuthResponse res = new AuthResponse(user.getUserId(), user.getUserMail(), token, user.isIsAdmin(), user.isIsParticipant(), user.isIsMember(), user.isIsManager());
            um.update(user);
			return Response.status(200).entity(res)
					.build();
		}
		else{
			return Response.status(401)
					.build();
		}
	}
	
	@OPTIONS
	@Path("/login")
	public Response myResource() {
	    return Response.ok()
				.build();
	}

}
