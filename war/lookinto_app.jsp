<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.accesscompany.as.asgameapi.OnlineApplication" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
String appId = request.getParameter("appId");

Entity onlineApp = OnlineApplication.getOnlineApplication(appId);
if (onlineApp == null) {
	%>
	Could not found <%=appId %>
	<%
}
else {
	 %>
	 id: <%=appId %><br/>
	 name: <%=onlineApp.getProperty("name") %><br>
	 description: <%=onlineApp.getProperty("description") %><br>
	 <%
}

%>
</body>
</html>