<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<%
String[][][] sqc = (String[][][]) session.getAttribute("sqc");
int[][] sqa = (int[][]) session.getAttribute("sqa");
int[][] sp = (int[][]) session.getAttribute("sp");
float[][] avgmarks_quiz = (float[][]) session.getAttribute("avgmarks_quiz");
String[][] savgmarks_quiz = (String[][]) session.getAttribute("savgmarks_quiz");
String username = (String) session.getAttribute("username");
String quiz_name = (String) session.getAttribute("quiz_name");
int ns = (int) session.getAttribute("ns");
int total = (int) session.getAttribute("total");
int no_q = 0;
float no_m = 0;
out.print(username+"<br><br>");
out.print(quiz_name+"<br><br>");
for(int i = 0; i < ns; i++)
{
	out.print("Section - "+(i+1)+"<br><br>");
	for(int j = 0; j < sp[i][0]; j++)
	{
		out.print("Q"+(j+1)+":  "+sqc[i][j][0]+"<br>");
		no_m = avgmarks_quiz[i][j] + no_m;
		out.print("Avg = "+savgmarks_quiz[i][j]);
		out.print("<ol>");
		for(int k = 1; k <= (sp[i][1]); k++)
		{
			out.print("<li>  "+sqc[i][j][k]);
		}
		out.print("</ol>");
	}
}
out.print("Entire average is "+String.format("%.2f", no_m));
%>
</body>
</html>