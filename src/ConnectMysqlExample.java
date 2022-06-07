import java.sql.*;

import java.util.*;

import com.mysql.cj.xdevapi.Statement;

import handler.User;
import handler.UserManagement;

public class ConnectMysqlExample {
    private static String DB_URL = "jdbc:mysql://localhost:3306/appchat_db";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
 

    public static void main(String args[]) {
    	User user = new User(1);
//    	user.updateLastOnline();
//    	System.out.print(user.getLastName());
//    	
//    	user.getLastOnline();
//    	
//    	
//    	Message me = new Message(1);
//    	System.out.println(me.getText());
    	
//    	Conversation con = new Conversation(3,user);
//    	Message[] list = con.getListMessage();
//    	System.out.print(list.length);
//    	for(int i=0;i<list.length;i++) {
//    		if(list[i].isMe(user)) System.out.print("-");
//    		System.out.print(list[i].getText());
//    		System.out.println();
//    	}
    	
//    	con.sendText("adsfasfasfsdfdsf");
    	
    	
    	
//    	UserManagement um = new UserManagement();
//    	if(um.register("thinh", "123456", "quach", "huy thinh")) {
//    		System.out.println("dang ky thanh cong!");
//    	}else {
//    		System.out.print("dang ky that bai");
//    	}
    	
    	
//    	if(um.login("admin", "123456")) {
//		System.out.println("dang nhap thanh cong!");
//		}else {
//			System.out.print("dang nhap that bai");
//		}
    	
//    	User[] list_onl = um.getListOnline();
//    	
//    	for(int i=0;i<list_onl.length;i++) {
//    		System.out.println(list_onl[i].getLastName());
//    	}
    	
    }
}
