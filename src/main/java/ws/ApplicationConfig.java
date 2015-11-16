package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application{

	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new java.util.HashSet();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set <Class<?>> resources){
		resources.add(ws.RoomRestful.class);
		resources.add(ws.UserRestful.class);
		resources.add(ws.EventRestful.class);
		resources.add(ws.OrderRestful.class);
		resources.add(ws.ActivityRestful.class);
		resources.add(auth.AuthService.class);
		resources.add(ws.ProductRestful.class);
	}
}