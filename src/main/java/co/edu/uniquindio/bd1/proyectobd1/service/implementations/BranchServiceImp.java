package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.repository.BranchRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImp implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    public BranchServiceImp() {

    }

}
