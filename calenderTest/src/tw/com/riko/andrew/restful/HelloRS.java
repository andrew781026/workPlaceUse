package tw.com.riko.andrew.restful;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")   
public class HelloRS {
	
    @GET
    @Path("/{name}")
    public String sayHelloGet(@PathParam("name") String name) {
        return "Hello, " + name;
    }
    
    @PUT
    @Path("/{name}")
    public String sayHelloPost(@PathParam("name") String name) {
        return "Hello, " + name;
    }
    
}
