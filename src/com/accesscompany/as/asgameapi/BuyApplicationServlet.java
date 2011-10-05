package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class BuyApplicationServlet extends HttpServlet {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		String appId = req.getParameter("appId");
		String playerId = req.getParameter("playerId");
		Entity player = SocialPlayer.getSocialPlayer(playerId);
		Entity app = OnlineApplication.getOnlineApplication(appId);
		
		Query q = new Query("Coin");
		q.addFilter("playerKey", Query.FilterOperator.EQUAL, player.getKey());
		
		PreparedQuery pq = datastore.prepare(q);
		
		long balance = 0;
		for (Entity lineTransaction : pq.asIterable()) {
			balance = balance + Long.parseLong(lineTransaction.getProperty("amount").toString());
		}
		
		long price = Long.parseLong(app.getProperty("price").toString());
		if (balance < price) {
			resp.getWriter().println("Not enough fund");
		}
		Coin.withdraw(playerId, appId, price, "bought " + app.getProperty("name"));
	}

}
