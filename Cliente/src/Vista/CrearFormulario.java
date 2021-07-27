package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.servicios.CasillasBeanRemote;
import com.servicios.FormulariosBeanRemote;
import entidades.Casilla;
import entidades.Formulario;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;

public class CrearFormulario {

	JFrame frmCrearFormulario;
	private JTextField textNombre;
	private JTable table;
	public static Formulario buscarForm = new Formulario();
	public static Casilla buscarCasilla = new Casilla();
	public static boolean modificarCasilla = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearFormulario window = new CrearFormulario();
					
					window.frmCrearFormulario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearFormulario() throws NamingException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException{
		FormulariosBeanRemote formulariosBean = (FormulariosBeanRemote)
				InitialContext.doLookup("PDT/FormulariosBean!com.servicios.FormulariosBeanRemote");
		CasillasBeanRemote casillasBean = (CasillasBeanRemote)
				InitialContext.doLookup("PDT/CasillasBean!com.servicios.CasillasBeanRemote");
		
		
		frmCrearFormulario = new JFrame();
		frmCrearFormulario.setResizable(false);
		frmCrearFormulario.setTitle("Crear formulario");
		frmCrearFormulario.setBounds(100, 100, 540, 677);
		frmCrearFormulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrearFormulario.getContentPane().setLayout(null);
		frmCrearFormulario.setLocationRelativeTo(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(80, 11, 187, 20);
		frmCrearFormulario.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 14, 60, 14);
		frmCrearFormulario.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Resumen:");
		lblNewLabel_1.setBounds(10, 131, 60, 14);
		frmCrearFormulario.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 377, 291);
		frmCrearFormulario.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(81, 67, 186, 152);
		frmCrearFormulario.getContentPane().add(scrollPane_1);
		
