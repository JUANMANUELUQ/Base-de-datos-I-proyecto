package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    public void save(Branch branch);

    public List<Branch> findByMunipality(Long code);

    public List<Branch> findByMunipality(String name);

    public Optional<Branch> findByName(String name);

    public Optional<Branch> findByCode(Long code);

    public List<Branch> findByMunipalityName(String municipalityName);

    public Optional<Branch> findByMunipalityNameAndMunicipality(String name, String municipality);

    public void delete(Branch branch);

}
