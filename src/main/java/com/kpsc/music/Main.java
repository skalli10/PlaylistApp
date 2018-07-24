package com.kpsc.music;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class Main {

   
	public static void main(String[] args) {
		

		try {
			 Datasource ds = new Datasource();
  			 List<String> playlist = ds.getPlaylists();
  			 System.out.println(playlist.toString());
  		}
  		catch ( Exception e) 
  		{   e.printStackTrace();
  			//System.out.println("Error in Main: " +  );
			}

		  
		try {
			 Datasource ds = new Datasource();
  			 List<PlaylistItemDesc> itemlist = ds.getPlaylistItems();
  			 System.out.println(itemlist.toString());
  		}
  		catch ( Exception e) 
  		{   e.printStackTrace();
  			//System.out.println("Error in Main: " +  );
			}

		  
		try {
			 Datasource ds = new Datasource();
  			 List<PlaylistItemDesc> itemlist = ds.getPlaylist("Hindi");
  			 System.out.println(itemlist.toString());
  		}
  		catch ( Exception e) 
  		{   e.printStackTrace();
  			//System.out.println("Error in Main: " +  );
			}

		  
		try {
			 Datasource ds = new Datasource();
  			 List<PlaylistItemDesc> itemlist = ds.getPlaylistSong("Om Nama Shivaya");
  			 System.out.println(itemlist.toString());
  		}
  		catch ( Exception e) 
  		{   e.printStackTrace();
  			}
		  
		try {
			 Datasource ds = new Datasource();
			 String i = ds.deleteItem("Chants","OM","OM");
			 System.out.println("Response: " + i);
		}
		catch ( Exception e) 
		{   e.printStackTrace();
			}
		  
		try {
			 Datasource ds = new Datasource();
			 String i = ds.insertItem("Chants","OM","OM");
			 System.out.println("Response: " + i);
		}
		catch ( Exception e) 
		{   e.printStackTrace();
			}
		  
		try {
			 Datasource ds = new Datasource();
			 String i = ds.updateSong("OM","OM","Sri Sri");
			 System.out.println("Response: " + i);
		}
		catch ( Exception e) 
		{   e.printStackTrace();
			}
	
		
		        

	}

}
