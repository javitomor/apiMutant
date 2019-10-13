package api.meli.core.model;

import api.meli.core.entity.AdnEntity;

public class AdnModel {

	private long id;

	private String cadena;

	private boolean isMuntant;
	
	private int hashCode;

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public AdnModel(AdnEntity entity) {
		this.setId(entity.getId());
		this.setCadena(entity.getCadena());
		this.setMuntant(entity.isMutant());
		this.setHashCode(entity.getHashCode());
	}

	public AdnModel() {
	}

	public AdnModel(long id, String cadena, boolean isMuntant, int hashCode) {
		this.id = id;
		this.cadena = cadena;
		this.isMuntant = isMuntant;
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

	public boolean isMuntant() {
		return isMuntant;
	}

	public void setMuntant(boolean isMuntant) {
		this.isMuntant = isMuntant;
	}

}
