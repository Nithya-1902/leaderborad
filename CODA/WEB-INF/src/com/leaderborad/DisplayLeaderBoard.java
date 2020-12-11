package com.leaderborad;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBConn;

/**
 * Servlet implementation class DisplayLeaderBoard
 */
@WebServlet("/DisplayLeaderBoard")
public class DisplayLeaderBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageno = Integer.parseInt(request.getParameter("pageno"));
		String sql = "SELECT distinct *  FROM Game ORDER BY score DESC LIMIT " + (pageno - 1) * 10 + ",10;";
		String result = "";
		ResultSet rs = DBConn.performDBOperation(sql,DBConn.SELECT);
		if (rs == null) {
			response.getWriter().write("eof");
			return;
		} else {
			result = DisplayLeaderBoardImpl.formatLeaderBoardData(rs);
		}

		response.getWriter().write(result);
	}


}
