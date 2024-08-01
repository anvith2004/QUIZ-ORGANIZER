<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<%
String[][][] sqc = (String[][][]) session.getAttribute("sqc");
int[][] sqa = (int[][]) session.getAttribute("sqa");
int[][] sp = (int[][]) session.getAttribute("sp");
String username = (String) session.getAttribute("username");
String quiz_name = (String) session.getAttribute("quiz_name");
String time = (String) session.getAttribute("time");
int ns = (int) session.getAttribute("ns");
out.println("<div id = 'timer'>"+time+"</div>");
out.println("<form action='arrAnsGet'>");
out.print(username+"<br><br>");
out.print(quiz_name+"<br><br>");
out.println("<input type='hidden' name='username' value='"+username+"'>");
out.println("<input type='hidden' name='quiz_name' value='"+quiz_name+"'>");
out.println("<input type='hidden' name='ns' value='"+ns+"'>");
for(int i = 0; i < ns; i++)
{
	out.print("Section - "+(i+1)+"<br><br>");
	for(int j = 0; j < sp[i][0]; j++)
	{
		out.print("Q"+(j+1)+":  "+sqc[i][j][0]+"<br>");
		out.print("<ol>");
		for(int k = 1; k <= (sp[i][1]); k++)
		{
			out.print("<li>  "+sqc[i][j][k]);
			out.println("<input type='radio' name='useranswer"+(i+1)+""+(j+1)+"' value='"+k+"'><br><br>");

		}
		out.print("</ol>");
	}
}
out.println(
		"<input id='submit' type='submit'>"+
		"</form>"
		);
%>
<script src = 'trail.js'></script>
</body>
</html>