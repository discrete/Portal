package com.accesscompany.as.asgameapi;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Coin {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity transaction(String playerId, String appId, int amount, String note)
	{
		Entity player = SocialPlayer.getSocialPlayer(playerId);
		Entity app = OnlineApplication.getOnlineApplication(appId);
		Entity lineTransaction = new Entity("Coin", player.getKey());
		lineTransaction.setProperty("by", app.getKey());
		lineTransaction.setProperty("amount", amount);
		Date depositDate = new Date();
		lineTransaction.setProperty("depositDate", depositDate);
		lineTransaction.setProperty("note", note);
		
		datastore.put(lineTransaction);
		return lineTransaction;
	}
	
	public static Entity deposit(String playerId, String appId, int amount, String note)
	{
		return transaction(playerId, appId, amount, note);
	}
	
	public static Entity withdraw(String playerId, String appId, int amount, String note)
	{
		amount = amount * -1;
		return transaction(playerId, appId, amount, note);
	}
}
