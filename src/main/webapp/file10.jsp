<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<%
int del = (int) session.getAttribute("del");
if (del == 1)
{
	out.println("<form action='arrQuesGet'>");
}
else
{
	out.println("<form action='arrQuesExe'>");
}
out.println("Quiz List :<br><br>");
String username = (String) session.getAttribute("username");
String ql[] = (String[]) session.getAttribute("ql");
int n = (int) session.getAttribute("n");
out.println("<input type='hidden' name='n1' value='"+username+"'>");
for (int i = 0; i < n; i++)
{
	out.println(ql[i]);
	out.println("<input type='radio' name='n2' value='"+ql[i]+"'><br><br>");
}
out.println("<input type='hidden' name='n3' value='"+10+"'><br><br>");

//name of quiz to execute<br>
//<input type='text' name='n2'><br><br>
%>

	<input type='submit'>
</form>
</body>
</html>