package org.enset.client;

import org.enset.client.entities.Customer;
import org.enset.client.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepository.save(new Customer(null,"zakaria maazaz","maazaz.zakaria@gmail.com"));
            customerRepository.save(new Customer(null,"walid maazaz","maazaz.walid@gmail.com"));
            customerRepository.save(new Customer(null,"youssef maazaz","maazaz.youssef@gmail.com"));
            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
