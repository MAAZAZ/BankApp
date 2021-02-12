package org.enset.kafkastreamcompte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Compte {
    private Long id;
    private double solde;
    private Date dateCreation;
    private String type;
    private String etat;
    private Long customerID;
    private Customer customer;
    private Collection<Operation> operations;
}
