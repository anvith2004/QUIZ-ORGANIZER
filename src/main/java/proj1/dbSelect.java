package proj1;

import java.sql.*;

public class dbSelect extends conn
{
	public PreparedStatement pst;
	public ResultSet rs;
	public String query;
	public dbSelect(String query) throws SQLException
	{
		super();
		this.query = query;
		pst = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = pst.executeQuery();
	}	
	public String retrieveStr(String column_name,int n) throws SQLException
	{
		rs.absolute(n);
		String name = rs.getString(column_name);
		return name;
	}
	public int retrieveInt(String column_name, int n) throws SQLException
	{
		rs.absolute(n);
		int name = rs.getInt(column_name);
		return name;
	}
	public String retrieveStrFirst(String column_name) throws SQLException
	{
		rs.absolute(1);
		String name = rs.getString(column_name);
		return name;
	}
	public int retrieveIntFirst(String column_name) throws SQLException
	{
		rs.absolute(1);
		int name =  rs.getInt(column_name);
		return name;
	}
	public int retrieveIntFirst1(String column_name) throws SQLException
	{
		int name;
		 if (rs.next()) 
		 {
			 rs.absolute(1);
			 name = rs.getInt(column_name);
		 } 
		 else 
		 {
			 name = 0;
		 }
		return name;
	}
	public int check(String column_name, String field) throws SQLException
	{
		if (rs.last()) 
		{
            int lastIndex = rs.getRow();
            for (int i = 1; i <= lastIndex; i++)
            {
            	rs.absolute(i);
            	String name = rs.getString(column_name);
            	if (name.equals(field))
            	{
            		return i;
            	}
            }
        } 
		else 
		{
            System.out.println("Result set is empty.");
        }
		return 0;
	}
	protected void finalize() throws SQLException  
	{
		rs.close();
		pst.close();
	}
}