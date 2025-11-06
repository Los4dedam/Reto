import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame; // <-- Importar JFrame
import java.awt.EventQueue;

// No necesitas FlowLayout si no lo usas
// import java.awt.FlowLayout; 

public class bingo extends JPanel {

	private static final long serialVersionUID = 1L;

	// TU MÉTODO MAIN CORREGIDO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 1. Crear la ventana principal
					JFrame ventana = new JFrame("Ventana de Bingo");
					
					// 2. Crear tu panel (como lo tenías)
					bingo panelDeBingo = new bingo();
					
					// 3. Añadir tu panel a la ventana
					ventana.setContentPane(panelDeBingo);
					
					// 4. Configurar la ventana
					ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que el programa se cierre al cerrar la ventana
					ventana.pack(); // Ajusta el tamaño de la ventana al contenido
					
					// 5. Hacer visible la VENTANA (no el panel)
					ventana.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the panel.
	 */
	public bingo() {
		// Tu GridLayout de 9 columnas.
		// Como añadiste 27 botones, esto creará una cuadrícula de 3 filas x 9 columnas.
		setLayout(new GridLayout(0, 9, 0, 0)); 
		
		JButton btn1 = new JButton("");
		add(btn1);
		
		JButton bnt2 = new JButton(""); // <-- Pequeña errata aquí, "bnt2" en lugar de "btn2"
		add(bnt2);
		
		JButton btn3 = new JButton("");
		add(btn3);
		
		JButton btn4 = new JButton("");
		add(btn4);
		
		JButton btn5 = new JButton("");
		add(btn5);
		
		JButton btn6 = new JButton("");
		add(btn6);
		
		JButton btn7 = new JButton("");
		add(btn7);
		
		JButton btn8 = new JButton("");
		add(btn8);
		
		JButton btn9 = new JButton("");
		add(btn9);
		
		JButton btn10 = new JButton("");
		add(btn10);
		
		JButton btn11 = new JButton("");
		add(btn11);
		
		JButton btn12 = new JButton("");
		add(btn12);
		
		JButton btn13 = new JButton("");
		add(btn13);
		
		JButton btn14 = new JButton("");
		add(btn14);
		
		JButton btn15 = new JButton("");
		add(btn15);
		
		JButton btn16 = new JButton("");
		add(btn16);
		
		JButton btn17 = new JButton("");
		add(btn17);
		
		JButton btn18 = new JButton("");
		add(btn18);
		
		JButton btn19 = new JButton("");
		add(btn19);
		
		JButton btn20 = new JButton("");
		add(btn20);
		
		JButton btn21 = new JButton("");
		add(btn21);
		
		JButton btn = new JButton(""); // <-- Variable con nombre inconsistente
		add(btn);
		
		JButton btn22 = new JButton("");
		add(btn22);
		
		JButton btn23 = new JButton("");
		add(btn23);
		
		JButton btn24 = new JButton("");
		add(btn24);
		
		JButton btn25 = new JButton("");
		add(btn25);
		
		JButton btn26 = new JButton("");
		add(btn26);

	}
}