package com.kpsc.music;

public class PlaylistItemDesc {
	String listname;
	String title;
	String singer;
	String album;
	String artist;
	
	public PlaylistItemDesc(String listname, String title, String singer, String album, String artist) {
		this.listname = listname;
		this.title = title;
		this.singer = singer;
		this.album = album;
		this.artist = artist;
	}
	

	 @Override
	   public String toString() {	   
		   String s = this.listname + " | "  + this.title + " | " + 
	                  this.singer   + " | "  + this.album+ " | "  + this.artist + "\r";	   
		   return s;
	   }

}
