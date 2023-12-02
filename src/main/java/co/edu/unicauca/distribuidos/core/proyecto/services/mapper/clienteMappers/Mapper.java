package co.edu.unicauca.distribuidos.core.proyecto.services.mapper.clienteMappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper objMapper = new ModelMapper();
        return objMapper;
    }
}
