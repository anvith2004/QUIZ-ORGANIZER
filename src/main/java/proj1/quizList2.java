package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class quizList2 extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			if (username.equals("admin"))
			{
				username = (String) req.getParameter("n2");
				session.setAttribute("username1", username);
			}
			PrintWriter out = res.getWriter();
			//dbSelect db = new dbSelect("SELECT DISTINCT quiz_name, attempts FROM portal.user_answers WHERE username = '"+username+"'");
			dbSelect db1 = new dbSelect("SELECT COUNT(DISTINCT(`quiz_name`)) FROM portal.user_answers WHERE username = '"+username+"'");
			int n = db1.retrieveIntFirst1("COUNT(DISTINCT(`quiz_name`))");
			dbSelect db2 = new dbSelect("SELECT DISTINCT quiz_name FROM portal.user_answers WHERE username = '"+username+"'");

			String ql[] = new String[n];
			int max_attempts[] = new int[n];
			for (int i = 1; i <= n; i++)
			{
				ql[i-1] = db2.retrieveStr("quiz_name", i);
				//System.out.println(ql[i-1]);
				dbSelect db3 = new dbSelect("SELECT MAX(DISTINCT(attempts)) FROM portal.user_answers WHERE username = '"+username+"' and quiz_name = '"+ql[i-1]+"'");
				max_attempts[i-1] = db3.retrieveIntFirst("MAX(DISTINCT(attempts))");
			}

			session.setAttribute("ql", ql);
			session.setAttribute("n", n);
			session.setAttribute("max_attempts", max_attempts);
			res.sendRedirect("select_quiz.jsp");
		} 	
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
