package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import com.servicios.UsuariosBeanRemote;

import entidades.Usuario;
import entidades.Usuario.Roles;

public class Registro {

	JFrame frmRegistro;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textNombreUsuario;
	private JTextField textEmail;
	private JPasswordField passwordContrasena;
	private JTextField textCedula;
	private JTextField textProfesion;
	private JTextField textInstituto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.frmRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registro() throws NamingException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException{
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PDT/UsuariosBean!com.servicios.UsuariosBeanRemote");
		
		frmRegistro = new JFrame();
		frmRegistro.setResizable(false);
		frmRegistro.setTitle("Registro");
		frmRegistro.setBounds(100, 100, 520, 300);
		frmRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistro.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(189, 32, 111, 22);
		comboBox.addItem("COMÚN");
		comboBox.addItem("EXPERTO");
		comboBox.addItem("ADMINISTRADOR");
		
		
		JLabel lblNewLabel = new JLabel("Seleccione el tipo de usuario que desea");
		lblNewLabel.setBounds(153, 7, 227, 14);
		frmRegistro.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 66, 68, 14);
		frmRegistro.getContentPane().add(lblNewLabel_1);
		
		textNombre = new JTextField();
		textNombre.setBounds(88, 63, 86, 20);
		frmRegistro.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(10, 103, 68, 14);
		frmRegistro.getContentPane().add(lblNewLabel_2);
		
		textApellido = new JTextField();
		textApellido.setBounds(88, 100, 86, 20);
		frmRegistro.getContentPane().add(textApellido);
		textApellido.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre de usuario:");
		lblNewLabel_3.setBounds(235, 65, 127, 14);
		frmRegistro.getContentPane().add(lblNewLabel_3);
		
		textNombreUsuario = new JTextField();
		textNombreUsuario.setBounds(372, 63, 86, 20);
		frmRegistro.getContentPane().add(textNombreUsuario);
		textNombreUsuario.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(372, 100, 86, 20);
		frmRegistro.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(283, 103, 79, 14);
		frmRegistro.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_5.setBounds(10, 140, 77, 14);
		frmRegistro.getContentPane().add(lblNewLabel_5);
		
		passwordContrasena = new JPasswordField();
		passwordContrasena.setBounds(88, 137, 86, 20);
		frmRegistro.getContentPane().add(passwordContrasena);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(265, 140, 86, 14);
		frmRegistro.getContentPane().add(lblCedula);
		
		textCedula = new JTextField();
		textCedula.setBounds(372, 137, 86, 20);
		frmRegistro.getContentPane().add(textCedula);
		textCedula.setColumns(10);
		
		JLabel lblProfesion = new JLabel("Profesi\u00F3n:");
		lblProfesion.setBounds(10, 175, 77, 14);
		frmRegistro.getContentPane().add(lblProfesion);
		
		textProfesion = new JTextField();
		textProfesion.setBounds(88, 172, 86, 20);
		frmRegistro.getContentPane().add(textProfesion);
		textProfesion.setColumns(10);
		
		JLabel lblInstituto = new JLabel("Instituto:");
		lblInstituto.setBounds(265, 175, 97, 14);
		frmRegistro.getContentPane().add(lblInstituto);
		
		textInstituto = new JTextField();
		textInstituto.setBounds(372, 172, 86, 20);
		frmRegistro.getContentPane().add(textInstituto);
		textInstituto.setColumns(10);
		
