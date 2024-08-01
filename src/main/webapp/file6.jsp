<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<%
int marks = (int) session.getAttribute("marks");
String quiz_name = (String) session.getAttribute("quiz_name");
String username = (String) session.getAttribute("username");
if (username.equals("admin"))
{
	username = (String) session.getAttribute("username1");
}
out.println("u got "+marks+" marks");
%>
<form action='arrQuesExe'>
<%
out.println("<input type='hidden' name='n1' value='"+username+"'>");
out.println("<input type='hidden' name='n2' value='"+quiz_name+"'>");
out.println("<input type='hidden' name='n3' value='"+4+"'><br><br>");
%>

	<input type='submit' value='detailed analysis'>
</form>
</body>
</html>