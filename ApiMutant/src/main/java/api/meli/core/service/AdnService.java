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
			if (!existeAdn(entidad.getHashCode())) {
				repositorio.save(entidad);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean existeAdn(int hashCode) {
		try {
			AdnEntity entity = repositorio.findByHashCode(hashCode);
			if (entity.getId() != 0)
				return true;

		} catch (Exception e) {
			return false;
		}
		return false;

	}

	public int getCountPerson(boolean mutant) {
		try {
			int cantidad = repositorio.countByIsMutant(mutant);
			return cantidad >= 0 ? cantidad : 0;
		}catch(NullPointerException e) {
			return 0;
		}
		
	}

}
