package com.ayshriv.patientservice.repository;

import com.ayshriv.patientservice.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT p FROM Patient p WHERE p.isActive =true AND p.isDeleted =false Order By p.updatedAt ASC")
    public Page<Patient> findAllASC(Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.isActive =true AND p.isDeleted =false Order By p.updatedAt DESC")
    public Page<Patient> findAllDESC(Pageable pageable);
}
