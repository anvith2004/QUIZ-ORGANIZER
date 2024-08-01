package proj1;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
	
public class jdbchttp1 extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		try 
		{
			PrintWriter out = res.getWriter();
			String a = req.getParameter("n1");
			String b = req.getParameter("n2");
			dbSelect db = new dbSelect("SELECT * FROM portal.login;");
			int row = db.check("username",a);
			String x;
			if (row == 0)
			{
				x = "Invalid Username";
			}
			else
			{
				if (db.retrieveStr("password",row).equals(b))
				{
					x = "Welcome";
				}
				else
				{
					x = "Invalid Password";
				}
			}
			HttpSession session = req.getSession();
			session.setAttribute("x", x);
			session.setAttribute("username", a);
			if (x == "Welcome")
			{
				res.sendRedirect("welcome.jsp");
				db.finalize();
			}
			else
			{
				out.println("<html><body>");
				out.println(x);
				out.println("</html></body>");
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}

