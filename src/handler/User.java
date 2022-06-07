package handler;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date createAt;
	private boolean isActive;
	private String lastOnline;
	private DBManagement db;
	
	public User(int id) {
		db = new DBManagement();
		db.connect();
		ResultSet result = db.query("SELECT * FROM user WHERE id="+id);
		this.id = id;
		try {
			result.next();
			this.username = result.getString(2);
			this.password = result.getString(3);
			this.firstName = result.getString(4);
			this.lastName = result.getString(5);
			this.createAt = result.getDate(6);
			this.isActive = result.getBoolean(7);
			this.lastOnline = result.getString(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public String getLastOnline() {
//		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(Instant.now().getEpochSecond());
		return lastOnline;
	}
	
	public void updateLastOnline() {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		db.update("UPDATE user SET lastOnline='"+Instant.now().getEpochSecond()+"' WHERE id="+this.id);
	}
	
}
