<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Arial, sans-serif;
}

body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f4f4f4;
}

.wrapper {
  width: 80%;
  max-width: 600px;
  margin: 20px;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form {
  display: flex;
  flex-direction: column;
}

form input {
  margin-bottom: 15px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

form input[type="submit"] {
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

form input[type="submit"]:hover {
  background-color: #0056b3;
}

</style>
</head>
<body>
<div class="wrapper">
<%
String username = (String) request.getParameter("n1");
String quiz_name = (String) request.getParameter("n2");
int ns = Integer.parseInt((String) request.getParameter("n3"));
int hr = Integer.parseInt((String) request.getParameter("n4"));
int min = Integer.parseInt((String) request.getParameter("n5"));
int sec = Integer.parseInt((String) request.getParameter("n6"));
session.setAttribute("hr", hr);
session.setAttribute("min", min);
session.setAttribute("sec", sec);
//out.println(hr+""+min+""+sec);
out.println("<form action='file2.jsp'>");
out.println("<input type='hidden' name='username' value='"+username+"'>");
out.println("<input type='hidden' name='quiz_name' value='"+quiz_name+"'>");
out.println("<input type='hidden' name='ns' value='"+ns+"'>");
for (int i = 1; i <= ns; i++)
{	
	out.println("Section - "+i+":<br><br>");
	out.println("No. of Questions  ");
	out.println("<input type='text' name='nq"+i+"'><br><br>");
	out.println("No. of Options  ");
	out.println("<input type='text' name='nc"+i+"'><br><br>");
	out.println("Positive marks per Question  ");
	out.println("<input type='text' name='mp"+i+"'><br><br>");
	out.println("Negative marks per Question  ");
	out.println("<input type='text' name='mn"+i+"'><br><br>");
}
out.println(
			"<input type='submit'>"+
			"</form>"
			);
%>
</div>
</body>
</html>