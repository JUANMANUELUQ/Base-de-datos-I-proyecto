package co.edu.uniquindio.bd1.proyectobd1.service.interfaces;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {

    public List<Period> findAll();

    public void save(Period period);

    public Optional<Period> findByPeriodMonths(int period);

}
