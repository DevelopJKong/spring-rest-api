package com.spring.restApiProject.domain.dto.request;

import lombok.Data;

import com.spring.restApiProject.domain.entity.Address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddMemberDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean verified;
    private Address address;

}
