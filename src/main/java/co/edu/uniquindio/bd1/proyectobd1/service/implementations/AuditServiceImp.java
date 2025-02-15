package co.edu.uniquindio.bd1.proyectobd1.service.implementations;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Audit;
import co.edu.uniquindio.bd1.proyectobd1.repository.AuditRepository;
import co.edu.uniquindio.bd1.proyectobd1.service.interfaces.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImp implements AuditService {

    @Autowired
    private AuditRepository auditRepo;

    public AuditServiceImp() {

    }

    public void save(Audit audit) {
        auditRepo.save(audit);
    }

    public List<Audit> findAll() {
        return auditRepo.findAll();
    }
}
