package Tablero;

import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import Menu.menu;
import Partida.Ciudad;
import Partida.Vacunas;
import Partida.Virus;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Tablero extends JFrame {
	
	int enfAct = 0;
	int numBrot = 0;
	int acciones  = 4;
	int ronda = 1;
	JLabel Rondas = new JLabel("ronda 1");
	JLabel Acciones = new JLabel("acciones: 4");
    JProgressBar progressBar_Vader = new JProgressBar();
    JProgressBar progressBar_Grievous = new JProgressBar();
    JProgressBar progressBar_Maul = new JProgressBar();
    JProgressBar progressBar_Sidious = new JProgressBar();
    JButton [] planetas = new JButton [48];
    JFrame ventanaSecundaria;
    JLabel Conquistas;
    JLabel Brotes;
    
	public Tablero(Ciudad[] ciudades, Virus[] viruses, Vacunas[] vacuna, int infectadasRonda, int enfActDerr, int brotDerr, int porcVac) {
	

    	Font customFont = loadFont("Starjedi.ttf");
    	Font buttonFont = customFont.deriveFont(Font.PLAIN, 14);
		
        // Configuración de la ventana principal
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        setSize(screenWidth, screenHeight);
        setTitle("CONQUISTA ESTELAR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 // Establecer el icono de la ventana
        Image iconImage = new ImageIcon("logo.jpg").getImage();
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
        getContentPane().setLayout(null);
        
        JButton Informacion = new JButton("informacion");
        Informacion.setForeground(new Color(255, 255, 255));
        Informacion.setBounds(675, 850, 150, 30);
        getContentPane().add(Informacion);
        Informacion.setFont(buttonFont);
        setButtonProperties(Informacion);
        
        JButton Investigar = new JButton("investigar");
        Investigar.setForeground(new Color(255, 255, 255));
        Investigar.setBounds(675, 915, 150, 30);
        getContentPane().add(Investigar);
        Investigar.setFont(buttonFont);
        setButtonProperties(Investigar);
        Investigar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                ventanaSecundaria = new JFrame("Selección investigación");
                ventanaSecundaria.getContentPane().setLayout(new GridLayout(4, 1));
             
                
                
                JButton btnDarthVader = new JButton("darth vader");
                btnDarthVader.setForeground(new Color(255, 0, 0));
                btnDarthVader.setFont(buttonFont);
                setButtonProperties(btnDarthVader);
                btnDarthVader.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	investigarVacuna(vacuna[3], progressBar_Vader, porcVac, ciudades, infectadasRonda, planetas, vacuna, enfActDerr, brotDerr);
                    	mirarVictoria(vacuna, ventanaSecundaria);
                    }
                });
                
                JButton btnDarthMaul = new JButton("darth maul");
                btnDarthMaul.setForeground(new Color(255, 255, 0));
                btnDarthMaul.setFont(buttonFont);
                setButtonProperties(btnDarthMaul);
                btnDarthMaul.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	investigarVacuna(vacuna[1], progressBar_Maul, porcVac, ciudades, infectadasRonda, planetas, vacuna, enfActDerr, brotDerr);
                    	mirarVictoria(vacuna, ventanaSecundaria);
                    }
                });
                
                JButton btnGeneralGrievous = new JButton("general grievous");
                btnGeneralGrievous.setForeground(new Color(128, 255, 0));
                btnGeneralGrievous.setFont(buttonFont);
                setButtonProperties(btnGeneralGrievous);
                btnGeneralGrievous.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	investigarVacuna(vacuna[2], progressBar_Grievous, porcVac, ciudades, infectadasRonda, planetas, vacuna, enfActDerr, brotDerr);
                    	mirarVictoria(vacuna, ventanaSecundaria);
                    }
                });
                
                JButton btnDarthSidious = new JButton("darth sidious");
                btnDarthSidious.setForeground(new Color(0, 255, 255));
                btnDarthSidious.setFont(buttonFont);
                setButtonProperties(btnDarthSidious);
                btnDarthSidious.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	investigarVacuna(vacuna[0], progressBar_Sidious, porcVac, ciudades, infectadasRonda, planetas, vacuna, enfActDerr, brotDerr);
                    	mirarVictoria(vacuna, ventanaSecundaria);
                    }
                });
                
                ventanaSecundaria.getContentPane().add(btnDarthVader);
                ventanaSecundaria.getContentPane().add(btnDarthMaul);
                ventanaSecundaria.getContentPane().add(btnGeneralGrievous);
                ventanaSecundaria.getContentPane().add(btnDarthSidious);

                ventanaSecundaria.getContentPane().setBackground(Color.BLACK);
                ventanaSecundaria.setSize(400, 200);
                ventanaSecundaria.setLocationRelativeTo(null);
                ventanaSecundaria.setVisible(true);
            }
        });
        
        
        Brotes = new JLabel("brotes: " + numBrot + "/" + brotDerr);
        Brotes.setForeground(new Color(255, 255, 255));
        Brotes.setHorizontalAlignment(SwingConstants.CENTER);
        Brotes.setBounds(950, 850, 150, 30);
        getContentPane().add(Brotes);
        Brotes.setFont(buttonFont);
        
     	mirarInfectadas(ciudades);
        Conquistas = new JLabel("conquistas: " + enfAct + "/" + enfActDerr);
        Conquistas.setForeground(new Color(255, 255, 255));
        Conquistas.setHorizontalAlignment(SwingConstants.CENTER);
        Conquistas.setBounds(950, 915, 150, 30);
        getContentPane().add(Conquistas);
        Conquistas.setFont(buttonFont);
        
        
        Acciones.setHorizontalAlignment(SwingConstants.CENTER);
        Acciones.setForeground(new Color(255, 255, 255));
        Acciones.setBounds(1100, 850, 150, 30);
        getContentPane().add(Acciones);
        Acciones.setFont(buttonFont);
        
        
        Rondas.setHorizontalAlignment(SwingConstants.CENTER);
        Rondas.setForeground(new Color(255, 255, 255));
        Rondas.setBounds(1100, 915, 150, 30);
        getContentPane().add(Rondas);
        Rondas.setFont(buttonFont);
        
        JLabel Darth_Maul = new JLabel("darth maul");
        Darth_Maul.setBackground(new Color(255, 255, 0));
        Darth_Maul.setForeground(new Color(255, 255, 0));
        Darth_Maul.setBounds(12, 885, 250, 30);
        getContentPane().add(Darth_Maul);
        Darth_Maul.setHorizontalAlignment(SwingConstants.CENTER);
        Darth_Maul.setFont(buttonFont);
        
        JLabel General_Grievous = new JLabel("general grievous");
        General_Grievous.setHorizontalAlignment(SwingConstants.CENTER);
        General_Grievous.setForeground(new Color(128, 255, 0));
        General_Grievous.setBounds(319, 885, 250, 30);
        getContentPane().add(General_Grievous);
        General_Grievous.setFont(buttonFont);
        
        JLabel Darth_Vader = new JLabel("darth vader");
        Darth_Vader.setHorizontalAlignment(SwingConstants.CENTER);
        Darth_Vader.setForeground(new Color(255, 0, 0));
        Darth_Vader.setBounds(319, 820, 250, 30);
        getContentPane().add(Darth_Vader);
        Darth_Vader.setFont(buttonFont);
        
        JLabel Darth_Sidious = new JLabel("darth sidious");
        Darth_Sidious.setHorizontalAlignment(SwingConstants.CENTER);
        Darth_Sidious.setForeground(new Color(0, 255, 255));
        Darth_Sidious.setBounds(12, 820, 250, 30);
        getContentPane().add(Darth_Sidious);
        Darth_Sidious.setFont(buttonFont);
        

        progressBar_Vader.setToolTipText("");
        progressBar_Vader.setStringPainted(true);
        progressBar_Vader.setForeground(new Color(255, 0, 0));
        progressBar_Vader.setBounds(319, 850, 250, 30);
        getContentPane().add(progressBar_Vader);
        progressBar_Vader.setUI(new BasicProgressBarUI() {
            protected Color getSelectionForeground() {
                return Color.BLACK; // Cambiamos el color del texto del porcentaje
            }
        });
        

        
        progressBar_Grievous.setStringPainted(true);
        progressBar_Grievous.setForeground(new Color(128, 255, 0));
        progressBar_Grievous.setBounds(319, 915, 250, 30);
        getContentPane().add(progressBar_Grievous);
        progressBar_Grievous.setUI(new BasicProgressBarUI() {
            protected Color getSelectionForeground() {
                return Color.BLACK; // Cambiamos el color del texto del porcentaje
            }
        });
        

        progressBar_Maul.setForeground(new Color(255, 255, 0));
        progressBar_Maul.setStringPainted(true);
        progressBar_Maul.setBounds(12, 915, 250, 30);
        getContentPane().add(progressBar_Maul);
        progressBar_Maul.setUI(new BasicProgressBarUI() {
            protected Color getSelectionForeground() {
                return Color.BLACK; // Cambiamos el color del texto del porcentaje
            }
        });
        

        progressBar_Sidious.setForeground(new Color(0, 255, 255));
        progressBar_Sidious.setStringPainted(true);
        progressBar_Sidious.setBounds(12, 850, 250, 30);
        getContentPane().add(progressBar_Sidious);
        progressBar_Sidious.setUI(new BasicProgressBarUI() {
            protected Color getSelectionForeground() {
                return Color.BLACK; // Cambiamos el color del texto del porcentaje
            }
        });
        
        //Jedah
        planetas[47] = new JButton(ciudades[47].getnombre().toLowerCase()+ "(" + ciudades[47].getinfeccion() + ")");
        planetas[47].setForeground(new Color(255, 0, 0));
        planetas[47].setBounds(1132, 769, 175, 23);
        getContentPane().add( planetas[47]);
        planetas[47].setFont(buttonFont);
        setButtonProperties( planetas[47]);
        planetas[47].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[47], ciudades, infectadasRonda,  planetas[47], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Iego
        planetas[46] = new JButton(ciudades[46].getnombre().toLowerCase()+ "(" + ciudades[46].getinfeccion() + ")");
        planetas[46].setForeground(new Color(255, 0, 0));
        planetas[46].setBounds(1076, 429, 175, 23);
        getContentPane().add( planetas[46]);
        planetas[46].setFont(buttonFont);
        setButtonProperties( planetas[46]);
        planetas[46].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[46], ciudades, infectadasRonda,  planetas[46], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Scarif
        planetas[45] = new JButton(ciudades[45].getnombre().toLowerCase()+ "(" + ciudades[45].getinfeccion() + ")");
        planetas[45].setForeground(new Color(255, 0, 0));
        planetas[45].setBounds(1076, 656, 175, 23);
        getContentPane().add( planetas[45]);
        planetas[45].setFont(buttonFont);
        setButtonProperties( planetas[45]);
        planetas[45].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[45], ciudades, infectadasRonda,  planetas[45], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Exegol
        planetas[44] = new JButton(ciudades[44].getnombre().toLowerCase()+ "(" + ciudades[44].getinfeccion() + ")");
        planetas[44].setForeground(new Color(255, 0, 0));
        planetas[44].setBounds(1040, 184, 175, 23);
        getContentPane().add( planetas[44]);
        planetas[44].setFont(buttonFont);
        setButtonProperties( planetas[44]);
        planetas[44].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[44], ciudades, infectadasRonda,  planetas[44], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Crait
        planetas[43] = new JButton(ciudades[43].getnombre().toLowerCase()+ "(" + ciudades[43].getinfeccion() + ")");
        planetas[43].setForeground(new Color(255, 0, 0));
        planetas[43].setBounds(1132, 240, 175, 23);
        getContentPane().add( planetas[43]);
        planetas[43].setFont(buttonFont);
        setButtonProperties( planetas[43]);
        planetas[43].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[43], ciudades, infectadasRonda,  planetas[43], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Ahch To
        planetas[42] = new JButton(ciudades[42].getnombre().toLowerCase()+ "(" + ciudades[42].getinfeccion() + ")");
        planetas[42].setForeground(new Color(255, 0, 0));
        planetas[42].setBounds(1076, 123, 175, 23);
        getContentPane().add( planetas[42]);
        planetas[42].setFont(buttonFont);
        setButtonProperties( planetas[42]);
        planetas[42].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[42], ciudades, infectadasRonda,  planetas[42], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Korriban
        planetas[41] = new JButton(ciudades[41].getnombre().toLowerCase()+ "(" + ciudades[41].getinfeccion() + ")");
        planetas[41].setForeground(new Color(255, 0, 0));
        planetas[41].setBounds(1103, 22, 175, 23);
        getContentPane().add( planetas[41]);
        planetas[41].setFont(buttonFont);
        setButtonProperties( planetas[41]);
        planetas[41].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[41], ciudades, infectadasRonda,  planetas[41], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Ithor
        planetas[40] = new JButton(ciudades[40].getnombre().toLowerCase()+ "(" + ciudades[40].getinfeccion() + ")");
        planetas[40].setForeground(new Color(255, 0, 0));
        planetas[40].setBounds(940, 40, 175, 23);
        getContentPane().add( planetas[40]);
        planetas[40].setFont(buttonFont);
        setButtonProperties( planetas[40]);
        planetas[40].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[40], ciudades, infectadasRonda,  planetas[40], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Duro
        planetas[39] = new JButton(ciudades[39].getnombre().toLowerCase()+ "(" + ciudades[39].getinfeccion() + ")");
        planetas[39].setForeground(new Color(255, 0, 0));
        planetas[39].setBounds(925, 133, 175, 23);
        getContentPane().add( planetas[39]);
        planetas[39].setFont(buttonFont);
        setButtonProperties( planetas[39]);
        planetas[39].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[39], ciudades, infectadasRonda,  planetas[39], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Bothawui
        planetas[38] = new JButton(ciudades[38].getnombre().toLowerCase()+ "(" + ciudades[38].getinfeccion() + ")");
        planetas[38].setForeground(new Color(255, 0, 0));
        planetas[38].setBounds(1006, 338, 175, 23);
        getContentPane().add( planetas[38]);
        planetas[38].setFont(buttonFont);
        setButtonProperties( planetas[38]);
        planetas[38].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[38], ciudades, infectadasRonda,  planetas[38], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Eriadu
        planetas[37] = new JButton(ciudades[37].getnombre().toLowerCase()+ "(" + ciudades[37].getinfeccion() + ")");
        planetas[37].setForeground(new Color(255, 0, 0));
        planetas[37].setBounds(971, 552, 175, 23);
        getContentPane().add( planetas[37]);
        planetas[37].setFont(buttonFont);
        setButtonProperties( planetas[37]);
        planetas[37].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[37], ciudades, infectadasRonda,  planetas[37], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Ilum
        planetas[36] = new JButton(ciudades[36].getnombre().toLowerCase()+ "(" + ciudades[36].getinfeccion() + ")");
        planetas[36].setForeground(new Color(255, 0, 0));
        planetas[36].setBounds(867, 736, 175, 23);
        getContentPane().add( planetas[36]);
        planetas[36].setFont(buttonFont);
        setButtonProperties( planetas[36]);
        planetas[36].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[36], ciudades, infectadasRonda,  planetas[36], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Wayland
        planetas[35] = new JButton(ciudades[35].getnombre().toLowerCase()+ "(" + ciudades[35].getinfeccion() + ")");
        planetas[35].setForeground(new Color(128, 255, 0));
        planetas[35].setBounds(823, 604, 175, 23);
        getContentPane().add( planetas[35]);
        planetas[35].setFont(buttonFont);
        setButtonProperties( planetas[35]);
        planetas[35].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[35], ciudades, infectadasRonda,  planetas[35], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Bakura
        planetas[34] = new JButton(ciudades[34].getnombre().toLowerCase()+ "(" + ciudades[34].getinfeccion() + ")");
        planetas[34].setForeground(new Color(128, 255, 0));
        planetas[34].setBounds(894, 478, 175, 23);
        getContentPane().add( planetas[34]);
        planetas[34].setFont(buttonFont);
        setButtonProperties( planetas[34]);
        planetas[34].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[34], ciudades, infectadasRonda,  planetas[34], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Rishi
        planetas[33] = new JButton(ciudades[33].getnombre().toLowerCase()+ "(" + ciudades[33].getinfeccion() + ")");
        planetas[33].setForeground(new Color(128, 255, 0));
        planetas[33].setBounds(877, 386, 175, 23);
        getContentPane().add( planetas[33]);
        planetas[33].setFont(buttonFont);
        setButtonProperties( planetas[33]);
        planetas[33].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[33], ciudades, infectadasRonda,  planetas[33], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Hapes
        planetas[32] = new JButton(ciudades[32].getnombre().toLowerCase()+ "(" + ciudades[32].getinfeccion() + ")");
        planetas[32].setForeground(new Color(128, 255, 0));
        planetas[32].setBounds(646, 573, 175, 23);
        getContentPane().add( planetas[32]);
        planetas[32].setFont(buttonFont);
        setButtonProperties( planetas[32]);
        planetas[32].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[32], ciudades, infectadasRonda,  planetas[32], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Dathomir
        planetas[31] = new JButton(ciudades[31].getnombre().toLowerCase()+ "(" + ciudades[31].getinfeccion() + ")");
        planetas[31].setForeground(new Color(128, 255, 0));
        planetas[31].setBounds(763, 444, 175, 23);
        getContentPane().add( planetas[31]);
        planetas[31].setFont(buttonFont);
        setButtonProperties( planetas[31]);
        planetas[31].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[31], ciudades, infectadasRonda,  planetas[31], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Onderon
        planetas[30] = new JButton(ciudades[30].getnombre().toLowerCase()+ "(" + ciudades[30].getinfeccion() + ")");
        planetas[30].setForeground(new Color(128, 255, 0));
        planetas[30].setBounds(854, 286, 175, 23);
        getContentPane().add( planetas[30]);
        planetas[30].setFont(buttonFont);
        setButtonProperties( planetas[30]);
        planetas[30].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[30], ciudades, infectadasRonda,  planetas[30], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Manaan
        planetas[29] = new JButton(ciudades[29].getnombre().toLowerCase()+ "(" + ciudades[29].getinfeccion() + ")");
        planetas[29].setForeground(new Color(128, 255, 0));
        planetas[29].setBounds(799, 208, 175, 23);
        getContentPane().add( planetas[29]);
        planetas[29].setFont(buttonFont);
        setButtonProperties( planetas[29]);
        planetas[29].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[29], ciudades, infectadasRonda,  planetas[29], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Mygeeto
        planetas[28] = new JButton(ciudades[28].getnombre().toLowerCase()+ "(" + ciudades[28].getinfeccion() + ")");
        planetas[28].setForeground(new Color(128, 255, 0));
        planetas[28].setBounds(717, 352, 175, 23);
        getContentPane().add( planetas[28]);
        planetas[28].setFont(buttonFont);
        setButtonProperties( planetas[28]);
        planetas[28].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[28], ciudades, infectadasRonda,  planetas[28], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Zeltros
        planetas[27] = new JButton(ciudades[27].getnombre().toLowerCase()+ "(" + ciudades[27].getinfeccion() + ")");
        planetas[27].setForeground(new Color(128, 255, 0));
        planetas[27].setBounds(697, 252, 175, 23);
        getContentPane().add( planetas[27]);
        planetas[27].setFont(buttonFont);
        setButtonProperties( planetas[27]);
        planetas[27].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[27], ciudades, infectadasRonda,  planetas[27], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Malachor
        planetas[26] = new JButton(ciudades[26].getnombre().toLowerCase()+ "(" + ciudades[26].getinfeccion() + ")");
        planetas[26].setForeground(new Color(128, 255, 0));
        planetas[26].setBounds(571, 510, 175, 23);
        getContentPane().add( planetas[26]);
        planetas[26].setFont(buttonFont);
        setButtonProperties( planetas[26]);
        planetas[26].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[26], ciudades, infectadasRonda,  planetas[26], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Kessel
        planetas[25] = new JButton(ciudades[25].getnombre().toLowerCase()+ "(" + ciudades[25].getinfeccion() + ")");
        planetas[25].setForeground(new Color(128, 255, 0));
        planetas[25].setBounds(584, 397, 175, 23);
        getContentPane().add( planetas[25]);
        planetas[25].setFont(buttonFont);
        setButtonProperties( planetas[25]);
        planetas[25].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[25], ciudades, infectadasRonda,  planetas[25], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Mon Cala
        planetas[24] = new JButton(ciudades[24].getnombre().toLowerCase()+ "(" + ciudades[24].getinfeccion() + ")");
        planetas[24].setForeground(new Color(128, 255, 0));
        planetas[24].setBounds(558, 286, 175, 23);
        getContentPane().add( planetas[24]);
        planetas[24].setFont(buttonFont);
        setButtonProperties( planetas[24]);
        planetas[24].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[24], ciudades, infectadasRonda,  planetas[24], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Lothal
        planetas[23] = new JButton(ciudades[23].getnombre().toLowerCase()+ "(" + ciudades[23].getinfeccion() + ")");
        planetas[23].setForeground(new Color(255, 255, 0));
        planetas[23].setBounds(448, 786, 175, 23);
        getContentPane().add( planetas[23]);
        planetas[23].setFont(buttonFont);
        setButtonProperties( planetas[23]);
        planetas[23].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[23], ciudades, infectadasRonda,  planetas[23], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Nal Hutta
        planetas[22] = new JButton(ciudades[22].getnombre().toLowerCase()+ "(" + ciudades[22].getinfeccion() + ")");
        planetas[22].setForeground(new Color(255, 255, 0));
        planetas[22].setBounds(491, 619, 175, 23);
        getContentPane().add( planetas[22]);
        planetas[22].setFont(buttonFont);
        setButtonProperties( planetas[22]);
        planetas[22].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[22], ciudades, infectadasRonda,  planetas[22], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Taris
        planetas[21] = new JButton(ciudades[21].getnombre().toLowerCase()+ "(" + ciudades[21].getinfeccion() + ")");
        planetas[21].setForeground(new Color(255, 255, 0));
        planetas[21].setBounds(319, 680, 175, 23);
        getContentPane().add( planetas[21]);
        planetas[21].setFont(buttonFont);
        setButtonProperties( planetas[21]);
        planetas[21].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[21], ciudades, infectadasRonda,  planetas[21], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Felucia
        planetas[20] = new JButton(ciudades[20].getnombre().toLowerCase()+ "(" + ciudades[20].getinfeccion() + ")");
        planetas[20].setForeground(new Color(255, 255, 0));
        planetas[20].setBounds(403, 466, 175, 23);
        getContentPane().add( planetas[20]);
        planetas[20].setFont(buttonFont);
        setButtonProperties( planetas[20]);
        planetas[20].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[20], ciudades, infectadasRonda,  planetas[20], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Dantooine
        planetas[19] = new JButton(ciudades[19].getnombre().toLowerCase()+ "(" + ciudades[19].getinfeccion() + ")");
        planetas[19].setForeground(new Color(255, 255, 0));
        planetas[19].setBounds(270, 466, 175, 23);
        getContentPane().add( planetas[19]);
        planetas[19].setFont(buttonFont);
        setButtonProperties( planetas[19]);
        planetas[19].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[19], ciudades, infectadasRonda,  planetas[19], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Mandalore
        planetas[18] = new JButton(ciudades[18].getnombre().toLowerCase()+ "(" + ciudades[18].getinfeccion() + ")");
        planetas[18].setForeground(new Color(255, 255, 0));
        planetas[18].setBounds(127, 563, 175, 23);
        getContentPane().add( planetas[18]);
        planetas[18].setFont(buttonFont);
        setButtonProperties( planetas[18]);
        planetas[18].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[18], ciudades, infectadasRonda,  planetas[18], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Ryloth
        planetas[17] = new JButton(ciudades[17].getnombre().toLowerCase()+ "(" + ciudades[17].getinfeccion() + ")");
        planetas[17].setForeground(new Color(255, 255, 0));
        planetas[17].setBounds(42, 748, 175, 23);
        getContentPane().add( planetas[17]);
        planetas[17].setFont(buttonFont);
        setButtonProperties( planetas[17]);
        planetas[17].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[17], ciudades, infectadasRonda,  planetas[17], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Sullust
        planetas[16] = new JButton(ciudades[16].getnombre().toLowerCase()+ "(" + ciudades[16].getinfeccion() + ")");
        planetas[16].setForeground(new Color(255, 255, 0));
        planetas[16].setBounds(54, 466, 175, 23);
        getContentPane().add( planetas[16]);
        planetas[16].setFont(buttonFont);
        setButtonProperties( planetas[16]);
        planetas[16].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[16], ciudades, infectadasRonda,  planetas[16], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Corellia
        planetas[15] = new JButton(ciudades[15].getnombre().toLowerCase()+ "(" + ciudades[15].getinfeccion() + ")");
        planetas[15].setForeground(new Color(255, 255, 0));
        planetas[15].setBounds(284, 308, 175, 23);
        getContentPane().add( planetas[15]);
        planetas[15].setFont(buttonFont);
        setButtonProperties( planetas[15]);
        planetas[15].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[15], ciudades, infectadasRonda,  planetas[15], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Chandrilla
        planetas[14] = new JButton(ciudades[14].getnombre().toLowerCase()+ "(" + ciudades[14].getinfeccion() + ")");
        planetas[14].setForeground(new Color(255, 255, 0));
        planetas[14].setBounds(182, 253, 175, 23);
        getContentPane().add( planetas[14]);
        planetas[14].setFont(buttonFont);
        setButtonProperties( planetas[14]);
        planetas[14].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[14], ciudades, infectadasRonda,  planetas[14], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Jakku
        planetas[13] = new JButton(ciudades[13].getnombre().toLowerCase()+ "(" + ciudades[13].getinfeccion() + ")");
        planetas[13].setForeground(new Color(255, 255, 0));
        planetas[13].setBounds(284, 228, 175, 23);
        getContentPane().add( planetas[13]);
        planetas[13].setFont(buttonFont);
        setButtonProperties( planetas[13]);
        planetas[13].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[13], ciudades, infectadasRonda,  planetas[13], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Utapau
        planetas[12] = new JButton(ciudades[12].getnombre().toLowerCase()+ "(" + ciudades[12].getinfeccion() + ")");
        planetas[12].setForeground(new Color(255, 255, 0));
        planetas[12].setBounds(42, 184, 175, 23);
        getContentPane().add( planetas[12]);
        planetas[12].setFont(buttonFont);
        setButtonProperties( planetas[12]);
        planetas[12].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[12], ciudades, infectadasRonda,  planetas[12], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Yavin
        planetas[11] = new JButton(ciudades[11].getnombre().toLowerCase()+ "(" + ciudades[11].getinfeccion() + ")");
        planetas[11].setForeground(new Color(0, 255, 255));
        planetas[11].setBounds(812, 22, 175, 23);
        getContentPane().add( planetas[11]);
        planetas[11].setFont(buttonFont);
        setButtonProperties( planetas[11]);
        planetas[11].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[11], ciudades, infectadasRonda,  planetas[11], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Dagobah
        planetas[10] = new JButton(ciudades[10].getnombre().toLowerCase()+ "(" + ciudades[10].getinfeccion() + ")");
        planetas[10].setForeground(new Color(0, 255, 255));
        planetas[10].setBounds(602, 161, 175, 23);
        getContentPane().add( planetas[10]);
        planetas[10].setFont(buttonFont);
        setButtonProperties( planetas[10]);
        planetas[10].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[10], ciudades, infectadasRonda,  planetas[10], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Mustafar
        planetas[9] = new JButton(ciudades[9].getnombre().toLowerCase()+ "(" + ciudades[9].getinfeccion() + ")");
        planetas[9].setForeground(new Color(0, 255, 255));
        planetas[9].setBounds(697, 67, 175, 23);
        getContentPane().add( planetas[9]);
        planetas[9].setFont(buttonFont);
        setButtonProperties( planetas[9]);
        planetas[9].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[9], ciudades, infectadasRonda,  planetas[9], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Geonosis
        planetas[8] = new JButton(ciudades[8].getnombre().toLowerCase()+ "(" + ciudades[8].getinfeccion() + ")");
        planetas[8].setForeground(new Color(0, 255, 255));
        planetas[8].setBounds(602, 123, 175, 23);
        getContentPane().add( planetas[8]);
        planetas[8].setFont(buttonFont);
        setButtonProperties( planetas[8]);
        planetas[8].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[8], ciudades, infectadasRonda,  planetas[8], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Kamino
        planetas[7] = new JButton(ciudades[7].getnombre().toLowerCase()+ "(" + ciudades[7].getinfeccion() + ")");
        planetas[7].setForeground(new Color(0, 255, 255));
        planetas[7].setBounds(354, 194, 175, 23);
        getContentPane().add( planetas[7]);
        planetas[7].setFont(buttonFont);
        setButtonProperties( planetas[7]);
        planetas[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[7], ciudades, infectadasRonda,  planetas[7], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Naboo
        planetas[6] = new JButton(ciudades[6].getnombre().toLowerCase()+ "(" + ciudades[6].getinfeccion() + ")");
        planetas[6].setForeground(new Color(0, 255, 255));
        planetas[6].setBounds(584, 11, 175, 23);
        getContentPane().add( planetas[6]);
        planetas[6].setFont(buttonFont);
        setButtonProperties( planetas[6]);
        planetas[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[6], ciudades, infectadasRonda,  planetas[6], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Hoth
        planetas[5] = new JButton(ciudades[5].getnombre().toLowerCase()+ "(" + ciudades[5].getinfeccion() + ")");
        planetas[5].setForeground(new Color(0, 255, 255));
        planetas[5].setBounds(319, 133, 175, 23);
        getContentPane().add( planetas[5]);
        planetas[5].setFont(buttonFont);
        setButtonProperties( planetas[5]);
        planetas[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[5], ciudades, infectadasRonda,  planetas[5], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Endor
        planetas[4] = new JButton(ciudades[4].getnombre().toLowerCase()+ "(" + ciudades[4].getinfeccion() + ")");
        planetas[4].setForeground(new Color(0, 255, 255));
        planetas[4].setBounds(448, 82, 175, 23);
        getContentPane().add( planetas[4]);
        planetas[4].setFont(buttonFont);
        setButtonProperties( planetas[4]);
        planetas[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[4], ciudades, infectadasRonda,  planetas[4], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Kashyyk
        planetas[3] = new JButton(ciudades[3].getnombre().toLowerCase()+ "(" + ciudades[3].getinfeccion() + ")");
        planetas[3].setForeground(new Color(0, 255, 255));
        planetas[3].setBounds(340, 0, 175, 23);
        getContentPane().add( planetas[3]);
        planetas[3].setFont(buttonFont);
        setButtonProperties( planetas[3]);
        planetas[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[3], ciudades, infectadasRonda,  planetas[3], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Alderaan
        planetas[2] = new JButton(ciudades[2].getnombre().toLowerCase()+ "(" + ciudades[2].getinfeccion() + ")");
        planetas[2].setForeground(new Color(0, 255, 255));
        planetas[2].setBounds(228, 143, 175, 23);
        getContentPane().add( planetas[2]);
        planetas[2].setFont(buttonFont);
        setButtonProperties( planetas[2]);
        planetas[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[2], ciudades, infectadasRonda,  planetas[2], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Tatooine
        planetas[1] = new JButton(ciudades[1].getnombre().toLowerCase()+ "(" + ciudades[1].getinfeccion() + ")");
        planetas[1].setForeground(new Color(0, 255, 255));
        planetas[1].setBounds(182, 40, 175, 23);
        getContentPane().add( planetas[1]);
        planetas[1].setFont(buttonFont);
        setButtonProperties( planetas[1]);
        planetas[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[1], ciudades, infectadasRonda,  planetas[1], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        //Coruscant
        planetas[0] = new JButton(ciudades[0].getnombre().toLowerCase()+ "(" + ciudades[0].getinfeccion() + ")");
        planetas[0].setForeground(new Color(0, 255, 255));
        planetas[0].setBounds(12, 22, 175, 23);
        getContentPane().add( planetas[0]);
        planetas[0].setFont(buttonFont);
        setButtonProperties( planetas[0]);
        planetas[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarVentana(ciudades[0], ciudades, infectadasRonda,  planetas[0], vacuna, enfActDerr, brotDerr); // Mostrar la ventana con la información
            }
        });
        
        JLabel Mapa = new JLabel("");
        Mapa.setForeground(new Color(0, 128, 255));
        Mapa.setBounds(0, 0, 1264, 985);
        Mapa.setIcon(new ImageIcon("Mapa_conquista_final.jpg"));
        getContentPane().add(Mapa);
              
        setVisible(true);
        
        
        
	}

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
	
	// Método para mostrar una ventana con el nombre de la ciudad y el nivel de infección
	private void mostrarVentana(Ciudad ciudad, Ciudad [] ciudades, int infectadasRonda, JButton boton, Vacunas [] vacuna, int enfActDerr, int brotDerr) {
	    
		JFrame ventana = new JFrame();
	    JLabel etiquetaCiudad = new JLabel("planeta : " + ciudad.getnombre().toLowerCase());
	    JLabel etiquetaNivel = new JLabel("nivel de conquista : " + ciudad.getinfeccion());
	    JButton reconquistarButton = new JButton("reconquistar");
	    reconquistarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Boolean vacAct = false;
            	int j = 0;
            	
            	if(ciudad.getinfeccion() != 0 && acciones != 0) { 		
            		
            		for(int i = 0; i < vacuna.length; i++) {
            			if(ciudad.getenfermedad() == vacuna[i].getnombre()) {
            				vacAct = true;
            				j = i;
            			}
            				
            		}

            		if(vacAct == true && vacuna[j].getporcentaje() >= 100) {
            			enfAct = enfAct - ciudad.getinfeccion();
            			ciudad.setinfeccion(0);
            			Conquistas.setText("conquistas: " + enfAct + "/" + enfActDerr);
                		boton.setText(ciudad.getnombre().toLowerCase() + "(" + ciudad.getinfeccion() + ")");
                		etiquetaNivel.setText("nivel de conquista : " + ciudad.getinfeccion());
                		JOptionPane.showMessageDialog(null, "Vacunada galáctica");
            		}else {
            			ciudad.setinfeccion(ciudad.getinfeccion() - 1);
            			enfAct--;
            			Conquistas.setText("conquistas: " + enfAct + "/" + enfActDerr);
                		boton.setText(ciudad.getnombre().toLowerCase() + "(" + ciudad.getinfeccion() + ")");
                		etiquetaNivel.setText("nivel de conquista : " + ciudad.getinfeccion());
            		}
            		
            		
            		acciones--;
            		modifcarAcciones();
            		if(acciones == 0) {
            			ronda++;
                		acciones = 4;
                		modifcarAcciones();
                		pasarRonda();
                		infectarCiudades(ciudades, ciudad, infectadasRonda, enfActDerr, brotDerr);
            		}
            	}else if(ciudad.getinfeccion() == 0 && acciones != 0){
            		JOptionPane.showMessageDialog(null, "Este planeta no esta conquistado");
            	}
                
            }
        });

	    Font customFont = loadFont("Starjedi.ttf");
    	Font buttonFont = customFont.deriveFont(Font.PLAIN, 14);
	    
	    etiquetaCiudad.setFont(buttonFont);
	    etiquetaNivel.setFont(buttonFont);
	    reconquistarButton.setFont(buttonFont);
	    
	    ventana.getContentPane().setBackground(Color.BLACK);

	    etiquetaCiudad.setForeground(Color.WHITE);
	    etiquetaNivel.setForeground(Color.WHITE);
	    reconquistarButton.setForeground(Color.WHITE);

	    ventana.getContentPane().setLayout(new GridBagLayout());

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    ventana.getContentPane().add(etiquetaCiudad, gbc);

	    gbc.gridy = 1;
	    ventana.getContentPane().add(etiquetaNivel, gbc);

	    gbc.gridy = 2;
	    ventana.getContentPane().add(reconquistarButton, gbc);

	    ventana.setSize(400, 250);
	    ventana.setLocationRelativeTo(null);
	    ventana.setVisible(true);

	    // Aplicar propiedades al botón "Reconquistar"
	    setButtonProperties(reconquistarButton);
	}
	
	private void infectarCiudades(Ciudad [] ciudades, Ciudad ciudad, int infectadasRonda, int enfActDerr, int brotDerr) {
		int i = 0;
		int j = 0;
		
		i = 0;
		while(j != infectadasRonda && enfAct != enfActDerr) {
			if((int)(Math.random()*20) == 2) {
				
				if(ciudades[i].getinfeccion() >= 0 && ciudades[i].getinfeccion() < 3 ) {
					ciudades[i].setinfeccion(ciudades[i].getinfeccion() + 1);
					//System.out.println("Ciudad infectada: "+ciudades[i].getnombre());
					j++;
					enfAct++;
				}if(ciudades[i].getinfeccion() == 3) {
					brotarCiudades(ciudades, ciudades[i], infectadasRonda, enfActDerr, brotDerr);
				}
			}
			
			if(i == 47) {
				i = 0;
			}else {
				i++;
			}
			
		}
		Conquistas.setText("conquistas: " + enfAct + "/" + enfActDerr);
		
		if(enfAct == enfActDerr) {
			JOptionPane.showMessageDialog(null, "Has perdido");
			ventanaSecundaria.dispose();
			dispose();
		}
	}

	private void mirarInfectadas(Ciudad[] ciudades) {
		enfAct = 0;
		
		for(int i = 0; i < 48; i++) {
			if(ciudades[i].getinfeccion() != 0) {
				enfAct++;
			}
		}
	}
	
	private void brotarCiudades(Ciudad [] ciudades, Ciudad ciudad, int infectadasRonda, int enfActDerr, int brotDerr) {
		numBrot++;
		Brotes.setText(("brotes: " + numBrot + "/" + brotDerr));
		int i = 0;
		int j = 0;
		int z = 0;
		ArrayList<String> colindantes = new ArrayList<String>();
		for(i = 0; i < ciudad.ciudadesColindantes.length; i++) {
			colindantes.add(ciudad.ciudadesColindantes[i]);
		}
		
		while(colindantes.size() != 0 && enfAct != enfActDerr && numBrot!= brotDerr) {
			for(i = 0; i < ciudades.length; i++) {
				for(j = 0; j < colindantes.size(); j++) {
					if(ciudades[i].getnombre() == colindantes.get(j)) {
						if(ciudades[i].getinfeccion() < 3) {
							ciudades[i].setinfeccion(ciudades[i].getinfeccion() + 1);
							enfAct++;
							Conquistas.setText("conquistas: " + enfAct + "/" + enfActDerr);
						}else if(ciudades[i].getinfeccion() == 3) {
							numBrot++;
							Brotes.setText(("brotes: " + numBrot + "/" + brotDerr));
							for(z = 0; z < ciudades[i].ciudadesColindantes.length; z++) {
								colindantes.add(ciudades[i].ciudadesColindantes[z]);
							}
						}
					}
				}
			}
		}
		
		if(enfAct == enfActDerr || numBrot == brotDerr) {
			JOptionPane.showMessageDialog(null, "Has perdido");
			ventanaSecundaria.dispose();
			dispose();
		}
		
	}
	
	private void modifcarAcciones() {
		Acciones.setText("acciones: " + acciones);
	}
	
	private void pasarRonda() {
		Rondas.setText("ronda " + ronda);
	}

	private void investigarVacuna(Vacunas vacuna, JProgressBar prog, int porcVac, Ciudad[] ciudades, int infectadasRonda, JButton [] planetas, Vacunas[] vacunas, int enfActDerr, int brotDerr) {
		if(vacuna.getporcentaje() >= 100) {
			JOptionPane.showMessageDialog(null, "Ya está investigado");
		}else if(acciones !=4){
			JOptionPane.showMessageDialog(null, "No puedes investigar, no tienes acciones suficientes");
		}else {
			vacuna.setporcentaje(vacuna.getporcentaje() + porcVac);
			prog.setValue(vacuna.getporcentaje());
			if(vacunas[0].getporcentaje() == 100 && vacunas[1].getporcentaje() == 100 && vacunas[2].getporcentaje() == 100 && vacunas[3].getporcentaje() == 100) {
            	JOptionPane.showMessageDialog(null, "¡Has ganado!");
            	menu men = new menu();
            	dispose();
            }else {
            	ronda++;
    			modifcarAcciones();
        		pasarRonda();
        		//Implementar infectar ciudades sin llamar a la función
        		int i = 0;
        		int j = 0;
        		
        		i = 0;
        		while(j != infectadasRonda && enfAct != enfActDerr) {
        			if((int)(Math.random()*20) == 2) {
        				
        				if(ciudades[i].getinfeccion() >= 0 && ciudades[i].getinfeccion() < 3 ) {
        					ciudades[i].setinfeccion(ciudades[i].getinfeccion() + 1);
        					//System.out.println("Ciudad infectada: "+ciudades[i].getnombre());
        					j++;
        					enfAct++;
        				}if(ciudades[i].getinfeccion() == 3) {
        					brotarCiudades(ciudades, ciudades[i], infectadasRonda, enfActDerr, brotDerr);
        				}
        			}
        			
        			if(i == 47) {
        				i = 0;
        			}else {
        				i++;
        			}
        			
        		}
        		Conquistas.setText("conquistas: " + enfAct + "/" + enfActDerr);
        		
        		if(enfAct == enfActDerr) {
        			JOptionPane.showMessageDialog(null, "Has perdido");
        			ventanaSecundaria.dispose();
        			dispose();
        		}
        		
        		for( i = 0; i < ciudades.length; i++) {
        			planetas[i].setText(ciudades[i].getnombre().toLowerCase() + "(" + ciudades[i].getinfeccion() + ")");
        		}
            }
			
    		
		}
	}
	
	private void mirarVictoria(Vacunas[] vacuna, JFrame ventanaSecundaria) {
		if(vacuna[0].getporcentaje() == 100 && vacuna[1].getporcentaje() == 100 && vacuna[2].getporcentaje() == 100 && vacuna[3].getporcentaje() == 100) {
        	ventanaSecundaria.dispose();
        }
	}
}