package auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import auth.Credentials;
import domain.User;
import model.UserModel;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthService{

 
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(Credentials cred) {
		UserModel um = new UserModel();
		User user = um.getByMail(cred.getUsername());
		String out = "";
		if (user != null && user.getUserPwd().equals(cred.getPassword())){
			MessageDigest md;
			String token = UUID.randomUUID().toString() + "wKcGt" + user.getUserPwd();
	        try {
	            md = MessageDigest.getInstance("SHA-512");

	            md.update(token.getBytes());
	            byte[] mb = md.digest();
	            out = "";
	            for (int i = 0; i < mb.length; i++) {
	                byte temp = mb[i];
	                String s = Integer.toHexString(new Byte(temp));
	                while (s.length() < 2) {
	                    s = "0" + s;
	                }
	                s = s.substring(s.length() - 2);
	                out += s;
	            }

	        } catch (NoSuchAlgorithmException e) {
	            System.out.println("ERROR: " + e.getMessage());
	        }
            user.setUserToken(out);
            AuthResponse res = new AuthResponse(user.getUserId(), user.getUserMail(), out, user.isIsAdmin(), user.isIsParticipant(), user.isIsMember(), user.isIsManager());
            um.update(user);
			return Response.status(200).entity(res)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTION, HEAD")
					.build();
		}
		else{
			return Response.status(401)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTION, HEAD")
					.build();
		}
	}
}
