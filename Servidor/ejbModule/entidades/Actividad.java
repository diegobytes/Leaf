package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;




/**
 * Entity implementation class for Entity: Actividad
 *
 */
@Entity
public class Actividad implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long idF;
	
	@Column
	private String formulario;
	
	@Column
	private Date cargadoFecha;
	
	@Column
	private String cargadoPor;
	
	@Column
	private boolean eliminado;
	
	@Column
	private String cargadoRol;
	
	@Column
	@NotNull
	private String estacionMuestreo;
	
	
	@Column
	@NotNull
	private String metodoMuestreo;
	
	@Column
	@NotNull
	private String departamento;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<String, String> casillaVariable1;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<String, String> casillaVariable2;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Map<String, String> casillaVariable3;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCargadoFecha() {
		return cargadoFecha;
	}
	public void setCargadoFecha(Date cargadoFecha) {
		this.cargadoFecha = cargadoFecha;
	}
	public String getCargadoPor() {
		return cargadoPor;
	}
	public void setCargadoPor(String cargadoPor) {
		this.cargadoPor = cargadoPor;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	public String getCargadoRol() {
		return cargadoRol;
	}
	public void setCargadoRol(String cargadoRol) {
		this.cargadoRol = cargadoRol;
	}
	
	public Map<String, String> getCasillaVariable1() {
		return casillaVariable1;
	}
	public void setCasillaVariable1(Map<String, String> casillaVariable1) {
		this.casillaVariable1 = casillaVariable1;
	}
	public Map<String, String> getCasillaVariable2() {
		return casillaVariable2;
	}
	public void setCasillaVariable2(Map<String, String> casillaVariable2) {
		this.casillaVariable2 = casillaVariable2;
	}
	public Map<String, String> getCasillaVariable3() {
		return casillaVariable3;
	}
	public void setCasillaVariable3(Map<String, String> casillaVariable3) {
		this.casillaVariable3 = casillaVariable3;
	}
	
	
	
	public String getFormulario() {
		return formulario;
	}
	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}
	public String getEstacionMuestreo() {
		return estacionMuestreo;
	}
	public void setEstacionMuestreo(String estacionMuestreo) {
		this.estacionMuestreo = estacionMuestreo;
	}
	public String getMetodoMuestreo() {
		return metodoMuestreo;
	}
	public void setMetodoMuestreo(String metodoMuestreo) {
		this.metodoMuestreo = metodoMuestreo;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}



	public Long getIdF() {
		return idF;
	}
	public void setIdF(Long idF) {
		this.idF = idF;
	}



	private static final long serialVersionUID = 1L;	
	public Actividad() {
		super();
	} 
	
   
}
