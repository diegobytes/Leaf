package entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Casilla
 *
 */
@Entity
public class Casilla implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(length=40)
	private String parametro;
	
	@ManyToMany(mappedBy = "casillas", fetch = FetchType.EAGER)
	private Set<Formulario> formularios = new HashSet<>();
	
	public Set<Formulario> getFormularios() {
		return formularios;
	}
	public void setFormularios(Set<Formulario> formularios) {
		this.formularios = formularios;
	}
	@NotNull
	@Column(length=40)
	private String unidadDeMedida;
	
	@Column(length=250)
	private String descripcion;
	
	@NotNull
	@Column//(nullable=false)
	@Enumerated
	TipoDeValor tipoDeValor;
	
	@Column
	private boolean eliminado;


	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
	public enum TipoDeValor{
		INT,
		BOOLEAN,
		STRING,
		DOUBLE
		
	}
	
	
	
	
	public TipoDeValor getTipoDeValor() {
		return tipoDeValor;
	}
	public void setTipoDeValor(TipoDeValor tipoDeValor) {
		this.tipoDeValor = tipoDeValor;
	}
	private static final long serialVersionUID = 1L;	
	public Casilla() {
		super();
	} 
	
   
}
