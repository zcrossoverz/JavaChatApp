package handler;
import java.sql.ResultSet;

public class Message {
	private DBManagement db;
	private int id;
	private int fromId;
	private int toId;
	private int status;
	private String text;
	private boolean type;
	private String time;
//	private int conversation_id;
	
	public Message(int id) {
		db = new DBManagement();
		this.id = id;
		db.connect();
		ResultSet rs = db.query("SELECT * FROM message WHERE id="+id);
		try {
			rs.next();
			this.fromId = rs.getInt(2);
			this.toId = rs.getInt(3);
			this.status = rs.getInt(4);
			this.text = rs.getString(5);
			this.type = rs.getBoolean(6);
//			this.conversation_id = rs.getInt(7);
			this.time = rs.getString(8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public int getFromId() {
		return fromId;
	}

	public int getToId() {
		return toId;
	}

	public int getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}

	public boolean isType() {
		return type;
	}

	public String getTime() {
		return time;
	}
	
	public boolean isMe(User user) {
		return fromId==user.getId();
	}
}
