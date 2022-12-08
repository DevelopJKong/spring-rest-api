package com.spring.restApiProject.domain.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    @Column(name = "STREET")
    @Comment("도로명")
    private String street;

    @Column(name = "CITY")
    @Comment("도시명")
    private String city;

    @Column(name = "ZIPCODE")
    @Comment("우편번호")
    private String zipcode;

    public Address() {
    }

    public Address(String street, String city, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
