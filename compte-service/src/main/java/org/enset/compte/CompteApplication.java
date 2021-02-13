package org.enset.compte;

import org.enset.compte.model.Compte;
import org.enset.compte.model.Operation;
import org.enset.compte.repository.CompteRepository;
import org.enset.compte.repository.OperationRepository;
import org.enset.compte.service.ICompteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class CompteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository, OperationRepository operationRepository, ICompteService compteService,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            System.out.println("***********************************");
            repositoryRestConfiguration.exposeIdsFor(Compte.class);
            repositoryRestConfiguration.exposeIdsFor(Operation.class);

            Compte compte= compteRepository.save(new Compte(null, 0, new Date(),"COURANT","ACTIVE", (long)1, null, null));
            Operation op1 = operationRepository.save(new Operation(null, new Date(), new Random().nextDouble()*6000, "DEBIT", compte));
            Operation op2 = operationRepository.save(new Operation(null, new Date(), new Random().nextDouble()*6000, "DEBIT", compte));
            List<Operation> operations=new ArrayList<>();
            operations.add(op1);
            operations.add(op2);
            compte.setOperations(operations);

            compte.getOperations().forEach(opt->{
                compte.setSolde(compte.getSolde()+opt.getMontant());
            });
            compteRepository.save(compte);

            System.out.println("***********************************");

            Compte compte2= compteRepository.save(new Compte(null, 0, new Date(),"COURANT","ACTIVE", (long)2, null, null));
            Operation op3 = operationRepository.save(new Operation(null, new Date(), new Random().nextDouble()*6000, "DEBIT", compte2));
            List<Operation> operations2=new ArrayList<>();
            operations2.add(op3);
            compte2.setOperations(operations2);

	    compte2.getOperations().forEach(opt->{
                compte2.setSolde(compte2.getSolde()+opt.getMontant());
            });

            compteRepository.save(compte2);

            System.out.println("***********************************");
        };
    }

}
