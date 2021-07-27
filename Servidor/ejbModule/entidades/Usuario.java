package entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(length=40)
	private String nombre;
	
	@NotNull
	@Column(length=40)
	private String contrasena;
	
	@NotNull
	@Column(length=40)
	private String apellido;
	
	@Column(length=40)
	private String email;
	
	
	@Column
	private boolean eliminado;
	
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}


	@NotNull
	@Column(length=40, unique=true)
	private String nombreUsuario;
	
	@Column(length=11)
	private String cedula;
	
	@Column(length=40)
	private String profesion;
	
	@Column(length=40)
	private String instituto;
	
	
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getInstituto() {
		return instituto;
	}
	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	
	@NotNull
	@Column//(nullable=false)
	@Enumerated
	Roles roles;
	
	
	

	
	public Usuario(@NotNull String nombre, @NotNull String contrasena, @NotNull String apellido, String email,
			@NotNull String nombreUsuario, @NotNull Roles roles) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.apellido = apellido;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.roles = roles;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public enum Roles{
		ADMINISTRADOR,
		EXPERTO,
		COMUN,
		SOLICITUDEXPERTO,
		SOLICITUDADMINISTRADOR
		
	}
	
	private static final long serialVersionUID = 1L;	
	public Usuario() {
		super();
	} 
	
   
}
