package com.kpsc.music;

import java.lang.*;
import java.sql.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//C:\Progra~1\curl-7.59.0-win64-mingw\bin\curl.exe

@Path("/playlists")
public class PlaylistService {
	

	   //curl -X "OPTIONS" http://localhost:8080/PlaylistApp/webapi/playlists
	   @OPTIONS
	   @Produces(MediaType.APPLICATION_JSON)
	   public String getSupportedOperations(){
	      return "<operations>GET, PUT, POST, DELETE</operations>";
	   }

		//      http://localhost:8080/PlaylistApp/webapi/playlists/lists
	    // curl http://localhost:8080/PlaylistApp/webapi/playlists/lists
		  @GET
		  @Path("/lists")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response getPlaylists() throws Exception {	
		        
			  Datasource ds = new Datasource();
			  List<String> playlist = ds.getPlaylists();
			  return Response.status(200).entity(playlist.toString()).build();	 
		  }

		   //     http://localhost:8080/PlaylistApp/webapi/playlists/lists/Hindi
		  // curl http://localhost:8080/PlaylistApp/webapi/playlists/lists/Hindi
			  @GET
			  @Path("/lists/{listname}")
			  @Produces(MediaType.APPLICATION_JSON)
			  public Response getPlaylist(@PathParam("listname") String listname) throws Exception {	
			        
				  Datasource ds = new Datasource();
				  List<PlaylistItemDesc> itemlist = ds.getPlaylist(listname);
				  return Response.status(200).entity(itemlist.toString()).build();	 
			  }

	//http://localhost:8080/PlaylistApp/webapi/playlists/
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getPlaylistItems() throws Exception {	
	        
		  Datasource ds = new Datasource();
		  List<PlaylistItemDesc> itemlist = ds.getPlaylistItems();
		  return Response.status(200).entity(itemlist.toString()).build();	 
	  }

		//http://localhost:8080/PlaylistApp/webapi/playlists/songs/Om Nama Shivaya
		  @GET
		  @Path("/songs/{songname}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response getPlaylistSong(@PathParam("songname") String songname) throws Exception {	
		        
			  Datasource ds = new Datasource();
			  List<PlaylistItemDesc> itemlist = ds.getPlaylistSong(songname);
			  return Response.status(200).entity(itemlist.toString()).build();	 
		  }

			//http://localhost:8080/PlaylistApp/webapi/playlists/songs/Om Nama Shivaya/Hum Dono
			  @GET
			  @Path("/songs/{songname}/{albumname}")
			  @Produces(MediaType.APPLICATION_JSON)
			  public Response getPlaylistSong(@PathParam("songname")  String songname,
					                          @PathParam("albumname") String albumname) throws Exception {	
			        
				  Datasource ds = new Datasource();
				  List<PlaylistItemDesc> itemlist = ds.getPlaylistSong(songname, albumname);
				  return Response.status(200).entity(itemlist.toString()).build();	 
			  }

    
	 //curl -X "DELETE" http://localhost:8080/PlaylistApp/webapi/playlists/Chants/OM/OM
	  @DELETE
	  @Path("/{playlist}/{song}/{album}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response deleteItem(@PathParam("playlist")  String list,
			                     @PathParam("song")  String song,
			                     @PathParam("album")  String album) throws Exception
	    {
		    Datasource ds = new Datasource();
		   String s = ds.deleteItem(list,song,album);
		   return Response.status(200).entity(s).build();	// returns String   
	    }
	  	  

		 //curl -X "POST" http://localhost:8080/PlaylistApp/webapi/playlists/Chants/OM/OM
		  @POST
		  @Path("/{playlist}/{song}/{album}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response insertItem(@PathParam("playlist")  String list,
				                     @PathParam("song")  String song,
				                     @PathParam("album")  String album) throws Exception
		    {
			    Datasource ds = new Datasource();
			    String s = ds.insertItem(list,song,album);
			    return Response.status(200).entity(s).build();	// returns String   
		    }
		  

			 //curl -X "PUT" http://localhost:8080/PlaylistApp/webapi/playlists/OM/OM/Sri Sri
			  @PUT
			  @Path("/{song}/{album}/{newSinger}")
			  @Produces(MediaType.APPLICATION_JSON)
			  public Response updateItem(@PathParam("song")  String song,
					                     @PathParam("album")  String album,
					                     @PathParam("newSinger")  String newSinger) throws Exception
			    {
				    Datasource ds = new Datasource();
				   String s = ds.updateSong(song,album,newSinger);
				   return Response.status(200).entity(s).build();	// returns String   
			    }
	  

		  
			 //http://localhost:8080/PlaylistApp/webapi/playlists/delsongs/Chants3/OM/OM
			  @GET
			  @Path("/delsongs/{playlist}/{song}/{album}")
			  @Produces(MediaType.APPLICATION_JSON)
			  public Response deleteItem1(@PathParam("playlist")  String list,
					                     @PathParam("song")  String song,
					                     @PathParam("album")  String album) throws Exception
			    {
				    Datasource ds = new Datasource();
				   String s = ds.deleteItem(list,song,album);
				   return Response.status(200).entity(s).build();	// returns String     
			    }
	   
	  //http://localhost:8080/PlaylistApp/webapi/playlists/insertSongs/Chants/OM/OM
	  @GET
	  @Path("/insertSongs/{playlist}/{song}/{album}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response addSong(@PathParam("playlist")  String list,
                              @PathParam("song")  String song,
                              @PathParam("album")  String album) throws Exception
	    {
		    Datasource ds = new Datasource();
		    String s = ds.insertItem(list,song,album);
		   return Response.status(200).entity(s).build();	// returns String   
	    }   
	   
	  //http://localhost:8080/PlaylistApp/webapi/playlists/OM/OM/Sri Sri
	  @GET
	  @Path("/{song}/{album}/{newSinger}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response updateSong( @PathParam("song")  String song,
                                  @PathParam("album")  String album,
                                  @PathParam("newSinger")  String newSinger) throws Exception
	     {
		    Datasource ds = new Datasource();
		   String s = ds.updateSong(song,album,newSinger);
		   return Response.status(200).entity(s).build();	// returns String   
	    }
	    

}
	 
		      
      //Song user = new Song (Integer.parseInt(userid), name, job );

	
