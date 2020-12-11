<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>

<html>
<%
		try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Players", "root", "root");
		Statement stmt = con.createStatement();
		String sql = "SELECT team_name  FROM Game;";
		ResultSet rs = stmt.executeQuery(sql);
			JSONArray array = new JSONArray();
		while(rs.next()) {

			array.put(rs.getString("team_name"));
			//out.println(rs.getString("team_name"));
		}			out.println(array.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
%>
	
</html>
