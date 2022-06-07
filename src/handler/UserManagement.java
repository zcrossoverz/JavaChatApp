package handler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class UserManagement {
	private DBManagement db;
	
	public UserManagement() {
		this.db = new DBManagement();
		this.db.connect();
	}
	
	public int register(String username, String password, String firstName, String lastName) {
		int q = db.update("INSERT INTO user (username, password, firstName, lastName, createAt, isActive, lastOnline) "
				+ "SELECT * FROM (SELECT '"+username+"', '"+password+"', '"+firstName+"', '"+lastName+"', curdate(), 1, TIMESTAMPDIFF(SECOND, '2022-01-01 00:00:00', NOW())) as tmp WHERE NOT EXISTS (SELECT id FROM user WHERE username = '"+username+"') LIMIT 1;");
		if(q>0) {
			ResultSet rs = db.query("SELECT id FROM user WHERE username='"+username+"'");
			try {
				rs.next();
				return rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -2;
			}
		}
		else return -1;
	}
	
	public int login(String username, String password) {
		ResultSet rs = db.query("SELECT password,id FROM user WHERE username='"+username+"'");
		try {
			rs.next();
			if(password.equals(rs.getString(1))) {
				return rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public User[] getListOnline() {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ResultSet rs = db.query("SELECT id FROM user WHERE lastOnline >= "+(Instant.now().getEpochSecond()-2));
		try {
			rs.last();
			User[] listOnline = new User[rs.getRow()];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				listOnline[i] = new User(rs.getInt(1));
				i++;
			}
			return listOnline;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public int getNumberUser() {
		ResultSet rs = db.query("SELECT count(id) FROM user;");
		try {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
