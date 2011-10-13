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
import com.google.appengine.api.datastore.Query.SortDirection;

public class OnlineApplication {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity registerOnlineApplication(String name, long price, String description)
	{
		Entity onlineApp = new Entity("OnlineApplication");
		onlineApp.setProperty("name", name);
		onlineApp.setProperty("price", price);
		Date registeredDate = new Date();
		onlineApp.setProperty("registeredDate", registeredDate);
		onlineApp.setProperty("description", description);
		
		datastore.put(onlineApp);
		return onlineApp;
	}
	public static Entity createOrUpdateOnlineApplication(String appId, String name, long price, String description)
	{
		//TODO add icon to a field
		Entity onlineApp = getOnlineApplication(appId);
		if (onlineApp == null) {
			onlineApp = new Entity("OnlineApplication", appId);
		}
		onlineApp.setProperty("name", name);
		onlineApp.setProperty("price", price);
		onlineApp.setProperty("description", description);
		datastore.put(onlineApp);
		
		return onlineApp;
	}
	
	public static Entity getOnlineApplication(String appId)
	{
		long id = Long.parseLong(appId);
		Key idKey = KeyFactory.createKey("OnlineApplication", id);
	  	try {	  
	  		return datastore.get(idKey);
	    }
	  	catch (EntityNotFoundException e) {
	  		return null;
	    }
	}

	public static Entity getOnlineApplication(Key appKey)
	{
	  	try {	  
	  		return datastore.get(appKey);
	    }
	  	catch (EntityNotFoundException e) {
	  		return null;
	    }
	}
	
	public static List<Entity> getRecommendedOnlineApplication(String playerId,
			List<Entity> inventory) {
		List<Entity> recommendedApplications = new ArrayList<Entity>();
		int foundCount = 0;
		Query recommendedQuery = new Query("OnlineApplication");
		List<Entity> recommended= datastore.prepare(recommendedQuery).asList(FetchOptions.Builder.withDefaults());
	    if (!recommended.isEmpty()) {
	        AppLoop: for (Entity app: recommended) {
	        	if (inventory != null ){
		        	for (Entity item: inventory) {
		        		if (item.getKey().equals(app.getKey())) {
		        			continue AppLoop;
		        		}
		        	}
	        	}
	        	recommendedApplications.add(app);
    			foundCount++;
	        	if (foundCount > 3) {
	        		break AppLoop;
	        	}
	        }
	        return recommendedApplications;
	    }

		return null;
	}

}
