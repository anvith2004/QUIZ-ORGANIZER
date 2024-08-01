package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class avg_marks extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		try
		{
			PrintWriter out = res.getWriter();
			HttpSession session = req.getSession();
			String username = req.getParameter("username");
			String quiz_name = req.getParameter("n1");
			int nc = 0;
			int nq = 0; 
			int mp = 0;
			int mn = 0;
			int marks = 0;

			
			dbSelect db1 = new dbSelect("SELECT MAX(section_no) FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"';");
			int ns = db1.retrieveIntFirst("MAX(section_no)");
			int[][] sp = new int[ns][4];
			String[][][] sqc = new String[ns][100][20];
			int[][] sqa = new int[ns][100];
			for (int i = 0; i < ns; i++)
			{
				dbSelect db10 = new dbSelect("SELECT * FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"';");
				nc = db10.retrieveIntFirst("no_of_options");
				nq = db10.retrieveIntFirst("no_of_questions");
				mp = db10.retrieveIntFirst("positive_marks");
				mn = db10.retrieveIntFirst("negative_marks");
				System.out.println(nq);
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
			db1.finalize();
			float[][] avgmarks_quiz = new float[ns][100];
			String[][] savgmarks_quiz = new String[ns][100];
			dbSelect db = new dbSelect("SELECT COUNT(DISTINCT username, attempts) FROM portal.user_answers WHERE quiz_name = '"+quiz_name+"'");
			int total = db.retrieveIntFirst("COUNT(DISTINCT username, attempts)");
			float pos = 0;
			float neg = 0;
			int marks_ques = 0;
			for(int i = 0; i < ns; i++)
			{
				
				
				for(int j = 0; j < sp[i][0]; j++)
				{
					dbSelect db4 = new dbSelect("SELECT * FROM portal.user_answers WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"' and question_no = '"+(j+1)+"'");
					pos = 0;
					neg = 0;
					for (int k = 0; k < total; k++)
					{
						
						int correct_answer = db4.retrieveInt("correct_answer", (k+1));
						if (correct_answer == 1)
						{
							pos += 1;
						}
						else
						{
							neg += 1;
						}
						
					}
					//System.out.println("s"+(i+1));
					//System.out.println("q"+(j+1));
					//System.out.println("pos "+pos);
					avgmarks_quiz[i][j] = (pos*((float)sp[i][2]) - neg*((float)sp[i][3]))/total;
					savgmarks_quiz[i][j] = String.format("%.2f", avgmarks_quiz[i][j]);
				}
				
			}
			session.setAttribute("avgmarks_quiz", avgmarks_quiz);
			session.setAttribute("quiz_name", quiz_name);
			session.setAttribute("savgmarks_quiz", savgmarks_quiz);
			session.setAttribute("ns", ns);
			session.setAttribute("total", total);
			session.setAttribute("sqc", sqc);
			session.setAttribute("sqa", sqa);
			session.setAttribute("sp", sp);
			res.sendRedirect("file9.jsp");
			//out.println("your marks is "+marks);
			
			
		} 	
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
