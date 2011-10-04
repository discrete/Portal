package com.accesscompany.as.asgameapi;

import java.util.Map;
import java.util.logging.Level;

import com.google.appengine.api.datastore.Entity;

public class SimpleJSON {
	/**
	 * List the entities in JSON format
	 * 
	 * @param entities
	 *          entities to return as JSON strings
	 */
  public static String writeJSON(Iterable<Entity> entities) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    sb.append("[");
    for (Entity result : entities) {
      Map<String, Object> properties = result.getProperties();
      sb.append("{");
      if (result.getKey().getName() == null)
        sb.append("\"name\" : \"" + result.getKey().getId() + "\",");
      else
        sb.append("\"name\" : \"" + result.getKey().getName() + "\",");

      for (String key : properties.keySet()) {
        sb.append("\"" + key + "\" : \"" + properties.get(key) + "\",");
      }
      sb.deleteCharAt(sb.lastIndexOf(","));
      sb.append("},");
      i++;
    }
    if (i > 0) {
      sb.deleteCharAt(sb.lastIndexOf(","));
    }
    sb.append("]");
    return sb.toString();
  }
}
