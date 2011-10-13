package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ResetDemoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		Inventory.deletePlayer(playerId);
		Coin.deletePlayer(playerId);
		resp.getWriter().println(playerId + " has been reset.");
	}

}
