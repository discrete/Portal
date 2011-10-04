package com.accesscompany.as.asgameapi;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class OnlineApplication {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity registerOnlineApplication(String name, String description)
	{
		Entity onlineApp = new Entity("OnlineApplication");
		onlineApp.setProperty("name", name);
		onlineApp.setProperty("description", description);
		
		datastore.put(onlineApp);
		return onlineApp;
	}
	public static Entity createOrUpdateOnlineApplication(String appId, String name, String description)
	{
		//TODO add icon to a field
		Entity onlineApp = getOnlineApplication(appId);
		if (onlineApp == null) {
			onlineApp = new Entity("OnlineApplication", appId);
		}
		onlineApp.setProperty("name", name);
		onlineApp.setProperty("description", description);
		datastore.put(onlineApp);
		
		return onlineApp;
	}
	
	public static Entity getOnlineApplication(String appId)
	{
		Key idKey = KeyFactory.createKey("OnlineApplication",appId);
	  	try {	  
	  		return datastore.get(idKey);
	    }
	  	catch (EntityNotFoundException e) {
	  		return null;
	    }
	}

}