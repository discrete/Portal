package com.accesscompany.as.asgameapi;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

public class Coin {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity transaction(String playerId, String appId, long price, String note)
	{
		Entity lineTransaction = new Entity("Coin");
		lineTransaction.setProperty("playerId", playerId);
		lineTransaction.setProperty("appId", appId);
		lineTransaction.setProperty("amount", price);
		Date depositDate = new Date();
		lineTransaction.setProperty("depositDate", depositDate);
		lineTransaction.setProperty("note", note);
		
		datastore.put(lineTransaction);
		return lineTransaction;
	}
	
	public static Entity deposit(String playerId, String appId, long amount, String note)
	{
		return transaction(playerId, appId, amount, note);
	}
	
	public static Entity withdraw(String playerId, String appId, long price, String note)
	{
		price = price * -1;
		return transaction(playerId, appId, price, note);
	}
	
	public static long balance(String playerId)
	{
		long balance = 0;
		Query query = new Query("Coin");
	    List<Entity> transactions = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
	    if (transactions.isEmpty()) {
	    	return 0;
	    }
	    else {
	        for (Entity transaction : transactions) {
	        	balance = balance + Long.parseLong(transaction.getProperty("amount").toString());
	        }
	        return balance;
	    }
	}
}
