package com.kpsc.music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Playlist {
	
	private int playlist_id;
	private String listname;

	public Playlist (String listname) {
		this.listname = listname;
	}
	


}
