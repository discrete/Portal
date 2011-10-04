package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class AuthenticatePlayerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		String password = req.getParameter("password");
		Entity socialPlayer = SocialPlayer.getSocialPlayer(playerId);
		if (socialPlayer == null) {
			resp.getWriter().println("We could not found you");
		}
		else {
			String savedPassword = (String)socialPlayer.getProperty("Password");
			if (savedPassword.equals(password)) {
				resp.getWriter().println("Welcome " + (String)socialPlayer.getProperty("FirstName"));
			}
			else {
				resp.getWriter().println("Wrong password");
			}
		}
	}


}
