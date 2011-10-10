package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class BankTrasactionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		String amount = req.getParameter("amount");
		String note = req.getParameter("note");
		
		/* request to as-billing */
		Entity lineTransaction = Coin.deposit(playerId, "1", Long.parseLong(amount), note);
		if (lineTransaction != null) {
			resp.getWriter().println("Your deposit is safe with me");
		}
		else {
			resp.getWriter().println("Sorry, we could not make the deposit");
		}
	}

}
