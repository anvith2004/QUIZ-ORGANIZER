<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<form action='get_marks'>

<%
out.println("Quiz List :<br><br>");
String username = (String) session.getAttribute("username");
if (username.equals("admin"))
{
	username = (String) session.getAttribute("username1");
}
String ql[] = (String[]) session.getAttribute("ql");
int max_attempts[] = (int[]) session.getAttribute("max_attempts");
int n = (int) session.getAttribute("n");
out.println("<input type='hidden' name='username' value='"+username+"'>");
for (int i = 0; i < n; i++)
{
	for (int j = 1; j <=  max_attempts[i]; j++)
	{
		out.println(ql[i]+"_"+j);
		out.println("<input type='radio' name='n1' value='"+ql[i]+"_"+j+"'><br><br>");
		out.println("<input type='hidden' name='n2' value="+j+"><br><br>");
	}
}
%>
	<input type='submit'>
	</form>
</body>
</html>