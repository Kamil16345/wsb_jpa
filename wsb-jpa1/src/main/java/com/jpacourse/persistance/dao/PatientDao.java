package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    List<PatientEntity> findByFirstNameAndLastName(String firstName, String lastname);

    List<PatientEntity> findByEmail(String email);

    List<PatientEntity> findByVisitDate(Long patientId, LocalDate visitDate);

    List<PatientEntity> findVisitsWithDoctor(Long patientId, Long doctorId);

    long countEntities();

    List<PatientEntity> findPatientsWithUpcomingVisits(Long patientId);

    PatientEntity createVisitForPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription);
}
