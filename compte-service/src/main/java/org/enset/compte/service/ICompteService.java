package org.enset.compte.service;

import org.enset.compte.model.Compte;
import org.enset.compte.model.Operation;

import java.util.Collection;

public interface ICompteService {
    void ajouterCompte(Compte compte);
    void versement(long idCompte, double montant);
    void retrait(long idCompte,double montant);
    void virement(long idCompte, long idCompte2,double montant);
    Collection<Operation> consulterOp(long idCompte);
    Compte consulterCompte(long idCompte);
    void activeCompte(long idCompte);
    void suspendreCompte(long idCompte);
}
