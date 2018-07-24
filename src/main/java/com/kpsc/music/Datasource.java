package com.kpsc.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

//import com.kpsc.rest.Song;

//import com.kpsc.playlist.Song;

import java.lang.*;

public class Datasource {
	
	private static final String DB_NAME = "playlist.db";
	private static final String DB_PATH = "jdbc:sqlite:C:\\Users\\sajni\\eclipse-workspace\\PlaylistApp\\" ;
    
		
	private static Connection conn;
	 
	private static String queryPlaylist = 
			               "select listname from playlist" ;
	 
	private static PreparedStatement PSqueryPlaylistItemDesc;
	private static String queryPlaylistItemDesc = 
			               " select playlist.listname, song.title, song.singer, album.name, album.artist " + 
			               " from playlist_item " + 
			               "inner join playlist on playlist_item.playlist_id = playlist.playlist_id " + 
			               "inner join song     on playlist_item.song_id     = song.song_id " + 
			               "inner join album    on album.album_id            = song.album_id";
	
	
	public Datasource() throws Exception {
	  this.openConn();
	
	}
	
	private void openConn() throws Exception{
				
		try {
			  
			  Class.forName("org.sqlite.JDBC");
			  conn = DriverManager.getConnection(DB_PATH + DB_NAME);
			  //conn = DriverManager.getConnection("jdbc:sqlite:playlist.db");  
		    }
		
		catch (SQLException e)
		{
			System.out.println("Error in Datasource.openConn:" + e.getMessage());
			
		}
		 
	  }
	
// --------------------------------------------------------------------------
	
	public void closeConn() {
		try {
			 
		 if (!conn.isClosed() & conn != null)
			{ conn.close(); }
		  }		
		catch (SQLException e)
		{
			System.out.println("Error in Method closeConn: " + e.getMessage());
		}
	  }
	  
//--------------------------------------------------------------------------
	
	public List<String> getPlaylists() throws Exception {
		
		String s = null;
		List<String> pl = new ArrayList<String>();	
		
		try {   
            
	         Statement statement = conn.createStatement();
			 ResultSet rs = statement.executeQuery(queryPlaylist);
				
	         System.out.println(queryPlaylist);
	          
	          if (rs == null){
	        	     System.out.println("No rows returned");
	                 return null;
	               }
	          else {
	                 while (rs.next()) {	                	
	                	s = rs.getString("listname");
	                	pl.add(s);
	               }
	         }	          
	          conn.close();
		}
		catch (SQLException e)
		{ System.out.println(e.getMessage()); }
	    
		return pl;
	}
	  
// --------------------------------------------------------------------------
	
	public List<PlaylistItemDesc> getPlaylistItems() throws Exception {
		
		PlaylistItemDesc item = null;
		List<PlaylistItemDesc> itemlist = new ArrayList<PlaylistItemDesc>();	

		try {
			
			  PSqueryPlaylistItemDesc  = conn.prepareStatement(queryPlaylistItemDesc);
 	          ResultSet rs = PSqueryPlaylistItemDesc.executeQuery(); 
 	          
 	         System.out.println(queryPlaylistItemDesc);
 	          
 	          if (rs == null){
 	        	    System.out.println("No rows returned");
 	                 return null;
 	               }
 	          else {
 	                 while (rs.next()) {
 	                	
 	                	item = new PlaylistItemDesc(rs.getString("listname"),
 	                			                    rs.getString("title"),
 	                			                    rs.getString("singer"),
 	                			                    rs.getString("name"),
 	                			                    rs.getString("artist")); 
 	                	itemlist.add(item);
 	               }
 	         }
	          conn.close();
		}

		catch (SQLException e)
		{ System.out.println(e.getMessage()); }
				
		return itemlist;
	}
   

	  
	// --------------------------------------------------------------------------
		
		public List<PlaylistItemDesc> getPlaylist(String listname) throws Exception {
			
			PlaylistItemDesc item = null;
			List<PlaylistItemDesc> itemlist = new ArrayList<PlaylistItemDesc>();	

			try {
				
				 PSqueryPlaylistItemDesc  = conn.prepareStatement(queryPlaylistItemDesc +
			                                                     " where playlist.listname = '" + listname + "'");
	 	         ResultSet rs = PSqueryPlaylistItemDesc.executeQuery(); 
	 	          
	 	         System.out.println(queryPlaylistItemDesc);
	 	          
	 	          if (rs == null){
	 	        	    System.out.println("No rows returned");
	 	                 return null;
	 	               }
	 	          else {
	 	                 while (rs.next()) {
	 	                	
	 	                	item = new PlaylistItemDesc(rs.getString("listname"),
	 	                			                    rs.getString("title"),
	 	                			                    rs.getString("singer"),
	 	                			                    rs.getString("name"),
	 	                			                    rs.getString("artist")); 
	 	                	itemlist.add(item);
	 	               }
	 	         }
		          conn.close();
			}

			catch (SQLException e)
			{ System.out.println(e.getMessage()); }
					
			return itemlist;
		}
		   

		  
		// --------------------------------------------------------------------------
			
