package carton;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet; 
import java.util.List;
import java.util.Random;
import java.util.Scanner; 
import java.util.Set; 
import java.io.File; 
import java.io.FileNotFoundException; 

public class Carton extends JPanel {

    private static final long serialVersionUID = 1L;
    

    private static final String ARCHIVO_NUMEROS = "C:\\Users\\DAM1\\Desktop\\numeros_cantados.txt";
    
    private JButton[][] botones = new JButton[3][9];
    private int[][] numerosDelCarton = new int[3][9];


    private final Color COLOR_MARCADO = new Color(255, 193, 7);
    private final Color COLOR_NORMAL = Color.WHITE;
    private final Color COLOR_BLANCO = new Color(230, 230, 230);
    private final Color COLOR_ERROR = Color.RED; // --- NUEVO ---
    private final Font FUENTE_NUMERO = new Font("Arial", Font.BOLD, 20);


    public Carton() {
        setLayout(new GridLayout(3, 9, 3, 3)); 
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                JButton btn = new JButton();
                btn.setFont(FUENTE_NUMERO);
                btn.setFocusPainted(false);
                

                btn.addActionListener(e -> {
                    JButton botonPulsado = (JButton) e.getSource();
                    if (botonPulsado.isEnabled()) {
                        Color fondoActual = botonPulsado.getBackground();
                        

                        if (fondoActual.equals(COLOR_MARCADO) || fondoActual.equals(COLOR_ERROR)) {
                            botonPulsado.setBackground(COLOR_NORMAL);
                        } else {
                      
                            botonPulsado.setBackground(COLOR_MARCADO);
                        }
                    }
                });
                
                botones[i][j] = btn;
                add(btn);
            }
        }


        generarYMostrarCarton();
        

    }


    public void generarYMostrarCarton() {
        this.numerosDelCarton = generarLogicaCarton();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int numero = numerosDelCarton[i][j];
                JButton btn = botones[i][j];

                if (numero == 0) {
                    btn.setText("");
                    btn.setBackground(COLOR_BLANCO);
                    btn.setEnabled(false);
                } else {
                    btn.setText(String.valueOf(numero));
                    btn.setBackground(COLOR_NORMAL);
                    btn.setEnabled(true);
                }
            }
        }
    }
    

    private Set<Integer> getNumerosCantados() {
        Set<Integer> numeros = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(ARCHIVO_NUMEROS))) {
            while (scanner.hasNextLine()) {
                try {
                    numeros.add(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException e) {

                }
            }
        } catch (FileNotFoundException e) {

            System.err.println("Archivo de números no encontrado. Asumiendo 0 números cantados.");
        }
        return numeros;
    }

    private boolean chequearValidezMarcas(Set<Integer> numerosCantados) {
        boolean todoCorrecto = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                boolean usuarioLoMarco = botones[i][j].getBackground().equals(COLOR_MARCADO);
                int numeroDelCarton = numerosDelCarton[i][j];
                
                if (usuarioLoMarco && !numerosCantados.contains(numeroDelCarton)) {

                    botones[i][j].setBackground(COLOR_ERROR); 
                    todoCorrecto = false;
                }
            }
        }
        return todoCorrecto;
    }

    public void limpiarMarcas() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (botones[i][j].isEnabled()) {
                    botones[i][j].setBackground(COLOR_NORMAL);
                }
            }
        }
    }

    public boolean verificarLineaCompleta() {

        Set<Integer> numerosCantados = getNumerosCantados();

        if (!chequearValidezMarcas(numerosCantados)) {
            JOptionPane.showMessageDialog(this, "¡Error! Has marcado números (en rojo) que aún no han salido.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        for (int i = 0; i < 3; i++) { 
            int marcadosEnFila = 0;
            for (int j = 0; j < 9; j++) {
            
                if (numerosDelCarton[i][j] != 0 && botones[i][j].getBackground().equals(COLOR_MARCADO)) {
                    marcadosEnFila++;
                }
            }
           
            if (marcadosEnFila == 5) {
                return true; // ¡Línea!
            }
        }
        return false; 
    }


    public boolean verificarBingoCompleto() {

        Set<Integer> numerosCantados = getNumerosCantados();
        

        if (!chequearValidezMarcas(numerosCantados)) {
            JOptionPane.showMessageDialog(this, "¡Error! Has marcado números (en rojo) que aún no han salido.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        int totalMarcados = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (numerosDelCarton[i][j] != 0 && botones[i][j].getBackground().equals(COLOR_MARCADO)) {
                    totalMarcados++;
                }
            }
        }

        return totalMarcados == 15;
    }



    
    @SuppressWarnings("unused")
	private int[][] generarLogicaCarton() {
        int[][] carton = new int[3][9];
        Random rand = new Random();
        
        @SuppressWarnings("unchecked")
        List<Integer>[] numerosPorColumna = (List<Integer>[]) new List[9];
        
        numerosPorColumna[0] = generarRango(1, 9);
        for(int j=1; j<8; j++) {
            numerosPorColumna[j] = generarRango(j*10, j*10 + 9);
        }
        numerosPorColumna[8] = generarRango(80, 90);

        for(List<Integer> lista : numerosPorColumna) {
            Collections.shuffle(lista);
        }

        List<Integer> indicesColumna = new ArrayList<>();
        for(int j=0; j<9; j++) indicesColumna.add(j);

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(indicesColumna);
            List<Integer> colsParaEstaFila = indicesColumna.subList(0, 5);
            
            for (int col : colsParaEstaFila) {
                if (!numerosPorColumna[col].isEmpty()) {
                    carton[i][col] = numerosPorColumna[col].remove(0);
                }
            }
        }
        
        for(int j=0; j<9; j++) {
            List<Integer> numsEnCol = new ArrayList<>();
            if(carton[0][j] != 0) numsEnCol.add(carton[0][j]);
            if(carton[1][j] != 0) numsEnCol.add(carton[1][j]);
            if(carton[2][j] != 0) numsEnCol.add(carton[2][j]);
            
            Collections.sort(numsEnCol);
            
            int idx = 0;
            if(carton[0][j] != 0) carton[0][j] = numsEnCol.get(idx++);
            if(carton[1][j] != 0) carton[1][j] = numsEnCol.get(idx++);
            if(carton[2][j] != 0) carton[2][j] = numsEnCol.get(idx++);
        }
        return carton;
    }

    private List<Integer> generarRango(int min, int max) {
        List<Integer> lista = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            lista.add(i);
        }
        return lista;
    }
}