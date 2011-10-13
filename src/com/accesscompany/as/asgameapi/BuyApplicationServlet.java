package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class BuyApplicationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		int errorCode = 0;
		/* super.doGet(req, resp); */
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		String appId = req.getParameter("appId");
		String sessionId = req.getParameter("sessionId");
		String userAgent = req.getParameter("userAgent");
		
		Entity socialPlayer = SocialPlayer.getSocialPlayer(playerId);
		Entity onlineApp = OnlineApplication.getOnlineApplication(appId);
		
		//Get the balance of the socialPlayer
		long balance = Coin.balance(playerId);
		long price = Long.parseLong(onlineApp.getProperty("price").toString());
		if (balance >= price) {
			Entity receipt = Coin.withdraw(playerId, "1", price, "Buying an application from AS" + appId);
			Inventory.createPurchase(playerId, onlineApp.getKey(), receipt.getKey());
			if (userAgent == null) {
				resp.getWriter().println("[{\"resultcode\": 0}]");
			}
			else {
				resp.getWriter().println("purchase made");
			}
			
		}
		else {
			errorCode = 1;
			if (userAgent == null) {
				resp.getWriter().println("[{\"resultcode\": 1}]");
			}
			else {
				resp.getWriter().println("Not enough funds");
			}
		}
		
	}

}
