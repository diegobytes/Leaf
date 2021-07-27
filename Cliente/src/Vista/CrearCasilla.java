package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.servicios.CasillasBeanRemote;

import entidades.Casilla;
import entidades.Casilla.TipoDeValor;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCasilla {

	JFrame frmCasillas;
	private JTextField textParametro;
	private JTextField textUnidadMedida;
	private JTextArea textDescripcion;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCasilla window = new CrearCasilla();
					window.frmCasillas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearCasilla() throws NamingException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException{
		CasillasBeanRemote casillasBean = (CasillasBeanRemote)
				InitialContext.doLookup("PDT/CasillasBean!com.servicios.CasillasBeanRemote");
		frmCasillas = new JFrame();
		frmCasillas.setResizable(false);
		frmCasillas.setTitle("Casillas");
		frmCasillas.setBounds(100, 100, 346, 380);
		frmCasillas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCasillas.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Par\u00E1metro:");
		lblNewLabel.setBounds(49, 11, 94, 14);
		frmCasillas.getContentPane().add(lblNewLabel);
		
		textParametro = new JTextField();
		textParametro.setBounds(132, 8, 147, 20);
		frmCasillas.getContentPane().add(textParametro);
		textParametro.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Unidad de medida:");
		lblNewLabel_1.setBounds(10, 46, 110, 14);
		frmCasillas.getContentPane().add(lblNewLabel_1);
		
		textUnidadMedida = new JTextField();
		textUnidadMedida.setBounds(133, 43, 146, 20);
		frmCasillas.getContentPane().add(textUnidadMedida);
		textUnidadMedida.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("  Descripci\u00F3n:");
		lblNewLabel_2.setBounds(37, 90, 94, 14);
		frmCasillas.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(132, 84, 147, 74);
		frmCasillas.getContentPane().add(scrollPane);
		
		textDescripcion = new JTextArea();
		scrollPane.setViewportView(textDescripcion);
		
		JLabel lblNewLabel_3 = new JLabel(" Tipo de valor:");
		lblNewLabel_3.setBounds(37, 173, 88, 14);
		frmCasillas.getContentPane().add(lblNewLabel_3);
		
		comboBox = new JComboBox();
		comboBox.setBounds(132, 169, 87, 22);
		comboBox.addItem("STRING");
		comboBox.addItem("INT");
		comboBox.addItem("DOUBLE");
		comboBox.addItem("BOOLEAN");
		frmCasillas.getContentPane().add(comboBox);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textParametro.getText().isEmpty() || textParametro.getText().length() > 40) {
					JOptionPane.showMessageDialog(frmCasillas, "El campo \"Parámetro\" no puede estar vacío (Como máximo 40 caracteres)");
				}else if(textUnidadMedida.getText().isEmpty() || textUnidadMedida.getText().length() > 40) {
					JOptionPane.showMessageDialog(frmCasillas, "El campo \"Unidad de medida\" no puede estar vacío (Como máximo 40 caracteres)");
				}else {
					if(CrearFormulario.modificarCasilla==false) {
						Casilla c = new Casilla();
						if(!textDescripcion.getText().isEmpty()) {
							c.setDescripcion(textDescripcion.getText());
						}
						c.setParametro(textParametro.getText());
						c.setTipoDeValor(TipoDeValor.valueOf(comboBox.getSelectedItem().toString()));
						c.setUnidadDeMedida(textUnidadMedida.getText());
						try {
							casillasBean.crear(c);
							JOptionPane.showMessageDialog(frmCasillas, "Casilla creada correctamente");
							
						}catch(Exception ex) {
							ex.printStackTrace();
							
						}
						
					}else if(CrearFormulario.modificarCasilla==true) {
						Casilla c = casillasBean.obtenerPorId(CrearFormulario.buscarCasilla.getId());
						if(!textDescripcion.getText().isEmpty()) {
							c.setDescripcion(textDescripcion.getText());
						}
						c.setParametro(textParametro.getText());
						c.setTipoDeValor(TipoDeValor.valueOf(comboBox.getSelectedItem().toString()));
						c.setUnidadDeMedida(textUnidadMedida.getText());
						try {
							String[] opciones = new String[2];
							opciones[0] = new String("Si");
							opciones[1] = new String("No");
							if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas modificar la casilla? ", "Modificar casilla", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
								casillasBean.actualizar(c);
								JOptionPane.showMessageDialog(frmCasillas, "Casilla modificada correctamente");
							}
							
							
						}catch(Exception ex) {
							ex.printStackTrace();
							
						}
						
					}
					
					
					
					
				}
				
				
				
				
				
			}
		});
		btnGuardar.setBounds(130, 237, 89, 31);
		frmCasillas.getContentPane().add(btnGuardar);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearFormulario window = new CrearFormulario();
					window.frmCrearFormulario.setVisible(true);
					CrearFormulario.modificarCasilla=false;
					frmCasillas.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBounds(10, 307, 79, 23);
		frmCasillas.getContentPane().add(btnNewButton);
		checkModificarCasilla();
	}
	
	public void checkModificarCasilla() {
		if(CrearFormulario.modificarCasilla==true) {
			textParametro.setText(CrearFormulario.buscarCasilla.getParametro());
			textUnidadMedida.setText(CrearFormulario.buscarCasilla.getUnidadDeMedida());
			if(CrearFormulario.buscarCasilla.getDescripcion() != null && !CrearFormulario.buscarCasilla.getDescripcion().isEmpty() ) {
				textDescripcion.setText(CrearFormulario.buscarCasilla.getDescripcion());
				
				
			}
			comboBox.setSelectedItem(CrearFormulario.buscarCasilla.getTipoDeValor().toString());
			
			
		}
	}
}
