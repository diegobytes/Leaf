package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import entidades.Usuario;
import entidades.Usuario.Roles;

import java.awt.Color;
import java.awt.Font;

public class Panel {

	JFrame frmPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel window = new Panel();
					window.frmPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Panel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPanel = new JFrame();
		frmPanel.setResizable(false);
		frmPanel.setTitle("Panel");
		frmPanel.setBounds(100, 100, 450, 495);
		frmPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGestionUsuarios = new JButton("Gesti\u00F3n de Usuarios");
		btnGestionUsuarios.setBackground(new Color(169, 169, 169));
		btnGestionUsuarios.setOpaque(true);
		btnGestionUsuarios.setBorderPainted(false);
		btnGestionUsuarios.setBounds(0, 270, 434, 136);
		btnGestionUsuarios.setIcon(new ImageIcon(Panel.class.getResource("/iconos/usuario.png")));
		btnGestionUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GestionDeUsuarios window = new GestionDeUsuarios();
					window.frmGestinDeUsuarios.setVisible(true);
					frmPanel.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
				
				
			}
		});
		
		JButton btnAnalisisDatos = new JButton("An\u00E1lisis de Datos");
		btnAnalisisDatos.setBackground(new Color(230, 230, 250));
		btnAnalisisDatos.setOpaque(true);
		btnAnalisisDatos.setBorderPainted(false);
		btnAnalisisDatos.setIcon(new ImageIcon(Panel.class.getResource("/iconos/grafica.png")));
		btnAnalisisDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AnalisisDeDatos window = new AnalisisDeDatos();
					window.frmActividades.setVisible(true);
					frmPanel.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
				
			}
		});
		btnAnalisisDatos.setBounds(0, 0, 434, 136);
		
		JButton btnGestionFormularios = new JButton("Gesti\u00F3n de Formularios");
		
		
		btnGestionFormularios.setForeground(Color.BLACK);
		btnGestionFormularios.setBackground(new Color(220, 220, 220));
		btnGestionFormularios.setOpaque(true);
		btnGestionFormularios.setBorderPainted(false);
		
		btnGestionFormularios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GestionDeFormularios window = new GestionDeFormularios();
					window.frmGestinDeFormularios.setVisible(true);
					frmPanel.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
				
			}
		});
		btnGestionFormularios.setIcon(new ImageIcon(Panel.class.getResource("/iconos/formulario.png")));
		btnGestionFormularios.setBounds(0, 135, 434, 136);
		
		
		
		frmPanel.getContentPane().setLayout(null);
		frmPanel.getContentPane().add(btnGestionUsuarios);
		frmPanel.getContentPane().add(btnAnalisisDatos);
		frmPanel.getContentPane().add(btnGestionFormularios);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login window = new Login();
					Login.usuarioLogeado = new Usuario();
					window.frmLogin.setVisible(true);
					frmPanel.dispose();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnSalir.setForeground(Color.LIGHT_GRAY);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setOpaque(true);
		btnSalir.setBorderPainted(false);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setBounds(0, 406, 434, 50);
		frmPanel.getContentPane().add(btnSalir);
		
		if(Login.usuarioLogeado.getRoles().equals(Roles.EXPERTO)) {
			frmPanel.setBounds(100, 100, 450, 355);
			btnGestionUsuarios.setVisible(false);
			btnSalir.setBounds(0, 270, 434, 50);
		}
	}
}
