package org.enset.compte.service;

import org.enset.compte.model.Compte;
import org.enset.compte.model.Operation;
import org.enset.compte.repository.CompteRepository;
import org.enset.compte.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class CompteService implements ICompteService {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void ajouterCompte(Compte compte) {
        compteRepository.save(compte);
    }

    @Override
    public void versement(long idCompte, double montant) {
        Compte compte=compteRepository.findById(idCompte).get();
        Operation operation=operationRepository.save(new Operation(null,new Date(),montant,"DEBIT",compte));
        compte.getOperations().add(operation);
        compte.setSolde(compte.getSolde()+montant);
        compteRepository.save(compte);
    }

    @Override
    public void retrait(long idCompte, double montant) {
        Compte compte=compteRepository.findById(idCompte).get();
        Operation operation=operationRepository.save(new Operation(null,new Date(),montant,"CREDIT",compte));
        compte.getOperations().add(operation);
        compte.setSolde(compte.getSolde()-montant);
        compteRepository.save(compte);;
    }

    @Override
    public void virement(long idCompte, long idCompte2, double montant) {
        Compte compte=compteRepository.findById(idCompte).get();
        Compte compte2=compteRepository.findById(idCompte2).get();

        Operation operation=operationRepository.save(new Operation(null,new Date(),montant,"CREDIT",compte));
        Operation operation2=operationRepository.save(new Operation(null,new Date(),montant,"DEBIT",compte2));

        compte.getOperations().add(operation);
        compte2.getOperations().add(operation2);

        compte.setSolde(compte.getSolde()-montant);
        compte2.setSolde(compte.getSolde()+montant);

        compteRepository.save(compte);
        compteRepository.save(compte2);
    }

    @Override
    public Collection<Operation> consulterOp(long idCompte) {
        Compte compte=compteRepository.findById(idCompte).get();
        return compte.getOperations();
    }

    @Override
    public Compte consulterCompte(long idCompte) {
        return compteRepository.findById(idCompte).get();
    }

    @Override
    public void activeCompte(long idCompte) {
        Compte compte=compteRepository.findById(idCompte).get();
        if(!compte.getEtat().equals("ACTIVE"))
            compte.setEtat("ACTIVE");
        compteRepository.save(compte);
    }

    @Override
    public void suspendreCompte(long idCompte) {
        Compte compte=compteRepository.findById(idCompte).get();
        if(!compte.getEtat().equals("SUSPENDED"))
            compte.setEtat("SUSPENDED");
        compteRepository.save(compte);
    }
}
