package com.kpsc;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
  
@Path("/hello")
public class HelloWorldService {
	

	  //@GET
	  //@Produces(MediaType.TEXT_PLAIN)
	  //public Response getIt() {
	  //String message = "Hello World\n";
	  //Response rsp = Response.status(Status.OK).entity(message).build();
	  //return rsp;
	    
	//http://localhost:8080/PlaylistApp/webapi/hello/World	
    @GET
    @Path("/{name}")
    public Response getMsg(@PathParam("name") String name) {
        String output = "Hello   : " + name;
        Response rsp = Response.status(200).entity(output).build(); 
        return  rsp;
    }
    
    //----------------------------   
    
    // http://localhost:8080/PlaylistApp/webapi/hello/San Jose/CA/12345
    @GET
    @Path("{city}/{state}/{zip}")
    public Response getMsg(@PathParam("city")  String myCity,
                           @PathParam("state") String myState,
                           @PathParam("zip")   String myZip) {
 
        String addr = "I live in  " + myCity  + ", " 
                                    + myState + " - "
        		                    + myZip;
        return Response.status(200).entity(addr).build();
    }
    
    //----------------------------
    
	//http://localhost:8080/PlaylistApp/webapi/hello/101
    
    // http://localhost:8080/PlaylistApp/webapi/hello/abc >> this will call previous method getMsg
    @GET
    @Path("{num: [0-9]+}")
    public Response showNumber(@PathParam("num") String Nbr) { 
        return Response
                .status(200)
                .entity(" showNumber() method called to display value : " + Nbr).build();
    }
    
    
}