package frame;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import thread.UpdateOnline;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;

import handler.Conversation;
import handler.Message;
import handler.User;
import handler.UserManagement;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField input_msg;
	private User user;
	private User[] listOnline;
	private JPanel online_list;
	private UserManagement um;
	private JButton[] label_online_list;
	private int numberTotalUser;
	private User buddy;
	private JPanel panelChatNull;
	private JPanel panelChat;
	private JLabel[] msg;
	private Conversation conversation;
	private JScrollPane scrollPanelChat;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public MainMenu(User user) {

		this.user = user;
		this.buddy = null;
		this.msg = new JLabel[20];
		
		//		goi thread update user
		
		UpdateOnline updateOnlineThread = new UpdateOnline(this.user);
		updateOnlineThread.start(); 
		
		//
		
		this.um = new UserManagement();
		this.numberTotalUser = um.getNumberUser();
		
		setTitle("HiChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelChatNull = new JPanel();
		panelChatNull.setBackground(Color.WHITE);
		panelChatNull.setBounds(270, 10, 381, 322);
		
		panelChat = new JPanel();
		panelChat.setBackground(SystemColor.control);
		panelChat.setBounds(270, 10, 381, 322);
		contentPane.add(panelChatNull);
//		contentPane.add(panelChat);
//		panelChatNull.setLayout(null);
		
		JLabel nullMsg = new JLabel("<html>\r\n"
				+ "    <p style=\"text-align: center;\">Chọn một người bạn<br>để bắt đầu cuộc<br>trò chuyện ngay</p>\r\n"
				+ "</html>");
		nullMsg.setBounds(148, 102, 103, 85);
		panelChatNull.add(nullMsg);
		panelChat.setLayout(null);
		
		JPanel chatArea = new JPanel();
		chatArea.setBackground(SystemColor.text);
		for(int i=0;i<20;i++) {
			msg[i] = new JLabel("");
			msg[i].setVisible(false);
			chatArea.add(msg[i]);
		}
		scrollPanelChat = new JScrollPane();
		scrollPanelChat.setBorder(null);
		scrollPanelChat.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		scrollPanelChat.setBounds(10, 10, 361, 234);
		scrollPanelChat.setViewportView(chatArea);
		chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
		panelChat.add(scrollPanelChat);
		
		input_msg = new JTextField();
		input_msg.setBounds(10, 272, 253, 40);
		panelChat.add(input_msg);
		input_msg.setColumns(10);
		
		JButton sendTextBtn = new JButton("G\u1EEDi");
		sendTextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conversation.sendText(input_msg.getText());
				updateLabelMsg();
			}
		});
		sendTextBtn.setBounds(286, 271, 85, 40);
		panelChat.add(sendTextBtn);
		
		JPanel panelInformation = new JPanel();
		panelInformation.setBackground(SystemColor.text);
		panelInformation.setBounds(10, 10, 250, 152);
		contentPane.add(panelInformation);
		panelInformation.setLayout(null);
		
		JLabel lb_Information = new JLabel(user.getUsername()+" - "+user.getFirstName()+" "+user.getLastName());
		lb_Information.setBounds(10, 10, 207, 17);
		panelInformation.add(lb_Information);
		
		JButton btnLogOut = new JButton("\u0110\u0103ng xu\u1EA5t");
		btnLogOut.setBorder(null);
		btnLogOut.setFocusable(false);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest fm = new Guest();
				fm.show();
				dispose();
			}
		});
		btnLogOut.setBounds(132, 42, 108, 21);
		panelInformation.add(btnLogOut);
		
		JButton btnDeleteChat = new JButton("X\u00F3a chat");
		btnDeleteChat.setBorder(null);
		btnDeleteChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conversation.deleteChat();
				updateLabelMsg();
			}
		});
		
		btnDeleteChat.setBounds(132, 73, 108, 21);
		btnDeleteChat.setEnabled(false);
		panelInformation.add(btnDeleteChat);
		
		ImageIcon img_user = new ImageIcon("E:\\WorkspaceEclipse\\AppChat\\src\\frame\\user.png");
		JLabel lblNewLabel_1 = new JLabel("<html>\r\n"
				+ "    <img src=\"https://haycafe.vn/wp-content/uploads/2021/11/Anh-avatar-dep-chat-lam-hinh-dai-dien.jpg\" style=\"border-radius: 8px;\" height=\"100\" width=\"90\">\r\n"
				+ "</html>");
		lblNewLabel_1.setBounds(10, 39, 112, 86);
		panelInformation.add(lblNewLabel_1);
		
		JScrollPane scrollPaneOnline = new JScrollPane();
		scrollPaneOnline.setBackground(SystemColor.text);
		scrollPaneOnline.setFocusable(false);
		scrollPaneOnline.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneOnline.setBounds(10, 180, 250, 152);
		

		online_list = new JPanel();
		online_list.setBackground(SystemColor.text);
		scrollPaneOnline.setViewportView(online_list);
		online_list.setLayout(new BoxLayout(online_list, BoxLayout.Y_AXIS));

