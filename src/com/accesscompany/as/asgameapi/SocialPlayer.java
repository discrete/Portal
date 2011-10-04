package com.accesscompany.as.asgameapi;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SocialPlayer {
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity createOrUpdateSocialPlayer(String playerId, String password,
			String email, String firstName, String lastName) {
		Entity socialPlayer = getSocialPlayer(playerId);
		if (socialPlayer == null) {
			socialPlayer = new Entity("SocialPlayer", playerId);
			
		}
		socialPlayer.setProperty("password", password);
		socialPlayer.setProperty("email", email);
		socialPlayer.setProperty("firstName", firstName);
		socialPlayer.setProperty("lastName", lastName);
		
		datastore.put(socialPlayer);
		
		return socialPlayer;
	}
	
	public static Entity getSocialPlayer(String playerId) {
		Key idKey = KeyFactory.createKey("SocialPlayer",playerId);
	  	try {	  
	  		return datastore.get(idKey);
	    }
	  	catch (EntityNotFoundException e) {
	  		return null;
	    }
	}
	
	public static String deleteSocialPlayer(String playerId) {
		Entity entity = getSocialPlayer(playerId);
		if (entity != null) {
			datastore.delete(entity.getKey());
			return ("SocialPlayer deleted successfully.");
		} else
			return ("SocialPlayer not found");
	}
}
