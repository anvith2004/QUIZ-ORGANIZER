package proj1;

import java.io.*;
import jakarta.servlet.http.*;

public class logout extends HttpServlet 
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		HttpSession session = req.getSession();
		session.invalidate();
		res.sendRedirect("login.jsp");
	}

}
