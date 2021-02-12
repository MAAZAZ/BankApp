package org.enset.kafkastreamcompte.model;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
}

