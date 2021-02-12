package org.enset.compte.feignClient;

import org.enset.compte.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="CLIENT-SERVICE")
public interface CustomerServiceClient {

    @RequestMapping(method = RequestMethod.GET,value="/customers", produces = "application/json")
    PagedModel<Customer> pageCustomers(@RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.GET, value="/customers/{id}", produces = "application/json")
    Customer getCustomerById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);
}
