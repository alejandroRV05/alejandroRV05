package Menu;

//Video de referencia: https://www.youtube.com/watch?v=gBtuj_MjgtY

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

/**
 * Clase que crea la ventana de login
 */
public class login extends JFrame implements ActionListener{
	
	JPasswordField Password;
	JTextField  Username;
	JLabel lblContrasena, lblUsuario, message, title;
	JButton Conectar, Reset;
	JCheckBox showpassword;
	
	/*CREATE TABLE login_pandemic(
        usuario VARCHAR2(25),
        contra VARCHAR2 (25),
        CONSTRAINT usuario_contra_pk PRIMARY KEY(usuario,contra)
	);*/
	
	
	public static Connection con;
	
	
	
	public login() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,625);
		this.setTitle("Login");
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
        // Carga la fuente personalizada
        Font customFont = loadFont("Starjedi.ttf");
        Font buttonFont = customFont.deriveFont(Font.PLAIN, 15);
        Font textFont = customFont.deriveFont(Font.PLAIN, 10);
		
        // Establecer el icono de la ventana
        Image iconImage = new ImageIcon("logo.jpg").getImage();
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        
        //Label del usuario
		lblUsuario = new JLabel("usuario:");
		lblUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBounds(286,221,100,40);
        lblUsuario.setFont(buttonFont);
		
        //Label de la contraseña
		lblContrasena = new JLabel("contraseña:");
		lblContrasena.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setBounds(250,273,200,40);
		lblContrasena.setFont(buttonFont);
        
        //Label del mensaje
		message = new JLabel("");
		message.setFont(new Font("Comic Sans MS", Font.BOLD, 21));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(new Color(255, 255, 255));
		message.setBackground(new Color(0, 0, 255));
		message.setBounds(250,421,626,130);
		
		//Texto donde se escribe el username
		Username = new JTextField();
		Username.setBounds(375,221,300,40);
		Username.setFont(buttonFont);
		
		//Texto donde se escribe la contraseña
		Password = new JPasswordField();
		Password.setBounds(375,273,300,40);
		Password.setEchoChar('$');
		Password.setFont(buttonFont);
		
		//Boton para mostrar la contraseña
		showpassword = new JCheckBox("Mostrar contraseña");
		showpassword.setForeground(new Color(255, 255, 255));
		showpassword.setBounds(681,273,250,40);
		showpassword.setFont(buttonFont);
        showpassword.setOpaque(false);
        showpassword.setContentAreaFilled(false); 
        showpassword.setBorderPainted(false); 
		showpassword.addActionListener(this);
		
		//Boton para conectar
		Conectar = new JButton("conectar");
		Conectar.setForeground(new Color(255, 255, 255));
		Conectar.setBounds(375,324,150,40);
		Conectar.addActionListener(this);
		Conectar.setFont(buttonFont);
	    setButtonProperties(Conectar);
		
	    //Boton para resetear los campos de usuario y contraseña
		Reset = new JButton("reset");
		Reset.setForeground(new Color(255, 255, 255));
		Reset.setBounds(525,324,150,40);
		Reset.addActionListener(this);
		Reset.setFont(buttonFont);
	    setButtonProperties(Reset);
		
		getContentPane().add(showpassword);
		getContentPane().add(Reset);
		getContentPane().add(message);
		getContentPane().add(Conectar);
		getContentPane().add(Username);
		getContentPane().add(lblUsuario);
		getContentPane().add(lblContrasena);
		getContentPane().add(Password);
		
		//Fondo de pantalla
		JLabel Fondo = new JLabel("");
		Fondo.setForeground(new Color(0, 0, 0));
		Fondo.setIcon(new ImageIcon("fondo_login2.jpg"));
		Fondo.setBounds(0, -18, 1000, 625);
		getContentPane().add(Fondo);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
		//Miramos si le ha dado al boton conectar
		if(evt.getSource() == Conectar) {
			
			//Declaración de Arrays de Strings que se usan en las funciones de bbdd
			String[] listaElementosSeleccionados = new String[5];
			String[] Select = new String[20];
			String usuario = "";
			String contra = "";
			int reply;
			String userText = Username.getText();
			String pwdText = new String(Password.getPassword());
			
			listaElementosSeleccionados = new String[1];
			listaElementosSeleccionados[0] = "COUNT(*)";

			usuario = userText;
			contra = pwdText;
		
			//Miramos que se han rellenado los dos campos de texto
			if(usuario.contentEquals("") || contra.contentEquals("")) {
				if(usuario.contentEquals("") && contra.contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Los campos de usuario y contraseña no pueden estar vacíos");
				}else if(usuario.contentEquals("")) {
					JOptionPane.showMessageDialog(null, "El campo de usuario no puede estar vacío");
				}else {
					JOptionPane.showMessageDialog(null, "El campo de contraseña no puede estar vacío");
				}
			}else {
				message.setText("Conectando al servidor...");
				Connection con = bbdd.conectarBaseDatos();
				Select = bbdd.select(con, "SELECT COUNT(*) FROM LOGIN_PANDEMIC WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
				
				//Miramos si existe en la base de datos
				if(Select[0].charAt(0) != '0') {
					JOptionPane.showMessageDialog(null, "Credenciales correctos.\nIniciando Conquista Estelar");
					 //setVisible(false);
					 menu men = new menu(usuario,contra);
					 dispose();
				}else {
					 reply = JOptionPane.showConfirmDialog(null,
				            "El usuario no existe en la base de datos. ¿Quiéres crear un usuario con los datos proporcionados?",
				            "Confirmar creación de usuario",
				            JOptionPane.YES_NO_OPTION);
					
					//Miramos si quiere crear un nuevo usuario
					if (reply == JOptionPane.YES_OPTION) {
						bbdd.insert(con, "INSERT INTO LOGIN_PANDEMIC (usuario,contra) VALUES ('" + usuario + "','"+ contra + "')");
					    JOptionPane.showMessageDialog(null, "Creado con éxito.\nIniciando Conquista Estelar");
					    //setVisible(false);
					    menu men = new menu(usuario,contra);
					    dispose();
					}else {
						message.setText("");
					}
					
				}
			}
			
			
			
		}
		
		//Miramos si ha apretado el boton de reset
		if(evt.getSource() == Reset) {
			Username.setText("");
			Password.setText("");
			message.setText("");
		}
		
		//Miramos si ha apretado el checkbox de mostrar contraseña
		if(evt.getSource() == showpassword) {
			if(showpassword.isSelected()) {
				Password.setEchoChar((char) 0);
			}else {
				Password.setEchoChar('*');
			}
		}

	}
	
	/**
     * Intenta cargar una fuente de texto personalizada, si no la encuentra devuelve otra fuente que funcione
     *
     * @param path  Ruta donde se encuentra el archivo para cargar la fuente
     * @return Font  Devuelve la fuente modificada si ha encontrado el archivo correspondiente
     */
    public Font loadFont(String path) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, 12);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Si hay un error al cargar la fuente, devuelve una fuente por defecto (Tahoma)
            return new Font("Tahoma", Font.PLAIN, 12);
        }
    }
	
    /**
     * Modifica los botones para hacerlos mas esteticos
     *
     * @param button  Objeto JButton que se usa para modificar sus propiedades
     */
    public void setButtonProperties(JButton button) {
        button.setOpaque(false); // Hace que el botón sea transparente
        button.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        button.setBorderPainted(false); // Quita el borde del botón
    }
}