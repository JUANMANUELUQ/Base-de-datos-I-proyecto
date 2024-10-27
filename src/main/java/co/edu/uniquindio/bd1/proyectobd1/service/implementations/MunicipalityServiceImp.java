package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Municipality;
import co.edu.uniquindio.bd1.proyectobd1.repository.MunicipalityRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipalityServiceImp implements MunicipalityService {

    @Autowired
    private MunicipalityRepository municipalityRepo;

    public MunicipalityServiceImp() {

    }

    public void save(Municipality municipality) {
        municipalityRepo.save(municipality);
    }

    public Optional<Municipality> findByCode(Long id) {
        return municipalityRepo.findByCode(id);
    }

    public Optional<Municipality> findByName(String nombreMunicipio) {
        return municipalityRepo.findByName(nombreMunicipio);
    }

    public List<Municipality> findAllMunicipalities() {
        return municipalityRepo.findAll();
    }

    public void delete(Municipality municipality) {
        municipalityRepo.delete(municipality);
    }
}
