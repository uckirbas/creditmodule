package com.inghubs.creditmodule.connetor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {

    private Long id;
    private String name;
    private String surname;

}
