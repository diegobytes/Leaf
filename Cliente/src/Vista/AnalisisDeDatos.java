package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.servicios.ActividadesBeanRemote;
import com.servicios.FormulariosBeanRemote;

import entidades.Actividad;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter.ComparisonType;

public class AnalisisDeDatos {

	JFrame frmActividades;
	private JButton btnFiltrarFecha;
	private JTable table;
	private JButton btnVolver;
	private JButton btnEliminar;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private JDatePickerImpl datePicker;
	private JDatePickerImpl datePicker2;
	private JTextField filtroEstacion;
	private JButton btnModificar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JScrollPane scrollPane;
	public static Actividad buscarActividad;
	public static boolean modificarActividad = false;
	private JTextField filtroMetodo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalisisDeDatos window = new AnalisisDeDatos();
					window.frmActividades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnalisisDeDatos() throws NamingException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException{
		ActividadesBeanRemote actividadesBean = (ActividadesBeanRemote)
				InitialContext.doLookup("PDT/ActividadesBean!com.servicios.ActividadesBeanRemote");
		FormulariosBeanRemote formulariosBean = (FormulariosBeanRemote)
				InitialContext.doLookup("PDT/FormulariosBean!com.servicios.FormulariosBeanRemote");
		frmActividades = new JFrame();
		frmActividades.setResizable(false);
		frmActividades.setTitle("Actividades");
		frmActividades.setBounds(100, 100, 674, 640);
		frmActividades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActividades.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 538, 345);
		frmActividades.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int seleccionada = table.getSelectedRow();
				if(seleccionada>-1) {
					try {
						Long id = Long.parseLong(table.getModel().getValueAt(seleccionada, 4).toString());
						buscarActividad = actividadesBean.obtenerPorId(id);
						Datos window = new Datos();
						window.frmDatosDeActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						window.frmDatosDeActividad.setVisible(true);
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				}
				
				
			}
		});
		btnAbrir.setBounds(558, 232, 89, 23);
		frmActividades.getContentPane().add(btnAbrir);
		
		lblNewLabel = new JLabel("Filtrar por:");
		lblNewLabel.setBounds(288, 11, 89, 14);
		frmActividades.getContentPane().add(lblNewLabel);
		
		filtroEstacion = new JTextField();
		filtroEstacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filtroEstacion.getText();
				filtrarEstacion(query,table);
				
				
			}
		});
		filtroEstacion.setBounds(64, 49, 86, 20);
		frmActividades.getContentPane().add(filtroEstacion);
		filtroEstacion.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Estaci\u00F3n:");
		lblNewLabel_1.setBounds(10, 52, 75, 14);
		frmActividades.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Rol:");
		lblNewLabel_2.setBounds(177, 52, 46, 14);
		frmActividades.getContentPane().add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("ADMINISTRADOR");
		comboBox.addItem("EXPERTO");
		comboBox.addItem("COMUN");
		comboBox.setBounds(211, 48, 111, 22);
		frmActividades.getContentPane().add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String query = comboBox.getSelectedItem().toString();
				filtrarRol(query,table);
				
			}
		});
		
		lblNewLabel_3 = new JLabel("Fecha inicio:");
		lblNewLabel_3.setBounds(337, 52, 89, 14);
		frmActividades.getContentPane().add(lblNewLabel_3);
		
		
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column ==0 || column==1 || column==2 || column ==3) {
					return false;
				}
				else {
					return true;
				}
			}
			
		};
		String[] columnas = {
				"Nombre", "Fecha","Estación","Usuario","ID", "Método","ROL","DEPARTAMENTO"
			};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		
		
		
		table.setModel(modelo);
		
		final TableRowSorter<TableModel> filtrarEstacion = new TableRowSorter<TableModel>(modelo);
		table.setRowSorter(filtrarEstacion);
		
		table.removeColumn(table.getColumnModel().getColumn(7));
		table.removeColumn(table.getColumnModel().getColumn(6));
		table.removeColumn(table.getColumnModel().getColumn(4));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		
		
		
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Login.usuarioLogeado.getRoles().toString().equals("ADMINISTRADOR") || Login.usuarioLogeado.getRoles().toString().equals("EXPERTO")) {
						Panel window = new Panel();
						window.frmPanel.setVisible(true);
						frmActividades.dispose();
					}else {
						GestionDeFormularios window = new GestionDeFormularios();
						window.frmGestinDeFormularios.setVisible(true);
						frmActividades.dispose();
					}
					
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
			}
		});
		btnVolver.setBounds(10, 553, 89, 37);
		frmActividades.getContentPane().add(btnVolver);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				limpiarFiltro(table);
				
				try {
					List<Object[]> datosFormularios = listaActividades();
					for(Object[] dato : datosFormularios) {
						modelo.addRow(dato);
						
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frmActividades, "ERROR AL LISTAR ACTIVIDADES");
				}
				
				
				
				
			}
		});
		btnListar.setBounds(558, 185, 89, 23);
		frmActividades.getContentPane().add(btnListar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionada = table.getSelectedRow();
				if(seleccionada>-1) {
					try {
						Long id = Long.parseLong(table.getModel().getValueAt(seleccionada, 4).toString());
						buscarActividad = actividadesBean.obtenerPorId(id);
						modificarActividad = true;
						GestionDeFormularios.formulario = formulariosBean.obtenerPorID(buscarActividad.getIdF());
						CargarFormulario window = new CargarFormulario();
						window.frmCargarActividadDe.setVisible(true);
						
						frmActividades.dispose();
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				}
				
				
				
			}
		});
		btnModificar.setBounds(558, 278, 89, 23);
		frmActividades.getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionada = table.getSelectedRow();
				if(seleccionada>-1) {
					Long id = Long.parseLong(table.getModel().getValueAt(seleccionada, 4).toString());
					
					
					String[] opciones = new String[2];
					opciones[0] = new String("Si");
					opciones[1] = new String("No");
					if(JOptionPane.showOptionDialog(null, "Estas seguro que deseas eliminar la actividad?", "Eliminar Actividad", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
						actividadesBean.eliminar(id);
						modelo.setRowCount(0);
						try {
							List<Object[]> datosUsuarios = listaActividades();
							for(Object[] dato : datosUsuarios) {
								modelo.addRow(dato);
								
							}
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(frmActividades, "ERROR AL LISTAR ACTIVIDADES");
						}
						
					}else {
						
					}
					
				}
				
				
				
				
			}
		});
	
		btnEliminar.setBounds(558, 321, 89, 23);
		frmActividades.getContentPane().add(btnEliminar);
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(419, 81, 111, 25);
		
		/*springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 264, SpringLayout.EAST, datePicker);
		springLayout.putConstraint(SpringLayout.EAST, datePicker.getJFormattedTextField(), -32, SpringLayout.EAST, datePicker);
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.NORTH, datePicker.getJFormattedTextField(), 0, SpringLayout.NORTH, datePicker);*/
		datePicker.setBounds(419, 49, 111, 23);
		
		frmActividades.getContentPane().add(datePicker);
		frmActividades.getContentPane().add(datePicker2);
		
		filtroMetodo = new JTextField();
		filtroMetodo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = filtroMetodo.getText();
				filtrarMetodo(query,table);
				
				
			}
		});
		filtroMetodo.setBounds(64, 84, 86, 20);
		frmActividades.getContentPane().add(filtroMetodo);
		filtroMetodo.setColumns(10);
		
		lblNewLabel_4 = new JLabel("M\u00E9todo:");
		lblNewLabel_4.setBounds(10, 87, 46, 14);
		frmActividades.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Fecha fin:");
		lblNewLabel_5.setBounds(351, 87, 75, 14);
		frmActividades.getContentPane().add(lblNewLabel_5);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItem("");
		comboBox_1.addItem("ARTIGAS");
		comboBox_1.addItem("CANELONES");
		comboBox_1.addItem("CERRO LARGO");
		comboBox_1.addItem("COLONIA");
		comboBox_1.addItem("DURAZNO");
		comboBox_1.addItem("FLORES");
		comboBox_1.addItem("FLORIDA");
		comboBox_1.addItem("LAVALLEJA");
		comboBox_1.addItem("MALDONADO");
		comboBox_1.addItem("MONTEVIDEO");
		comboBox_1.addItem("PAYSANDÚ");
		comboBox_1.addItem("RÍO NEGRO");
		comboBox_1.addItem("RIVERA");
		comboBox_1.addItem("ROCHA");
		comboBox_1.addItem("SALTO");
		comboBox_1.addItem("SAN JOSÉ");
		comboBox_1.addItem("SORIANO");
		comboBox_1.addItem("TACUAREMBÓ");
		comboBox_1.addItem("TREINTA Y TRES");
		comboBox_1.setBounds(211, 111, 111, 22);
		frmActividades.getContentPane().add(comboBox_1);
		
		btnFiltrarFecha = new JButton("Filtrar fecha");
		btnFiltrarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date inicio = (Date) datePicker.getModel().getValue();
				Date fin = (Date) datePicker2.getModel().getValue();
				
				if(inicio!=null && fin!=null) {
					if(inicio.compareTo(fin)>0) {
						JOptionPane.showMessageDialog(frmActividades, "La fecha de inicio no puede ser mayor a la de fin");
						
					}else {
						filtrarFecha(inicio, fin, comboBox_1.getSelectedItem().toString(), table);
					}
					
					
				}else if(inicio==null){
					JOptionPane.showMessageDialog(frmActividades, "Fecha inicio no puede estar vacío");
					
					
				}
				
				else {
					modelo.setRowCount(0);
					
					try {
						List<Object[]> datosFormularios = listaActividades();
						for(Object[] dato : datosFormularios) {
							modelo.addRow(dato);
							
						}
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frmActividades, "ERROR AL LISTAR ACTIVIDADES");
					}
				}
				
			}
		});
		btnFiltrarFecha.setBounds(419, 131, 111, 23);
		frmActividades.getContentPane().add(btnFiltrarFecha);
		
		lblNewLabel_6 = new JLabel("Departamento:");
		lblNewLabel_6.setBounds(117, 115, 120, 14);
		frmActividades.getContentPane().add(lblNewLabel_6);
		
		
		checkRol();
		
		
	}
	
	private void checkRol() {
		if(Login.usuarioLogeado.getRoles().toString() == "ADMINISTRADOR" || Login.usuarioLogeado.getRoles().toString() == "EXPERTO") {
			btnEliminar.setVisible(true);
			btnFiltrarFecha.setVisible(true);
			lblNewLabel_6.setVisible(true);
			lblNewLabel.setVisible(true);
			lblNewLabel_1.setVisible(true);
			lblNewLabel_2.setVisible(true);
			lblNewLabel_3.setVisible(true);
			lblNewLabel_4.setVisible(true);
			lblNewLabel_5.setVisible(true);
			comboBox.setVisible(true);
			comboBox_1.setVisible(true);
			filtroMetodo.setVisible(true);
			filtroEstacion.setVisible(true);
			datePicker.setVisible(true);
			datePicker2.setVisible(true);
			frmActividades.setBounds(100, 100, 674, 640);
			btnVolver.setBounds(10, 553, 89, 37);
			scrollPane.setBounds(10, 182, 538, 345);

			
		}else {
			btnEliminar.setVisible(false);
			btnFiltrarFecha.setVisible(false);
			lblNewLabel_6.setVisible(false);
			lblNewLabel.setVisible(false);
			lblNewLabel_1.setVisible(false);
			lblNewLabel_2.setVisible(false);
			lblNewLabel_3.setVisible(false);
			lblNewLabel_4.setVisible(false);
			lblNewLabel_5.setVisible(false);
			comboBox.setVisible(false);
			comboBox_1.setVisible(false);
			filtroMetodo.setVisible(false);
			filtroEstacion.setVisible(false);
			datePicker.setVisible(false);
			datePicker2.setVisible(false);
			frmActividades.setBounds(100, 100, 674, 500);
			btnVolver.setBounds(10, 410, 89, 37);
			scrollPane.setBounds(10, 20, 538, 345);
		}
	}
	
	private void filtrarEstacion(String query, JTable table) {
		TableRowSorter<TableModel> ts = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(ts);
		
		ts.setRowFilter(RowFilter.regexFilter(query,2));
	}
	private void filtrarRol(String query, JTable table) {
		TableRowSorter<TableModel> ts = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(ts);
		
		ts.setRowFilter(RowFilter.regexFilter(query));
	}
	private void limpiarFiltro(JTable table) {
		TableRowSorter<TableModel> ts = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(ts);
		
		ts.setRowFilter(RowFilter.regexFilter(""));
	}
	
	private void filtrarMetodo(String query, JTable table) {
		TableRowSorter<TableModel> ts = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(ts);
		
		ts.setRowFilter(RowFilter.regexFilter(query,5));
	}
	
	private void filtrarFecha(Date inicio, Date fin, String query, JTable table) {
		List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
		filters.add(RowFilter.dateFilter(ComparisonType.AFTER, inicio));
		filters.add(RowFilter.dateFilter(ComparisonType.BEFORE, fin));
		filters.add(RowFilter.regexFilter(query));
		TableRowSorter<TableModel> tr = new TableRowSorter<>(table.getModel());
		table.setRowSorter(tr);
		RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
		tr.setRowFilter(rf);
		
		
		
	}
	
	
	
	public List<Object[]> listaActividades() throws NamingException{
		ActividadesBeanRemote actividadesBean = (ActividadesBeanRemote)
				InitialContext.doLookup("PDT/ActividadesBean!com.servicios.ActividadesBeanRemote");
		List<Object[]> datosActividades = new ArrayList<Object[]>();
		List<Actividad> listaActividades;
		
		if(Login.usuarioLogeado.getRoles().toString().equals("ADMINISTRADOR") || Login.usuarioLogeado.getRoles().toString().equals("EXPERTO")) {
			listaActividades = actividadesBean.obtenerTodos();
		}
		else {
			listaActividades = actividadesBean.listaComun(Login.usuarioLogeado.getNombreUsuario());
		}
		
		
		for(Actividad actividad : listaActividades) {
			
			
			
			String nombreFormulario = actividad.getFormulario();
			Date fecha = actividad.getCargadoFecha();
			String estacion = actividad.getEstacionMuestreo();
			String usuario = actividad.getCargadoPor();
			Long id = actividad.getId();
			String metodo = actividad.getMetodoMuestreo();
			String rol = actividad.getCargadoRol();
			String departamento = actividad.getDepartamento();
			
			
			if(actividad.isEliminado()) {
				continue;
			}else {
				Object[] datos = {nombreFormulario, fecha, estacion, usuario, id, metodo, rol, departamento};
				datosActividades.add(datos);
				
			}
			
		}
		return datosActividades;
		
	}
}
