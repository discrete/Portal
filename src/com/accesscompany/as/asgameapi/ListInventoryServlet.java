package com.accesscompany.as.asgameapi;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class ListInventoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		List<Entity> inventory = Inventory.getInventory(playerId);
		if (inventory != null) {
			resp.getWriter().println(SimpleJSON.writeJSON(inventory, null));
		}
		else {
			resp.getWriter().println("[]");
		}
	}

}
