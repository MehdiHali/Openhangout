package com.hali.openhangout.chat.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hali.util.DaoHelper;

public class RoomDao {
	
	public static Room find(int id) {
		Room room = null;
		try {
			Connection conn = DaoHelper.getDbConn();
			PreparedStatement stmt = conn.prepareStatement("select * from rooms where id = ?");
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			res.next();
			room = new Room();
			room.setId(res.getInt(1));
			room.setName(res.getString(1));
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();;
		}

		return room;
	}
	
	public static List<Room> findAll() {
		List<Room> rooms = new ArrayList<Room>();
		try {
			Connection conn = DaoHelper.getDbConn();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from rooms");
//			rooms = new HashSet<Room>();
			while(res.next()) {
				Room room = new Room();
				room.setId(res.getInt(1));
				room.setName(res.getString(2));
				rooms.add(room);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();;
		}
		return rooms;
	}
	
	public static boolean create(Room room) {
		boolean created = false;
		try {
			Connection conn = DaoHelper.getDbConn();
			PreparedStatement stmt =  conn.prepareStatement("insert into rooms (name) values (?)");
			stmt.setString(1, room.getName());
			int insertionCount = stmt.executeUpdate();
			if(insertionCount > 0) created = true;
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return created;
	}
	
	public static boolean delete(int roomID) {
		boolean deleted = false;
		try {
			Connection conn = DaoHelper.getDbConn();
			PreparedStatement stmt =  conn.prepareStatement("delete from rooms where id = ?");
			stmt.setInt(1, roomID);
			int deletionCount = stmt.executeUpdate();
			if(deletionCount > 0) deleted = true;
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
	

}
