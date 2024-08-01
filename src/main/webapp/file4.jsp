<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<%
String[][][] sqc = (String[][][]) session.getAttribute("sqc");
int[][] sqa = (int[][]) session.getAttribute("sqa");
int[][] sp = (int[][]) session.getAttribute("sp");
int[][] squa = (int[][]) session.getAttribute("squa");
String username = (String) session.getAttribute("username");
if (username.equals("admin"))
{
	username = (String) session.getAttribute("username1");
}
String quiz_name = (String) session.getAttribute("quiz_name");
int ns = (int) session.getAttribute("ns");
out.print(username+"<br><br>");
out.print(quiz_name+"<br><br>");
for(int i = 0; i < ns; i++)
{
	out.print("Section - "+(i+1)+"<br><br>");
	for(int j = 0; j < sp[i][0]; j++)
	{
		out.print("Q"+(j+1)+":  "+sqc[i][j][0]+"<br>");
		if (squa[i][j] == 0)
		{
			out.print("n/a<br>");
		}
		out.print("<ol>");
		for(int k = 1; k <= (sp[i][1]); k++)
		{
			out.print("<li>  "+sqc[i][j][k]);
			if (sqa[i][j] == squa[i][j])
			{
				if (sqa[i][j] == k)
				{
					out.print("       r");
				}
				
			}
			else
			{
				if (sqa[i][j] == k)
				{
					out.print("                  r");
				}
				if (squa[i][j] == k)
				{
					out.print("                  w");
				}
			}
		}
		out.print("</ol>");
	}
}

%>
</body>
</html>