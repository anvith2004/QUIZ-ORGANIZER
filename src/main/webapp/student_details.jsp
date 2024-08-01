<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="proj1.dbSelect" %>
<!DOCTYPE html>
<html>
<body>
<%
String n1 = "";
String n2 = "";
String n3 = "";
int n4 = 0;
String x = (String) session.getAttribute("username");
if (x == null)
{
	response.sendRedirect("login.jsp");
}
else
{
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	dbSelect db = new dbSelect("SELECT * FROM portal.student_details WHERE `username` = '"+x+"';");
	n1 = db.retrieveStrFirst("firstname");
	n2 = db.retrieveStrFirst("lastname");
	n3 = db.retrieveStrFirst("username");
	n4 = db.retrieveIntFirst("phone");
}
%>
Student Details<br><br>
firstname : <%= n1 %><br><br>
lastname : <%= n2 %><br><br>
username : <%= n3 %><br><br>
phone number : <%= n4 %><br><br>
<form action = "logout">
	<input type = submit value = "logout">
</form>
</body>
</html>