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
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

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
		List<Entity> recommended = OnlineApplication.getRecommendedOnlineApplication(playerId, inventory);
		if (inventory != null) {
			JSONArray jsonInventory = SimpleJSON.entitiesToJSON(inventory);
			JSONArray jsonRecommended = SimpleJSON.entitiesToJSON(recommended);
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("owned", jsonInventory);
				obj.put("recommended", jsonRecommended);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			resp.getWriter().println(obj.toString());
		}
		else {
			resp.getWriter().println("[]");
		}
	}

}