		textCedula.setVisible(false);
		textInstituto.setVisible(false);
		textProfesion.setVisible(false);
		lblCedula.setVisible(false);
		lblInstituto.setVisible(false);
		lblProfesion.setVisible(false);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("COMÚN")) {
					textCedula.setVisible(false);
					textInstituto.setVisible(false);
					textProfesion.setVisible(false);
					lblCedula.setVisible(false);
					lblInstituto.setVisible(false);
					lblProfesion.setVisible(false);
				}
				else if(comboBox.getSelectedItem().equals("EXPERTO")) {
					textCedula.setVisible(true);
					textInstituto.setVisible(false);
					textProfesion.setVisible(true);
					lblCedula.setVisible(true);
					lblInstituto.setVisible(false);
					lblProfesion.setVisible(true);
					
				}
				else if(comboBox.getSelectedItem().equals("ADMINISTRADOR")) {
					textCedula.setVisible(true);
					textInstituto.setVisible(true);
					textProfesion.setVisible(false);
					lblCedula.setVisible(true);
					lblInstituto.setVisible(true);
					lblProfesion.setVisible(false);
					
				}
				
			}
			
		});
		
		
		frmRegistro.getContentPane().add(comboBox);
		
		JButton btnSolicitarRegistro = new JButton("Registrate");
		btnSolicitarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String contrasenaString = new String(passwordContrasena.getPassword());
				
				
				if(textNombreUsuario.getText().isEmpty() || textNombreUsuario.getText().length() < 8 || textNombreUsuario.getText().length() > 40 || textNombreUsuario.getText().matches(".*[0-9]+.*")) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Nombre de usuario\" no puede estar vacío y debe contener al menos 8 caracteres no numéricos (Como máximo 40)");
					
				}
				else if(textNombre.getText().isEmpty() || textNombre.getText().length() > 40) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Nombre\" no puede estar vacío (Máximo 40 caracteres)");
					
				}
				else if(textApellido.getText().isEmpty() || textApellido.getText().length() > 40) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Apellido\" no puede estar vacío (Máximo 40 caracteres)");
					
				}
				else if((comboBox.getSelectedItem().equals("ADMINISTRADOR") || comboBox.getSelectedItem().equals("EXPERTO")) && (textCedula.getText().isEmpty() || !textCedula.getText().matches("[0-9]{1}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{1}") || textCedula.getText().length() > 11)) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Cedula\" no puede estar vacío y debe cumplir con el formato de cédula uruguaya (1.111.111-1)");
						
					
				}
				else if(!textEmail.getText().isEmpty() && (!textEmail.getText().endsWith(".com") || textEmail.getText().length() > 40 || textEmail.getText().startsWith("@") || !textEmail.getText().contains("@"))) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Email\" debe contener @ y terminar en .com (Máximo 40 caracteres)");
					
				}
				
				else if(contrasenaString.isEmpty() || contrasenaString.length() > 40 || !contrasenaString.matches("([a-zA-Z].*[0-9]+.*)|([0-9].*[a-zA-Z]+.*)")) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Contraseña\" no puede estar vacío y debe contener letras y números (Máximo 40 caracteres)");
					
				}
				else if(comboBox.getSelectedItem().equals("EXPERTO") && (textProfesion.getText().length() > 40)) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Profesión\" no puede ser mayor a 40 caracteres");
						
					
				}
				else if(comboBox.getSelectedItem().equals("ADMINISTRADOR") && (textInstituto.getText().length() > 40)) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo \"Instituto\" no puede ser mayor a 40 caracteres");
						
					
				}
				else {
					
					
					
					
					
					if(comboBox.getSelectedItem().equals("COMÚN")){
						Usuario usuario = new Usuario();
						usuario.setNombre(textNombre.getText());
						usuario.setApellido(textApellido.getText());
						usuario.setEmail(textEmail.getText());
						usuario.setNombreUsuario(textNombreUsuario.getText());
						usuario.setContrasena(contrasenaString);
						usuario.setRoles(Roles.COMUN);
						
						
						try {
							usuariosBean.usuarioExiste(textNombreUsuario.getText()).getNombreUsuario();
							JOptionPane.showMessageDialog(frmRegistro, "El nombre de usuario ya está en uso");
							
							
						}catch(EJBException ex) {
							
							try {
								usuariosBean.crear(usuario);
								JOptionPane.showMessageDialog(frmRegistro, "Usuario creado correctamente");
								
							}catch(EJBException exc) {
								
							}
							
							
						}
						
						
					}
					else if (comboBox.getSelectedItem().equals("EXPERTO")) {
						Usuario usuario = new Usuario();
						usuario.setNombre(textNombre.getText());
						usuario.setApellido(textApellido.getText());
						usuario.setEmail(textEmail.getText());
						usuario.setNombreUsuario(textNombreUsuario.getText());
						usuario.setContrasena(contrasenaString);
						usuario.setCedula(textCedula.getText());
						usuario.setProfesion(textProfesion.getText());
						usuario.setRoles(Roles.SOLICITUDEXPERTO);
						
						
						try {
							usuariosBean.usuarioExiste(textNombreUsuario.getText()).getNombreUsuario();
							JOptionPane.showMessageDialog(frmRegistro, "El nombre de usuario ya está en uso");
							
							
						}catch(EJBException ex) {
							
							try {
								usuariosBean.crear(usuario);
								JOptionPane.showMessageDialog(frmRegistro, "Usuario creado correctamente");
								
							}catch(EJBException exc) {
								
							}
							
							
						}
						
					}
					else if (comboBox.getSelectedItem().equals("ADMINISTRADOR")) {
						Usuario usuario = new Usuario();
						
						usuario.setNombre(textNombre.getText());
						usuario.setApellido(textApellido.getText());
						usuario.setEmail(textEmail.getText());
						usuario.setNombreUsuario(textNombreUsuario.getText());
						usuario.setContrasena(contrasenaString);
						usuario.setCedula(textCedula.getText());
						usuario.setInstituto(textInstituto.getText());
						usuario.setRoles(Roles.SOLICITUDADMINISTRADOR);
						
						try {
							usuariosBean.usuarioExiste(textNombreUsuario.getText()).getNombreUsuario();
							JOptionPane.showMessageDialog(frmRegistro, "El nombre de usuario ya está en uso");
							
							
						}catch(EJBException ex) {
							
							try {
								usuariosBean.crear(usuario);
								JOptionPane.showMessageDialog(frmRegistro, "Usuario creado correctamente");
								
							}catch(EJBException exc) {
								
							}
							
							
						}
						
						
					}
					
				}
				
				
			}
		});
		btnSolicitarRegistro.setBounds(189, 215, 135, 23);
		frmRegistro.getContentPane().add(btnSolicitarRegistro);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window;
				try {
					window = new Login();
					window.frmLogin.setVisible(true);
					frmRegistro.dispose();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setBounds(10, 215, 89, 23);
		frmRegistro.getContentPane().add(btnVolver);
	}
}
