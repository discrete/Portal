<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Applications for you</title>
</head>
<body>
<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Greetings belonging to the selected Guestbook.
    String playerId = request.getParameter("playerId");
    Query query = new Query("OnlineApplication");
    List<Entity> onlineApps = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
    if (onlineApps.isEmpty()) {
        %>
        <p>No registered application yet</p>
        <%
    } else {
        %>
        <p>registered applications</p>
        <%
        for (Entity app : onlineApps) {
        	%>
        	id: <%= app.getKey().getId() %><br/>
        	name: <a href="lookinto_app.jsp?playerId=<%=playerId %>&appId=<%=app.getKey().getId()%>"><%= app.getProperty("name") %></a><br/>
        	description: <%=app.getProperty("description") %><br/>
            <%
        }
    }
%>
</body>
</html>