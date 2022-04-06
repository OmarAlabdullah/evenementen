package evenementen.event.presentation.controller;

import evenementen.event.application.CustomerService;
import evenementen.event.domain.Customer;
import evenementen.event.domain.exception.CustomerAlreadyExistsException;
import evenementen.event.domain.exception.CustomerNotFoundException;
import evenementen.event.presentation.dto.CustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    private void saveCustomer(@Validated @RequestBody CustomerRequest customer) {
        try {
            customerService.save(customer.customerId, customer.firstname, customer.lastname, customer.password, customer.email);
        } catch (CustomerAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PutMapping("/customer")
    private void updateCustomer(@Validated @RequestBody CustomerRequest customer) {
        System.out.println(customer.firstname+ customer.lastname+ customer.password+ customer.email);
        try {
            customerService.save(74L, customer.firstname, customer.lastname, customer.password, customer.email);
        } catch (CustomerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/customer/{customerId}")
    private Customer findById(@PathVariable("customerId") Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping("/customer/{userName}")
        private Customer findByUserName(@PathVariable("userName") String userName) {
        System.out.println(userName);
        return customerService.findByUserName(userName);
    }

    @GetMapping("/customers")
    private List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/customer/{customerId}")
    private void deleteCustomer(@PathVariable("customerId") Long customerId) {
        try {
            customerService.deleteCustomerById(customerId);
        }
        catch (CustomerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }



}
