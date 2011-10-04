package com.accesscompany.as.asgameapi;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SocialPlayerOnlineApplicationRelation {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity createSocialPlayerOnlineApplicationRelation(Key playerKey, Key appKey, Key coinKey)
	{
		Entity relation = new Entity("RelationSP&OA", playerKey);
		relation.setProperty("appKey", appKey);
		relation.setProperty("coinKey", coinKey);
		Date relatedDate = new Date();
		relation.setProperty("relatedDate", relatedDate);
		
		
		datastore.put(relation);
		return relation; 
	}
	
	public static Entity createSocialPlayerOnlineApplicationRelation(String playerId, String appId, Key coinKey)
	{
		Entity player = SocialPlayer.getSocialPlayer(playerId);
		Entity app = OnlineApplication.getOnlineApplication(appId);
		return createSocialPlayerOnlineApplicationRelation(player.getKey(), app.getKey(), coinKey);
		
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
