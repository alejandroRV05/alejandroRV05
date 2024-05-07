package Partida;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;

public class testLogin extends JFrame {
	JTextField username;
	JLabel label_password, label_username, message, title;
	JButton btn;
	
	public testLogin() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setTitle("Demo Login");
		
		getContentPane().setLayout(new FlowLayout());
		
		label_password = new JLabel();
		label_password.setIcon(new ImageIcon("/Users/alejandrorodriguezvallin/Desktop/Ilerna/Prog/Pandemic/src/Menu/fondoSW3.jpeg"));
		label_password.setBounds(200,250,100,40);
		
		getContentPane().add(label_password);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(btnNewButton);
		
		this.setVisible(true);
		
		
		
	}
	
	
	
}
