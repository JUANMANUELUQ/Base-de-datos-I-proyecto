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
        branchRepo.save(branch);
    }

    public List<Branch> findByMunipality(Long code) {
        return branchRepo.findByMunicipalityCode(code);
    }

    public Optional<Branch> finByName(String name) {
        return branchRepo.findByName(name);
    }

    public Optional<Branch> findByCode(Long code) {
        return branchRepo.findByCodeBranch(code);
    }
}
