package com.leaderborad;

import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayLeaderBoardImpl {
	public static String formatLeaderBoardData(ResultSet rs) {
		try {
			JSONArray array = new JSONArray();
			while (rs.next()) {
				try {
					JSONObject object = new JSONObject();
					object.put("team_name", rs.getString("team_name"));
					object.put("wins", rs.getInt("wins"));
					object.put("losses", rs.getInt("losses"));
					object.put("ties", rs.getInt("ties"));
					object.put("score", rs.getInt("score"));
					array.put(object);
				} catch (Exception e) {
				}
			}
			return array.toString();
		} catch (Exception e) {
			return null;
		}
	}
}
