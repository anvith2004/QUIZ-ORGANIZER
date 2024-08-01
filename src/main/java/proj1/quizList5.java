package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class quizList5 extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			PrintWriter out = res.getWriter();
			dbSelect db = new dbSelect("SELECT DISTINCT(`quiz_name`) FROM portal.quiz_properties");
			dbSelect db1 = new dbSelect("SELECT COUNT(DISTINCT(`quiz_name`)) FROM portal.quiz_properties");
			int n = db1.retrieveIntFirst1("COUNT(DISTINCT(`quiz_name`))");
			String ql[] = new String[n];
			for (int i = 1; i <= n; i++)
			{
				ql[i-1] = db.retrieveStr("quiz_name", i);
			}
			HttpSession session = req.getSession();
			session.setAttribute("ql", ql);
			session.setAttribute("n", n);
			session.setAttribute("del", 1);
			res.sendRedirect("file10.jsp");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
