import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 816, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		user = new JTextField();
		user.setBackground(UIManager.getColor("Button.background"));
		user.setBounds(393, 269, 272, 26);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBackground(UIManager.getColor("Button.background"));
		pass.setBounds(393, 342, 272, 26);
		frame.getContentPane().add(pass);
		
		JLabel lblNewLabel = new JLabel("    Username");
		lblNewLabel.setBounds(182, 274, 94, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("    Password");
		lblNewLabel_1.setBounds(182, 347, 94, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("          MELODY DIRECTORY");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(247, 141, 262, 50);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			//ActionPerform Method, executes the Login operation
			public void actionPerformed(ActionEvent e) {
				//get text from user
				String un=user.getText();
				
				//get text from password
				String pw=pass.getText();
				
				//Admin Credentials for Successful Login
				if(un.equals("admin") && pw.equals("123")) {
					JOptionPane.showMessageDialog(null, "Login Successful");
				} else {
					//Invalid Credentials Message
					JOptionPane.showMessageDialog(null, "Invalid Username or Password");
				}
			}
		});
		btnNewButton.setBounds(548, 444, 117, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
