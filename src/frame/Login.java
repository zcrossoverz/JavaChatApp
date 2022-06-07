package frame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import handler.User;
import handler.UserManagement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username_input;
	private JPasswordField password_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("\u0110\u0103ng nh\u1EADp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 45, 128, 27);
		contentPane.add(lblNewLabel);
		
		username_input = new JTextField();
		username_input.setBounds(152, 47, 263, 27);
		contentPane.add(username_input);
		username_input.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("M\u1EADt kh\u1EA9u");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 84, 105, 27);
		contentPane.add(lblNewLabel_1);
		
		password_input = new JPasswordField();
		password_input.setBounds(152, 86, 263, 27);
		contentPane.add(password_input);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng nh\u1EADp");
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.setBorder(null);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagement um = new UserManagement();
				int login_id = um.login(username_input.getText(),password_input.getText());
				if(login_id>=0) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
					dispose();
					MainMenu main = new MainMenu(new User(login_id));
					main.show();
				}else {
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(50, 121, 134, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("H\u1EE7y");
		btnNewButton_1.setForeground(new Color(224, 255, 255));
		btnNewButton_1.setBackground(new Color(123, 104, 238));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest guest = new Guest();
				guest.show();
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(194, 121, 128, 34);
		contentPane.add(btnNewButton_1);
	}

}
