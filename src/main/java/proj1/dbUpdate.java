package proj1;

import java.sql.*;

public class dbUpdate extends conn
{
	public PreparedStatement pst;
	public String query;
	public dbUpdate(String query) throws SQLException
	{
		super();
		this.query = query;
		pst = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
	}	
	public int exe() throws SQLException
	{
		int rs = pst.executeUpdate();
		return rs;
	}
	public void arrUpdateOptions(String username, String quiz_name, String[][][] sqc,  int[][] sp) throws SQLException
	{
		for(int i = 0; i < sqc.length; i++)
		{
			for(int j = 0; j < sp[i][0]; j++)
			{
				for(int k = 1; k <= sp[i][1]; k++)
				{
					dbSelect dbl = new dbSelect("SELECT COUNT(id) FROM portal.quiz_options;");
					int n = dbl.retrieveIntFirst("COUNT(id)");
					pst.setInt(1, n+1);
					pst.setString(2, username);
					pst.setString(3, quiz_name);
					pst.setInt(4, (i+1));
					pst.setInt(5, (j+1));
					pst.setInt(6, (k));
					pst.setString(7, sqc[i][j][k]);
					int rs = pst.executeUpdate();
				}
			}
		}
		
		
		
	}
	public void arrUpdateAnswers(String username, String quiz_name, String[][][] sqc,  int[][] sp, int[][] sqa) throws SQLException
	{
		for(int i = 0; i < sqc.length; i++)
		{
			for(int j = 0; j < sp[i][0]; j++)
			{
				dbSelect dbl = new dbSelect("SELECT COUNT(id) FROM portal.quiz_answers;");
				int n = dbl.retrieveIntFirst("COUNT(id)");
				pst.setInt(1, n+1);
				pst.setString(2, username);
				pst.setString(3, quiz_name);
				pst.setInt(4, (i+1));
				pst.setInt(5, (j+1));
				pst.setString(6, sqc[i][j][0]);
				pst.setInt(7, sqa[i][j]);
				int rs = pst.executeUpdate();
			}
		}
	}
	public void arrUpdateProperties(String username, String quiz_name, int[][] sp, String time) throws SQLException
	{
		for(int i = 0; i < sp.length; i++)
		{
			dbSelect dbl = new dbSelect("SELECT COUNT(id) FROM portal.quiz_properties;");
			int n = dbl.retrieveIntFirst("COUNT(id)");
			pst.setInt(1, n+1);
			pst.setString(2, username);
			pst.setString(3, quiz_name);
			pst.setInt(4, (i+1));
			pst.setInt(5, sp[i][0]);
			pst.setInt(6, sp[i][1]);
			pst.setInt(7, sp[i][2]);
			pst.setInt(8, sp[i][3]);
			pst.setString(9, time);
			int rs = pst.executeUpdate();
			
		}
	}
	public void arrUpdateUserAnswers(int[][] squa, int[][] csqua, int[][] sp, String user_name, String quiz_name) throws SQLException
	{
		String a = "INSERT INTO `portal`.`user_answers` (`id`, `username`, `quiz_name`, `attempts`, `section_no`, `question_no`, `ans_no`, `correct_answer`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		System.out.println(squa.length);
		dbSelect db2 = new dbSelect("SELECT MAX(attempts) FROM portal.user_answers WHERE quiz_name = '"+quiz_name+"';");
		int m = db2.retrieveIntFirst1("MAX(attempts)");
		for(int i = 0; i <  squa.length; i++)
		{
			for(int j = 0; j <  sp[i][0]; j++)
			{
				dbSelect dbl = new dbSelect("SELECT MAX(id) FROM portal.user_answers;");
				int n = dbl.retrieveIntFirst("MAX(id)");
				System.out.println("yes2");
				pst.setInt(1, (n+1));
				pst.setString(2, user_name);
				pst.setString(3, quiz_name);
				pst.setInt(4, (m+1));
				pst.setInt(5, (i+1));
				pst.setInt(6, (j+1));
				pst.setInt(7, squa[i][j]);
				pst.setInt(8, csqua[i][j]);
				int rs = pst.executeUpdate();
			}
		}
		
	}
	protected void finalize() throws SQLException  
	{
		pst.close();
	}
}