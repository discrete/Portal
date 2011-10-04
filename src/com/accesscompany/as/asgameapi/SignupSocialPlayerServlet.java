package com.accesscompany.as.asgameapi;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SignupSocialPlayerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		String playerId = req.getParameter("playerId");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		
		try {
			SocialPlayer.createOrUpdateSocialPlayer(playerId, password, email, firstName, lastName);
		}
		catch (Exception e){
			return;
		}
	}
}
