package api.meli.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.meli.core.entity.AdnEntity;
import api.meli.core.repository.AdnRepository;

@Service("adnService")
public class AdnService {

	@Autowired
	private AdnRepository repositorio;
	
	public boolean guardar(AdnEntity entidad) {
		try {
		repositorio.save(entidad);
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}
	
	public boolean existeAdn(int hashCode) {
		
			AdnEntity entity = repositorio.findByHashCode(hashCode);
			
			if(entity.getId()==0) {
				return true;
			}else {return false;}
		
	}
	
	public int getCountMutant() {
		return repositorio.countByIsMutant(true);
	}
	
	public int getCountHuman() {
		return repositorio.countByIsMutant(false);
	}
}
