package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.builders.TestDoctorBuilder;
import com.jpacourse.persistance.builders.TestPatientBuilder;
import com.jpacourse.persistance.builders.TestVisitBuilder;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private VisitDao visitDao;
    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void shouldCreateVisitForPatient() {
        //given
        PatientEntity testPatient = TestPatientBuilder.defaultPatient().build();
        DoctorEntity testDoctor = TestDoctorBuilder.defaultDoctor().build();
        LocalDateTime dateOfVisit = LocalDateTime.of(2025, 5, 5, 16, 15, 0);
        int amountOfVisitsBeforeInsert = testPatient.getVisits() != null ? testPatient.getVisits().size() : 0;

        //when
        patientDao.save(testPatient);
        doctorDao.save(testDoctor);
        patientDao.createVisitForPatient(testPatient.getId(), testDoctor.getId(), dateOfVisit, "testVisitDesc");

        //then
        assertThat(testPatient.getVisits()).isNotNull();
        assertThat(testPatient.getVisits().size()).isEqualTo(amountOfVisitsBeforeInsert + 1);
    }

    @Transactional
    @Test
    public void shouldFindTwoPatientsWithLastNameEqualNowak() {
        //given
        PatientEntity testPatient1 = TestPatientBuilder.defaultPatient()
                .withFirstName("Joanna")
                .withLastName("Nowak")
                .build();
        PatientEntity testPatient2 = TestPatientBuilder.defaultPatient()
                .withFirstName("Adam")
                .withLastName("Nowak")
                .withEmail("adam.Nowak@test.com")
                .withTelephoneNumber("777123321")
                .withPatientNumber("PAT-001")
                .build();

        //when
        patientDao.save(testPatient1);
        patientDao.save(testPatient2);
        List<PatientEntity> patientsWithLastNameNowak = patientDao.findByLastName("Nowak");

        //then
        assertThat(patientsWithLastNameNowak).isNotNull();
        assertThat(patientsWithLastNameNowak.size()).isEqualTo(2);
    }

    @Transactional
    @Test
    public void shouldFindPatientsWithMoreThanTwoVisits() {
        //given
        PatientEntity testPatient1 = TestPatientBuilder.defaultPatient().build();
        PatientEntity testPatient2 = TestPatientBuilder.defaultPatient()
                .withFirstName("Jan")
                .withLastName("Testowy")
                .build();
        DoctorEntity testDoctor = TestDoctorBuilder.defaultDoctor().build();
        VisitEntity visit1 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient1)
                .withDoctor(testDoctor)
                .build();
        VisitEntity visit2 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient1)
                .withDoctor(testDoctor)
                .withTime(LocalDateTime.now().plusDays(11))
                .build();
        VisitEntity visit3 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient1)
                .withDoctor(testDoctor)
                .withTime(LocalDateTime.now().plusDays(96))
                .build();
        VisitEntity visit4 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient2)
                .withDoctor(testDoctor)
                .withTime(LocalDateTime.now().plusDays(34))
                .build();

        //when
        addressDao.save(testPatient1.getAddress());
        patientDao.save(testPatient1);
        patientDao.save(testPatient2);
        addressDao.save(testDoctor.getAddress());
        doctorDao.save(testDoctor);
        visitDao.save(visit1);
        visitDao.save(visit2);
        visitDao.save(visit3);
        visitDao.save(visit4);
        List<PatientEntity> patientsWithMoreVisitsThan = patientDao.findPatientsWithMoreVisitsThan(2);

        //then
        assertThat(patientsWithMoreVisitsThan).isNotNull();
        assertThat(patientsWithMoreVisitsThan.size()).isEqualTo(1);
    }
}
