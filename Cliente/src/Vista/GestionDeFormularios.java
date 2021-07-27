package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.servicios.FormulariosBeanRemote;

import entidades.Formulario;
import entidades.Usuario;

import javax.swing.JOptionPane;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GestionDeFormularios {

	JFrame frmGestinDeFormularios;
	private JTable table;
	private JButton btnVolver;
	private JButton btnEliminar;
	private JButton btnCrear;
	private JButton btnModificar;
	private JButton btnSalir;
	public static Formulario formulario;
	public static boolean modificar = false;
	private JButton btnCargadas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionDeFormularios window = new GestionDeFormularios();
					window.frmGestinDeFormularios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionDeFormularios() throws NamingException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException {
		FormulariosBeanRemote formulariosBean = (FormulariosBeanRemote)
				InitialContext.doLookup("PDT/FormulariosBean!com.servicios.FormulariosBeanRemote");
		frmGestinDeFormularios = new JFrame();
		frmGestinDeFormularios.setResizable(false);
		frmGestinDeFormularios.setTitle("Gesti\u00F3n de Formularios");
		frmGestinDeFormularios.setBounds(100, 100, 680, 450);
		frmGestinDeFormularios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestinDeFormularios.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 30, 522, 308);
		frmGestinDeFormularios.getContentPane().add(scrollPane);
		
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0 || column==1) {
					return false;
				}
				else {
					return true;
				}
			}
			
		};
		String[] columnas = {
				"Nombre", "Resumen"
			};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		
		table.setModel(modelo);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(259);
		scrollPane.setViewportView(table);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				
				try {
					List<Object[]> datosFormularios = listaFormularios();
					for(Object[] dato : datosFormularios) {
						modelo.addRow(dato);
						
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frmGestinDeFormularios, "ERROR AL LISTAR FORMULARIOS");
				}
				
				
			}
		});
		btnListar.setBounds(565, 28, 89, 23);
		frmGestinDeFormularios.getContentPane().add(btnListar);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int columna = 0;
				int fila = table.getSelectedRow();
				if(fila > -1) {
					String nombre = table.getModel().getValueAt(fila, columna).toString();
					formulario = formulariosBean.formularioExiste(nombre);
					
					try {
						CargarFormulario window = new CargarFormulario();
						window.frmCargarActividadDe.setVisible(true);
						frmGestinDeFormularios.dispose();
					} catch (Exception exc) {
						exc.printStackTrace();
					}
					
				}
				else {
					
				}
				
			}
		});
		btnAbrir.setBounds(565, 75, 89, 23);
		frmGestinDeFormularios.getContentPane().add(btnAbrir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columna = 0;
				int fila = table.getSelectedRow();
				String nombre = table.getModel().getValueAt(fila, columna).toString();
				String[] opciones = new String[2];
				opciones[0] = new String("Si");
				opciones[1] = new String("No");
				if(JOptionPane.showOptionDialog(null, "Estas seguro que deseas eliminar el formulario: " + nombre+"?", "Eliminar Formulario", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
					formulariosBean.eliminar(nombre);
					modelo.setRowCount(0);
					try {
						List<Object[]> datosUsuarios = listaFormularios();
						for(Object[] dato : datosUsuarios) {
							modelo.addRow(dato);
							
						}
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frmGestinDeFormularios, "ERROR AL LISTAR FORMULARIOS");
					}
					
				}else {
					
				}
				
				
				
				
			}
		});
		btnEliminar.setBounds(565, 119, 89, 23);
		frmGestinDeFormularios.getContentPane().add(btnEliminar);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CrearFormulario window = new CrearFormulario();
					window.frmCrearFormulario.setVisible(true);
					frmGestinDeFormularios.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
			}
		});
		btnCrear.setBounds(565, 163, 89, 23);
		frmGestinDeFormularios.getContentPane().add(btnCrear);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columna = 0;
				int fila = table.getSelectedRow();
				if(fila > -1) {
					String nombre = table.getModel().getValueAt(fila, columna).toString();
					formulario = formulariosBean.formularioExiste(nombre);
					
					
					try {
						modificar = true;
						CrearFormulario window = new CrearFormulario();
						window.frmCrearFormulario.setVisible(true);
						frmGestinDeFormularios.dispose();
					} catch (Exception exc) {
						exc.printStackTrace();
					}
					
				}
				else {
					
				}
				
				
				
				
				
			}
		});
		btnModificar.setBounds(565, 212, 89, 23);
		frmGestinDeFormularios.getContentPane().add(btnModificar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Panel window = new Panel();
					window.frmPanel.setVisible(true);
					frmGestinDeFormularios.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
			}
		});
		btnVolver.setBounds(23, 349, 113, 51);
		frmGestinDeFormularios.getContentPane().add(btnVolver);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login window = new Login();
					Login.usuarioLogeado = new Usuario();
					window.frmLogin.setVisible(true);
					frmGestinDeFormularios.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
			}
		});
		btnSalir.setBounds(237, 349, 113, 51);
		frmGestinDeFormularios.getContentPane().add(btnSalir);
		
		btnCargadas = new JButton("<html>Actividades<br />cargadas</html>");
		btnCargadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AnalisisDeDatos window = new AnalisisDeDatos();
					window.frmActividades.setVisible(true);
					frmGestinDeFormularios.dispose();
					
				}catch(Exception ex) {
					
				}
				
			}
		});
		btnCargadas.setBounds(565, 119, 89, 51);
		frmGestinDeFormularios.getContentPane().add(btnCargadas);
		checkUsuarioComun();
	}
	
	public List<Object[]> listaFormularios() throws NamingException{
		FormulariosBeanRemote formulariosBean = (FormulariosBeanRemote)
				InitialContext.doLookup("PDT/FormulariosBean!com.servicios.FormulariosBeanRemote");
		List<Object[]> datosFormularios = new ArrayList<Object[]>();
		
		List<Formulario> listaFormularios = formulariosBean.obtenerTodos();
		for(Formulario formulario : listaFormularios) {
			
			
			
			String nombre = formulario.getNombre();
			String resumen = formulario.getResumen();
			
			
			if(formulario.isEliminado()) {
				continue;
			}else {
				Object[] datos = {nombre, resumen};
				datosFormularios.add(datos);
				
			}
			
		}
		return datosFormularios;
		
	}
	
	public void checkUsuarioComun() {
		if(Login.usuarioLogeado.getRoles().toString().equals("ADMINISTRADOR") || Login.usuarioLogeado.getRoles().toString().equals("EXPERTO")) {
			
			btnCargadas.setVisible(false);
			btnSalir.setVisible(false);
			btnVolver.setVisible(true);
			btnModificar.setVisible(true);
			btnEliminar.setVisible(true);
			btnCrear.setVisible(true);
			
		}else {
			
			btnVolver.setVisible(false);
			btnModificar.setVisible(false);
			btnEliminar.setVisible(false);
			btnCrear.setVisible(false);
		}
	}
}
