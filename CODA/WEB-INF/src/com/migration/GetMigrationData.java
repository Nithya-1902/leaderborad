package com.migration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dbcon.DBConn;

public class GetMigrationData {
	public static void populateDatatoDB() {
		String targetURL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/Leaderboard_Initial_Dataset65148c7.json";
		HttpURLConnection connection = null;

		try {
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Get Response
			InputStream is = (InputStream) connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line, result = "";
			while ((line = rd.readLine()) != null) {
				// System.out.println(line);
				result = result + line;
			}
			JSONArray array = new JSONArray(result);
		    for(int i=0; i < array.length(); i++)   
		    {  
		    	JSONObject object = array.getJSONObject(i);  
		    	String v1=object.getString("team_name");  
		    	int v2=object.getInt("wins");  
		    	int v3=object.getInt("losses");  
		    	int v4=object.getInt("ties");  
		    	int v5=object.getInt("score");  
				String sql = ("insert into Game(team_name,wins,losses,ties,score) values('"+v1+"',"+v2+","+v3+","+v4+","+v5+")");
				DBConn.performDBOperation(sql, DBConn.UPDATE);
		    }

			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
