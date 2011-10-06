package com.accesscompany.as.asgameapi;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
			resp.getWriter().println("1");
		}
		else {
			String savedPassword = (String)socialPlayer.getProperty("password");
			if (savedPassword.equals(password)) {
				IdGenerator idGen = new IdGenerator();
				String sessionId = idGen.generateId(8);
				socialPlayer = SocialPlayer.createOrUpdateSocialPlayer(playerId, savedPassword, (String)socialPlayer.getProperty("email"), (String)socialPlayer.getProperty("firstName"), (String)socialPlayer.getProperty("lastName"), (String)socialPlayer.getProperty("nickname"), sessionId);
				/*resp.getWriter().println("Welcome " + (String)socialPlayer.getProperty("firstName"));
				resp.getWriter().println("sessionId:" + sessionId);
				resp.getWriter().println("name:" + socialPlayer.getProperty("firstName").toString());
				resp.getWriter().println("nickname:" + socialPlayer.getProperty("nickname").toString());*/
				/* resp.getWriter().println("0"); */
				Set<Entity> result = new HashSet<Entity>();
				result.add(socialPlayer);
				resp.getWriter().println(SimpleJSON.writeJSON(result, "0"));
			}
			else {
				resp.getWriter().println("2");
			}
		}
	}


}
