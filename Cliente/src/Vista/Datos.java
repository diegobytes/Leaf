package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.awt.Font;

public class Datos {
	private JTextPane textDatos;
	JFrame frmDatosDeActividad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Datos window = new Datos();
					window.frmDatosDeActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Datos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatosDeActividad = new JFrame();
		frmDatosDeActividad.setTitle("Datos de actividad");
		frmDatosDeActividad.setBounds(100, 100, 448, 410);
		frmDatosDeActividad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatosDeActividad.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 432, 371);
		frmDatosDeActividad.getContentPane().add(scrollPane);
		
		textDatos = new JTextPane();
		textDatos.setEditable(false);
		textDatos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		scrollPane.setViewportView(textDatos);
		cargarDatos();
		
		
		
		
		
	}
	
	public void cargarDatos() {
		StyledDocument documento = textDatos.getStyledDocument();
		try {
			documento.insertString(0, "Estación de muestreo: " + AnalisisDeDatos.buscarActividad.getEstacionMuestreo() +"\n", null);
			documento.insertString(0, "Método de muestreo: " + AnalisisDeDatos.buscarActividad.getMetodoMuestreo() +"\n", null);
			documento.insertString(0, "Departamento: " + AnalisisDeDatos.buscarActividad.getDepartamento() +"\n", null);			
			AnalisisDeDatos.buscarActividad.getCasillaVariable1().forEach((key,value) -> {
				try {
					documento.insertString(0,key + ": " +value + "\n",null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			AnalisisDeDatos.buscarActividad.getCasillaVariable2().forEach((key2,value2) -> {
				try {
					documento.insertString(0,key2 + ": " +value2 + "\n",null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			
			AnalisisDeDatos.buscarActividad.getCasillaVariable3().forEach((key3,value3) -> {
				try {
					documento.insertString(0,key3 + ": " +value3 + "\n",null);
				} catch (BadLocationException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			
				
			
			
			
			});
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
