package api.meli.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.meli.core.entity.AdnEntity;

@Repository("adnRepository")
public interface AdnRepository extends JpaRepository<AdnEntity, Serializable>{
	
	public abstract AdnEntity findByHashCode(int hashCode);
	
	public abstract int countByIsMutant(boolean isMutant);

}
