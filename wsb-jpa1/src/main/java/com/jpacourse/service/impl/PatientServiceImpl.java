package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patient) {
        this.patientDao = patient;
    }

    @Override
    public PatientTO findById(final Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public PatientTO save(PatientTO patientTO) {
        PatientEntity entity = PatientMapper.mapToEntity(patientTO);
        PatientEntity savedPatient = patientDao.save(entity);
        return PatientMapper.mapToTO(savedPatient);
    }

    @Override
    public List<PatientTO> findAll() {
        List<PatientEntity> entities = patientDao.findAll();
        return entities.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientTO update(PatientTO patientTO) {
        findById(patientTO.getId());
        PatientEntity entity = PatientMapper.mapToEntity(patientTO);
        PatientEntity updatedEntity = patientDao.update(entity);
        return PatientMapper.mapToTO(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        patientDao.delete(id);
    }

    public List<PatientTO> findByFirstNameAndLastName(String firstName, String lastName) {
        List<PatientEntity> patientByFirstAndLastName = patientDao.findByFirstNameAndLastName(firstName, lastName);
        return PatientMapper.mapToTO(patientByFirstAndLastName);
    }

    public List<PatientTO> findByEmail(String email) {
        List<PatientEntity> patientByEmail = patientDao.findByEmail(email);
        return PatientMapper.mapToTO(patientByEmail);
    }

    public List<PatientTO> findByVisitDate(Long patientId, LocalDate visitDate) {
        List<PatientEntity> patientByVisitDate = patientDao.findByVisitDate(patientId, visitDate);
        return PatientMapper.mapToTO(patientByVisitDate);
    }

    //TODO: sprawdź czy potrzebne.
    public List<PatientTO> findVisitsWithDoctor(Long patientId, Long doctorId) {
        List<PatientEntity> visitsWithDoctor = patientDao.findVisitsWithDoctor(patientId, doctorId);
        return PatientMapper.mapToTO(visitsWithDoctor);
    }

    public long countEntities() {
        return patientDao.countEntities();
    }

    public List<PatientTO> findPatientsWithUpcomingVisits(Long patientId) {
        List<PatientEntity> patientByVisitDate = patientDao.findPatientsWithUpcomingVisits(patientId);
        return PatientMapper.mapToTO(patientByVisitDate);
    }
}