//		updateMsg();
		
		// hien thi danh sach online user

		label_online_list = new JButton[numberTotalUser];
		
		for(int i=0;i<numberTotalUser;i++) {
			label_online_list[i] = new JButton("");
			label_online_list[i].setPreferredSize(new Dimension(200,150));
			label_online_list[i].setVisible(false);
			label_online_list[i].setBorder(null);
			label_online_list[i].setAlignmentX(0.1f);
			online_list.add(label_online_list[i]);
			
			int buddy_id = i;
			
			label_online_list[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buddy = null;
					for(int i=0;i<20;i++) {
						msg[i].setText("");
						msg[i].setVisible(false);
					}
					buddy = new User(listOnline[buddy_id].getId());
					btnDeleteChat.setEnabled(true);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							panelChatNull.setVisible(false);
							contentPane.add(panelChat);
						}
						
					});
					conversation = new Conversation(buddy.getId(), user);
					UpdateMsgList thread_update_msg = new UpdateMsgList();
					thread_update_msg.start();
				}
			});
		}
		
		
		
		//	goi thread  update online list
		
		UpdateOnlineList upd = new UpdateOnlineList();
		upd.start();
		
		
		//
		
		contentPane.add(scrollPaneOnline);
		
		JLabel lblNewLabel = new JLabel("Danh sách online");
		lblNewLabel.setBounds(10, 167, 106, 13);
		contentPane.add(lblNewLabel);

	}
	
	public void updateLabelOnline() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<numberTotalUser;i++) {
					label_online_list[i].setVisible(false);
				}
				listOnline = um.getListOnline();
				for(int i=0;i<listOnline.length;i++) {
					label_online_list[i].setText(listOnline[i].getUsername());
					if(listOnline[i].getId()!=user.getId()) label_online_list[i].setVisible(true);
				}
			}
			
		});
	}
	
	
	public void updateLabelMsg() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++) {
					msg[i].setText("");
					msg[i].setVisible(false);
				}
				Message[] msgs = conversation.getListMessage();
				for(int i=0;i<msgs.length;i++) {
					String text = new String();
					String name = new String();
					if(msgs[i].getFromId() == user.getId()) name = "<span style=\"color: red;\">"+user.getUsername()+"</span>";
					else name = "<span style=\"color: green; text-align: right;\">"+buddy.getUsername()+"</span>";
					text = "<html>\r\n"
							+ "    <p style=\"padding: 4px 6px;\">"+name+": "+msgs[i].getText()+"</p>\r\n"
							+ "</html>";
					msg[i].setText(text);
					msg[i].setVisible(true);
		//			scrollPanelChat.getVerticalScrollBar().setValue(scrollPanelChat.getVerticalScrollBar().getMaximum());
		//			System.out.print(msgs[i].getText());
				}
			}
			
		});
	}
	
	private class UpdateOnlineList extends Thread {
		@Override
		public void run() {
			while(true) {
				updateLabelOnline();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class UpdateMsgList extends Thread {
		@Override
		public void run() {
			while(true) {
				updateLabelMsg();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateMsg() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				contentPane.remove(panelChatNull);
				contentPane.add(panelChat);
			}
			
		});
	}
}
