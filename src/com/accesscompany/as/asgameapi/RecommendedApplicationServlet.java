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
public class RecommendedApplicationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doGet(req, resp);
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		List<Entity> inventory = Inventory.getInventory(playerId);
		JSONObject objOutput = new JSONObject();
		try {
			JSONArray jsonInventory;
			if (inventory != null) {
				jsonInventory = SimpleJSON.entitiesToJSON(inventory);
			}
			else {
				jsonInventory = new JSONArray();
			}
			objOutput.put("owned", jsonInventory);	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		List<Entity> recommended = OnlineApplication.getRecommendedOnlineApplication(playerId, inventory);
		try {
			JSONArray jsonRecommended;
			if (recommended != null) {
				jsonRecommended = SimpleJSON.entitiesToJSON(recommended);
			}
			else {
				jsonRecommended = new JSONArray();
			}
			objOutput.put("recommended", jsonRecommended);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		resp.getWriter().println(objOutput.toString());
	}

}