			public List<PlaylistItemDesc> getPlaylistSong(String songname) throws Exception {
				
				PlaylistItemDesc item = null;
				List<PlaylistItemDesc> itemlist = new ArrayList<PlaylistItemDesc>();	

				try {
					
			         Statement statement = conn.createStatement();
					 ResultSet rs = statement.executeQuery(queryPlaylistItemDesc +
                                                          " where song.title = '" + songname + "'");
										
		 	         System.out.println(queryPlaylistItemDesc +
                             " where song.title = '" + songname + "'");
		 	          
		 	          if (rs == null){
		 	        	    System.out.println("No rows returned");
		 	                 return null;
		 	               }
		 	          else {
		 	                 while (rs.next()) {
		 	                	
		 	                	item = new PlaylistItemDesc(rs.getString("listname"),
		 	                			                    rs.getString("title"),
		 	                			                    rs.getString("singer"),
		 	                			                    rs.getString("name"),
		 	                			                    rs.getString("artist")); 
		 	                	itemlist.add(item);
		 	               }
		 	         }
			          conn.close();
				}

				catch (SQLException e)
				{ System.out.println(e.getMessage()); }
						
				return itemlist;
			}
		// --------------------------------------------------------------------------
			
			public List<PlaylistItemDesc> getPlaylistSong(String songname,String albumname) throws Exception {
				
				PlaylistItemDesc item = null;
				List<PlaylistItemDesc> itemlist = new ArrayList<PlaylistItemDesc>();	

				try {
					
			         Statement statement = conn.createStatement();
					 ResultSet rs = statement.executeQuery(queryPlaylistItemDesc +
                                                          " where song.title = '" + songname + "'" +
							                              " and album.name = '"   + albumname + "'"
							                               );
										
		 	         System.out.println(queryPlaylistItemDesc +
                             " where song.title = '" + songname + "'" +
                             " and album.name = '"   + albumname + "'");
		 	          
		 	          if (rs == null){
		 	        	    System.out.println("No rows returned");
		 	                 return null;
		 	               }
		 	          else {
		 	                 while (rs.next()) {
		 	                	
		 	                	item = new PlaylistItemDesc(rs.getString("listname"),
		 	                			                    rs.getString("title"),
		 	                			                    rs.getString("singer"),
		 	                			                    rs.getString("name"),
		 	                			                    rs.getString("artist")); 
		 	                	itemlist.add(item);
		 	               }
		 	         }
			          conn.close();
				}

				catch (SQLException e)
				{ System.out.println(e.getMessage()); }
						
				return itemlist;
			}
			
	//-----------------------------------------------------------------------

			public String deleteItem( String playlist, String song, String album) throws SQLException {

				  String response = "deleteItem: ";	
				  PreparedStatement psSelect = null;;
					 
		         String sql = 
				          "select playlist.playlist_id, song.song_id, album.album_id " + 
		                  "from playlist_item " + 
		                  "inner join playlist on playlist_item.playlist_id = playlist.playlist_id " + 
		                  "inner join song     on playlist_item.song_id     = song.song_id " + 
		                  "inner join album    on album.album_id            = song.album_id " +
		                  " where playlist.listname = '" + playlist + "'" +
		                  "   and song.title= '" + song + "'" +
		                  "   and album.name = '" + album + "'" 
		                  ;		         		         
		         
				try {      psSelect = conn.prepareStatement(sql);
				           ResultSet rsSelect = psSelect.executeQuery();
				         	
			 	          if (!rsSelect.isBeforeFirst()){ 		
			 	        	   response = response + "Song not found.";
			 	        	   
			 	               }
			 	           else  {
			 	        	  						  		 	          
			 	        	 int playlist_id = rsSelect.getInt(1) ;
			 	        	 int song_id     = rsSelect.getInt(2) ;
			 	        	 int album_id    = rsSelect.getInt(3) ;
			 	        	 System.out.println(playlist_id + " - " +  
			 	        			            song_id + " - " +
			 	        			            album_id );
			 	        		
			 	        	 String sqlDelete = "delete from playlist_item where playlist_id = " + playlist_id +
			 	        			            " and song_id = " + song_id;
							 PreparedStatement psDelete = conn.prepareStatement(sqlDelete);		
							 int affectedRows = psDelete.executeUpdate();	
							 response = response +  "Rows deleted:" + affectedRows;	
			 	             }
		 	        
			          
				}

				catch (SQLException e)
				{ System.out.println(e.getMessage());
			      e.printStackTrace();}
				
				finally {
				        if (psSelect != null) {
				        	psSelect.close();
				           }
				        conn.close();
			        	return response;}
			}
			
	//-----------------------------------------------------------------------

