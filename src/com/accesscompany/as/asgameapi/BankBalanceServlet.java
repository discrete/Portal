package com.accesscompany.as.asgameapi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BankBalanceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("text/plain");
		String playerId = req.getParameter("playerId");
		long balance = Coin.balance(playerId);
		resp.getWriter().println("Your balance is " + balance);
	}

}
