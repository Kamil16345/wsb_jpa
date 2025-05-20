package com.jpacourse.persistance.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.AddressDao;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.*;
import com.jpacourse.persistance.enums.Specialization;
import com.jpacourse.persistance.enums.TreatmentType;
import com.jpacourse.service.DoctorService;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.VisitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private VisitDao visitDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void shouldDeletePatientAndAssociatedVisitsButPreserveDoctors() {
        //given
        AddressEntity address = createTestAddress();
        DoctorEntity doctor = createTestDoctor(address);
        PatientEntity patient = createTestPatient();
        VisitEntity visit1 = createTestVisit(patient, doctor, 5);
        VisitEntity visit2 = createTestVisit(patient, doctor, 10);
        patient.setVisits(List.of(visit1, visit2));
        Long patientId = patient.getId();
        Long doctorId = doctor.getId();

        //when
        patientService.deleteById(patientId);

        //then
        assertThat(visitService.findVisitsByPatientId(patientId).size()).isEqualTo(0);
        assertThat(doctorService.findById(doctorId)).isNotNull();
    }

    @Test
    @Transactional
    public void shouldReturnApprovedPatientToStructure() {
        //given
        AddressEntity address = createTestAddress();
        DoctorEntity doctor = createTestDoctor(address);
        PatientEntity patient = createTestPatient();
        VisitEntity visit1 = createTestVisit(patient, doctor, 5);
        VisitEntity visit2 = createTestVisit(patient, doctor, 10);
        patient.setVisits(List.of(visit1, visit2));

        //when
        PatientTO patientTO = patientService.findById(patient.getId());

        //then
        assertThat(patient.getId()).isEqualTo(patientTO.getId());
        assertThat(patient.getFirstName()).isEqualTo(patientTO.getFirstName());
        assertThat(patient.getLastName()).isEqualTo(patientTO.getLastName());
        assertThat(patient.getTelephoneNumber()).isEqualTo(patientTO.getTelephoneNumber());
        assertThat(patient.getEmail()).isEqualTo(patientTO.getEmail());
        assertThat(patient.getPatientNumber()).isEqualTo(patientTO.getPatientNumber());
        assertThat(patient.getDateOfBirth()).isEqualTo(patientTO.getDateOfBirth());
        assertThat(patient.getDateOfJoin()).isEqualTo(patientTO.getDateOfJoin());
        assertThat(patient.getVisits().size()).isEqualTo(2);
        assertThat(patientTO.getVisitEntities().size()).isEqualTo(0);
    }

    private AddressEntity createTestAddress() {
        AddressEntity address = new AddressEntity();
        address.setCity("Warszawa");
        address.setAddressLine1("Zielna 6");
        address.setPostalCode("12-123");

        return addressDao.save(address);
    }

    private DoctorEntity createTestDoctor(AddressEntity address) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Jan");
        doctor.setLastName("Kowalski");
        doctor.setTelephoneNumber("777777777");
        doctor.setEmail("JanKow@gmail.com");
        doctor.setDoctorNumber("DOC001");
        doctor.setSpecialization(Specialization.DERMATOLOGIST);
        doctor.setAddress(address);

        return doctorDao.save(doctor);
    }

    private PatientEntity createTestPatient() {
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Anna");
        patient.setLastName("Nowak");
        patient.setTelephoneNumber("777123321");
        patient.setEmail("anna.nowak@example.com");
        patient.setPatientNumber("PAT-001");
        patient.setDateOfBirth(LocalDate.of(1990, 5, 15));
        patient.setDateOfJoin(LocalDateTime.of(2025, 5, 16, 12, 12, 12));
        return patientDao.save(patient);
    }

    private VisitEntity createTestVisit(PatientEntity patient, DoctorEntity doctor, int plusDays) {

        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setTime(LocalDateTime.now().plusDays(plusDays));
        visit.setMedicalTreatments(List.of(createMedicalTreatmentEntity()));
        return visitDao.save(visit);
    }

    public MedicalTreatmentEntity createMedicalTreatmentEntity() {
        MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setDescription("testDesc");
        medicalTreatmentEntity.setType(TreatmentType.RTG);
        return medicalTreatmentEntity;
    }

}
