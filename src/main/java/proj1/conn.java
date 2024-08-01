package proj1;

import java.sql.*;

public class conn 
{
	public String url = "jdbc:mysql://localhost:3306/portal";
	public String username = "root";
	public String passwd = "1234";
	public Connection con;
	public conn() throws SQLException
	{
		con = DriverManager.getConnection(url,username,passwd);
	}
	protected void finalize() throws SQLException  
	{
		con.commit();
		con.close();
	}
}