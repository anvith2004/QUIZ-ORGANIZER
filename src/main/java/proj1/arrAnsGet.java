package proj1;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
	
public class arrAnsGet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String quiz_name = req.getParameter("quiz_name");
		System.out.println(username+quiz_name);
		
		try 
		{
			dbSelect db = new dbSelect("SELECT MAX(section_no) FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"';");
			int ns = db.retrieveIntFirst("MAX(section_no)");
			String[][][] sqc = new String[ns][100][20];
			int[][] sqa = new int[ns][100];
			int[][] squa = new int[ns][100];
			int[][] sp = new int[ns][4];
			int[][] csqa = new int[ns][100];
			
			for (int i = 0; i < ns; i++)
			{
				dbSelect db1 = new dbSelect("SELECT * FROM portal.quiz_properties WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"';");
				int nc = db1.retrieveIntFirst("no_of_options");
				int nq = db1.retrieveIntFirst("no_of_questions");
				int mp = db1.retrieveIntFirst("positive_marks");
				int mn = db1.retrieveIntFirst("negative_marks");
				sp[i][0] = nq;
				sp[i][1] = nc;
				sp[i][2] = mp;
				sp[i][3] = mn;
				for(int j = 0; j < nq; j++)
				{
					dbSelect db2 = new dbSelect("SELECT * FROM portal.quiz_answers WHERE quiz_name = '"+quiz_name+"' and section_no = '"+(i+1)+"' and question_no = "+(j+1)+";");
					sqc[i][j][0] = db2.retrieveStrFirst("question");
					sqa[i][j] = db2.retrieveIntFirst("answer_no");
					for(int k = 1; k <= nc; k++)
					{
						dbSelect db3 = new dbSelect("SELECT * FROM portal.quiz_options WHERE quiz_name = '"+quiz_name+"' and  section_no = '"+(i+1)+"' and question_no = "+(j+1)+" and option_no = "+(k)+";");
						sqc[i][j][k] = (String) db3.retrieveStrFirst("option");
						db3.finalize();
					}
					db2.finalize();
				}
				db1.finalize();
			}
			for(int i = 0; i < ns; i++)
			{
				for(int j = 0; j < sp[i][0]; j++)
				{
					String x = (String) req.getParameter("useranswer"+(i+1)+""+(j+1));
					if (x == null)
					{
						squa[i][j] = 0;
					}
					else
					{
						int y = Integer.parseInt(x);
						squa[i][j] = y;
					}
					if (squa[i][j] == sqa[i][j])
					{
						csqa[i][j] = 1;
					}
					else
					{
						csqa[i][j] = 0;
					}
					//System.out.println((i+1)+" "+(j+1)+" : "+csqa[i][j]);
				}
			}
			dbUpdate db4 = new dbUpdate("INSERT INTO `portal`.`user_answers` (`id`, `username`, `quiz_name`, `attempts`, `section_no`, `question_no`, `ans_no`, `correct_answer`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			db4.arrUpdateUserAnswers(squa, csqa, sp, username, quiz_name);
			out.println("quiz submitted");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
//		out.println("<html>");
//		out.println("<body>");
//		for(int i = 0; i < nq; i++)
//		{
//			out.println(qc[i][0]+"<br>");
//			out.println(qa[i]+"<br>");
//			for(int j = 1; j <= nq; j++)
//			{
//				out.println(qc[i][j]+"<br>");
//			}
//		}
//		out.println("</body>");
//		out.println("</html>");
	}
}