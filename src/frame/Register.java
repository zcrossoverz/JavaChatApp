package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import handler.UserManagement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField username_input;
	private JPasswordField password_input;
	private JTextField lastName_input;
	private JTextField firstName_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setTitle("\u0110\u0103ng k\u00FD t\u00E0i kho\u1EA3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 102, 18);
		contentPane.add(lblNewLabel);
		
		username_input = new JTextField();
		username_input.setBounds(124, 9, 286, 26);
		contentPane.add(username_input);
		username_input.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("M\u1EADt kh\u1EA9u");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 48, 71, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Họ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 84, 45, 18);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng k\u00FD");
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagement um = new UserManagement();
				int new_id = um.register(username_input.getText(), password_input.getText(), firstName_input.getText(), lastName_input.getText());
				if(new_id>=0) {
					System.out.println("Tao thanh cong tai khoan moi voi id = "+new_id);
					JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
				}else {
					JOptionPane.showMessageDialog(null, "Đăng ký thất bại!");
				}
				
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(72, 151, 110, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("H\u1EE7y");
		btnNewButton_1.setForeground(new Color(240, 248, 255));
		btnNewButton_1.setBackground(new Color(255, 105, 180));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest guest = new Guest();
				guest.show();
				dispose();
			}
		});
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(214, 151, 110, 29);
		contentPane.add(btnNewButton_1);
		
		password_input = new JPasswordField();
		password_input.setBounds(124, 45, 286, 26);
		contentPane.add(password_input);
		
		JLabel lblNewLabel_3 = new JLabel("Tên");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 123, 64, 18);
		contentPane.add(lblNewLabel_3);
		
		lastName_input = new JTextField();
		lastName_input.setBounds(124, 118, 286, 25);
		contentPane.add(lastName_input);
		lastName_input.setColumns(10);
		
		firstName_input = new JTextField();
		firstName_input.setBounds(124, 83, 286, 25);
		contentPane.add(firstName_input);
		firstName_input.setColumns(10);
	}
}
