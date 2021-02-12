package org.enset.compte;

import org.enset.compte.model.Compte;
import org.enset.compte.model.Operation;
import org.enset.compte.repository.CompteRepository;
import org.enset.compte.repository.OperationRepository;
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
    CommandLineRunner start(CompteRepository compteRepository, OperationRepository operationRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        return args -> {
            System.out.println("***********************************");
            repositoryRestConfiguration.exposeIdsFor(Compte.class);
            Operation operation=new Operation(null, new Date(), new Random().nextDouble()*6000,
                    "Debit" , null );
            Operation operation2=new Operation(null, new Date(), new Random().nextDouble()*6000,
                    "Debit" , null );
            List<Operation> ops=new ArrayList<>();
            ops.add(operation);
            ops.add(operation2);
            operationRepository.save(operation);
            operationRepository.save(operation2);
            Compte compte=new Compte();
            compte.setOperations(new ArrayList<>());
            compte.setOperations(ops);
            compte.setCustomerID(1L);
            compte.setDateCreation(new Date());
            compte.setEtat("COURANT");
            compte.setType("ACTIVE");

            ops.forEach(opt -> {
                operationRepository.save(opt);
            } );

            compte.getOperations().forEach(opt->{
                compte.setSolde(compte.getSolde()+opt.getMontant());
            });
            compteRepository.save(compte);
            System.out.println(compte);
            System.out.println("***********************************");
        };
    }

}
