package proj1;

import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class get extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{

		
		String a = "hello";
		RequestDispatcher rd = req.getRequestDispatcher("/get2");
		req.setAttribute("a", a);
		HttpSession session = req.getSession();
		session.setAttribute("x", a);
		res.sendRedirect("get2");
	}
}
