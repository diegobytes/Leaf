package Vista;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.servicios.UsuariosBeanRemote;

import entidades.Usuario;
import entidades.Usuario.Roles;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionDeUsuarios {

	JFrame frmGestinDeUsuarios;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionDeUsuarios window = new GestionDeUsuarios();
					window.frmGestinDeUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionDeUsuarios() throws NamingException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException {
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PDT/UsuariosBean!com.servicios.UsuariosBeanRemote");
		frmGestinDeUsuarios = new JFrame();
		frmGestinDeUsuarios.setResizable(false);
		frmGestinDeUsuarios.setTitle("Gesti\u00F3n de Usuarios");
		frmGestinDeUsuarios.setBounds(100, 100, 949, 589);
		frmGestinDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestinDeUsuarios.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 21, 727, 447);
		frmGestinDeUsuarios.getContentPane().add(scrollPane);
		
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 2 || column==4) {
					return false;
				}
				else {
					return true;
				}
			}
			
		};
		String[] columnas = {
				"Nombre", "Apellido", "Nombre de usuario", "C\u00E9dula", "Contrase\u00F1a", "Email", "Rol", "Instituto", "Profesi\u00F3n"
			};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		
		JButton btnAceptarSolicitud = new JButton("Aceptar Solicitud");
		btnAceptarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columnaRol = 6;
				int columnaNombreUsuario = 2;
				int fila = table.getSelectedRow();
				Object rol = table.getModel().getValueAt(fila, columnaRol);
				Object nombreUsuario = table.getModel().getValueAt(fila, columnaNombreUsuario);
				if(!(rol==null || rol.equals(""))&& (rol.toString().contentEquals("SOLICITUDADMINISTRADOR") || rol.toString().contentEquals("SOLICITUDEXPERTO"))) {
					Usuario usuario = usuariosBean.usuarioExiste(nombreUsuario.toString());
					String[] opciones = new String[2];
					opciones[0] = new String("Si");
					opciones[1] = new String("No");
					if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas aceptar la solicitud del usuario: " + nombreUsuario.toString() +"?", "Eliminar Usuario", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
						if(rol.toString().contentEquals("SOLICITUDADMINISTRADOR")) {
							usuario.setRoles(Roles.ADMINISTRADOR);
							usuariosBean.actualizar(usuario);
							modelo.setRowCount(0);
							try {
								List<Object[]> datosUsuarios = listaUsuarios();
								for(Object[] dato : datosUsuarios) {
									modelo.addRow(dato);
									
								}
								btnAceptarSolicitud.setVisible(false);
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
							}
						}
						else if(rol.toString().contentEquals("SOLICITUDEXPERTO")) {
							usuario.setRoles(Roles.EXPERTO);
							usuariosBean.actualizar(usuario);
							modelo.setRowCount(0);
							try {
								List<Object[]> datosUsuarios = listaUsuarios();
								for(Object[] dato : datosUsuarios) {
									modelo.addRow(dato);
									
								}
								btnAceptarSolicitud.setVisible(false);
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
							}
						}
						
						
					}
					
					
					

				}
				
				
				
				
				
				
				
				
			}
		});
		btnAceptarSolicitud.setBounds(767, 172, 139, 23);
		frmGestinDeUsuarios.getContentPane().add(btnAceptarSolicitud);
		btnAceptarSolicitud.setVisible(false);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			
			
			
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(table.getSelectedRow() > -1) {
					int columnaRol = 6;
					int fila = table.getSelectedRow();
					Object rol = table.getModel().getValueAt(fila, columnaRol);
					if(!(rol==null || rol.equals("")) && (rol.toString().contentEquals("SOLICITUDADMINISTRADOR") || rol.toString().contentEquals("SOLICITUDEXPERTO"))) {
						btnAceptarSolicitud.setVisible(true);
						
					}
					else {
						btnAceptarSolicitud.setVisible(false);
					}
					
					
					
					
				}
				
				
			}
		});
		
		
		
		JButton btnListarUsuarios = new JButton("Listar");
		btnListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				
				try {
					List<Object[]> datosUsuarios = listaUsuarios();
					for(Object[] dato : datosUsuarios) {
						modelo.addRow(dato);
						
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
				}
				
				
				
			}
		});
		btnListarUsuarios.setBounds(767, 24, 139, 23);
		frmGestinDeUsuarios.getContentPane().add(btnListarUsuarios);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columnaNombre = 0;
				int columnaApellido = 1;
				int columnaNombreUsuario = 2;
				int columnaCedula = 3;
				int columnaEmail = 5;
				int columnaRol = 6;
				int columnaInstituto = 7;
				int columnaProfesion = 8;
				int fila = table.getSelectedRow();
				
				Object nombre = table.getModel().getValueAt(fila, columnaNombre);
				Object apellido = table.getModel().getValueAt(fila, columnaApellido);
				Object nombreUsuario = table.getModel().getValueAt(fila, columnaNombreUsuario);
				Object cedula = table.getModel().getValueAt(fila, columnaCedula);
				Object email = table.getModel().getValueAt(fila, columnaEmail);
				Object rol = table.getModel().getValueAt(fila, columnaRol);
				Object instituto = table.getModel().getValueAt(fila, columnaInstituto);
				Object profesion = table.getModel().getValueAt(fila, columnaProfesion);
				
				
				Usuario usuario = usuariosBean.usuarioExiste(nombreUsuario.toString());
				
				
				if(nombre==null || nombre.equals("") || nombre.toString().length() > 40) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Nombre\" no puede estar vacío (Máximo 40 caracteres)");
					
				}
				
				else if(apellido==null || apellido.equals("") || apellido.toString().length() > 40) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Apellido\" no puede estar vacío (Máximo 40 caracteres)");
					
				}
				else if(rol==null || rol.equals("")) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Rol\" no puede estar vacío y solo puede tomar los valores ADMINISTRADOR, COMUN, EXPERTO");
					
					
				}else if(!(rol.toString().contentEquals("ADMINISTRADOR") || rol.toString().contentEquals("COMUN") || rol.toString().contentEquals("EXPERTO"))) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Rol\" solo puede tomar los valores ADMINISTRADOR, COMUN, EXPERTO");

					
				}
				else if((cedula==null || cedula.equals("") || !cedula.toString().matches("[0-9]{1}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{1}") || cedula.toString().length() > 11) && (rol.toString().contentEquals("ADMINISTRADOR") || rol.toString().contentEquals("EXPERTO"))) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Cedula\" no puede estar vacío y debe cumplir con el formato de cédula uruguaya (1.111.111-1)");
						
				
				}
				else if(!(email==null || email.toString().isEmpty()) && (!email.toString().endsWith(".com") || email.toString().length() > 40 || email.toString().startsWith("@") || !email.toString().contains("@"))) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Email\" debe contener @ y terminar en .com (Máximo 40 caracteres)");
					
				}
				
				else if(!(profesion==null || profesion.equals("")) && (profesion.toString().length() > 40) && (rol.toString().contentEquals("EXPERTO"))){
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Profesión\" no puede ser mayor a 40 caracteres");
						
					
				}
				else if(!(instituto==null || instituto.equals("")) && (instituto.toString().length() > 40) && (rol.toString().contentEquals("ADMINISTRADOR"))) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Instituto\" no puede ser mayor a 40 caracteres");
						
					
				}/*else if(rol==null || !rol.equals("ADMINISTRADOR") || !rol.equals("COMUN") || !rol.toString().equals("EXPERTO")) {
					JOptionPane.showMessageDialog(frmGestinDeUsuarios, "El campo \"Rol\" no puede estar vacio y solo puede tomar los valores ADMINISTRADOR, COMUN, EXPERTO");
					
				}*/
				else {
					

					String[] opciones = new String[2];
					opciones[0] = new String("Si");
					opciones[1] = new String("No");
					if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas modificar al usuario: " + nombreUsuario.toString() +"?", "Eliminar Usuario", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
						
						if(rol.toString().contentEquals("COMUN")) {
							usuario.setNombre(nombre.toString());
							usuario.setApellido(apellido.toString());
							if(email!=null) {
								usuario.setEmail(email.toString());
								
							}
							
							usuario.setRoles(Roles.COMUN);
							usuariosBean.actualizar(usuario);
							
							modelo.setRowCount(0);
							try {
								List<Object[]> datosUsuarios = listaUsuarios();
								for(Object[] dato : datosUsuarios) {
									modelo.addRow(dato);
									
								}
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
							}
						}
						else if(rol.toString().contentEquals("EXPERTO")) {
							usuario.setNombre(nombre.toString());
							usuario.setApellido(apellido.toString());
							if(email!=null) {
								usuario.setEmail(email.toString());
								
							}
							usuario.setCedula(cedula.toString());
							if(profesion!=null) {
								usuario.setProfesion(profesion.toString());
								
							}
							usuario.setRoles(Roles.EXPERTO);
							
							usuariosBean.actualizar(usuario);
							
							modelo.setRowCount(0);
							try {
								List<Object[]> datosUsuarios = listaUsuarios();
								for(Object[] dato : datosUsuarios) {
									modelo.addRow(dato);
									
								}
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
							}
							
						}
						else if(rol.toString().contentEquals("ADMINISTRADOR")) {
							usuario.setNombre(nombre.toString());
							usuario.setApellido(apellido.toString());
							if(email!=null) {
								usuario.setEmail(email.toString());
								
							}
							usuario.setCedula(cedula.toString());
							if(instituto!=null) {
								usuario.setInstituto(instituto.toString());
								
							}
							usuario.setRoles(Roles.ADMINISTRADOR);
							
							usuariosBean.actualizar(usuario);
							
							modelo.setRowCount(0);
							try {
								List<Object[]> datosUsuarios = listaUsuarios();
								for(Object[] dato : datosUsuarios) {
									modelo.addRow(dato);
									
								}
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
							}
							
							
						}
						

						
					}else {
						
					}
					
					
					
				
				}
				
				
				
				
				
			}
		});
		btnModificar.setBounds(767, 76, 139, 23);
		frmGestinDeUsuarios.getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columna = 2;
				int fila = table.getSelectedRow();
				String nombreUsuario = table.getModel().getValueAt(fila, columna).toString();
				String[] opciones = new String[2];
				opciones[0] = new String("Si");
				opciones[1] = new String("No");
				if(JOptionPane.showOptionDialog(null, "Estas seguro que deseas eliminar al usuario: " + nombreUsuario +"?", "Eliminar Usuario", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
					usuariosBean.eliminar(nombreUsuario);
					modelo.setRowCount(0);
					try {
						List<Object[]> datosUsuarios = listaUsuarios();
						for(Object[] dato : datosUsuarios) {
							modelo.addRow(dato);
							
						}
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frmGestinDeUsuarios, "ERROR AL LISTAR USUARIOS");
					}
					
				}else {
					
				}
				
			}
		});
		btnEliminar.setBounds(767, 128, 139, 23);
		frmGestinDeUsuarios.getContentPane().add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Panel window = new Panel();
					window.frmPanel.setVisible(true);
					frmGestinDeUsuarios.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
			}
		});
		btnVolver.setBounds(25, 498, 89, 41);
		frmGestinDeUsuarios.getContentPane().add(btnVolver);
		
		
		
	}
	
	
	
	
	
	public List<Object[]> listaUsuarios() throws NamingException{
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PDT/UsuariosBean!com.servicios.UsuariosBeanRemote");
		List<Object[]> datosUsuarios = new ArrayList<Object[]>();
		
		List<Usuario> listaUsuarios = usuariosBean.obtenerTodos();
		for(Usuario usuario : listaUsuarios) {
			
			String nombre = usuario.getNombre();
			String apellido = usuario.getApellido();
			String nombreUsuario = usuario.getNombreUsuario();
			String cedula = usuario.getCedula();
			String contrasena = usuario.getContrasena();
			String email = usuario.getEmail();
			Roles rol = usuario.getRoles();
			String instituto = usuario.getInstituto();
			String profesion = usuario.getProfesion();
			
			if(usuario.isEliminado()) {
				continue;
			}else {
				Object[] datos = {nombre, apellido, nombreUsuario, cedula, contrasena, email, rol, instituto, profesion};
				datosUsuarios.add(datos);
				
			}
			
			
			
			
			
			
			
			
		}
		return datosUsuarios;
		
	}
	
	
}
