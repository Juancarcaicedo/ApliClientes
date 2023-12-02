package co.edu.unicauca.distribuidos.core.proyecto.services.DTO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import co.edu.unicauca.distribuidos.core.proyecto.models.Region;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
	private Integer id;
	@NotNull(message = "{user.name.empty}")
	@Size(min = 5, max = 45, message = "la cantidad de caracteres del nombre debe estar entre 5 y 45")
	private String nombre;
	@NotNull(message = "{user.lastname.empty}")
	@Size(min = 5, max = 45, message = "{user.lastname.length}")
	private String apellido;
	@NotNull(message = "{user.email.emply}")
	@Email(message = "{user.email.mask}")
	private String email;
	@PastOrPresent(message = "{user.date.past}")
	private Date createAt;
	
	private RegionDTO region;
   
	
}
