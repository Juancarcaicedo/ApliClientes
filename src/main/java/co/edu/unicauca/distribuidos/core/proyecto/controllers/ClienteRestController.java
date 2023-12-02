
package co.edu.unicauca.distribuidos.core.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import co.edu.unicauca.distribuidos.core.proyecto.models.Region;
import co.edu.unicauca.distribuidos.core.proyecto.services.DTO.ClienteDTO;
import co.edu.unicauca.distribuidos.core.proyecto.services.services.clienteServices.IClienteService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<ClienteDTO> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public ClienteDTO show(@Min(1) @PathVariable Integer id) {
		ClienteDTO objCliente = null;
		objCliente = clienteService.findById(id);
		return objCliente;
	}

	@PostMapping("/clientes")
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO cliente) {
		ClienteDTO objCliente = clienteService.save(cliente);
		ResponseEntity<ClienteDTO> objRespuesta = new ResponseEntity<ClienteDTO>(objCliente, HttpStatus.CREATED);
		return objRespuesta;
	}

	@PutMapping("/clientes/{id}")
	public ClienteDTO update(@Valid @RequestBody ClienteDTO cliente, @PathVariable Integer id) {
		ClienteDTO objCliente = null;
		System.out.println("actualizando cliente");
		objCliente = clienteService.update(id, cliente);
		return objCliente;
	}

	@DeleteMapping("/clientes/{id}")
	public Boolean delete(@PathVariable Integer id) {
		Boolean bandera = false;
		bandera = clienteService.delete(id);
		return bandera;
	}
	
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
	return clienteService.findAllRegiones();
	}

}
