package co.edu.uniquindio.bd1.proyectobd1.service;

import co.edu.uniquindio.bd1.proyectobd1.model.MiEntidad;
import co.edu.uniquindio.bd1.proyectobd1.repository.MiEntidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiEntidadService {

    @Autowired
    private MiEntidadRepository miEntidadRepository;

    public MiEntidadService() {

    }

    public List<MiEntidad> getAllEntities() {
        return miEntidadRepository.findAll();
    }

    public Optional<MiEntidad> getEntityById(Long id) {
        return miEntidadRepository.findById(id);
    }

    public MiEntidad saveEntity(MiEntidad miEntidad) {
        return miEntidadRepository.save(miEntidad);
    }
}
