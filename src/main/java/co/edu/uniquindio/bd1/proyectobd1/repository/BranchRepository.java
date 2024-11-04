package co.edu.uniquindio.bd1.proyectobd1.repository;

import co.edu.uniquindio.bd1.proyectobd1.model.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("SELECT b FROM Branch b WHERE b.codeBranch=:codeBranch")
    Optional<Branch> findByCodeBranch(@Param("codeBranch") Long codeBranch);

    @Query("SELECT b FROM Branch b WHERE b.name=:name")
    Optional<Branch> findByName(@Param("name") String name);

    @Query("SELECT b FROM Branch b WHERE b.municipality.code=:municipalityCode")
    List<Branch> findByMunicipalityCode(@Param("municipalityCode") Long municipalityCode);

    @Query("SELECT b FROM Branch b WHERE b.municipality.name=:municipalityName")
    List<Branch> findByMunicipalityCode(@Param("municipalityName") String municipalityName);

    @Query("SELECT b FROM Branch b WHERE b.municipality.name=:name")
    List<Branch> findByMunipalityName(@Param("name") String name);

    @Query("SELECT b FROM Branch b WHERE b.name=:name AND b.municipality.name=:municipality")
    Optional<Branch> findByMunipalityNameAndMunicipality(@Param("name") String name,@Param("municipality") String municipality);

    @Modifying
    @Query("UPDATE Branch b SET b.name = :name WHERE b.municipality.name=:municipality")
    int updateBranch(@Param("name") String name,@Param("municipality") String municipality);

    @Modifying
    @Transactional
    @Query("DELETE FROM Branch b WHERE b.municipality.name=:municipality")
    int deleteBranchByMunicipality(@Param("municipality") String municipality);

}
