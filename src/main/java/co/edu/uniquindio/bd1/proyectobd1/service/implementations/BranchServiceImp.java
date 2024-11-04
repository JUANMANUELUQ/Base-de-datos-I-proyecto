package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;
import co.edu.uniquindio.bd1.proyectobd1.repository.BranchRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImp implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    public BranchServiceImp() {

    }

    public void save(Branch branch) {
        boolean execute=true;
        if (branch.getCodeBranch()==null) {
            Long primaryKey=0L;
            while(execute) {
                primaryKey=(long)(Math.random()*Long.MAX_VALUE);
                if (findByCode(primaryKey).isEmpty()) {
                    execute=false;
                }
            }
            branch.setCodeBranch(primaryKey);
        }
        branchRepo.save(branch);
    }

    public List<Branch> findByMunipality(Long code) {
        return branchRepo.findByMunicipalityCode(code);
    }

    public List<Branch> findByMunipality(String name) {
        return branchRepo.findByMunicipalityCode(name);
    }

    public Optional<Branch> findByName(String name) {
        return branchRepo.findByName(name);
    }

    public Optional<Branch> findByCode(Long code) {
        return branchRepo.findByCodeBranch(code);
    }

    public List<Branch> findByMunipalityName(String municipalityName) {
        return branchRepo.findByMunipalityName(municipalityName);
    }

    public Optional<Branch> findByMunipalityNameAndMunicipality(String name, String municipality) {
        return branchRepo.findByMunipalityNameAndMunicipality(name, municipality);
    }

    public void delete(Branch branch) {
        branchRepo.delete(branch);
    }

}
