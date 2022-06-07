package handler;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Conversation {
	private Message[] messages;
	private int user_id; // id người nhận
	private DBManagement db;
	private int conversation_id;
	private int length;
	private int myId; // id người gửi

	public Conversation(int user_id, User user) {
		this.user_id = user_id;
		this.myId = user.getId();
		db = new DBManagement();
		db.connect();
		int user1, user2;
		if(myId < user_id) {
			user1 = this.myId;
			user2 = user_id;
		}else {
			user1 = user_id;
			user2 = this.myId;
		}
		
		int create_conversation = db.update("INSERT INTO conversation (user1, user2)"
				+ "	SELECT * FROM (SELECT "+user1+", "+user2+") as tmp"
				+ " WHERE NOT EXISTS ("
				+ "	SELECT id FROM conversation WHERE user1="+user1+" AND user2="+user2
				+ ") LIMIT 1;");
		
		if(create_conversation>0) {
			System.out.println("tao cuoc tro chuyen moi!");
		}
		
		ResultSet rs = db.query("SELECT id FROM Conversation WHERE user1="+user1+" AND user2="+user2);
		try {
			rs.next();
			this.conversation_id = rs.getInt(1);
			ResultSet result = db.query("SELECT * FROM message WHERE conversation_id="+this.conversation_id);
			result.last();
			this.length = result.getRow();
			result.beforeFirst();;
			this.messages = new Message[this.length];
			int i=0;
			while(result.next()) {
				messages[i] = new Message(result.getInt(1));
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Message[] getListMessage() {
		
		int user1, user2;
		if(myId < user_id) {
			user1 = this.myId;
			user2 = user_id;
		}else {
			user1 = user_id;
			user2 = this.myId;
		}
		
		ResultSet rs = db.query("SELECT id FROM Conversation WHERE user1="+user1+" AND user2="+user2);
		try {
			rs.next();
			this.conversation_id = rs.getInt(1);
			ResultSet result = db.query("SELECT * FROM message WHERE conversation_id="+this.conversation_id+" ORDER BY id DESC LIMIT 20;");
			result.last();
			this.length = result.getRow();
			result.beforeFirst();;
			this.messages = new Message[this.length];
			int i=0;
			while(result.next()) {
				messages[i] = new Message(result.getInt(1));
				i++;
			}
			return this.messages;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return this.messages;
	}
	
	public int getConversationId() {
		return this.conversation_id;
	}
	public void sendText(String text) {
		int msg = db.update("INSERT INTO message (fromId, toId, status, content, type, conversation_id, time)"
				+ " VALUES ("+this.myId+", "+this.user_id+", 1, '"+text+"', 0, "+conversation_id+", now())");
		if(msg>0) {
			System.out.println("gui tin nhan thanh cong!");
		}else {
			System.out.println("gui tin nhan that bai!");
		}
	}
	
	public void deleteChat() {
		int delete = db.update("DELETE FROM message WHERE conversation_id="+this.conversation_id+";");
		if(delete > 0) JOptionPane.showMessageDialog(null, "Xóa chat thành công!");
	}
	
}
