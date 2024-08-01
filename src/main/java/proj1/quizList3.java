package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class quizList3 extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			PrintWriter out = res.getWriter();
			//dbSelect db = new dbSelect("SELECT DISTINCT quiz_name, attempts FROM portal.user_answers WHERE username = '"+username+"'");
			dbSelect db1 = new dbSelect("SELECT COUNT(DISTINCT quiz_name) FROM portal.user_answers");
			int n = db1.retrieveIntFirst1("COUNT(DISTINCT quiz_name)");
			dbSelect db2 = new dbSelect("SELECT DISTINCT quiz_name FROM portal.user_answers");
			System.out.println(n);
			String ql[] = new String[n];
			for (int i = 1; i <= n; i++)
			{
				ql[i-1] = db2.retrieveStr("quiz_name", i);
				//System.out.println(ql[i-1]);
			}

			session.setAttribute("ql", ql);
			session.setAttribute("n", n);
			res.sendRedirect("avgselect_quiz.jsp");
		} 	
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
