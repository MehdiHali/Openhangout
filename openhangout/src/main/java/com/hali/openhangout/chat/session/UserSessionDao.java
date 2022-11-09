package com.hali.openhangout.chat.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.hali.openhangout.chat.room.Room;
import com.hali.openhangout.chat.room.RoomDao;
import com.hali.util.DaoHelper;

public class UserSessionDao {
	
	public static boolean create(UserSession session) {
		boolean created = false;
		try {
			Connection conn = DaoHelper.getDbConn();
			PreparedStatement stmt = conn.prepareStatement("insert into sessions(id, roomID) values (?,?)");
			stmt.setString(1, session.getId());
			stmt.setInt(2, session.getRoom().getId());
			int insertionCount = stmt.executeUpdate();
			if(insertionCount > 0) created = true;
			conn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		return created;
	}
	
	public static UserSession find(String sessionID) {
		UserSession session = null;
		Connection conn = DaoHelper.getDbConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from sessions where id = ?");
			stmt.setString(1, sessionID);
			ResultSet res = stmt.executeQuery();
			res.next();
			session = new UserSession();
			session.setId(res.getString(1));
				Room room = RoomDao.find(res.getInt(2)) ;
			session.setRoom(room);
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return session;
	}

	public static boolean delete(String sessionID) {
		boolean deleted = false;
		Connection conn = DaoHelper.getDbConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("delete from sessions where id = ?");
			stmt.setString(1, sessionID);
			int deletionCount = stmt.executeUpdate();
			if(deletionCount > 0) deleted = true;
			conn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		return deleted;
	}
	
	public static Set<String> getSessionsIdByRoom(int roomID){
		Set<String> sessionsIds = null;
				Connection conn = DaoHelper.getDbConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("select id from sessions where roomID = ?");
			stmt.setInt(1, roomID);
			ResultSet res = stmt.executeQuery();
			sessionsIds = new HashSet<String>();
			while(res.next()) {
				sessionsIds.add(res.getString(1));
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sessionsIds;
	}

}
