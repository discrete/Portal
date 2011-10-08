package com.accesscompany.as.asgameapi;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Inventory {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity createPurchase(String playerId, Key appKey, Key coinKey)
	{
		Entity purchase = new Entity("Inventory");
		purchase.setProperty("playerId", playerId);
		purchase.setProperty("appKey", appKey);
		purchase.setProperty("coinKey", coinKey);
		Date relatedDate = new Date();
		purchase.setProperty("relatedDate", relatedDate);
		
		
		datastore.put(purchase);
		return purchase; 
	}
}
