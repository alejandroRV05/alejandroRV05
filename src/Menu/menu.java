package Menu;

import javax.swing.*;

import Partida.Ciudad;
import Partida.Vacunas;
import Partida.Virus;
import Tablero.TableroCargar;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;

public class menu extends JFrame{
	
	private Clip clip;
	
    public menu(String usuario, String contra) {

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
				dificultad dif = new dificultad(usuario, contra);
				detenerMusica();
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
        Ranking.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarRanking();
        	}
        });
        
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
            	Connection con = bbdd.conectarBaseDatos();
    			String[] listaElementosSeleccionados = new String[5];
    			String[] Select = new String[20];
    			listaElementosSeleccionados = new String[1];
				listaElementosSeleccionados[0] = "COUNT(*)";
				Select = bbdd.select(con, "SELECT COUNT(*) FROM PANDEMIC_PARTIDA WHERE USUARIO = '"+usuario+"' AND CONTRA = '"+ contra +"'", listaElementosSeleccionados);	
    			
    			if(Select[0].charAt(0) != '0') {
    				Ciudad[] ciudades = new Ciudad[48];
    				Virus[] viruses = new Virus [4];
    				Vacunas [] vacuna = new Vacunas [4];
    				
    				ciudades = llenarCiudades();
    				viruses = llenarVirus();
    				vacuna = llenarVacunas();
    				int infectadasRonda = 0; 
    				int enfActDerr = 0; 
    				int brotDerr = 0; 
    				int porcVac = 0;
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "NUMINFROND";
    				Select = bbdd.select(con, "SELECT NUMINFROND FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				infectadasRonda = Integer.valueOf(Select[0]);
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "NUMINFPERD";
    				Select = bbdd.select(con, "SELECT NUMINFPERD FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				enfActDerr = Integer.valueOf(Select[0]);
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "NUMBROTPERD";
    				Select = bbdd.select(con, "SELECT NUMBROTPERD FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				brotDerr = Integer.valueOf(Select[0]);
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "PORCVAC";
    				Select = bbdd.select(con, "SELECT PORCVAC FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				porcVac = Integer.valueOf(Select[0]);
    				
    				
    				for(int i = 0; i < 48; i++) {
    					listaElementosSeleccionados = new String[1];
        				listaElementosSeleccionados[0] = "INFECCION";
        				Select = bbdd.select(con, "SELECT c.INFECCION FROM PANDEMIC_CIUDADES p, TABLE(p.CIUDADES) c WHERE p.USUARIO = '" + usuario + "' AND p.CONTRA = '" + contra +"' AND c.NOMBRE = '"+ciudades[i].getnombre()+"'", listaElementosSeleccionados);
        				ciudades[i].setinfeccion(Integer.valueOf(Select[0]));
    				}
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "VACROJ";
    				Select = bbdd.select(con, "SELECT VACROJ FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				vacuna[3].setporcentaje(Integer.valueOf(Select[0]));
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "VACVERD";
	    				Select = bbdd.select(con, "SELECT VACVERD FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
	    				vacuna[2].setporcentaje(Integer.valueOf(Select[0]));
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "VACAMA";
    				Select = bbdd.select(con, "SELECT VACAMA FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				vacuna[1].setporcentaje(Integer.valueOf(Select[0]));
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "VACAZUL";
    				Select = bbdd.select(con, "SELECT VACAZUL FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				vacuna[0].setporcentaje(Integer.valueOf(Select[0]));
    				
    				String modo = "";
    				
    				listaElementosSeleccionados = new String[1];
    				listaElementosSeleccionados[0] = "MODO";
    				Select = bbdd.select(con, "SELECT MODO FROM PANDEMIC_PARTIDA WHERE USUARIO = '" + usuario + "' AND CONTRA = '" + contra +"'", listaElementosSeleccionados);
    				modo = Select[0];
    					
					JOptionPane.showMessageDialog(null, "Partida encontrada. Cargando");
					TableroCargar tabc = new TableroCargar(ciudades, viruses, vacuna, infectadasRonda, enfActDerr, brotDerr, porcVac, usuario, contra, modo);
					detenerMusica();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "El usuario no tiene una partida guardada");
				}
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
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

 // Método para detener la música
    private void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
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
    
    private void mostrarRanking() {
        Font customFont = loadFont("Starjedi.ttf");
		Font text2Font = customFont.deriveFont(Font.PLAIN, 15);
        JFrame rankingFrame = new JFrame("Ranking del Juego");
        rankingFrame.setSize(600, 600);
        rankingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rankingFrame.setLocationRelativeTo(null);
        
        Connection con = bbdd.conectarBaseDatos();
		String[] listaElementosSeleccionados = new String[5];
		String[] Select = new String[20];
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "COUNT(*)";
		Select = bbdd.select(con, "SELECT COUNT(*) FROM PANDEMIC_RANKING", listaElementosSeleccionados);
		
		String usuarioFacil = "";
		String usuarioNormal = "";
		String usuarioDificil = "";
		String usuarioPersonalizado = "";
		
		String puntuacionFacil = "";
		String puntuacionNormal = "";
		String puntuacionDificil = "";
		String puntuacionPersonalizado = "";
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "USUARIO";
		Select = bbdd.select(con, "SELECT USUARIO FROM PANDEMIC_RANKING WHERE MODO = 'F' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'F')", listaElementosSeleccionados);
		if(Select[0] == null) {
			usuarioFacil = "";
		}else {
			usuarioFacil = Select[0];
		}
		
		
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "USUARIO";
		Select = bbdd.select(con, "SELECT USUARIO FROM PANDEMIC_RANKING WHERE MODO = 'N' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'N')", listaElementosSeleccionados);	
		if(Select[0] == null) {
			usuarioNormal = "";
		}else {
			usuarioNormal = Select[0];
		}
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "USUARIO";
		Select = bbdd.select(con, "SELECT USUARIO FROM PANDEMIC_RANKING WHERE MODO = 'D' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'D')", listaElementosSeleccionados);
		if(Select[0] == null) {
			usuarioDificil = "";
		}else {
			usuarioDificil = Select[0];
		}
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "USUARIO";
		Select = bbdd.select(con, "SELECT USUARIO FROM PANDEMIC_RANKING WHERE MODO = 'P' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'P')", listaElementosSeleccionados);
		if(Select[0] == null) {
			usuarioPersonalizado = "";
		}else {
			usuarioPersonalizado = Select[0];
		}
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "PUNTUACION";
		Select = bbdd.select(con, "SELECT PUNTUACION FROM PANDEMIC_RANKING WHERE MODO = 'F' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'F')", listaElementosSeleccionados);
		if(Select[0] == null) {
			puntuacionFacil = "";
		}else {
			puntuacionFacil = Select[0];
		}
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "PUNTUACION";
		Select = bbdd.select(con, "SELECT PUNTUACION FROM PANDEMIC_RANKING WHERE MODO = 'N' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'N')", listaElementosSeleccionados);
		if(Select[0] == null) {
			puntuacionNormal = "";
		}else {
			puntuacionNormal = Select[0];
		}
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "PUNTUACION";
		Select = bbdd.select(con, "SELECT PUNTUACION FROM PANDEMIC_RANKING WHERE MODO = 'D' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'D')", listaElementosSeleccionados);
		if(Select[0] == null) {
			puntuacionDificil = "";
		}else {
			puntuacionDificil = Select[0];
		}
		
		listaElementosSeleccionados = new String[1];
		listaElementosSeleccionados[0] = "PUNTUACION";
		Select = bbdd.select(con, "SELECT PUNTUACION FROM PANDEMIC_RANKING WHERE MODO = 'P' AND PUNTUACION = (SELECT MAX(PUNTUACION) FROM PANDEMIC_RANKING WHERE MODO = 'P')", listaElementosSeleccionados);
		if(Select[0] == null) {
			puntuacionPersonalizado = "";
		}else {
			puntuacionPersonalizado = Select[0];
		}
		
		
		
		JTextArea rankingText = new JTextArea();
		rankingText.setFont(text2Font);
		rankingText.setEditable(false);
		rankingText.setLineWrap(true);
		rankingText.setBackground(Color.BLACK);
		rankingText.setForeground(Color.WHITE);
		rankingText.setWrapStyleWord(true);
		rankingText.setText("ranking:\n\n" +
                " modo fácil: \n" +
                "\n jugador: "+usuarioFacil.toLowerCase()+" 	puntuación: "+puntuacionFacil+"\n" +
                "\nmodo normal: \n" +
                "\n jugador: "+usuarioNormal.toLowerCase()+" 	puntuación: "+puntuacionNormal+"\n" +
                "\nmodo difícil: \n" +
                "\n jugador: "+usuarioDificil.toLowerCase()+" 	puntuación: "+puntuacionDificil+"\n" +
                "\nmodo personalizado: \n" +
                "\n jugador: "+usuarioPersonalizado.toLowerCase()+" 	puntuación: "+puntuacionPersonalizado+"\n");
		
        

        JScrollPane scrollPane = new JScrollPane(rankingText);
        rankingFrame.getContentPane().add(scrollPane);
         
        rankingFrame.setVisible(true);
    }
    

	public static Ciudad[] llenarCiudades() {
		Ciudad[] ciudades = new Ciudad[48];
		File ciu = new File("ciudades.txt");
		String texto = "";
		String ciudad = "";
		ArrayList<String> imperio = new ArrayList<String>();
		String [] colindantes;
		int i = 0;
		int j = 0;
		int z = 0;
		int comas = 0;
		int [] val = new int[1];
		int [] coord = new int[2];
		imperio.add("Darth Sidious");
		imperio.add("Darth Maul");
		imperio.add("General Grievous");
		imperio.add("Darth Vader");
		
		
	
		try {
			FileReader fr = new FileReader(ciu);
		    BufferedReader br = new BufferedReader(fr); 
		     while(texto != null) {
		    	coord = new int [2];
		    	ciudades[i] = new Ciudad();
		    	ciudades[i].coordenadas = new int[2];
				texto = br.readLine();
				val[0] = 0;
				if(texto != null) {
					ciudad = devolverCiudad(texto, val);
					ciudades[i].setnombre(ciudad);
					
                    //Número 1
					val[0] = val[0] + 2;
                    //Número 2
                    coord[0] = Integer.valueOf(devolverCoordenada(texto, val));
                    //Número 3
                    coord[1] = Integer.valueOf(devolverCoordenada(texto, val));                    
                    
					ciudades[i].setcoordenadas(coord);
					ciudades[i].setcolor(imperio.get(j));
					ciudades[i].setinfeccion(0);
					
					comas = 0;
					for(z = 0; z != texto.length(); z++) {
						if(texto.charAt(z) == ',')
							comas++;
					}

					colindantes = new String [comas + 1];
					
					comas = 0;

					while(val[0] != texto.length()) {
						ciudad = "";
						for(z = val[0]; texto.charAt(z) != ',' && texto.charAt(z) != ';'; z++) {
							ciudad = ciudad +  texto.charAt(z);
						}
						val[0] = z + 1;
						colindantes[comas] = ciudad;
						comas++;
					}
					ciudades[i].setciudadesColindantes(colindantes);
					
				}
				
				//Cambiamos la palabra del arrayList cada 12 ciudades
				if(((i+1)%12) == 0 && i != 0) {
					j++;
				}
			    
			    if(i != 48) {
					i++;
				}
	        }
		    br.close();
		    fr.close();
		} catch (Exception e) {
				//System.out.println("ERROR E/SFF: " + e);
		}
				
		return ciudades;
	}
	
	
	public static Virus[] llenarVirus() {
		Virus[] viruses = new Virus [4];
		int i = 0;
		String id = "";
		String[] colores = new String [] {"azul", "amarillo", "verde", "rojo"};
		String[] imperio = new String [] {"Darth Sidious", "Darth Maul", "General Grievous",  "Darth Vader" };

	
		for(i = 0; i < 4; i++) {
			id = "";
			viruses[i] = new Virus();
			viruses[i].setcolor(colores[i]);
			viruses[i].setnombre(imperio[i]);
			id = id + i;
			viruses[i].setidentificador(id);	
		}
		
		return viruses;
	}

	
	public static Vacunas[] llenarVacunas() {
	Vacunas [] vacuna = new Vacunas [4];
	int i = 0;
	String[] colores = new String [] {"azul", "amarillo", "verde", "rojo"};
	String[] imperio = new String [] {"Darth Sidious", "Darth Maul", "General Grievous",  "Darth Vader" };
		
	for(i = 0; i < 4; i++) {
		vacuna[i] = new Vacunas();
		vacuna[i].setcolor(colores[i]);
		vacuna[i].setporcentaje(0);
		vacuna[i].setnombre(imperio[i]);
	}
		
	return vacuna;	
}
	

	//************************************************************************************
	//** Nombre de la función: devolverCiudad
	//** Explicación del que hace la función: Devuelve el nombre de la ciudad
	//** Parámetros de entrada: String total, int [] val
	//** Parámetros de salida: String nombreCiudad
	//************************************************************************************	
	public static String devolverCiudad(String total, int [] val) {
		String nombreCiudad = "";
		int i = 0;
		
		for(i = 0; total.charAt(i) != ';' && total.charAt(i) != ','; i++) {
			nombreCiudad = nombreCiudad + total.charAt(i);
		}
		val[0] = i + 1;
		
		
		
		return nombreCiudad;
	}
	
	//************************************************************************************
	//** Nombre de la función: devolverCoordenada
	//** Explicación del que hace la función: Devuelve la coordenada
	//** Parámetros de entrada: String total, int [] val
	//** Parámetros de salida: String coord
	//************************************************************************************	
	public static String devolverCoordenada(String total, int [] val) {
		int i = 0;
		String coord = "";
		
		for(i = val[0] ; total.charAt(i) != ';'; i++) {
			coord = coord + total.charAt(i);
		}

		val[0]  = i + 1;
		return coord;
		
		
	}
    

}