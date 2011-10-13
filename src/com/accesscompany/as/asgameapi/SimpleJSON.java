package com.accesscompany.as.asgameapi;

import java.util.Map;
import java.util.logging.Level;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

public class SimpleJSON {
	/**
	 * List the entities in JSON format
	 * 
	 * @param entities
	 *            entities to return as JSON strings
	 */
	public static String writeJSON(Iterable<Entity> entities, String resultcode) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		sb.append("[");
		for (Entity result : entities) {
			Map<String, Object> properties = result.getProperties();
			sb.append("{");
			if (result.getKey().getName() == null)
				sb.append("\"id\" : \"" + result.getKey().getId() + "\",");
			else
				sb.append("\"name\" : \"" + result.getKey().getName() + "\",");

			for (String key : properties.keySet()) {
				sb.append("\"" + key + "\" : \"" + properties.get(key) + "\",");
			}
			if (resultcode != null) {
				sb.append(" \"resultcode\" : \"" + resultcode + "\"");
			} else {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			sb.append("},");
			i++;
		}
		if (i > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("]");
		return sb.toString();
	}

	public static JSONArray entitiesToJSON(Iterable<Entity> entities) {
		JSONArray jsonArr = new JSONArray();
		for (Entity entity : entities) {
			jsonArr.put(entityToJSON(entity));
		}
		return jsonArr;
	}

	public static JSONObject entityToJSON(Entity entity) {
		JSONObject json = new JSONObject();
		try {
			for (Map.Entry<String, Object> prop : entity.getProperties().entrySet()) {
				json.put(prop.getKey(), prop.getValue());
			}
			json.put("appId", entity.getKey().getId());
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		
		return json;
	}
}
