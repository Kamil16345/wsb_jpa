package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Override
    public List<PatientEntity> findByFirstNameAndLastName(String firstName, String lastName) {
        return entityManager
                .createQuery("SELECT p FROM PatientEntity p WHERE p.firstName = :firstName and p.lastName = :lastName", PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByEmail(String email) {
        return entityManager
                .createQuery("SELECT p FROM PatientEntity p WHERE p.email = :email", PatientEntity.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByVisitDate(Long patientId, LocalDate visitDate) {
        return entityManager
                .createQuery("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId and v.time = :visitDate", PatientEntity.class)
                .setParameter("patientId", patientId)
                .setParameter("visitDate", visitDate)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findVisitsWithDoctor(Long patientId, Long doctorId) {
        return entityManager
                .createQuery("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId and v.doctor.id = :doctorId", PatientEntity.class)
                .setParameter("patientId", patientId)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    @Override
    public long countEntities() {
        return entityManager
                .createQuery("SELECT p FROM PatientEntity p", PatientEntity.class)
                .getResultList()
                .size();
    }

    @Override
    public List<PatientEntity> findPatientsWithUpcomingVisits(Long patientId) {
        return entityManager
                .createQuery("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId", PatientEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    @Transactional
    public PatientEntity createVisitForPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription) {
        PatientEntity patientEntity = entityManager.find(PatientEntity.class, patientId);
        if (patientEntity == null) {
            throw new EntityNotFoundException(patientId);
        }
        DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, doctorId);
        if (doctorEntity == null) {
            throw new EntityNotFoundException(doctorId);
        }

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(visitDate);
        visitEntity.setDescription(visitDescription);
        visitEntity.setDoctor(doctorEntity);


        if (patientEntity.getVisits() == null) {
            List<VisitEntity> visits = new ArrayList<>();
            visits.add(visitEntity);
            patientEntity.setVisits(visits);
        } else {
            patientEntity.getVisits().add(visitEntity);
        }
        visitEntity.setPatient(patientEntity);

        return entityManager.merge(patientEntity);
    }
}
