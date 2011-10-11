package com.accesscompany.as.asgameapi;

import java.util.ArrayList;
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
	
	public static List<Entity> getInventory(String playerId)
	{
		List<Entity> playerApplications = new ArrayList<Entity>();
		Query inventoryQuery = new Query("Inventory");
		inventoryQuery.addFilter("playerId", Query.FilterOperator.EQUAL, playerId);
		List<Entity> inventory = datastore.prepare(inventoryQuery).asList(FetchOptions.Builder.withDefaults());
	    if (!inventory.isEmpty()) {
	        for (Entity item : inventory) {
	        	playerApplications.add(OnlineApplication.getOnlineApplication((Key)item.getProperty("appKey")));
	        	
	        }
	        return playerApplications;
	    }
	    return null;
	}
	
	public static Entity getInventoryItem(String playerId, Key appKey)
	{
		Query inventoryQuery = new Query("Inventory");
		inventoryQuery.addFilter("playerId", Query.FilterOperator.EQUAL, playerId);
		inventoryQuery.addFilter("appKey", Query.FilterOperator.EQUAL, appKey);
		List<Entity> inventory = datastore.prepare(inventoryQuery).asList(FetchOptions.Builder.withDefaults());
	    if (!inventory.isEmpty()) {
	        for (Entity item : inventory) {
	        	return item;
	        }
	    }
	    return null;
	}
}
