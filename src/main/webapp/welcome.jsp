<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap" rel="stylesheet">
<style>
* {
margin: 0;
padding: 0;
box-sizing: border-box;
font-family: "Poppins", sans-serif;
}

body {
display: flex;
justify-content: center;
align-items: center;
min-height: 100vh;
background: url('img1.jpg') no-repeat;
background-size: cover;
background-position: center;
color: #fff;
}

.wrapper {
width: 420px;
background: transparent;
border: 2px solid rgba(255, 255, 255, .2);
border-radius: 10px;
padding: 30px 40px;
}

a {
color: #fff;
text-decoration: none;
font-weight: 600;
margin-bottom: -22px;
display: block;
font-size: 16px;
}

a:hover {
text-decoration: underline;
}

form {
margin-top: 15px;
}

input[type="submit"] {
background-color: #fff;
color: #333;
border: none;
outline: none;
border-radius: 40px;
padding: 10px 20px;
cursor: pointer;
font-size: 16px;
font-weight: 600;
}

input[type="submit"]:hover {
background-color: #e0e0e0;
}

.wrapper h1 {
    font-size: 36px;
    text-align: center;
}

</style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
<div class="wrapper">
<%
String x = (String) session.getAttribute("username");

if (x == null)
{
	response.sendRedirect("login.jsp");
}
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<h1>Welcome : <%= x %><br><br></h1>
<a href = "timer.jsp">Timer</a><br><br>
<%	
if (x != null)
{
	if (x.equals("admin"))
	{
		out.println("<a href = 'file0.jsp'>Create Quiz</a><br><br>");
		out.println("<a href = 'file7.jsp'>Analysis</a><br><br>");
		out.println("<a href = 'quizList4'>Edit Quiz</a><br><br>");
		out.println("<a href = 'quizList5'>Delete Quiz</a><br><br>");
		
	}
	else
	{
		out.println("<a href = 'student_details.jsp'>Student Details</a><br><br>");
		out.println("<a href = 'quizList1'>Take a Quiz</a><br><br>");
		out.println("<a href = 'quizList2'>Analysis</a><br><br>");
	}
}
%>	
<form action = "logout">
	<input type = submit value = "Logout">
</form><br>
</div>
</body>
</html>