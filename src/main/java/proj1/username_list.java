package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class username_list extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			PrintWriter out = res.getWriter();
			dbSelect db = new dbSelect("SELECT * FROM portal.login WHERE username != 'admin'");
			dbSelect db1 = new dbSelect("SELECT COUNT(username) FROM portal.login WHERE username != 'admin'");
			String user[] = new String[100];
			int n = db1.retrieveIntFirst1("COUNT(username)");
			String change = req.getParameter("change");
			for (int i = 1; i <= n; i++)
			{
				user[i-1] = db.retrieveStr("username", i);
			}
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			session.setAttribute("n", n);
			res.sendRedirect("file8.jsp");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
