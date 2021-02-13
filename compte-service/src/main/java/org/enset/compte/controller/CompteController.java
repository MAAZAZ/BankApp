package org.enset.compte.controller;

import lombok.Data;
import lombok.ToString;
import org.enset.compte.feignClient.CustomerServiceClient;
import org.enset.compte.model.Compte;
import org.enset.compte.model.Operation;
import org.enset.compte.repository.CompteRepository;
import org.enset.compte.repository.OperationRepository;
import org.enset.compte.service.ICompteService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
@RestController
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CustomerServiceClient customerServiceClient;
    @Autowired
    ICompteService iCompetService;
    @Autowired
    private KafkaTemplate<String, Compte> kafkaTemplate;
    private final String topic="operations";


    private String getToken(HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        return context.getTokenString();
    }

    @GetMapping("/comptes/full")
    public List<Compte> getCompte(HttpServletRequest request) {
        List<Compte> comptes = compteRepository.findAll();
        comptes.forEach(compte -> {
            compte.setCustomer(customerServiceClient.getCustomerById("Bearer "+this.getToken(request),compte.getCustomerID()));
        });
        return comptes;
    }

    @PostMapping("/comptes/full")
    public void addCompte(@RequestBody Compte compte) {
        iCompetService.ajouterCompte(compte);
    }

    @PostMapping("/comptes/full/{id}/versement")
    public void versement(HttpServletRequest request,@PathVariable Long id, @RequestBody Transaction transaction) {
        iCompetService.versement(id, transaction.getMontant());
        Compte compte=compteRepository.findById(id).get();
        compte.setCustomer(customerServiceClient.getCustomerById("Bearer "+this.getToken(request),compte.getCustomerID()));
        kafkaTemplate.send(topic,compte.getCustomer().getName(),compte);
    }

    @PostMapping("/comptes/full/{id}/retrait")
    public void retrait(HttpServletRequest request, @PathVariable Long id, @RequestBody Transaction transaction) {
        iCompetService.retrait(id, transaction.getMontant());
        Compte compte=compteRepository.findById(id).get();
        compte.setCustomer(customerServiceClient.getCustomerById("Bearer "+this.getToken(request),compte.getCustomerID()));
        kafkaTemplate.send(topic,compte.getCustomer().getName(),compte);
    }

    @PostMapping("/comptes/full/virement")
    public void virement(HttpServletRequest request, @RequestBody Virement virement) {
        iCompetService.virement(virement.getIdSrc(), virement.getIdDest(), virement.getMontant());
        Compte compte=compteRepository.findById(virement.getIdSrc()).get();
        Compte compte2=compteRepository.findById(virement.getIdDest()).get();
        compte.setCustomer(customerServiceClient.getCustomerById("Bearer "+this.getToken(request),compte.getCustomerID()));
        compte2.setCustomer(customerServiceClient.getCustomerById("Bearer "+this.getToken(request),compte2.getCustomerID()));
        kafkaTemplate.send(topic,compte.getCustomer().getName(),compte);
        kafkaTemplate.send(topic,compte2.getCustomer().getName(),compte2);
    }

    @GetMapping("/comptes/full/{id}")
    public Compte getCompte(HttpServletRequest request, @PathVariable Long id) {
        Compte compte=iCompetService.consulterCompte(id);
        compte.setCustomer(customerServiceClient.getCustomerById("Bearer "+this.getToken(request),compte.getCustomerID()));
        return compte;
    }

    @GetMapping("/comptes/full/{id}/activer")
    public void activerCompte(@PathVariable Long id) {
        iCompetService.activeCompte(id);
    }

    @GetMapping("/comptes/full/{id}/suspendre")
    public void suspendreCompte(@PathVariable Long id) {
        iCompetService.suspendreCompte(id);
    }

    @GetMapping("/comptes/full/{id}/operations")
    public Collection<Operation> getOperation(@PathVariable("id") Long id) {
        return iCompetService.consulterOp(id);
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        return e.getMessage();
    }

}

@Data
@ToString
class Transaction{
    private double montant;
}

@Data
@ToString
class Virement{
    private long idSrc, idDest;
    private double montant;
}