		JTextArea textResumen = new JTextArea();
		scrollPane_1.setViewportView(textResumen);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] columnas = {
				"Parámetro", "Descripción", "Unidad de medida", "ID", ""
			};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		
		table.setModel(modelo);
		table.removeColumn(table.getColumnModel().getColumn(3));
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		
		
		JButton btnListarCasillas = new JButton("Listar");
		btnListarCasillas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				
				try {
					List<Object[]> datosFormularios = listaCasillas();
					for(Object[] dato : datosFormularios) {
						modelo.addRow(dato);
						
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frmCrearFormulario, "ERROR AL LISTAR FORMULARIOS");
				}
				
				
				
				
				
			}
		});
		btnListarCasillas.setBounds(397, 362, 89, 31);
		frmCrearFormulario.getContentPane().add(btnListarCasillas);
		
		JLabel lblNewLabel_2 = new JLabel("Elige las casillas para el formulario (m\u00E1ximo 3)");
		lblNewLabel_2.setBounds(65, 239, 282, 31);
		frmCrearFormulario.getContentPane().add(lblNewLabel_2);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionada = table.getSelectedRow();
				if(seleccionada>-1) {
					try {
						Long id = Long.parseLong(table.getModel().getValueAt(seleccionada, 3).toString());
						buscarCasilla = casillasBean.obtenerPorId(id);
						modificarCasilla = true;
						CrearCasilla window = new CrearCasilla();
						window.frmCasillas.setVisible(true);
						frmCrearFormulario.dispose();
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				}
				
				
				
				
				
				
			}
		});
		btnModificar.setBounds(397, 320, 89, 31);
		frmCrearFormulario.getContentPane().add(btnModificar);
		
		JButton btnCrearCasilla = new JButton("<html>Crear<br />casilla</html>");
		btnCrearCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearCasilla window = new CrearCasilla();
					window.frmCasillas.setVisible(true);
					frmCrearFormulario.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
				
				
			}
		});
		btnCrearCasilla.setBounds(397, 31, 89, 64);
		frmCrearFormulario.getContentPane().add(btnCrearCasilla);
		agregarCheckbox(3, table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GestionDeFormularios window = new GestionDeFormularios();
					window.frmGestinDeFormularios.setVisible(true);
					GestionDeFormularios.modificar=false;
					frmCrearFormulario.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
			}
		});
		btnVolver.setBounds(10, 601, 89, 26);
		frmCrearFormulario.getContentPane().add(btnVolver);
		
		JButton btnRegistrar = new JButton("Guardar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().isEmpty() || textNombre.getText().length() > 40) {
					JOptionPane.showMessageDialog(frmCrearFormulario, "El campo \"Nombre\" no puede estar vacío (Como máximo 40 caracteres)");
				}else if(textResumen.getText().length() > 250) {
					JOptionPane.showMessageDialog(frmCrearFormulario, "El campo \"Resumen\" no puede superar los 250 caracteres");
					
				}else {
					
					ArrayList<Long> casillas = new ArrayList<Long>();
					for (int i = 0; i<table.getRowCount(); i++) {
						if(casillaSeleccionada(i,3)) {
							casillas.add(Long.parseLong(table.getModel().getValueAt(i, 3).toString()));
							
						}
					}
					if(casillas.size()>3) {
						JOptionPane.showMessageDialog(frmCrearFormulario, "No se puede crear un formulario con más de 3 casillas seleccionadas");
						casillas.clear();
					}
					else {
						try {
							
							if(GestionDeFormularios.modificar==false && !formulariosBean.formularioExiste(textNombre.getText()).getNombre().isEmpty()) {
								JOptionPane.showMessageDialog(frmCrearFormulario, "El nombre de formulario ya está en uso");
								casillas.clear();
								
							}
							else if(GestionDeFormularios.modificar==true && (!GestionDeFormularios.formulario.getNombre().contentEquals(textNombre.getText())) && !formulariosBean.formularioExiste(textNombre.getText()).getNombre().isEmpty()) {
								JOptionPane.showMessageDialog(frmCrearFormulario, "El nombre de formulario ya está en uso");
								casillas.clear();
								
							}else {
								throw new EJBException();
							}
								
								
						}catch(EJBException ex) {
							

							try {
								Formulario f = new Formulario();
								f.setCreadoPor(Login.usuarioLogeado.getNombreUsuario());
								f.setNombre(textNombre.getText());
								if(!textResumen.getText().isEmpty()) {
									f.setResumen(textResumen.getText());
									
								}
								if (GestionDeFormularios.modificar == false) {
									formulariosBean.crear(f);
									buscarForm = formulariosBean.formularioExiste(textNombre.getText());
									
								}else if(GestionDeFormularios.modificar == true) {
									buscarForm = formulariosBean.formularioExiste(GestionDeFormularios.formulario.getNombre());
									Set<Casilla> quitar = buscarForm.getCasillas();
									buscarForm.setNombre(textNombre.getText());
								     
									if(!textResumen.getText().isEmpty()) {
										buscarForm.setResumen(textResumen.getText());
										
									}
									
									buscarForm.quitarTodasCasillas(quitar);
									
									
								}
								
								
								
								
								Casilla c = new Casilla();
								
								for(Long idCasilla : casillas) {
									c = casillasBean.obtenerPorId(idCasilla);
									buscarForm.agregarCasilla(c);
								}
								
								if(GestionDeFormularios.modificar == true) {
									String[] opciones = new String[2];
									opciones[0] = new String("Si");
									opciones[1] = new String("No");
									if(JOptionPane.showOptionDialog(null, "Estas seguro que deseas modificar el formulario?", "Modificar Formulario", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
										formulariosBean.actualizar(buscarForm);
										GestionDeFormularios.formulario.setNombre(textNombre.getText());
										JOptionPane.showMessageDialog(frmCrearFormulario, "Formulario actualizado correctamente");
									}
									
								}
								
								
								casillas.clear();
								
								if(GestionDeFormularios.modificar == false) {
									formulariosBean.actualizar(buscarForm);
									JOptionPane.showMessageDialog(frmCrearFormulario, "Formulario creado correctamente");
									
								}else if(GestionDeFormularios.modificar == true) {
									
									
									
								}
								
								
								
							}catch(EJBException exc) {
								
							}
							
							
							
						}
						
						
						
						}
						
					}
				}
		});
		btnRegistrar.setBounds(172, 601, 89, 25);
		frmCrearFormulario.getContentPane().add(btnRegistrar);
		checkModificar(modelo,textResumen);
		if(GestionDeFormularios.modificar==true) {
			btnCrearCasilla.setVisible(false);
			
			
		}
		
		
	}
	
	public void checkModificar(DefaultTableModel modelo, JTextArea textResumen) {
		if(GestionDeFormularios.modificar==true) {
			modelo.setRowCount(0);
			
			try {
				textNombre.setText(GestionDeFormularios.formulario.getNombre());
				textResumen.setText(GestionDeFormularios.formulario.getResumen());
				
				ArrayList<Long> listaIDS = new ArrayList<Long>();
				Set<Casilla> casillasMarcar = GestionDeFormularios.formulario.getCasillas();
				
				List<Object[]> datosFormularios = listaCasillas();
				for(Object[] dato : datosFormularios) {
					modelo.addRow(dato);
				
				
				}
				
				if(!casillasMarcar.isEmpty()) {
					for(Casilla cas : casillasMarcar) {
						listaIDS.add(cas.getId());
						
					}
					
					
					for (int i = 0; i<table.getRowCount(); i++) {
						if(listaIDS.contains(Long.parseLong(table.getModel().getValueAt(i, 3).toString()))) {
							table.setValueAt(true, i, 3);
							
							
						}
					}
					
					listaIDS.clear();
					casillasMarcar.clear();
					
				}
				
				
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frmCrearFormulario, "ERROR AL LISTAR FORMULARIOS");
			}
			
		}
	}
	
	
	public void agregarCheckbox(int columna, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(columna);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		
	}
	
	public boolean casillaSeleccionada(int row, int columna) {
		if(table.getValueAt(row, columna) == null) {
			return false;
		}else if((boolean)table.getValueAt(row, columna) == true) {
			return true;
		}else {
			return false;
		}
		/*System.out.println(table.getValueAt(row, columna));
		return table.getValueAt(row, columna) != null;*/
		
	}
	
	
	public List<Object[]> listaCasillas() throws NamingException{
		CasillasBeanRemote casillasBean = (CasillasBeanRemote)
				InitialContext.doLookup("PDT/CasillasBean!com.servicios.CasillasBeanRemote");
		List<Object[]> datosCasillas = new ArrayList<Object[]>();
		
		List<Casilla> listaCasillas = casillasBean.obtenerTodos();
		for(Casilla casilla : listaCasillas) {
			
			
			
			String parametro = casilla.getParametro();
			String descripcion = casilla.getDescripcion();
			String unidadMedida = casilla.getUnidadDeMedida();
			Long id = casilla.getId();
			
			
			if(casilla.isEliminado()) {
				continue;
			}else {
				Object[] datos = {parametro, descripcion, unidadMedida, id};
				datosCasillas.add(datos);
				
			}
			
		}
		return datosCasillas;
		
	}
}