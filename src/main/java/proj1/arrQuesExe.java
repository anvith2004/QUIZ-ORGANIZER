package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class arrQuesExe extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try 
		{
			PrintWriter out = res.getWriter();
			String username = req.getParameter("n1");
			String quiz_name = req.getParameter("n2");
			int file = Integer.parseInt(req.getParameter("n3"));
			dbSelect db = new dbSelect("SELECT MAX(section_no) FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"';");
			int ns = db.retrieveIntFirst("MAX(section_no)");
			dbSelect db5 = new dbSelect("SELECT time FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"';");
			String time = db5.retrieveStrFirst("time");
			String[][][] sqc = new String[ns][100][20];
			int[][] sqa = new int[ns][100];
			int[][] sp = new int[ns][4];
			for (int i = 0; i < ns; i++)
			{
				dbSelect db1 = new dbSelect("SELECT * FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"';");
				int nc = db1.retrieveIntFirst("no_of_options");
				int nq = db1.retrieveIntFirst("no_of_questions");
				int mp = db1.retrieveIntFirst("positive_marks");
				int mn = db1.retrieveIntFirst("negative_marks");
				//System.out.println(nq);
				sp[i][0] = nq;
				sp[i][1] = nc;
				sp[i][2] = mp;
				sp[i][3] = mn;
				
				for(int j = 0; j < nq; j++)
				{
					dbSelect db2 = new dbSelect("SELECT * FROM portal.quiz_answers WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"' and question_no = "+(j+1)+";");
					sqc[i][j][0] = db2.retrieveStrFirst("question");
					//System.out.println(sqc[i][j][0]);
					sqa[i][j] = db2.retrieveIntFirst("answer_no");
					for(int k = 1; k <= nc; k++)
					{
						dbSelect db4 = new dbSelect("SELECT * FROM portal.quiz_options WHERE quiz_name = '"+quiz_name+"' and  section_no = '"+(i+1)+"' and question_no = "+(j+1)+" and option_no = "+(k)+";");
						sqc[i][j][k] = (String) db4.retrieveStrFirst("option");
					}
				}
			}
			HttpSession session = req.getSession();
			session.setAttribute("sp", sp);
			session.setAttribute("ns", ns);
			session.setAttribute("sqc", sqc);
			session.setAttribute("sqa", sqa);
			session.setAttribute("time", time);
			session.setAttribute("quiz_name", quiz_name);			
			if (file == 4)
			{
				
				res.sendRedirect("get_addmarks");
			}
			else
			{
				if (file == 10)
				{
					
					res.sendRedirect("file11.jsp");
				}
				else
				{
					res.sendRedirect("file5.jsp");
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}