			public String insertItem( String playlist, String song, String album) throws SQLException {

				  String response = "insertItem: ";	
				  PreparedStatement psCheckItem = null;
				  PreparedStatement psCheckSong = null;
				  PreparedStatement psCheckList = null;
				  PreparedStatement psInsert = null;
					 
			         String sqlCheckItem = 
					          "select playlist.playlist_id, song.song_id, album.album_id " + 
			                  "from playlist_item " + 
			                  "inner join playlist on playlist_item.playlist_id = playlist.playlist_id " + 
			                  "inner join song     on playlist_item.song_id     = song.song_id " + 
			                  "inner join album    on album.album_id            = song.album_id " +
			                  " where playlist.listname = '" + playlist + "'" +
			                  "   and song.title= '" + song + "'" +
			                  "   and album.name = '" + album + "'" 
			                  ;		         		         
					 
		         String sqlCheckSong = 
				          "select song.song_id, album.album_id " + 
		                  "from song " + 
		                  "inner join album    on album.album_id            = song.album_id " +
		                  " where song.title= '" + song + "'" +
		                  "   and album.name = '" + album + "'" ;		 
		         
		         String sqlCheckList = 
				          "select playlist.playlist_id from playlist where playlist.listname = '" + 
		                   playlist + "'"  ; 
		                   
		         String sqlInsert = "insert into playlist_item (playlist_id, song_id) values (?,?) ";
		         
				try {      psCheckItem = conn.prepareStatement(sqlCheckItem);
				           ResultSet rsCheckItem = psCheckItem.executeQuery(); 
				         	
			 	          if (rsCheckItem.isBeforeFirst()){ 	
			 	               response = response + "Song is already in the playlist.";
			 	        	   return response ;			 	        	   
			 	               }
			 	           else  {
			 	        	        psCheckSong = conn.prepareStatement(sqlCheckSong);
					                ResultSet rsCheckSong = psCheckSong.executeQuery(); 
					         	
					 	            if (!rsCheckSong.isBeforeFirst()) { 	
						 	               response = response + "Song not found.";
						 	        	   return response ;			 	        	   
						 	               }	 	        	   
					 	               
					 	            else{
					 	        	       psCheckList = conn.prepareStatement(sqlCheckList);
							               ResultSet rsCheckList = psCheckList.executeQuery(); 
							         	
							 	           if (!rsCheckList.isBeforeFirst()) { 	
							 	               response = response + "List not found.";
							 	        	   return response ;			 	        	   
							 	               }	 	        	    
							 	           else {
							 	        	       int song_id     = rsCheckSong.getInt(1);
							 	        	       int playlist_id = rsCheckList.getInt(1);  
							 	        	       
							 	        	       psInsert = conn.prepareStatement(sqlInsert);  
							 	        	       psInsert.setLong(1, playlist_id);  
							 	        	       psInsert.setLong(2, song_id);
							 	        	       
									               int insertedRows = psInsert.executeUpdate();			
									               response = response + "Rows inserted: " + insertedRows;
									 	           return response ;	 
							 	                }							 	               
					 	                  }							 	          					 	        	  
					 	          }			          
				    } catch (SQLException e) { 
				    	    System.out.println(e.getMessage());
			                e.printStackTrace();
			                }
				
				finally {
				        if (psCheckItem != null) {
				        	psCheckItem.close();
				           }
				        if (psCheckSong != null) {
				        	psCheckSong.close();
				           }
				        if (psCheckList != null) {
				        	psCheckList.close();
				           }
				        if (psInsert != null) {
				        	psInsert.close();
				           }
				        conn.close();
			        	return response;}
			}
			
	//-----------------------------------------------------------------------

			public String updateSong(  String song, String album, String newSinger) throws SQLException {

				  String response = "updateSong: ";	
				  PreparedStatement psCheckSong = null;
				  PreparedStatement psUpdate = null;
					 
			         String sqlCheckSong = 
				          "select song.song_id, album.album_id " + 
		                  "from song " + 
		                  "inner join album    on album.album_id = song.album_id " +
		                  " where song.title= '" + song + "'" +
		                  "   and album.name = '" + album + "'" ;	
		         
		         String sqlUpdate = "update song set singer = '" + newSinger +  "' where song_id = ? ";
		         
				try {      psCheckSong = conn.prepareStatement(sqlCheckSong);
                           ResultSet rsCheckSong = psCheckSong.executeQuery(); 
				         	
                           if (!rsCheckSong.isBeforeFirst()) { 	
			 	               response = response + "Song not found.";
			 	        	   return response ;			 	        	   
			 	               }
			 	          else {
					 	         int song_id     = rsCheckSong.getInt(1);
					 	              	       
							 	 psUpdate = conn.prepareStatement(sqlUpdate);  
							 	 psUpdate.setLong(1, song_id);
							 	        	       
								 int updatedRows = psUpdate.executeUpdate();			
								 response = response + "Rows updated: " + updatedRows;
								 return response ;	 
							  }							 	               					 	                  						 	          					 	        	  					 	          	          
				    } catch (SQLException e) { 
				    	    System.out.println(e.getMessage());
			                e.printStackTrace();
			                }
				
				finally {
				        if (psCheckSong != null) {
				        	psCheckSong.close();
				           }
				        if (psUpdate != null) {
				        	psUpdate.close();
				           }
				        conn.close();
			        	return response;}
			}
	   
}

	
	
	
