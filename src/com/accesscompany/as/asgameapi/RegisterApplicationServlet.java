package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class RegisterApplicationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.setContentType("text/plain");
		String name = req.getParameter("name");
		long price = Long.parseLong(req.getParameter("price"));
		String description = req.getParameter("description");
		Entity onlineApp = OnlineApplication.registerOnlineApplication(name, price, description);
		if (onlineApp == null) {
			resp.getWriter().println("ERROR: we could not register your application");
		}
		else {
			long appId =  onlineApp.getKey().getId();
			resp.getWriter().println("Your application " + onlineApp.getProperty("name").toString() +" appID: " + appId);
		}
	}


}
