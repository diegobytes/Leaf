package entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity implementation class for Entity: Formulario
 *
 */
@Entity
public class Formulario implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column
	private String creadoPor;
	
	@Column(length=250)
	private String resumen;
	
	@Column
	private boolean eliminado;
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	}, fetch = FetchType.EAGER)
	@JoinTable(name = "formulario_casilla",
	joinColumns = @JoinColumn(name = "formulario_id"),
	inverseJoinColumns = @JoinColumn(name = "casilla_id")
	)
	private Set<Casilla> casillas = new HashSet<>();
	
	
	@NotNull
	@Column(length=40, unique=true)
	private String nombre;
	
	

	
	public Set<Casilla> getCasillas() {
		return casillas;
	}
	public void setCasillas(Set<Casilla> casillas) {
		this.casillas = casillas;
	}
	
	public void agregarCasilla(Casilla casilla) {
		casillas.add(casilla);
		casilla.getFormularios().add(this);
	}
	
	public void quitarCasilla(Casilla casilla) {
		casillas.remove(casilla);
		casilla.getFormularios().remove(this);
	}
	public void quitarTodasCasillas(Set<Casilla> todas) {
		casillas.removeAll(todas);
		for(Casilla c : todas) {
			c.getFormularios().remove(this);
		}
		
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private static final long serialVersionUID = 1L;	
	public Formulario() {
		super();
	} 
	
   
}
