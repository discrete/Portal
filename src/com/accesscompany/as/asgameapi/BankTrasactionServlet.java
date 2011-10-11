package com.accesscompany.as.asgameapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
		
		String request_string = "http://as-billing.appspot.com/api/transaction?account=" + playerId + "&amount=" + amount;
		String line = null;
        try {
            URL url = new URL(request_string);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            

            while ((line = reader.readLine()) != null) {
            	if (line != null){
            		break;
            	}
            }
            reader.close();

        } catch (MalformedURLException e) {
            // ...
        } catch (IOException e) {
            // ...
        }
        if (line != null && line.equals(amount)) {
        	Entity lineTransaction = Coin.deposit(playerId, "1", Long.parseLong(amount), note);
    		if (lineTransaction != null) {
    			long balance = Coin.balance(playerId);
    			
    			resp.getWriter().println("[{\"amount\": \"" + amount + "\", \"balance\": \"" + balance + "\"}]");
    		}
    		else {
    			resp.getWriter().println("Sorry, we could not make the deposit[Internal]");
    		}
        }
		else {
			resp.getWriter().println("Sorry, we could not make the deposit[ASBilling]");
		}
	}

}
