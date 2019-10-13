package api.meli.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ADN")
@Entity
public class AdnEntity implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id_adn")
	private long id;
	
	@Column(name="cadena")
	private String cadena;
	
	@Column(name="is_mutant")
	private boolean isMutant;
	
	@Column(name="hash_code")
	private int hashCode;

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}

	public AdnEntity(long id, String cadena, boolean isMutant) {
		this.id = id;
		this.cadena = cadena;
		this.isMutant = isMutant;
	}

	public AdnEntity() {
	}

	public AdnEntity(String cadena, boolean isMutant, int hashCode) {
		this.cadena = cadena;
		this.isMutant = isMutant;
		this.hashCode=hashCode;
	}
	
	
	
	
}
