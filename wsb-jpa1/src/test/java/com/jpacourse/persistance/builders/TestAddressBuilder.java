package com.jpacourse.persistance.builders;

import com.jpacourse.persistance.entity.AddressEntity;

class TestAddressBuilder {
    private AddressEntity address = new AddressEntity();

    public TestAddressBuilder withCity(String city) {
        address.setCity(city);
        return this;
    }

    public TestAddressBuilder withAddressLine1(String addressLine1) {
        address.setAddressLine1(addressLine1);
        return this;
    }

    public TestAddressBuilder withPostalCode(String postalCode) {
        address.setPostalCode(postalCode);
        return this;
    }

    public AddressEntity build() {
        return address;
    }

    public static TestAddressBuilder defaultAddress() {
        return new TestAddressBuilder()
                .withCity("Warszawa")
                .withAddressLine1("Zielna 6")
                .withPostalCode("12-123");
    }
}

