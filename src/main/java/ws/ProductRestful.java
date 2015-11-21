package ws;

import java.util.List;

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


import domain.*;
import model.*;

@Path("products")
public class ProductRestful {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.getUserToken().equals(authToken)){
			ProductModel pm = new ProductModel();
			return Response.ok().entity(new GenericEntity<List<Product>>(pm.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{product_id}")
	public Response getProductById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("product_id") Short product_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.getUserToken().equals(authToken)){
			ProductModel pm = new ProductModel();
			Product p = pm.get(product_id);
			return Response.ok().entity(new GenericEntity<Product>(p){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{product_id}")
	public Response editProduct(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Product p) throws Exception{
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			ProductModel pm = new ProductModel();
			pm.update(p);
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{product_id}")
	public Response deleteProduct(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("product_id") Short product_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			ProductModel pm = new ProductModel();
			pm.delete(pm.get(product_id));
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categories")
	public Response findAllCategories(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken) throws IllegalArgumentException, Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.getUserToken().equals(authToken)){
			ProductCategoryModel pcm = new ProductCategoryModel();
			return Response.ok().entity(new GenericEntity<List<ProductCategory>>(pcm.findAll()){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categories/{category_id}")
	public Response getCategoryById(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, @PathParam("category_id") Short category_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.getUserToken().equals(authToken)){
			ProductCategoryModel pcm = new ProductCategoryModel();
			ProductCategory pc = pcm.get(category_id);
			if(pc == null)
				return null;
			return Response.ok().entity(new GenericEntity<ProductCategory>(pc){})
					.build();
		}else{
			return Response.status(403).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categories/{category_id}")
	public Response createProduct(@HeaderParam("auth-username") String authUsername, @HeaderParam("auth-token") String authToken, Product p, @PathParam("category_id") Short category_id) throws Exception {
		UserModel um = new UserModel();
		User u = um.getByMail(authUsername);
		if (u.isIsAdmin() && u.getUserToken().equals(authToken)){
			ProductCategoryModel pcm = new ProductCategoryModel();
			ProductCategory pc = pcm.get(category_id);
			ProductModel pm = new ProductModel();
			p.setProductCategory(pc);
			pm.save(p);
			return Response.ok()
					.build();
		}else{
			return Response.status(403).build();
		}
	}
}
