package com.playgame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayGame
 */
@WebServlet("/PlayGame")
public class PlayGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TeamName_A = request.getParameter("teamA");
		String TeamName_B = request.getParameter("teamB");
		String GameResult = request.getParameter("result");
		Score.updateScore(TeamName_A, TeamName_B, GameResult);
		response.getWriter().write("updated");
	}

}
