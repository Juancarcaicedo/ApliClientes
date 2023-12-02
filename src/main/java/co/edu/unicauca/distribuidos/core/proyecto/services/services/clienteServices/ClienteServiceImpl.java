
package co.edu.unicauca.distribuidos.core.proyecto.services.services.clienteServices;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.proyecto.exceptionControllers.exceptions.EntidadNoExisteException;
import co.edu.unicauca.distribuidos.core.proyecto.exceptionControllers.exceptions.EntidadYaExisteException;
import co.edu.unicauca.distribuidos.core.proyecto.models.ClienteEntity;
import co.edu.unicauca.distribuidos.core.proyecto.models.Region;
import co.edu.unicauca.distribuidos.core.proyecto.repositories.ClienteRepository;
import co.edu.unicauca.distribuidos.core.proyecto.services.DTO.ClienteDTO;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository servicioAccesoBaseDatos;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("messageResourceSB")
	MessageSource messageSource;

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDTO> findAll() {
		Iterable<ClienteEntity> clientesEntity = this.servicioAccesoBaseDatos.findAll();
		System.out.println("antes de la consulta");
		List<ClienteDTO> clientesDTO = this.modelMapper.map(clientesEntity, new TypeToken<List<ClienteDTO>>() {
		}.getType());
		return clientesDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteDTO findById(Integer id) {
		Optional<ClienteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		ClienteEntity user = optional.get();
		System.out.println("antes de la consulta");
		ClienteDTO clienteDTO = this.modelMapper.map(user, ClienteDTO.class);
		return clienteDTO;
	}

	@Override
	@Transactional()
	public ClienteDTO save(ClienteDTO cliente) {

		if (cliente.getId() != null) {
			final Boolean bandera = this.servicioAccesoBaseDatos.existsById(cliente.getId());
			if (bandera) {
				EntidadYaExisteException objException = new EntidadYaExisteException(
						"Cliente con id " + cliente.getId() + " existe en la BD");
				throw objException;

			}
		}

		ClienteEntity clienteEntity = this.modelMapper.map(cliente, ClienteEntity.class);
		ClienteEntity objCLienteEntity = this.servicioAccesoBaseDatos.save(clienteEntity);
		ClienteDTO clienteDTO = this.modelMapper.map(objCLienteEntity, ClienteDTO.class);
		return clienteDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public ClienteDTO update(Integer id, ClienteDTO objClienteConDatosNuevos) {
		Optional<ClienteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		ClienteDTO clienteDTOActualizado = null;
		ClienteEntity objClienteAlmacenado = optional.get();

		if (objClienteAlmacenado != null) {

			objClienteAlmacenado.setId(objClienteConDatosNuevos.getId());
			objClienteAlmacenado.setNombre(objClienteConDatosNuevos.getNombre());
			objClienteAlmacenado.setApellido(objClienteConDatosNuevos.getApellido());
			objClienteAlmacenado.setEmail(objClienteConDatosNuevos.getEmail());
			ClienteEntity clienteEntityActualizado = this.servicioAccesoBaseDatos.save(objClienteAlmacenado);
			clienteDTOActualizado = this.modelMapper.map(clienteEntityActualizado, ClienteDTO.class);
		}
		return clienteDTOActualizado;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Integer id) {
		boolean bandera = false;
		Optional<ClienteEntity> optional = this.servicioAccesoBaseDatos.findById(id);
		if (!optional.isPresent()) {
			EntidadNoExisteException objException = new EntidadNoExisteException(
					"Cliente con id " + id + " no existe en la BD");
			throw objException;
		} else {
			ClienteEntity user = optional.get();
			this.servicioAccesoBaseDatos.delete(user);
			bandera = true;

		}

		return bandera;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones(){
		return servicioAccesoBaseDatos.findAllRegiones();

	}
}