package handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManagement {
	private String DB_URL;
	private String DB_USER;
	private String DB_PASS;
	private Connection connect;
	private java.sql.Statement statement;
	
	public DBManagement(String db_url, String db_user, String db_pass) {
		this.DB_URL = db_url;
		this.DB_USER = db_user;
		this.DB_PASS = db_pass;
		this.connect = null;
	}
	
	public DBManagement() {
		this.DB_URL = "jdbc:mysql://localhost:3306/appchat_db";
		this.DB_USER = "root";
		this.DB_PASS = "";
		this.connect = null;
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	this.connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        	this.statement= connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);  
//        	System.out.println("database connected!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultSet query(String q) {
		ResultSet rs;
		try {
			rs = this.statement.executeQuery(q);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int update(String q) {
		try {
			return this.statement.executeUpdate(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
