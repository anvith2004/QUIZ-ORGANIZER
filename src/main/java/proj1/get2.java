package proj1;

import java.io.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class get2 extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		
		String a = (String) req.getAttribute("a");
		if (a == null)
		{
			System.out.println(5);
		}
		System.out.println(a);
	}
}
