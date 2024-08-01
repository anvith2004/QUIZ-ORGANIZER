package proj1;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;

public class jdbchttp2 extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		try 
		{
			PrintWriter out = res.getWriter();
			String a = req.getParameter("n1");
			String b = req.getParameter("n2");
			String c = req.getParameter("n3");
			String d = req.getParameter("n4");
			int e = Integer.parseInt(req.getParameter("n5"));
			dbSelect db = new dbSelect("SELECT * FROM portal.student_details;");
			dbSelect db3 = new dbSelect("SELECT * FROM portal.login;");
			int row1 = db.check("username",a);
			int row = db.check("username",a);
			String z, x = "Welcome";
			if (row == 0)
			{
				dbUpdate db1 = new dbUpdate("INSERT INTO `portal`.`login` (`id`, `username`, `password`) VALUES (?, ?, ?);");
				dbUpdate db2 = new dbUpdate("INSERT INTO `portal`.`student_details` (`id`, `firstname`, `lastname`, `username`, `phone`) VALUES (?, ?, ?, ?, ?);");
				db3.rs.last();
				db.rs.last();
				int y = db.rs.getRow();
				y = y + 1;
				int y1 = db3.rs.getRow();
				y1 = y1 + 1;
				db1.pst.setInt(1, y1);
				db1.pst.setString(2, c);
				db1.pst.setString(3, d);
				db2.pst.setInt(1, y);
				db2.pst.setString(2, a);
				db2.pst.setString(3, b);
				db2.pst.setString(4, c);
				db2.pst.setInt(5, e);
				x = db1.exe() + "";
				z = db2.exe() + "";
				x = "registered<br><br>change in " + x + " rows";
			}
			else
			{
				x = "Username already in use";
			}
			out.println("<html><body>");
			out.println(x);
			out.println("</html></body>");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}