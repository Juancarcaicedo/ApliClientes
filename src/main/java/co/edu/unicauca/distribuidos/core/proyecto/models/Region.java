package co.edu.unicauca.distribuidos.core.proyecto.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
@Entity
@Getter
@Table(name = "regiones")
public class Region {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long region_id;
private String nombre_region;
public Long getId() {
return region_id;
}
public void setId(Long id) {
this.region_id = id;
}

}