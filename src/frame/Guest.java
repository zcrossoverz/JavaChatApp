package frame;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Guest extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public Guest() {
		setResizable(false);
		setForeground(new Color(255, 105, 180));
		setBackground(new Color(255, 105, 180));
		setTitle("HiChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HiChat");
		lblNewLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 32));
		lblNewLabel.setBounds(30, 10, 120, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnGoToRegister = new JButton("\u0110\u0102NG K\u00DD");
		btnGoToRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Register register_frame = new Register();
				register_frame.show();
				dispose();
			}
		});
		btnGoToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGoToRegister.setFocusable(false);
		btnGoToRegister.setBackground(Color.RED);
		btnGoToRegister.setForeground(Color.WHITE);
		btnGoToRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoToRegister.setBounds(47, 75, 341, 46);
		contentPane.add(btnGoToRegister);
		
		JButton btnGoToLogin = new JButton("\u0110\u0102NG NH\u1EACP");
		btnGoToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login_frame = new Login();
				login_frame.show();
				dispose();
			}
		});
		btnGoToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGoToLogin.setFocusable(false);
		btnGoToLogin.setForeground(Color.RED);
		btnGoToLogin.setBackground(Color.GREEN);
		btnGoToLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoToLogin.setBounds(47, 137, 341, 46);
		contentPane.add(btnGoToLogin);
	}
}
