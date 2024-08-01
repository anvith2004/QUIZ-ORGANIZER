<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<%
String[][][] sqc = (String[][][]) session.getAttribute("sqc");
int[][] sqa = (int[][]) session.getAttribute("sqa");
int[][] sp = (int[][]) session.getAttribute("sp");
int del = (int) session.getAttribute("del");
//out.println(del);
String username = (String) session.getAttribute("username");
String quiz_name = (String) session.getAttribute("quiz_name");
String time = (String) session.getAttribute("time");
int ns = (int) session.getAttribute("ns");
out.println("<form action='arrQuesGet'>");
out.print(username+"<br><br>");
out.print(quiz_name+"<br><br>");
out.println("<input type='hidden' name='username' value='"+username+"'>");
out.println("<input type='hidden' name='quiz_name' value='"+quiz_name+"'>");
out.println("<input type='hidden' name='ns' value='"+ns+"'>");
String l = "";
for(int i = 0; i < ns; i++)
{
	int nq = sp[i][0];
	int mp = sp[i][2];
	int mn = sp[i][3];
	int nc = sp[i][1];
	out.println("positve marks : "+mp+"<br><br>");
	out.println("negative marks : "+mn+"<br><br>");
	out.println("<input type='hidden' name='nq"+(i+1)+"' value='"+nq+"'>");
	out.println("<input type='hidden' name='mp"+(i+1)+"' value='"+mp+"'>");
	out.println("<input type='hidden' name='mn"+(i+1)+"' value='"+mn+"'>");
	out.println("<input type='hidden' name='nc"+(i+1)+"' value='"+nc+"'>");
	out.print("Section - "+(i+1)+"<br><br>");
	for(int j = 0; j < sp[i][0]; j++)
	{
		out.print("Q"+(j+1)+":  "+"<input type='text' name='q"+(i+1)+""+(j+1)+"' value='"+sqc[i][j][0]+"'>"+"<br>");
		out.print("<ol>");
		for(int k = 1; k <= (sp[i][1]); k++)
		{
			out.print("<li>  "+"<input type='text' name='c"+(i+1)+""+(j+1)+""+(k)+"' value='"+sqc[i][j][k]+"'>"+"<br>");
			if (sqa[i][j] == k)
			{
				l = "checked";
			}
			out.println("<input type='radio' name='answer"+(i+1)+""+(j+1)+"' value='"+k+"' "+l+"><br><br>");
			l = "";
		}
		out.print("</ol>");
	}
}
out.println(
		"<input id='submit' type='submit'>"+
		"</form>"
		);
%>
</body>
</html>