package Menu;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class menu extends JFrame{
	
    public menu() {
        getContentPane().setBackground(new Color(240, 240, 240));
        getContentPane().setLayout(null);
        
        // Carga la fuente personalizada
        Font customFont = loadFont("Starjedi.ttf");
        Font buttonFont = customFont.deriveFont(Font.PLAIN, 30);
        Font titleFont = customFont.deriveFont(Font.PLAIN, 100);
        Font textFont = customFont.deriveFont(Font.PLAIN, 20);
        
        // Establecer el icono de la ventana
        Image iconImage = new ImageIcon("logo.jpg").getImage();
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        
        JButton NuevaPartida = new JButton("nueva partida");
        NuevaPartida.setForeground(new Color(255, 255, 255));
        NuevaPartida.setFont(buttonFont);
        setButtonProperties(NuevaPartida); // Establecer propiedades del botón
        NuevaPartida.setBounds(480, 397, 350, 40);
        getContentPane().add(NuevaPartida);
        NuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad dif = new dificultad();
				dispose();
			}
		});
              
        JLabel Title2 = new JLabel("estelar");
        Title2.setFont(titleFont);
        Title2.setForeground(new Color(255, 255, 0));
        Title2.setBounds(417, 177, 533, 92);
        getContentPane().add(Title2);
        
        JLabel Title1 = new JLabel("conquista");
        Title1.setFont(titleFont);
        Title1.setForeground(new Color(255, 255, 0));
        Title1.setBounds(358, 65, 697, 112);
        getContentPane().add(Title1);
        
        JLabel Versio = new JLabel("versión 1.0");
        Versio.setForeground(new Color(255, 255, 255));
        Versio.setBounds(1003, 892, 220, 40);
        Versio.setFont(buttonFont);
        getContentPane().add(Versio);
              
        JButton Salir = new JButton("Salir");
        Salir.setForeground(Color.WHITE);
        Salir.setFont(buttonFont);
        setButtonProperties(Salir); // Establecer propiedades del botón
        Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose(); // Cierra la ventana
            }
        });
        Salir.setBounds(480, 707, 350, 40);
        getContentPane().add(Salir);
        
        JButton Autores = new JButton("Autores");
        Autores.setForeground(Color.WHITE);
        Autores.setFont(buttonFont);
        setButtonProperties(Autores); // Establecer propiedades del botón
        Autores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y configurar la nueva ventana emergente
                JFrame autores = new JFrame("Autores");
                autores.setSize(600, 300);
                autores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                autores.setLocationRelativeTo(null);
                
                // Contenido de la ventana emergente
                JLabel label = new JLabel("alejandro rodríguez y andreu zapater");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setForeground(Color.WHITE);
                label.setBackground(Color.BLACK);
                label.setFont(textFont);
                autores.getContentPane().add(label);
                label.setOpaque(true); // Asegurarse de que el fondo se muestre
                
                autores.setVisible(true);
            }
        });
        Autores.setBounds(480, 553, 350, 40);
        getContentPane().add(Autores);
        
        JButton Ranking = new JButton("Ranking");
        Ranking.setForeground(Color.WHITE);
        Ranking.setFont(buttonFont);
        setButtonProperties(Ranking); // Establecer propiedades del botón
        Ranking.setBounds(480, 630, 350, 40);
        getContentPane().add(Ranking);
        
        JButton Instrucciones = new JButton("instrucciones");
        Instrucciones.setForeground(Color.WHITE);
        Instrucciones.setFont(buttonFont);
        setButtonProperties(Instrucciones); // Establecer propiedades del botón
        Instrucciones.setBounds(480, 475, 350, 40);
        getContentPane().add(Instrucciones);
        Instrucciones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarInstrucciones();
        	}
        });
        
        JButton CargarPartida = new JButton("Cargar Partida");
        CargarPartida.setForeground(Color.WHITE);
        CargarPartida.setFont(buttonFont);
        setButtonProperties(CargarPartida); // Establecer propiedades del botón
        CargarPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        CargarPartida.setBounds(480, 324, 350, 40);
        getContentPane().add(CargarPartida);
        
        JLabel fondo = new JLabel("");
        fondo.setFont(new Font("Tahoma", Font.PLAIN, 30));
        fondo.setBounds(0, 0, 1280, 1024);
        fondo.setIcon(new ImageIcon("fondo3.jpg"));
        getContentPane().add(fondo);
              
        // Configuración de la ventana principal
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        setSize(screenWidth, screenHeight);
        setTitle("CONQUISTA ESTELAR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
        // Reproducir música de fondo
        reproducirMusica("soundtrack.wav");
        
        setVisible(true);
    }
    
    // Método para cargar la fuente desde un archivo .ttf
    private Font loadFont(String path) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, 12);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Si hay un error al cargar la fuente, devuelve una fuente por defecto (Tahoma)
            return new Font("Tahoma", Font.PLAIN, 12);
        }
    }
    
    private void setButtonProperties(JButton button) {
        button.setOpaque(false); // Hace que el botón sea transparente
        button.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        button.setBorderPainted(false); // Quita el borde del botón
    }
    
    // Método para reproducir música de fondo
    private void reproducirMusica(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarInstrucciones() {
        Font customFont = loadFont("Starjedi.ttf");
		Font text2Font = customFont.deriveFont(Font.PLAIN, 15);
        JFrame instruccionesFrame = new JFrame("Instrucciones del Juego");
        instruccionesFrame.setSize(1000, 950);
        instruccionesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instruccionesFrame.setLocationRelativeTo(null);

        JTextArea instruccionesText = new JTextArea();
        /*instruccionesText.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));*/
        instruccionesText.setFont(text2Font);
        instruccionesText.setEditable(false);
        instruccionesText.setLineWrap(true);
        instruccionesText.setBackground(Color.BLACK);
        instruccionesText.setForeground(Color.WHITE);
        instruccionesText.setWrapStyleWord(true);
        instruccionesText.setText(" instrucciones del juego:\n\n" +
                " - el juego dispone de un mapa de la galaxia con 48 planetas.\n" +
                "\n - hay 4 enemigos principales: darth sidious, darth vader, general grievous y darth maul.\n" +
                "\n - cada enemigo intentará someter 12 planetas, distintos entre ellos.\n" +
                "\n - cada planeta podrá soportar el ataque del enemigo hasta 3 veces a la vez.\n" +
                "\n - si el enemigo consigue atacar una 4a vez el planeta, el imperio realizará un super ataque\n" +
                "   que se expandirá a los planetas colindantes.\n" +
                "\n - este super ataque se puede encadenar si los planetas colindantes\n" +
                "   tienen el nivel de ataque en 3 y así sucesivamente.\n" +
                "\n - para frenar esto y ganar la guerra, dispondremos de las siguientes acciones:\n" +
                "   repeler el ataque e investigar al enemigo.\n" +
                "\n - si se consigue investigar a un enemigo, podrás expulsar con una acción a un enemigo de un planeta\n" +
                "   el planeta quedará liberado de todos los ataques.\n" +
                "\n - la partida constará de rondas.\n" +
                "\n - al inicio de cada ronda, el imperio atacará aleatoriamente los planetas.\n" +
                "\n - en cada ronda, el jugador podrá realizar 4 acciones (se deben realizar todas).\n" +
                "\n - repeler el ataque gastará 1 acción, mientras que investigar gastará las 4 acciones.\n" +
                "\n - para ganar hay que completar la investigación de los 4 enemigos o someter al imperio\n" +
                "   eliminando todos los ataques que hay en los distintos planetas.\n" +
                "\n - el jugador perderá en los siguientes casos: todos los planetas tienen el nivel 3 de ataque o\n" +
        		"   si el imperio ha realizado un número de super ataques.\n");

        JScrollPane scrollPane = new JScrollPane(instruccionesText);
        instruccionesFrame.getContentPane().add(scrollPane);
         
        instruccionesFrame.setVisible(true);
    }
    

}