package com.team;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBConn;

/**
 * Servlet implementation class AddTeam
 */
@WebServlet("/AddTeam")
public class AddTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String team_name = (String) request.getParameter("team_name");
		String sql = "insert into Game(team_name,wins,losses,ties,score) values('" + team_name + "',0,0,0,0)";
		DBConn.performDBOperation(sql, DBConn.UPDATE);
		response.getWriter().write(team_name);
	}

}
