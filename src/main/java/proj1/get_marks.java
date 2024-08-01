package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class get_marks extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			PrintWriter out = res.getWriter();
			HttpSession session = req.getSession();
			String username = req.getParameter("username");
			String n1 = req.getParameter("n1");
			String[] parts = n1.split("_");
			int attempt = Integer.parseInt(parts[1]);
			String quiz_name = parts[0];
			int nc = 0;
			int nq = 0; 
			int mp = 0;
			int mn = 0;
			int marks = 0;
			
			
			dbSelect db1 = new dbSelect("SELECT MAX(section_no) FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"';");
			int ns = db1.retrieveIntFirst("MAX(section_no)");
			int[][] sp = new int[ns][4];
			for (int i = 0; i < ns; i++)
			{
				dbSelect db2 = new dbSelect("SELECT * FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"';");
				nc = db2.retrieveIntFirst("no_of_options");
				nq = db2.retrieveIntFirst("no_of_questions");
				mp = db2.retrieveIntFirst("positive_marks");
				mn = db2.retrieveIntFirst("negative_marks");
				
				sp[i][0] = nq;
				sp[i][1] = nc;
				sp[i][2] = mp;
				sp[i][3] = mn;
				db2.finalize();
			}
			db1.finalize();
			int[][] csqua = new int[ns][100];
			for(int i = 0; i < ns; i++)
			{
				
				for(int j = 0; j < sp[i][0]; j++)
				{
					dbSelect db = new dbSelect("SELECT * FROM portal.user_answers WHERE username = '"+username+"' and quiz_name = '"+quiz_name+"' and attempts = '"+attempt+"' and section_no = '"+(i+1)+"' and question_no = '"+(j+1)+"'");
					csqua[i][j] = db.retrieveIntFirst("correct_answer");
					
					//System.out.println(csqua[i][j]);
				}
			}
			for (int i = 0; i < ns; i++)
			{
				int qcount = 0;
				nq = sp[i][0];
				nc = sp[i][1];
				mp = sp[i][2];
				mn = sp[i][3];
				for (int j = 0; j < nq; j++)
				{
					
					qcount = qcount + csqua[i][j];
					
				}
				//System.out.println("qcount "+(i+1)+" : "+qcount);
				//System.out.println("nq "+(i+1)+" : "+nq);
				//System.out.println("mp "+(i+1)+" : "+mp);
				//System.out.println("mn "+(i+1)+" : "+mn);
				marks = marks + (qcount*mp) - ((nq-qcount)*mn);
			}
			session.setAttribute("marks", marks);
			session.setAttribute("quiz_name", quiz_name);
			session.setAttribute("attempt", attempt);
			res.sendRedirect("file6.jsp");
			//out.println("your marks is "+marks);
			
			
		} 	
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
