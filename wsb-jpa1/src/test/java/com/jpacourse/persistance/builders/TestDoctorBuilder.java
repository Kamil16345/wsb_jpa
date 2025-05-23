package com.jpacourse.persistance.builders;

import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.enums.Specialization;

public class TestDoctorBuilder {
    private DoctorEntity doctor = new DoctorEntity();

    public TestDoctorBuilder withFirstName(String firstName) {
        doctor.setFirstName(firstName);
        return this;
    }

    public TestDoctorBuilder withLastName(String lastName) {
        doctor.setLastName(lastName);
        return this;
    }

    public TestDoctorBuilder withTelephoneNumber(String telephoneNumber) {
        doctor.setTelephoneNumber(telephoneNumber);
        return this;
    }

    public TestDoctorBuilder withEmail(String email) {
        doctor.setEmail(email);
        return this;
    }

    public TestDoctorBuilder withDoctorNumber(String doctorNumber) {
        doctor.setDoctorNumber(doctorNumber);
        return this;
    }

    public TestDoctorBuilder withSpecialization(Specialization specialization) {
        doctor.setSpecialization(specialization);
        return this;
    }

    public TestDoctorBuilder withAddress(AddressEntity address) {
        doctor.setAddress(address);
        return this;
    }

    public DoctorEntity build() {
        return doctor;
    }

    public static TestDoctorBuilder defaultDoctor() {
        return new TestDoctorBuilder()
                .withFirstName("Anna")
                .withLastName("Nowak")
                .withTelephoneNumber("987654321")
                .withEmail("anna.nowak@example.com")
                .withDoctorNumber("DOC-001")
                .withSpecialization(Specialization.DERMATOLOGIST)
                .withAddress(TestAddressBuilder.defaultAddress().build());
    }
}

