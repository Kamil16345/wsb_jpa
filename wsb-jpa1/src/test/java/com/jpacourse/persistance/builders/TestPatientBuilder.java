package com.jpacourse.persistance.builders;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestPatientBuilder {
    private PatientEntity patient = new PatientEntity();



    public TestPatientBuilder withFirstName(String firstName) {
        patient.setFirstName(firstName);
        return this;
    }

    public TestPatientBuilder withLastName(String lastName) {
        patient.setLastName(lastName);
        return this;
    }

    public TestPatientBuilder withTelephoneNumber(String telephoneNumber) {
        patient.setTelephoneNumber(telephoneNumber);
        return this;
    }

    public TestPatientBuilder withEmail(String email) {
        patient.setEmail(email);
        return this;
    }

    public TestPatientBuilder withPatientNumber(String patientNumber) {
        patient.setPatientNumber(patientNumber);
        return this;
    }

    public TestPatientBuilder withDateOfBirth(LocalDate dateOfBirth) {
        patient.setDateOfBirth(dateOfBirth);
        return this;
    }

    public TestPatientBuilder withAddress(AddressEntity address) {
        patient.setAddress(address);
        return this;
    }

    public TestPatientBuilder withDateOfJoin(LocalDateTime dateOfJoin) {
        patient.setDateOfJoin(dateOfJoin);
        return this;
    }

    public TestPatientBuilder withVisits(List<VisitEntity> visits) {
        patient.setVisits(visits);
        return this;
    }

    public PatientEntity build() {
        return patient;
    }

    public static TestPatientBuilder defaultPatient() {
        return new TestPatientBuilder()
                .withFirstName("Jan")
                .withLastName("Kowalski")
                .withTelephoneNumber("123456789")
                .withEmail("jan.kowalski@example.com")
                .withPatientNumber("P12345")
                .withDateOfBirth(LocalDate.of(1980, 1, 1))
                .withDateOfJoin(LocalDateTime.now())
                .withAddress(TestAddressBuilder.defaultAddress().build())
                .withVisits(new ArrayList<>());
    }
}

