package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("SELECT b FROM Branch b WHERE b.codeBranch=:codeBranch")
    Optional<Branch> findByCodeBranch(@Param("codeBranch") Long codeBranch);

    @Query("SELECT b FROM Branch b WHERE b.name=:name")
    Optional<Branch> findByName(@Param("name") String name);

    @Query("SELECT b FROM Branch b WHERE b.municipality.code=:municipalityCode")
    List<Branch> findByMunicipalityCode(@Param("municipalityCode") Long municipalityCode);



}
