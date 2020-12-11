package com.team;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dbcon.DBConn;

/**
 * Servlet implementation class populateTeamNames
 */
@WebServlet("/populateTeamNames")
public class populateTeamNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String sql = "SELECT distinct team_name  FROM Game;";
			ResultSet rs = DBConn.performDBOperation(sql,DBConn.SELECT);
			JSONArray array = new JSONArray();
			try {
				while (rs.next()) {
					array.put(rs.getString("team_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().write(array.toString());
	}

}
