package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class arrQuesGet extends HttpServlet
{
	public String zero(int x)
	{
		
		String y = Integer.toString(x);
		if (x/10 < 1)
		{
			y = "0" + y;
		}
		return y;
	}
	public int delete(String quiz_name)
	{
		try 
		{
			//System.out.println(quiz_name);
			dbUpdate db = new dbUpdate("DELETE FROM portal.quiz_answers WHERE quiz_name = '"+quiz_name+"';");
			dbUpdate db1 = new dbUpdate("DELETE FROM portal.user_answers WHERE quiz_name = '"+quiz_name+"';");
			dbUpdate db2 = new dbUpdate("DELETE FROM portal.quiz_options WHERE quiz_name = '"+quiz_name+"';");
			dbUpdate db3 = new dbUpdate("DELETE FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"';");
			db.exe();
			db1.exe();
			db2.exe();
			db3.exe();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
		
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		int del = -1;
		if (session.getAttribute("del") != null)
		{
			del = (int) session.getAttribute("del");
		}
		
		
		//out.println(del);
		
		
		String username = (String) req.getParameter("username");
		String quiz_name = (String) req.getParameter("quiz_name");
		if (del == 1)
		{
			quiz_name = (String) req.getParameter("n2");
			delete(quiz_name);
			out.println("deleted quiz");
		}
		else
		{
			String time;
			if (del == 0)
			{
				time = (String) session.getAttribute("time");
			}
			else
			{
				String hr = zero((int) session.getAttribute("hr"));
				String min = zero((int) session.getAttribute("min"));
				String sec = zero((int) session.getAttribute("sec"));
				time = hr+":"+min+":"+sec;
			}
			int ns = Integer.parseInt((String) req.getParameter("ns"));
			String[][][] sqc = new String[ns][100][20];
			//String name = (String) req.getParameter("name");
			//String[][] qc = new String[nq][nc+1];
			int[][] sqa = new int[ns][100];
			int[][] sp = new int[ns][4];
			System.out.println(username);
			System.out.println(quiz_name);
			for(int i = 0; i < ns; i++)
			{
				int nq = Integer.parseInt((String) req.getParameter("nq"+(i+1)));
				int nc = Integer.parseInt((String) req.getParameter("nc"+(i+1)));
				int mp = Integer.parseInt((String) req.getParameter("mp"+(i+1)));
				int mn = Integer.parseInt((String) req.getParameter("mn"+(i+1)));
				System.out.println("nq = "+nq+", nc = "+nc+", mp = "+mp+", mn = "+mn);
				sp[i][0] = nq;
				sp[i][1] = nc;
				sp[i][2] = mp;
				sp[i][3] = mn;
				for (int j = 0; j < nq; j++)
				{	
					sqa[i][j] = Integer.parseInt((String) req.getParameter("answer"+(i+1)+""+(j+1)));
					sqc[i][j][0] = (String) req.getParameter("q"+(i+1)+""+(j+1));
					System.out.println("q = "+sqc[i][j][0]+", a = "+sqa[i][j]);
					for (int k = 1; k <= nc; k++)
					{
						sqc[i][j][k] = (String) req.getParameter("c"+(i+1)+""+(j+1)+""+(k));
						System.out.println("opt = "+sqc[i][j][k]);
					}
				}
			}
			
			try 
			{
				if (del == 0)
				{
					delete(quiz_name);
				}
				dbUpdate db = new dbUpdate("INSERT INTO `portal`.`quiz_options` (`id`, `username`, `quiz_name`, `section_no`, `question_no`, `option_no`, `option`) VALUES (?, ?, ?, ?, ?, ?, ?);");
				db.arrUpdateOptions(username, quiz_name, sqc, sp);
				dbUpdate db1 = new dbUpdate("INSERT INTO `portal`.`quiz_answers` (`id`, `username`, `quiz_name`, `section_no`, `question_no`, `question`, `answer_no`) VALUES (?, ?, ?, ?, ?, ?, ?);");
				db1.arrUpdateAnswers(username, quiz_name, sqc, sp, sqa);
				dbUpdate db2 = new dbUpdate("INSERT INTO `portal`.`quiz_properties` (`id`, `username`, `quiz_name`, `section_no`, `no_of_questions`, `no_of_options`, `positive_marks`, `negative_marks`, `time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
				db2.arrUpdateProperties(username, quiz_name, sp, time);
				if (del == 0)
				{
					out.println("quiz updated");
				} 
				else
				{
					out.println("quiz created");
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
//			out.println("<html>");
//			out.println("<body>");
//			for(int i = 0; i < nq; i++)
//			{
//				out.println(qc[i][0]+"<br>");
//				out.println(qa[i]+"<br>");
//				for(int j = 1; j <= nq; j++)
//				{
//					out.println(qc[i][j]+"<br>");
//				}
//			}
//			out.println("</body>");
//			out.println("</html>");
		}
	}
}