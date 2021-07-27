package Vista;

import java.awt.EventQueue;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.servicios.ActividadesBeanRemote;
import com.servicios.FormulariosBeanRemote;

import entidades.Actividad;
import entidades.Casilla;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargarFormulario {

	JFrame frmCargarActividadDe;
	private JTextField textFieldMetodoMuestreo;
	private JTextField textEstacionMuestreo;
	private JTextField textFieldCasilla1;
	private JTextField textFieldCasilla2;
	private JTextField textFieldCasilla3;
	private Actividad actividad;
	public String key1 = null;
	public String key2 = null;
	public String key3 = null;
	public Casilla casilla1;
	public Casilla casilla2;
	public Casilla casilla3;
	public Set<Casilla> casillas = GestionDeFormularios.formulario.getCasillas();
	public Iterator<Casilla> it = casillas.iterator();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarFormulario window = new CargarFormulario();
					window.frmCargarActividadDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CargarFormulario() throws NamingException{
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
		frmCargarActividadDe = new JFrame();
		frmCargarActividadDe.setResizable(false);
		frmCargarActividadDe.setTitle("Cargar actividad de campo");
		frmCargarActividadDe.setBounds(100, 100, 450, 400);
		frmCargarActividadDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCargarActividadDe.getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(AnalisisDeDatos.modificarActividad==true) {
						AnalisisDeDatos window = new AnalisisDeDatos();
						window.frmActividades.setVisible(true);
						casillas = null;
						it = null;
						casilla1 = null;
						casilla2 = null;
						casilla3 = null;
						AnalisisDeDatos.modificarActividad=false;
						frmCargarActividadDe.dispose();
						
					}else if(AnalisisDeDatos.modificarActividad==false) {
						GestionDeFormularios window = new GestionDeFormularios();
						window.frmGestinDeFormularios.setVisible(true);
						casillas = null;
						it = null;
						casilla1 = null;
						casilla2 = null;
						casilla3 = null;
						frmCargarActividadDe.dispose();
						
					}
					
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
				
				
			}
		});
		btnVolver.setBounds(10, 310, 89, 31);
		frmCargarActividadDe.getContentPane().add(btnVolver);
		
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.setBounds(147, 306, 173, 39);
		frmCargarActividadDe.getContentPane().add(btnGuardar);
		
		JLabel lblNewLabel = new JLabel("M\u00E9todo de muestreo:");
		lblNewLabel.setBounds(10, 11, 134, 14);
		frmCargarActividadDe.getContentPane().add(lblNewLabel);
		
		textFieldMetodoMuestreo = new JTextField();
		textFieldMetodoMuestreo.setBounds(154, 8, 153, 20);
		frmCargarActividadDe.getContentPane().add(textFieldMetodoMuestreo);
		textFieldMetodoMuestreo.setColumns(10);
		
		textEstacionMuestreo = new JTextField();
		textEstacionMuestreo.setColumns(10);
		textEstacionMuestreo.setBounds(154, 52, 153, 20);
		frmCargarActividadDe.getContentPane().add(textEstacionMuestreo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(154, 102, 89, 22);
		comboBox.addItem("ARTIGAS");
		comboBox.addItem("CANELONES");
		comboBox.addItem("CERRO LARGO");
		comboBox.addItem("COLONIA");
		comboBox.addItem("DURAZNO");
		comboBox.addItem("FLORES");
		comboBox.addItem("FLORIDA");
		comboBox.addItem("LAVALLEJA");
		comboBox.addItem("MALDONADO");
		comboBox.addItem("MONTEVIDEO");
		comboBox.addItem("PAYSANDÚ");
		comboBox.addItem("RÍO NEGRO");
		comboBox.addItem("RIVERA");
		comboBox.addItem("ROCHA");
		comboBox.addItem("SALTO");
		comboBox.addItem("SAN JOSÉ");
		comboBox.addItem("SORIANO");
		comboBox.addItem("TACUAREMBÓ");
		comboBox.addItem("TREINTA Y TRES");
		frmCargarActividadDe.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Estaci\u00F3n de muestreo:");
		lblNewLabel_1.setBounds(10, 55, 134, 14);
		frmCargarActividadDe.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Departamento");
		lblNewLabel_2.setBounds(10, 106, 114, 14);
		frmCargarActividadDe.getContentPane().add(lblNewLabel_2);
		
		textFieldCasilla1 = new JTextField();
		textFieldCasilla1.setBounds(154, 167, 153, 20);
		frmCargarActividadDe.getContentPane().add(textFieldCasilla1);
		textFieldCasilla1.setColumns(10);
		
		textFieldCasilla2 = new JTextField();
		textFieldCasilla2.setBounds(154, 220, 153, 20);
		frmCargarActividadDe.getContentPane().add(textFieldCasilla2);
		textFieldCasilla2.setColumns(10);
		
		JLabel lblCasilla1 = new JLabel("New label");
		lblCasilla1.setBounds(10, 170, 134, 14);
		frmCargarActividadDe.getContentPane().add(lblCasilla1);
		
		JLabel lblCasilla2 = new JLabel("New label");
		lblCasilla2.setBounds(10, 223, 134, 14);
		frmCargarActividadDe.getContentPane().add(lblCasilla2);
		
		JLabel lblCasilla3 = new JLabel("New label");
		lblCasilla3.setBounds(10, 269, 134, 14);
		frmCargarActividadDe.getContentPane().add(lblCasilla3);
		
		textFieldCasilla3 = new JTextField();
		textFieldCasilla3.setBounds(154, 266, 153, 20);
		frmCargarActividadDe.getContentPane().add(textFieldCasilla3);
		textFieldCasilla3.setColumns(10);
		
		lblCasilla1.setVisible(false);
		lblCasilla2.setVisible(false);
		textFieldCasilla1.setVisible(false);
		textFieldCasilla2.setVisible(false);
		lblCasilla3.setVisible(false);
		textFieldCasilla3.setVisible(false);
		
		actividad = new Actividad();
		
		Date fecha = new Date();
		
		Map<String, String> casillaVariable1 = new HashMap<String, String>();
		Map<String, String> casillaVariable2 = new HashMap<String, String>();
		Map<String, String> casillaVariable3 = new HashMap<String, String>();
		
		if(AnalisisDeDatos.modificarActividad == true) {
			textFieldMetodoMuestreo.setText(AnalisisDeDatos.buscarActividad.getMetodoMuestreo());
			textEstacionMuestreo.setText(AnalisisDeDatos.buscarActividad.getEstacionMuestreo());
			comboBox.setSelectedItem(AnalisisDeDatos.buscarActividad.getDepartamento().toString());
			actividad = actividadesBean.obtenerPorId(AnalisisDeDatos.buscarActividad.getId());
			casillas = formulariosBean.obtenerPorID(actividad.getIdF()).getCasillas();
		}
		
		
		
		if(casillas.size() == 3) {
			lblCasilla1.setVisible(true);
			lblCasilla2.setVisible(true);
			lblCasilla3.setVisible(true);
			textFieldCasilla1.setVisible(true);
			textFieldCasilla2.setVisible(true);
			textFieldCasilla3.setVisible(true);
			
			if(AnalisisDeDatos.modificarActividad == true) {
				AnalisisDeDatos.buscarActividad.getCasillaVariable1().forEach((key,value) -> {
					
					textFieldCasilla1.setText(value);
					lblCasilla1.setText(key);
		
				});
				AnalisisDeDatos.buscarActividad.getCasillaVariable2().forEach((key,value) -> {
					
					textFieldCasilla2.setText(value);
					lblCasilla2.setText(key);
				
		
				});
				AnalisisDeDatos.buscarActividad.getCasillaVariable3().forEach((key,value) -> {
					
					textFieldCasilla3.setText(value);
					lblCasilla3.setText(key);
		
				});
			}
			casilla1 = it.next();
			casilla2 = it.next();
			casilla3 = it.next();
			if(AnalisisDeDatos.modificarActividad == false) {
				lblCasilla1.setText(casilla1.getParametro());
				lblCasilla2.setText(casilla2.getParametro());
				lblCasilla3.setText(casilla3.getParametro());
				
			}

			
		}else if(casillas.size() == 2) {
			lblCasilla1.setVisible(true);
			lblCasilla2.setVisible(true);
			textFieldCasilla1.setVisible(true);
			textFieldCasilla2.setVisible(true);
			if(AnalisisDeDatos.modificarActividad == true) {
				AnalisisDeDatos.buscarActividad.getCasillaVariable1().forEach((key,value) -> {
					System.out.println(key);
					textFieldCasilla1.setText(value);
					lblCasilla1.setText(key);
				
		
				});
				AnalisisDeDatos.buscarActividad.getCasillaVariable2().forEach((key,value) -> {
					System.out.println(key);
					textFieldCasilla2.setText(value);
					lblCasilla2.setText(key);
		
				});
				
			}
			casilla1 = it.next();
			casilla2 = it.next();
			
			if(AnalisisDeDatos.modificarActividad == false) {
				lblCasilla1.setText(casilla1.getParametro());
				lblCasilla2.setText(casilla2.getParametro());
				
				
			}
			
			
		}else if(casillas.size() == 1) {
			lblCasilla1.setVisible(true);
			textFieldCasilla1.setVisible(true);
			if(AnalisisDeDatos.modificarActividad == true) {
				AnalisisDeDatos.buscarActividad.getCasillaVariable1().forEach((key,value) -> {
					
					textFieldCasilla1.setText(value);
					lblCasilla1.setText(key);
				
		
				});
				
				
			}
			
			
			casilla1 = it.next();
			
			if(AnalisisDeDatos.modificarActividad == false) {
				lblCasilla1.setText(casilla1.getParametro());
				
				
			}

			
			
		}else if (casillas.isEmpty()){
			
			
		}
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldMetodoMuestreo.getText().isEmpty() || textFieldMetodoMuestreo.getText().length() > 100) {
					JOptionPane.showMessageDialog(frmCargarActividadDe, "La casilla \"Método de muestreo\" no puede estar vacía (Como máximo 100 caracteres)");
					
				}else if(textEstacionMuestreo.getText().isEmpty() || textEstacionMuestreo.getText().length() > 100) {
					JOptionPane.showMessageDialog(frmCargarActividadDe, "La casilla \"Estación de muestreo\" no puede estar vacía (Como máximo 100 caracteres)");
					
				}else {
					if(casillas.size() == 3) {
						
						
						actividad.setCargadoFecha(fecha);
						actividad.setCargadoPor(Login.usuarioLogeado.getNombreUsuario());
						actividad.setCargadoRol(Login.usuarioLogeado.getRoles().toString());
						actividad.setDepartamento(comboBox.getSelectedItem().toString());
						actividad.setEstacionMuestreo(textEstacionMuestreo.getText());
						actividad.setMetodoMuestreo(textFieldMetodoMuestreo.getText());
						actividad.setFormulario(GestionDeFormularios.formulario.getNombre());
						actividad.setIdF(GestionDeFormularios.formulario.getId());
						
						
						if(textFieldCasilla1.getText().isEmpty()){
							casillaVariable1.put(lblCasilla1.getText(), " ");
							actividad.setCasillaVariable1(casillaVariable1);
						}
						
						else if(!textFieldCasilla1.getText().isEmpty()) {
							casillaVariable1.put(lblCasilla1.getText(), textFieldCasilla1.getText());
							actividad.setCasillaVariable1(casillaVariable1);
							
						}
						
						if(textFieldCasilla2.getText().isEmpty()){
							casillaVariable2.put(lblCasilla2.getText(), " ");
							actividad.setCasillaVariable2(casillaVariable2);
						}
						else if(!textFieldCasilla2.getText().isEmpty()) {
							casillaVariable2.put(lblCasilla2.getText(), textFieldCasilla2.getText());
							actividad.setCasillaVariable2(casillaVariable2);
							
						}
						if(textFieldCasilla3.getText().isEmpty()){
							casillaVariable3.put(lblCasilla3.getText(), " ");
							actividad.setCasillaVariable3(casillaVariable3);
						}
						
						else if(!textFieldCasilla3.getText().isEmpty()) {
							casillaVariable3.put(lblCasilla3.getText(), textFieldCasilla3.getText());
							actividad.setCasillaVariable3(casillaVariable3);
							
						}
						
						if(AnalisisDeDatos.modificarActividad==false) {
							actividadesBean.crear(actividad);
							JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad cargada correctamente");
						}else if(AnalisisDeDatos.modificarActividad==true) {
							String[] opciones = new String[2];
							opciones[0] = new String("Si");
							opciones[1] = new String("No");
							if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas modificar la actividad? ", "Modificar actividad", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
								actividadesBean.actualizar(actividad);
								JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad modificada correctamente");
							}
							
						}
						
						
						it = null;
						casillaVariable1.clear();
						casillaVariable2.clear();
						casillaVariable3.clear();
						
						
						
						
					}else if(casillas.size() == 2) {
						
						
						
						
						actividad.setCargadoFecha(fecha);
						actividad.setCargadoPor(Login.usuarioLogeado.getNombreUsuario());
						actividad.setCargadoRol(Login.usuarioLogeado.getRoles().toString());
						actividad.setDepartamento(comboBox.getSelectedItem().toString());
						actividad.setEstacionMuestreo(textEstacionMuestreo.getText());
						actividad.setMetodoMuestreo(textFieldMetodoMuestreo.getText());
						actividad.setFormulario(GestionDeFormularios.formulario.getNombre());
						actividad.setIdF(GestionDeFormularios.formulario.getId());
						
						if(textFieldCasilla1.getText().isEmpty()){
							casillaVariable1.put(lblCasilla1.getText(), " ");
							actividad.setCasillaVariable1(casillaVariable1);
						}
						else if(!textFieldCasilla1.getText().isEmpty()) {
							casillaVariable1.put(lblCasilla1.getText(), textFieldCasilla1.getText());
							actividad.setCasillaVariable1(casillaVariable1);
							
						}
						if(textFieldCasilla2.getText().isEmpty()){
							casillaVariable1.put(lblCasilla2.getText(), " ");
							actividad.setCasillaVariable2(casillaVariable2);
						}
						else if(!textFieldCasilla2.getText().isEmpty()) {
							casillaVariable2.put(lblCasilla2.getText(), textFieldCasilla2.getText());
							actividad.setCasillaVariable2(casillaVariable2);
							
						}
						
						
						
						if(AnalisisDeDatos.modificarActividad==false) {
							actividadesBean.crear(actividad);
							JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad cargada correctamente");
						}else if(AnalisisDeDatos.modificarActividad==true) {
							String[] opciones = new String[2];
							opciones[0] = new String("Si");
							opciones[1] = new String("No");
							if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas modificar la actividad? ", "Modificar actividad", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
								actividadesBean.actualizar(actividad);
								JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad modificada correctamente");
							}
						}
						
						it = null;
						casillaVariable1.clear();
						casillaVariable2.clear();
						casillaVariable3.clear();
					}else if(casillas.size() == 1) {
						
						
						
						
						actividad.setCargadoFecha(fecha);
						actividad.setCargadoPor(Login.usuarioLogeado.getNombreUsuario());
						actividad.setCargadoRol(Login.usuarioLogeado.getRoles().toString());
						actividad.setDepartamento(comboBox.getSelectedItem().toString());
						actividad.setEstacionMuestreo(textEstacionMuestreo.getText());
						actividad.setMetodoMuestreo(textFieldMetodoMuestreo.getText());
						actividad.setFormulario(GestionDeFormularios.formulario.getNombre());
						actividad.setIdF(GestionDeFormularios.formulario.getId());
						
						if(textFieldCasilla1.getText().isEmpty()){
							casillaVariable1.put(lblCasilla1.getText(), " ");
							actividad.setCasillaVariable1(casillaVariable1);
						}
						else if(!textFieldCasilla1.getText().isEmpty()) {
							casillaVariable1.put(lblCasilla1.getText(), textFieldCasilla1.getText());
							actividad.setCasillaVariable1(casillaVariable1);
							
						}
						
						
						if(AnalisisDeDatos.modificarActividad==false) {
							actividadesBean.crear(actividad);
							JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad cargada correctamente");
						}else if(AnalisisDeDatos.modificarActividad==true) {
							String[] opciones = new String[2];
							opciones[0] = new String("Si");
							opciones[1] = new String("No");
							if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas modificar la actividad? ", "Modificar actividad", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
								actividadesBean.actualizar(actividad);
								JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad modificada correctamente");
							}
						}
						it = null;
						casillaVariable1.clear();
						casillaVariable2.clear();
						casillaVariable3.clear();
					}else if(casillas.isEmpty()) {
						actividad.setCargadoFecha(fecha);
						actividad.setCargadoPor(Login.usuarioLogeado.getNombreUsuario());
						actividad.setCargadoRol(Login.usuarioLogeado.getRoles().toString());
						actividad.setDepartamento(comboBox.getSelectedItem().toString());
						actividad.setEstacionMuestreo(textEstacionMuestreo.getText());
						actividad.setMetodoMuestreo(textFieldMetodoMuestreo.getText());
						actividad.setFormulario(GestionDeFormularios.formulario.getNombre());
						actividad.setIdF(GestionDeFormularios.formulario.getId());

						if(AnalisisDeDatos.modificarActividad==false) {
							actividadesBean.crear(actividad);
							JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad cargada correctamente");
						}else if(AnalisisDeDatos.modificarActividad==true) {
							String[] opciones = new String[2];
							opciones[0] = new String("Si");
							opciones[1] = new String("No");
							if(JOptionPane.showOptionDialog(null, "Estás seguro que deseas modificar la actividad? ", "Modificar actividad", 0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
								actividadesBean.actualizar(actividad);
								JOptionPane.showMessageDialog(frmCargarActividadDe, "Actividad modificada correctamente");
							}
						}
						it = null;
						casillaVariable1.clear();
						casillaVariable2.clear();
						casillaVariable3.clear();
					}
					
				}
				
				
			}
		});
		
		
		
	}
}
