package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class get_addmarks extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			if (username.equals("admin"))
			{
				username = (String) session.getAttribute("username1");
			}
			String quiz_name = (String) session.getAttribute("quiz_name");
			int ns = (int) session.getAttribute("ns");
			int marks = (int) session.getAttribute("marks");
			int attempt = (int) session.getAttribute("attempt");
			String[][][] sqc = (String[][][]) session.getAttribute("sqc");
			int[][] sqa = (int[][]) session.getAttribute("sqa");
			int[][] squa = new int[sqa.length][100];
			int[][] sp = (int[][]) session.getAttribute("sp");
			
	
			
			for(int i = 0; i < sqa.length; i++)
			{
				for(int j = 0; j < sp[i][0]; j++)
				{
					dbSelect db = new dbSelect("SELECT * FROM portal.user_answers WHERE username = '"+username+"' and quiz_name = '"+quiz_name+"' and attempts = '"+attempt+"' and section_no = '"+(i+1)+"' and question_no = '"+(j+1)+"'");
					squa[i][j] = db.retrieveIntFirst("ans_no");
					System.out.println(squa[i][j]	);
				}
			}
			session.setAttribute("squa", squa);
			res.sendRedirect("file4.jsp");
		} 	
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
