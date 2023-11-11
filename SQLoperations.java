import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLoperations {
    Connection con;
	
	public SQLoperations() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/quizapp";
		String usr = "root";
		String pass = "password";//password unique to individual users
		con = DriverManager.getConnection(url, usr, pass);
	}
    public void newUser(String name, String uname, String pass) throws SQLException {
		String str = "INSERT INTO actors(fname, uname, pass) values ('"+name+"', '"+uname+"', '"+pass+"')";
		Statement stm = con.createStatement();
		stm.executeUpdate(str);
	}
	
	public int authUser(String uname, String pass) throws SQLException {
		String str = "SELECT * FROM actors WHERE uname = '"+uname+"'";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		if (!rst.next())
			return -1;
		else {
			if(rst.getString("pass").equals(pass))
				return rst.getInt("id");
			else
				return 0;
		}
	}
}
