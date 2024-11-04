package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Municipality;

import java.util.List;
import java.util.Optional;

public interface MunicipalityService {

    public void save(Municipality municipality);

    public Optional<Municipality> findByCode(Long id);

    public Optional<Municipality> findByName(String nombreMunicipio);

    public List<Municipality> findAllMunicipalities();

    public void delete(Municipality municipality);

}
