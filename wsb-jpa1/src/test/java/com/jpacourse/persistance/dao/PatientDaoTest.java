package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.enums.Specialization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void shouldCreateVisitForPatient() {
        //given
        PatientEntity testPatient = createTestPatient();
        AddressEntity testAddress = createTestAddress();
        DoctorEntity testDoctor = createTestDoctor(testAddress);
        LocalDateTime dateOfVisit = LocalDateTime.of(2025, 5, 5, 16, 15, 0);
        int amountOfVisitsBeforeInsert = testPatient.getVisits() != null ? testPatient.getVisits().size() : 0;
        //when
        patientDao.createVisitForPatient(testPatient.getId(), testDoctor.getId(), dateOfVisit, "testVisitDesc");

        //then
        assertThat(testPatient.getVisits()).isNotNull();
        assertThat(testPatient.getVisits().size()).isEqualTo(amountOfVisitsBeforeInsert + 1);
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
}
