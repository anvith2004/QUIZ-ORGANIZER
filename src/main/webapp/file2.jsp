<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Your Quiz Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <style>
    body {
  font-family: "Poppins", sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f4f4f4;
}

.wrapper {
  width: 60%;
  background: #fff;
  border: 2px solid rgba(0, 0, 0, 0.1);
  color: #333;
  border-radius: 10px;
  padding: 30px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 36px;
  text-align: center;
  margin-bottom: 20px;
}

ol {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 15px;
}

input[type="text"] {
  width: 70%; /* Adjust the width as needed */
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
  box-sizing: border-box;
  display: inline-block;
}

input[type="radio"] {
  margin-left: 10px; /* Adjust the margin as needed */
}

input[type="submit"] {
  background-color: #4caf50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

input[type="submit"]:hover {
  background-color: #45a049;
}


  </style>
</head>

<body>
  <div class="wrapper">
    <form action="arrQuesGet">
      <h1>Your Quiz Title</h1>
      <%
String username = (String) request.getParameter("username");
String quiz_name = (String) request.getParameter("quiz_name");
int ns = Integer.parseInt((String) request.getParameter("ns"));
out.println(username+"<br>");
out.println(quiz_name+"<br>");
out.println("<form action ='arrQuesGet'>");
out.println("<input type='hidden' name='ns' value='"+ns+"'>");
out.println("<input type='hidden' name='username' value='"+username+"'>");
out.println("<input type='hidden' name='quiz_name' value='"+quiz_name+"'>");
for(int i = 1; i <= ns; i++)
{
	out.println("Section - "+i+":"); 
	out.println("<ol>");
	int nq = Integer.parseInt((String) request.getParameter("nq"+i));
	int mp = Integer.parseInt((String) request.getParameter("mp"+i));
	int mn = Integer.parseInt((String) request.getParameter("mn"+i));
	int nc = Integer.parseInt((String) request.getParameter("nc"+i));
	out.println("positve marks : "+mp+"<br><br>");
	out.println("negative marks : "+mn+"<br><br>");
	out.println("<input type='hidden' name='nq"+i+"' value='"+nq+"'>");
	out.println("<input type='hidden' name='mp"+i+"' value='"+mp+"'>");
	out.println("<input type='hidden' name='mn"+i+"' value='"+mn+"'>");
	out.println("<input type='hidden' name='nc"+i+"' value='"+nc+"'>");
	for (int j = 1; j <= nq; j++)
	{	
		
		out.println("<li>  Q"+j+": <input type='text' name='q"+i+""+j+"'><br><br>");
		out.println("<ol>");
		for (int k = 1; k <= nc; k++)
		{
			out.println("<li> <input type='text' name='c"+i+""+j+""+k+"'>");
			out.println("<input type='radio' name='answer"+i+""+j+"' value='"+k+"'><br><br>");
		}
		out.println("</ol>");
	}
	out.println("</ol>");
	
}
out.println("<input type='submit'>");
%>
      
    </form>
  </div>
</body>

</html>
