package Vista;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import com.servicios.UsuariosBeanRemote;

import entidades.Usuario;
import entidades.Usuario.Roles;

import javax.swing.JPasswordField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Login{

	JFrame frmLogin;
	private JTextField txtUsuario;
	private JPasswordField passwordUsuario;
	public static Usuario usuarioLogeado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws NamingException 
	 */
	public Login() throws NamingException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws NamingException {
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PDT/UsuariosBean!com.servicios.UsuariosBeanRemote");
		
		
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 390, 409);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Usuario");
		txtUsuario.setBounds(110, 97, 154, 31);
		frmLogin.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordUsuario = new JPasswordField();
		passwordUsuario.setToolTipText("Contrase\u00F1a");
		passwordUsuario.setBounds(110, 139, 154, 31);
		frmLogin.getContentPane().add(passwordUsuario);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contrasenaString = new String(passwordUsuario.getPassword());
				if(contrasenaString.isEmpty()) {
					JOptionPane.showMessageDialog(frmLogin, "Debe ingresar una contraseña");
					
				}else if(txtUsuario.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmLogin, "Debe ingresar un nombre de usuario");
				}else {
					try {
						Usuario resultado = usuariosBean.login(txtUsuario.getText(), contrasenaString);
						if(resultado.isEliminado()) {
							JOptionPane.showMessageDialog(frmLogin, "Usuario o contraseña incorrecta");
							
						}
						else {
							if(resultado.getNombreUsuario().equals(txtUsuario.getText()) && resultado.getContrasena().equals(contrasenaString)) {
								usuarioLogeado = resultado;
								if(resultado.getRoles().equals(Roles.ADMINISTRADOR) || resultado.getRoles().equals(Roles.EXPERTO)) {
									
									try {
										Panel window = new Panel();
										window.frmPanel.setVisible(true);
										frmLogin.dispose();
									} catch (Exception exc) {
										exc.printStackTrace();
									}
									
									
								}else {
									
									try {
										GestionDeFormularios window = new GestionDeFormularios();
										window.frmGestinDeFormularios.setVisible(true);
										frmLogin.dispose();
									} catch (Exception exc) {
										exc.printStackTrace();
									}
									
								}
								
							}
						}
						/*if(resultado.getNombreUsuario().equals(txtUsuario.getText()) && resultado.getContrasena().equals(contrasenaString)) {
							JOptionPane.showMessageDialog(frmLogin, "funciona?");
							
						}*/
						
					}catch(Exception nre) {
						JOptionPane.showMessageDialog(frmLogin, "Usuario o contraseña incorrecta");
						
						
					}
					
				}
				
				
				
				
				
				
					
					
				
				/*System.out.print(resultado.isEmpty());
				Usuario u1 = resultado.get(0);
				
				if(u1.getNombre().equals(txtUsuario.getText()) && u1.getContrasena().equals(passwordUsuario.getPassword())) {
					JOptionPane.showMessageDialog(frmLogin, "funciona?");
				}
				else {
					JOptionPane.showMessageDialog(frmLogin, "no existe el usuario");
				} */
				
			}
		});
		btnIngresar.setBounds(146, 196, 89, 23);
		frmLogin.getContentPane().add(btnIngresar);
		
		JLabel lblNewLabel = new JLabel(" \u00BFNo tienes una cuenta?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(110, 264, 154, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro window;
				try {
					window = new Registro();
					window.frmRegistro.setVisible(true);
					frmLogin.dispose();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnRegistro.setBounds(110, 289, 154, 23);
		frmLogin.getContentPane().add(btnRegistro);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/iconos/leaf.png")));
		lblNewLabel_1.setBounds(87, -17, 199, 117);
		frmLogin.getContentPane().add(lblNewLabel_1);
	}
}
