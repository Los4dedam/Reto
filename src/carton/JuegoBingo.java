package carton; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class JuegoBingo extends JFrame {

    private static final long serialVersionUID = 1L;
    

    private static final String RUTA_PREGUNTAS = "C:\\Users\\DAM1\\Desktop\\preguntas.txt";

    private Carton panelCarton; 

    public JuegoBingo() {

        setTitle("Cartón de Bingo (Jugador)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));


        panelCarton = new Carton(); 
        add(panelCarton, BorderLayout.CENTER);


        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelControles.setBackground(new Color(220, 220, 220));

        JButton btnNuevo = new JButton("Nuevo Cartón");
        JButton btnLimpiar = new JButton("Limpiar Marcas");
        JButton btnVerificarLinea = new JButton("Verificar Línea");
        JButton btnVerificarBingo = new JButton("¡BINGO!");


        btnNuevo.addActionListener(e -> panelCarton.generarYMostrarCarton());
        btnLimpiar.addActionListener(e -> panelCarton.limpiarMarcas());

        btnVerificarLinea.addActionListener(e -> {
            if (panelCarton.verificarLineaCompleta()) { 
                JOptionPane.showMessageDialog(this, "¡LÍNEA!", "Verificación Correcta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Verificación de línea fallida");
            }
        });

        btnVerificarBingo.addActionListener(e -> {
            if (panelCarton.verificarBingoCompleto()) { 

                JOptionPane.showMessageDialog(this, "¡BINGO! ¡Cartón Lleno!", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                

                lanzarPreguntaAleatoria(); 
                
            } else {
                System.out.println("No tienes linea ni bingo.");
            }
        });


        panelControles.add(btnNuevo);
        panelControles.add(btnLimpiar);
        panelControles.add(btnVerificarLinea);
        panelControles.add(btnVerificarBingo);

        add(panelControles, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(null);
    }


    private void lanzarPreguntaAleatoria() {
        List<String[]> bancoDePreguntas = new ArrayList<>();
        File archivo = new File(RUTA_PREGUNTAS);

        // 1. Leer el archivo y cargar las preguntas
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();

                String[] partes = linea.split(";"); 
                if (partes.length == 5) { 
                    bancoDePreguntas.add(partes);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, 
                "No se encontró el archivo de preguntas en:" + RUTA_PREGUNTAS, 
                "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            return; 
        }


        if (bancoDePreguntas.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El archivo de preguntas está vacío o tiene un formato incorrecto.", 
                "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        Random rand = new Random();
        String[] preguntaElegida = bancoDePreguntas.get(rand.nextInt(bancoDePreguntas.size()));
        
        String pregunta = preguntaElegida[0];
        String respuestaCorrecta = preguntaElegida[1];


        List<String> opciones = new ArrayList<>();
        opciones.add(preguntaElegida[1]); // Correcta
        opciones.add(preguntaElegida[2]); // Inc1
        opciones.add(preguntaElegida[3]); // Inc2
        opciones.add(preguntaElegida[4]); // Inc3
        
        Collections.shuffle(opciones);


        int seleccion = JOptionPane.showOptionDialog(
            this, 
            pregunta, 
            "¡Pregunta Final!", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opciones.toArray(),
            opciones.get(0) 
        );

       
        if (seleccion == -1) {
           
            JOptionPane.showMessageDialog(this, "Has cancelado la pregunta.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        
        } else {
            String respuestaElegida = opciones.get(seleccion);
            if (respuestaElegida.equals(respuestaCorrecta)) {
                
                JOptionPane.showMessageDialog(this, "¡RESPUESTA CORRECTA! ¡Has ganado el bingo!", "¡Genial!", JOptionPane.INFORMATION_MESSAGE);
            
            } else {
                
                JOptionPane.showMessageDialog(this, 
                    "¡Respuesta incorrecta! Perdiste el bingo. La respuesta correcta era:" + respuestaCorrecta, 
                    "Ooh...", 
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JuegoBingo().setVisible(true);
        });
    }
}