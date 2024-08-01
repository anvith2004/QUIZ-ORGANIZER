<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<form action='avg_marks'>

<%
out.println("Quiz List :<br><br>");
String username = (String) session.getAttribute("username");
String ql[] = (String[]) session.getAttribute("ql");
int n = (int) session.getAttribute("n");
out.println("<input type='hidden' name='username' value='"+username+"'>");
for (int i = 0; i < n; i++)
{
	out.println(ql[i]);
	out.println("<input type='radio' name='n1' value='"+ql[i]+"'><br><br>");
}
%>
	<input type='submit'>
	</form>
</body>
</html>