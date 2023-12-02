

package co.edu.unicauca.distribuidos.core.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.edu.unicauca.distribuidos.core.proyecto.models.ClienteEntity;
import co.edu.unicauca.distribuidos.core.proyecto.models.Region;



public interface ClienteRepository extends CrudRepository<ClienteEntity, Integer> {
	@Query("from Region")
	public List<Region> findAllRegiones();

}
