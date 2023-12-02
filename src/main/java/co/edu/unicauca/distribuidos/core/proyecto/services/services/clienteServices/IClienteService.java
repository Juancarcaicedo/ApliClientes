package co.edu.unicauca.distribuidos.core.proyecto.services.services.clienteServices;

import java.util.List;

import co.edu.unicauca.distribuidos.core.proyecto.models.Region;
import co.edu.unicauca.distribuidos.core.proyecto.services.DTO.ClienteDTO;

public interface IClienteService {

	public List<ClienteDTO> findAll();

	public ClienteDTO findById(Integer id);

	public ClienteDTO save(ClienteDTO cliente);

	public ClienteDTO update(Integer id, ClienteDTO cliente);

	public boolean delete(Integer id);
	
	public List<Region> findAllRegiones();
}
